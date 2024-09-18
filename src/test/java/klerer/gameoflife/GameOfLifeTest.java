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

        GameOfLife gameOfLife = new GameOfLife(3, 3);

        // when
        int[][] expectedGrid = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };

        int[][] futureGrid = gameOfLife.nextGeneration();

        // then
        assertArrayEquals(expectedGrid, futureGrid);
    }
}
