def main():
    printDummy()
    print("Enter the numbers of Sudoku as shown above:")
    grid = inputGrid()
    print("\nUnsolved Sudoku:")
    printGrid(grid)
    if solve(grid):
        print("\nSolved Sudoku:")
        printGrid(grid)
    else:
        print("\nThis Sudoku can't be solved")


def printGrid(grid):
    for row, Row in enumerate(grid):
        for col, num in enumerate(Row):
            print(num, end=" ")
            if col % 3 == 2 and col != 8:
                print("|", end=" ")
        print("")
        if row % 3 == 2 and row != 8:
            print("---------------------")


def printDummy():
    for i in range(9):
        for j in range(9):
            print(0, end=" ")
        print("")


def inputGrid():
    grid = [input().split(" ") for _ in range(9)]
    grid = [[int(num) for num in Row] for Row in grid]
    return grid


def findEmpty(grid):
    for row, Row in enumerate(grid):
        for col, num in enumerate(Row):
            if num == 0:
                return row, col
    return None


def isValid(grid, num, pos):
    row, col = pos
    for i in range(9):
        if grid[row][i] == num:
            return False
        if grid[i][col] == num:
            return False

    ROW = row // 3
    COL = col // 3
    for i in range(3):
        for j in range(3):
            if grid[3 * ROW + i][3 * COL + j] == num:
                return False
    return True


def solve(grid):
    pos = findEmpty(grid)
    if not pos:
        return True
    row, col = pos

    for num in range(1, 10):
        if isValid(grid, num, pos):
            grid[row][col] = num
            if solve(grid):
                return True
            grid[row][col] = 0
    return False


main()
