package zombieapocalypse;

public class Map {
    private int height;
    private int width;
    private Cell[][] cells;

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.cells[i][j] = new EmptyCell();
            }
        }
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

    public void setCell(int x, int y, Cell cell) {
        this.cells[x][y] = cell;
    }
}
