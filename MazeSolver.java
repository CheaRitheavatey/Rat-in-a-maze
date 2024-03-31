public class MazeSolver {
    private static final int SIZE = 4; // Size of the maze

    public static void main(String[] args) {
        // the problem array
        int[][] maze = {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0}
        };

        // the solution array
        int[][] solution = new int[SIZE][SIZE];

        // if problem can be solved, print the solution
        if (solveMaze(maze, solution, 0, 0)) {
            System.out.println("Maze solution found:");
            printSolution(solution);
        } else {
            // if not, print no solution
            System.out.println("No solution exists for the given maze.");
        }
    }

    public static boolean solveMaze(int[][] maze, int[][] solution, int row, int col) {
        // base case
        if (row == SIZE - 1 && col == SIZE - 1) {  // if solution[3][3] = reach goal
            solution[row][col] = 1; // Mark the destination cell as part of the path
            return true;
        }

        // mark the cell that the rat has visited
        if (isValidMove(maze, solution, row, col)) {
            solution[row][col] = 1; // Mark the current cell as part of the path

            // Move down
            if (solveMaze(maze, solution, row + 1, col)) {
                return true;
            }

            // Move right
            if (solveMaze(maze, solution, row, col + 1)) {
                return true;
            }

            // Move up
            if (solveMaze(maze, solution, row - 1, col)) {
                return true;
            }

            // Move left
            if (solveMaze(maze, solution, row, col - 1)) {
                return true;
            }

            // If moving in any direction is not possible, backtrack and delete the path
            solution[row][col] = 0; // Mark the current cell as not part of the path
        }
        return false;
    }

    public static boolean isValidMove(int[][] maze, int[][] solution, int row, int col) {
        // make sure not to go out of array bounds and check for obstacles
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && maze[row][col] == 0 && solution[row][col] == 0;
    }

    public static void printSolution(int[][] solution) {
        // print out the whole array
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }
}