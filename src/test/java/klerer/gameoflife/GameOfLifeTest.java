package klerer.gameoflife;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class GameOfLifeTest {

    @Test
    public void testNextGeneration() {
        // given
        int[][] initialGrid = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };

        // when
        int[][] expectedGrid = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };

        GameOfLife gameOfLife = new GameOfLife();

        int rows = initialGrid.length;
        int columns = initialGrid[0].length;
        int[][] futureGrid = gameOfLife.nextGeneration(initialGrid, rows, columns);

        // then
        assertArrayEquals(expectedGrid, futureGrid);
    }
}
