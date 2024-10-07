package klerer.gameoflife;

public class GameOfLife {
    private int[][] grid;
    private int width;
    private int height;

    public GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new int[width][height];
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

    public void setCell(int x, int y, int value) {
        grid[x][y] = value;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void resizeGrid(int newWidth, int newHeight) {
        newWidth = Math.max(100, newWidth);
        newHeight = Math.max(100, newHeight);

        int[][] newGrid = new int[newWidth][newHeight];
        for (int x = 0; x < Math.min(width, newWidth); x++) {
            for (int y = 0; y < Math.min(height, newHeight); y++) {
                newGrid[x][y] = grid[x][y];
            }
        }

        this.width = newWidth;
        this.height = newHeight;
        this.grid = newGrid;
    }

    public void loadRLEInCenter(String rleData) {
        RleParser parser = new RleParser();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                setCell(x, y, 0); // Clear all cells
            }
        }

        parser.loadRle(this, rleData);
    }


    public int[][] nextGeneration() {
        int[][] future = new int[width][height];

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                int aliveNeighbours = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (!(i == 0 && j == 0) && isInBounds(row + i, col + j)) {
                            aliveNeighbours += grid[row + i][col + j];
                        }
                    }
                }

                if (grid[row][col] == 1) {
                    future[row][col] = (aliveNeighbours == 2 || aliveNeighbours == 3) ? 1 : 0;
                } else {
                    future[row][col] = (aliveNeighbours == 3) ? 1 : 0;
                }
            }
        }

        grid = future;
        return future;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
