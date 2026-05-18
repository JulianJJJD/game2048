package tcd3.game2048;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class GameImplTests {
    @Test
    @DisplayName("Should initialize score and moves with zero")
    public void newGame_shouldStartWithZeroScoreAndMoves() {
        // Arrange
        Game game = new GameImpl();

        // Act

        // Assert
        assertEquals(0, game.getScore());
        assertEquals(0, game.getMoves());
    }

    @Test
    @DisplayName("Should merge two equal numbers moving left")
    public void moveLeft_givenTwoEqualTiles_shouldMerge() {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {2, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        // Act
        game.move(Direction.left);

        // Assert
        assertEquals(4, game.getValueAt(0, 0));
        assertEquals(0, game.getValueAt(0, 1));
    }

    @Test
    @DisplayName("Single tile should move to the right")
    public void moveRight_givenSingleTile_shouldMoveToRight() {
        
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        // Act
        game.move(Direction.right);

        // Assert
        assertEquals(0, game.getValueAt(0, 0));
        assertEquals(0, game.getValueAt(0, 1));
        assertEquals(0, game.getValueAt(0, 2));
        assertEquals(2, game.getValueAt(0, 3));
    }

    @Test
    @DisplayName("Three tiles should merge rightmost pair when moving right")
    public void moveRight_givenThreeTiles_shouldMergeRight() {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {2, 2, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        // Act
        game.move(Direction.right);

        // Assert
        assertEquals(0, game.getValueAt(0, 0));
        assertEquals(0, game.getValueAt(0, 1));
        assertEquals(2, game.getValueAt(0, 2));
        assertEquals(4, game.getValueAt(0, 3));
    }

    @Test
    @DisplayName("Two and four should merge and shift correctly when moving right")
    public void moveRight_givenTwoAndFour_shouldMergeAndShiftCorrectly() {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {2, 2, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        // Act
        game.move(Direction.right);

        // Assert
        assertEquals(0, game.getValueAt(0, 0));
        assertEquals(0, game.getValueAt(0, 1));
        assertEquals(4, game.getValueAt(0, 2));
        assertEquals(4, game.getValueAt(0, 3));
    }
    @Test
@DisplayName("Valid move should add a new random tile")
public void moveRight_givenOneTile_shouldAddNewTile() {
    GameImpl game = new GameImpl();

    game.setGrid(new int[][] {
        {2, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0}
    });

    game.move(Direction.right);

    int count = 0;
    for (int row = 0; row < 4; row++) {
        for (int col = 0; col < 4; col++) {
            if (game.getValueAt(row, col) != 0) {
                count++;
            }
        }
    }

    assertEquals(2, count);
}

    @Test
    @DisplayName("Two pairs should merge correctly when moving right")
    public void moveRight_givenTwoPairs_shouldMergeBothPairs() {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {2, 2, 4, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        // Act
        game.move(Direction.right);

        // Assert
        assertEquals(0, game.getValueAt(0, 0));
        assertEquals(0, game.getValueAt(0, 1));
        assertEquals(4, game.getValueAt(0, 2));
        assertEquals(8, game.getValueAt(0, 3));
    }

    @Test
    @DisplayName("Should merge two equal numbers at the end when moving right")
    public void moveRight_givenTwoEqualTilesAtEnd_shouldMerge() {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {0, 0, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        // Act
        game.move(Direction.right);

        // Assert
        assertEquals(0, game.getValueAt(0, 0));
        assertEquals(0, game.getValueAt(0, 1));
        assertEquals(0, game.getValueAt(0, 2));
        assertEquals(4, game.getValueAt(0, 3));
    }

    @Test
    @DisplayName("Should initialize with two tiles")
    public void newGame_shouldInitializeWithTwoTiles() {
        // Arrange
        Game game = new GameImpl();

        // Act
        int count = 0;
        for(int c = 0; c < 4;c++) {
            for (int r = 0; r < 4; r++) {
                if (game.getValueAt(c, r) != 0) {
                    count++;
                }
            }
        }

        // Assert
        assertEquals(2, count);
    }

    @Test
    @DisplayName("Four equal tiles should merge into two pairs when moving left")
    public void moveLeft_givenFourEqualTiles_shouldCreateTwoMerges() {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {2, 2, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        // Act
        game.move(Direction.left);

        // Assert
        assertEquals(4, game.getValueAt(0,0));
        assertEquals(4, game.getValueAt(0,1));
        assertEquals(0, game.getValueAt(0,2));
        assertEquals(0, game.getValueAt(0,3));
    }

    @Test
    @DisplayName("Single tile should move to the up")
    public void moveUp_givenTwoEqualTilesAtBottom_shouldMergeToTop() {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0}
        });

        // Act
        game.move(Direction.up);

        // Assert
        assertEquals(4, game.getValueAt(0, 0));
        assertEquals(0, game.getValueAt(1, 0));
        assertEquals(0, game.getValueAt(2, 0));
        assertEquals(0, game.getValueAt(3, 0));
    }

    @Test
    @DisplayName("Single tile should move to the up")
    public void moveUp_givenOneTileAtBottom_shouldGoToTop() {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        });

        // Act
        game.move(Direction.up);

        // Assert
        assertEquals(2, game.getValueAt(0, 0));
        assertEquals(0, game.getValueAt(1, 0));
        assertEquals(0, game.getValueAt(2, 0));
        assertEquals(0, game.getValueAt(3, 0));
    }

    @Test
    @DisplayName("Single tile should move down")
    public void moveDown_givenOneTileAtTop_shouldMoveDown() {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        // Act
        game.move(Direction.down);

        // Assert
        assertEquals(0, game.getValueAt(0, 0));
        assertEquals(0, game.getValueAt(1, 0));
        assertEquals(0, game.getValueAt(2, 0));
        assertEquals(2, game.getValueAt(3, 0));
    }

    @Test
    @DisplayName("Should merge two equal numbers moving down")
    public void moveDown_givenTwoEqualTiles_shouldMerge()  {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        // Act
        game.move(Direction.down);

        // Assert
        assertEquals(0, game.getValueAt(0, 0));
        assertEquals(0, game.getValueAt(1, 0));
        assertEquals(0, game.getValueAt(2, 0));
        assertEquals(4, game.getValueAt(3, 0));
    }

    @Test
    @DisplayName("Four none equal tiles should not merge when moving")
    public void givenFourNotEqualTiles_shouldNotCreateMerges() {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {2, 4, 8, 16},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        // Act
        game.move(Direction.left);

        // Assert
        assertEquals(2, game.getValueAt(0,0));
        assertEquals(4, game.getValueAt(0,1));
        assertEquals(8, game.getValueAt(0,2));
        assertEquals(16, game.getValueAt(0,3));
    }
/*
    @Test
    @DisplayName("Full board with no equal neighbors should not change when moving left")
    public void moveLeft_givenFullBoardWithoutEqualNeighbors_isGameOver() {
        // Arrange
        GameImpl game = new GameImpl();
        game.setAddRandomTileAfterMove(false);
        game.setGrid(new int[][] {
                {2, 4, 8, 16},
                {4, 8, 16, 2},
                {8, 16, 2, 4},
                {16, 2, 4, 8}
        });

        // Act
        game.move(Direction.left);

        // Assert
        assertTrue(game.isOver());
    }*/


/*Test 1
[2,2,0,0] → LEFT

→ [4,0,0,0]
→ Score +4

Test 2
[2,2,2,2] → LEFT

→ [4,4,0,0]
→ Score +8

Test 3
[2,4,8,16] → LEFT

→ bleibt gleich
→ moves darf nicht steigen

Test 4

Full Board ohne Merges:

→ isOver() == true */
}
