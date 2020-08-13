import java.util.Scanner;

public class SudokuSolver {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        printDummy();
        System.out.println("Enter the numbers of Sudoku as shown above:");
        int[][] grid = inputGrid(scanner);
        System.out.println("\nUnsolved Sudoku:");
        printGrid(grid);
        if (solve(grid)){
            System.out.println("\nSolved Sudoku:");
            printGrid(grid);
        }
        else
            System.out.println("\nThis Sudoku can't be solved");
    }


    public static void printGrid(int[][] grid){
        for (int row = 0; row <= 8; row++){
            for (int col = 0; col <= 8; col++){
                System.out.printf("%d ", grid[row][col]);
                if (col % 3 == 2 && col != 8)
                    System.out.print("| ");
            }
            System.out.print("\n");
            if (row % 3 == 2 && row != 8)
                System.out.println("---------------------");
        }
    }


    public static void printDummy(){
        for (int i = 0; i <= 8; i++){
            for (int j = 0; j <= 8; j++){
                System.out.printf("%d ", 0);
            }
            System.out.print("\n");
        }
    }


    public static int[][] inputGrid(Scanner scanner){
        String[][] rawGrid = new String[9][9];
        for (int row = 0; row <= 8; row++){
            rawGrid[row] = scanner.nextLine().split(" ", 9);
        }

        int[][] grid = new int[9][9];
        for (int row = 0; row <= 8; row++){
            for (int col = 0; col <= 8; col++){
                grid[row][col] = Integer.parseInt(rawGrid[row][col]);
            }
        }
        return grid;
    }


    public static int[] findEmpty(int[][] grid){
        int[] pos = {9, 9};
        for (int row = 0; row <= 8; row++){
            for (int col = 0; col <= 8; col++){
                if (grid[row][col] == 0){
                    pos[0] = row;
                    pos[1] = col;
                    return pos;
                }
            }
        }
        return pos;
    }


    public static boolean isValid(int[][] grid, int num, int[] pos){
        int row = pos[0];
        int col = pos[1];
        for (int i = 0; i <= 8; i++){
            if (grid[row][i] == num)
                return false;
            if (grid[i][col] == num)
                return false;
        }

        int ROW = row / 3;
        int COL = col / 3;
        for (int i = 0; i<= 2; i++){
            for (int j = 0; j <= 2; j++){
                if (grid[3 * ROW + i][3 * COL + j] == num)
                    return false;
            }
        }
        return true;
    }


    public static boolean solve(int[][] grid){
        int[] pos = findEmpty(grid);
        int row = pos[0];
        int col = pos[1];
        if (row == 9 && col == 9)
            return true;

        for (int num = 1; num <= 9; num++){
            if (isValid(grid, num, pos)){
                grid[row][col] = num;
                if (solve(grid))
                    return true;
                grid[row][col] = 0;
            }
        }
        return false;
    }
}
