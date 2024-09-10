public class GameOfLife
{
    static int[][] nextGeneration(int grid[][], int rows, int columns) {
        int[][] future = new int[rows][columns];

        for (int l = 0; l < rows; l++) {
            for (int m = 0; m < columns; m++) {
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        if ((l + i >= 0 && l + i < rows) && (m + j >= 0 && m + j < columns))
                            aliveNeighbours += grid[l + i][m + j];

                aliveNeighbours -= grid[l][m];

                if ((grid[l][m] == 1) && (aliveNeighbours < 2))
                    future[l][m] = 0;
                else if ((grid[l][m] == 1) && (aliveNeighbours > 3))
                    future[l][m] = 0;
                else if ((grid[l][m] == 0) && (aliveNeighbours == 3))
                    future[l][m] = 1;
                else
                    future[l][m] = grid[l][m];
            }
        }

        return future;
    }
}