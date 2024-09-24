package klerer.gameoflife;

public class GameOfLife {
    private int[][] grid;
    public int width;
    public int height;

    public GameOfLife(int width, int height) {
        grid = new int[width][height];
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCell(int x, int y) {
        return grid[x][y];
    }

    public void setCell(int width, int height, int value) {
        grid[width][height] = value;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void loadPatternFromRLE(String rleData) {
        RLEParser parser = new RLEParser();
        parser.loadRLE(this, rleData);
    }

    public int[][] nextGeneration() {
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
                    if (aliveNeighbours == 2 || aliveNeighbours == 3) {
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
