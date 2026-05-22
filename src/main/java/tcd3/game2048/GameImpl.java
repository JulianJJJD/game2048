package tcd3.game2048;

public class GameImpl implements Game {
    private Board board;
    private int score = 0;
    private int moves = 0;
    private boolean addRandomTileAfterMove = true;

    public GameImpl() {
        this.board = new Board();
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
        return board.getValueAt(x, y);
    }

    /**
     * Check if there are no valid moves left and no empty spaces
     */
    public boolean isOver() {
        return !board.hasMovesAvailable();
    }

    /**
     * Check if 2048 tile has been achieved
     */
    public boolean isWon() {
        return board.contains2048();
    }

    @Override
    public String toString() {
        return board.toString();
    }

    /**
     * Initialize the game with two random tiles
     */
    public void initialize() {
        score = 0;
        moves = 0;
        board = new Board();
        board.initialize();
    }

    /**
     * Execute a move in the specified direction
     * Updates score and moves counter if move was effective
     */
    public void move(Direction direction) {
        int[][] before = board.getGridSnapshot();

        int scoreEarned = 0;
        switch (direction) {
            case left:
                scoreEarned = board.moveLeft();
                break;
            case right:
                scoreEarned = board.moveRight();
                break;
            case up:
                scoreEarned = board.moveUp();
                break;
            case down:
                scoreEarned = board.moveDown();
                break;
        }

        if (board.wasMoveEffective(before)) {
            score += scoreEarned;
            if (addRandomTileAfterMove) {
                board.addRandomTile();
            }
            moves++;
        }
    }

    /**
     * Set the board grid state (for testing purposes)
     */
    public void setGrid(int[][] newGrid) {
        board.setGrid(newGrid);
    }
}
