package klerer.gameoflife;

public class GameOfLife {
    private int[][] grid;

    public void setGrid(int[][] initialGrid) {
        this.grid = initialGrid;
    }

    public int[][] getGrid() {
        return grid;
    }

    public int[][] nextGeneration() {
        if (grid == null) {
            throw new IllegalStateException("Grid has not been initialized");
        }

        int rows = grid.length;
        int columns = grid[0].length;
        int[][] future = new int[rows][columns];

        for (int l = 0; l < rows; l++) {
            for (int m = 0; m < columns; m++) {
                int aliveNeighbours = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if ((l + i >= 0 && l + i < rows) && (m + j >= 0 && m + j < columns) && !(i == 0 && j == 0)) {
                            aliveNeighbours += grid[l + i][m + j];
                        }
                    }
                }

                if (grid[l][m] == 1) {
                    if (aliveNeighbours < 2 || aliveNeighbours > 3) {
                        future[l][m] = 0;
                    } else {
                        future[l][m] = 1;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        future[l][m] = 1;
                    }
                }
            }
        }

        grid = future;
        return future;
    }
}
