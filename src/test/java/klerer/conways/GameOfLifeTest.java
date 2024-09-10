package klerer.conways;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import klerer.conways.GameOfLife;
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

        // expected result after one generation
        int[][] expectedGrid = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };

        int rows = initialGrid.length;
        int columns = initialGrid[0].length;
        int[][] futureGrid = GameOfLife.nextGeneration(initialGrid, rows, columns);

        // then
        assertArrayEquals(expectedGrid, futureGrid);
    }
}
