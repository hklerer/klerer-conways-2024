package klerer.gameoflife;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class GameOfLifeTest {

    @Test
    public void nextGeneration() {
        // given
        GameOfLife gameOfLife = new GameOfLife(3, 3);

        gameOfLife.setCell(0, 1, 1);
        gameOfLife.setCell(1, 1, 1);
        gameOfLife.setCell(2, 1, 1);

        int[][] expectedGrid = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };

        // when
        int[][] futureGrid = gameOfLife.nextGeneration();

        // then
        assertArrayEquals(expectedGrid, futureGrid);
    }

    @Test
    public void loadPatternFromRle() {
        // given
        GameOfLife gameOfLife = new GameOfLife(3, 3);

        String rleString = "bo$ob!";

        // when
        gameOfLife.loadPatternFromRle(rleString);

        int[][] expectedGrid = {
                {0, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        };

        // then
        assertArrayEquals(expectedGrid, gameOfLife.getGrid());
    }
}
