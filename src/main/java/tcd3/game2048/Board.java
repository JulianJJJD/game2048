package tcd3.game2048;

import java.util.Random;

public class Board {
    private int[][] grid;
    private final int ROWS = 4;
    private final int COLS = 4;

    public Board() {
        this.grid = new int[ROWS][COLS];
    }

    /**
     * Initialize the board with two random tiles
     */
    public void initialize() {
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            int row = random.nextInt(ROWS);
            int col = random.nextInt(COLS);
            
            while (grid[row][col] != 0) {
                row = random.nextInt(ROWS);
                col = random.nextInt(COLS);
            }
            
            int val = random.nextInt(10) == 0 ? 4 : 2;
            grid[row][col] = val;
        }
    }

    /**
     * Get value at specific position
     */
    public int getValueAt(int x, int y) {
        return grid[x][y];
    }

    /**
     * Add a random tile (2 or 4) to an empty cell
     */
    public void addRandomTile() {
        Random random = new Random();
        int row = random.nextInt(ROWS);
        int col = random.nextInt(COLS);
        
        while (grid[row][col] != 0) {
            row = random.nextInt(ROWS);
            col = random.nextInt(COLS);
        }
        
        grid[row][col] = random.nextInt(10) == 0 ? 4 : 2;
    }

    /**
     * Move tiles left and return score earned
     */
    public int moveLeft() {
        int scoreEarned = 0;
        
        for (int row = 0; row < ROWS; row++) {
            int[] newRow = new int[COLS];
            int pos = 0;

            for (int col = 0; col < COLS; col++) {
                int value = grid[row][col];

                if (value == 0) continue;

                if (newRow[pos] == 0) {
                    newRow[pos] = value;
                } else if (newRow[pos] == value) {
                    newRow[pos] *= 2;
                    scoreEarned += newRow[pos];
                    pos++;
                } else {
                    pos++;
                    newRow[pos] = value;
                }
            }
            grid[row] = newRow;
        }
        
        return scoreEarned;
    }

    /**
     * Move tiles right and return score earned
     */
    public int moveRight() {
        int scoreEarned = 0;
        
        for (int row = 0; row < ROWS; row++) {
            int[] newRow = new int[COLS];
            int pos = COLS - 1;

            for (int col = COLS - 1; col >= 0; col--) {
                int value = grid[row][col];

                if (value == 0) continue;

                if (newRow[pos] == 0) {
                    newRow[pos] = value;
                } else if (newRow[pos] == value) {
                    newRow[pos] *= 2;
                    scoreEarned += newRow[pos];
                    pos--;
                } else {
                    pos--;
                    newRow[pos] = value;
                }
            }
            grid[row] = newRow;
        }
        
        return scoreEarned;
    }

    /**
     * Move tiles up and return score earned
     */
    public int moveUp() {
        int scoreEarned = 0;
        
        for (int col = 0; col < COLS; col++) {
            int[] newCol = new int[ROWS];
            int pos = 0;

            for (int row = 0; row < ROWS; row++) {
                int value = grid[row][col];

                if (value == 0) continue;

                if (newCol[pos] == 0) {
                    newCol[pos] = value;
                } else if (newCol[pos] == value) {
                    newCol[pos] *= 2;
                    scoreEarned += newCol[pos];
                    pos++;
                } else {
                    pos++;
                    newCol[pos] = value;
                }
            }
            
            for (int i = 0; i < ROWS; i++) {
                grid[i][col] = newCol[i];
            }
        }
        
        return scoreEarned;
    }

    /**
     * Move tiles down and return score earned
     */
    public int moveDown() {
        int scoreEarned = 0;
        
        for (int col = 0; col < COLS; col++) {
            int[] newCol = new int[ROWS];
            int pos = ROWS - 1;

            for (int row = ROWS - 1; row >= 0; row--) {
                int value = grid[row][col];

                if (value == 0) continue;

                if (newCol[pos] == 0) {
                    newCol[pos] = value;
                } else if (newCol[pos] == value) {
                    newCol[pos] *= 2;
                    scoreEarned += newCol[pos];
                    pos--;
                } else {
                    pos--;
                    newCol[pos] = value;
                }
            }
            
            for (int i = 0; i < ROWS; i++) {
                grid[i][col] = newCol[i];
            }
        }
        
        return scoreEarned;
    }

    /**
     * Check if there are any possible moves left
     */
    public boolean hasMovesAvailable() {
        // Check for empty cells
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] == 0) {
                    return true;
                }
            }
        }

        // Check for possible merges
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int current = grid[row][col];
                
                if (row < ROWS - 1 && current == grid[row + 1][col]) {
                    return true;
                }
                if (col < COLS - 1 && current == grid[row][col + 1]) {
                    return true;
                }
            }
        }
        
        return false;
    }

    /**
     * Check if 2048 tile exists
     */
    public boolean contains2048() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Create a copy of the current grid state
     */
    private int[][] copyGrid() {
        int[][] copy = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, COLS);
        }
        return copy;
    }

    /**
     * Check if two grids are equal
     */
    private boolean gridsEqual(int[][] a, int[][] b) {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (a[r][c] != b[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if a move was effective (changed the grid)
     */
    public boolean wasMoveEffective(int[][] before) {
        return !gridsEqual(before, grid);
    }

    /**
     * Get a snapshot of the grid before a move
     */
    public int[][] getGridSnapshot() {
        return copyGrid();
    }

    /**
     * Set the grid state (for testing purposes)
     */
    public void setGrid(int[][] newGrid) {
        for (int i = 0; i < ROWS; i++) {
            System.arraycopy(newGrid[i], 0, this.grid[i], 0, COLS);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                sb.append(grid[i][j]).append("\t");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
