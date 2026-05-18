package tcd3.game2048;
import java.util.Random;

public class GameImpl implements Game {
    int[][] grid = new int[4][4];
    private final int rows = 4;
    private final int cols = 4;
    int score = 0;
    int moves = 0;
    boolean isOver = false;
    boolean isWon = false;
    private boolean addRandomTileAfterMove = true;

    public GameImpl() {
        initialize();
    }

    public void setAddRandomTileAfterMove(boolean value) {
        this.addRandomTileAfterMove = value;
    }

    public int getMoves() {
        return moves;
    }

    public int getScore() {
        return score;
    }

    public int getValueAt(int x, int y) {
        return grid[x][y];
    }


    public boolean isOver() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (grid[row][col] == 0) {
                    return false;
                }
            }
        }

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (row < 3 && grid[row][col] == grid[row + 1][col]) {
                    return false;
                }
                if (col < 3 && grid[row][col] == grid[row][col + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWon() {
        for(int c=0;c<4;c++){
            for(int r=0;r<4;r++) {
                if (grid[c][r] == 2048) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String str = "";

        for (int i = 0 ; i < this.rows ; i ++ ){
            for (int j = 0 ; j < this.cols ; j++) {
                sb.append(grid[i][j]).append("\t");
            }
        sb.append("\n");
        }

        return sb.toString();
    }

    public void initialize() {
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            //int x = random.nextInt(4);
            int row = random.nextInt(4);
            int col = random.nextInt(4);
            int val;

            while (grid[row][col] != 0){
                row = random.nextInt(4);
                col = random.nextInt(4);
            }

            if (random.nextInt(10) == 0) {
                val = 4;
            } else {
                val = 2; 
            }
                
            grid[row][col] = val;
        }
    }

    public void move(Direction direction) {
        int[][] before = copyGrid();

        switch(direction) {
            case left:
                moveLeft();
                break;
            case right:
                moveRight();
                break;

            case up:
                moveUp();
                break;
            
            case down:
                moveDown();
                break;
            }

            if(!gridEquals(before, grid)) {
                if (addRandomTileAfterMove) {
                    addRandomTile();
                }
                moves++;
            }
    }

    private void addRandomTile() {
        Random random = new Random();
        int row = random.nextInt(4);
        int col = random.nextInt(4);
        while (grid[row][col] != 0) {
            row = random.nextInt(4);
            col = random.nextInt(4);
        }
        grid[row][col] = random.nextInt(10) == 0 ? 4 : 2;
    }

    private int[][] copyGrid() {
        int[][] copy = new int[4][4];
        for(int i = 0; i < 4; i++)
            System.arraycopy(grid[i], 0, copy[i], 0, 4);
        return copy;
    }

    private boolean gridEquals(int[][] a, int[][] other) {
        for(int r=0;r<4;r++) 
            for (int c=0;c<4;c++)
                if(a[r][c] != other[r][c]) return false;
        return true;
    }

    private void moveDown() {
        for(int col = 0; col < 4; col++) {
            int[] newCol = new int[4];
            int pos = 3;

            for ( int row = 3; row >= 0; row--) {
                int value = grid[row][col];

                if (value == 0) continue;

                if(newCol[pos] == 0) {
                    newCol[pos] = value;
                } else if(newCol[pos] == value) {
                    newCol[pos] *= 2;
                    score += newCol[pos];
                    pos--;
                } else {
                    pos--;
                    newCol[pos] = value;
                }
            }
            for(int i = 0; i < 4; i++) {
                grid[i][col] = newCol[i];
            }
        }

    }

    private void moveUp() {
        for(int col = 0; col < 4; col++) {
            int[] newCol = new int[4];
            int pos = 0;

            for ( int row = 0; row < 4; row++) {
                int value = grid[row][col];

                if (value == 0) continue;

                if(newCol[pos] == 0) {
                    newCol[pos] = value;
                } else if(newCol[pos] == value) {
                    newCol[pos] *= 2;
                    score += newCol[pos];
                    pos++;
                } else {
                    pos++;
                    newCol[pos] = value;
                }
            }
            for (int i = 0; i < 4; i++) {
                grid[i][col] = newCol[i];
            }
        }
    }

    private void moveRight() {
        for(int row = 0; row < 4; row++) {
            int[] newRow = new int[4];
            int pos = 3;

            for (int col = 3; col >= 0; col--) {
                int value = grid[row][col];

                if (value == 0) continue;

                if (newRow[pos] == 0) {
                    newRow[pos] = value;
                } else if (newRow[pos] == value) {
                    newRow[pos] *= 2;
                    score += newRow[pos];
                    pos--;
                } else {
                    pos--;
                    newRow[pos] = value;
                }
            }
            grid[row] = newRow;
        }
    }

    private void moveLeft() {
        for(int row = 0; row < 4; row++) {
            int[] newRow = new int[4];
            int pos = 0;

            for ( int col = 0; col < 4; col++) {
                int value = grid[row][col];

                if (value == 0) continue;

                if(newRow[pos] == 0) {
                    newRow[pos] = value;
                } else if(newRow[pos] == value) {
                    newRow[pos] *= 2;
                    score += newRow[pos];
                    pos++;
                } else {
                    pos++;
                    newRow[pos] = value;
                }
            }
            grid[row] = newRow;
        }
    }

    public void setGrid(int[][] newGrid) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(newGrid[i], 0, this.grid[i], 0, 4);
        }
    }
}
