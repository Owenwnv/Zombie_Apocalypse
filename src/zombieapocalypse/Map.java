package zombieapocalypse;

/* Class representing a map */
public class Map {
    // height of this Map
    private int height;
    // width of this Map
    private int width;
    // Cells of this Map
    private Cell[][] cells;

    /**
     * Builds a Map
     * @param height height of this Map
     * @param width width of this Map
     */
    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.cells[i][j] = new EmptyCell();
            }
        }
    }

    /**
     * Returns symbol depending on type of Cell
     * @return symbol of the Cell
     */
    public String getCellSymbol(Cell cell) {
        if (cell instanceof EmptyCell) {
            return "·";
        } else {
            return "?";
        }
    }

    /**
     * Prints out this Map
     */
    public void showMap() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print("______");
            }
            System.out.println();
            for (int j = 0; j < this.width; j++) {
                System.out.print("|     ");
            }
            System.out.print("|");
            System.out.println();
            for (int j = 0; j < this.width; j++) {
                System.out.print("|  " + getCellSymbol(this.cells[i][j]) + "  ");
            }
            System.out.print("|");
            System.out.println();
            for (int j = 0; j < this.width; j++) {
                System.out.print("|     ");
            }
            System.out.print("|");
            System.out.println();
            if (i == height - 1) {
                for (int j = 0; j < this.width; j++) {
                    System.out.print("______");
                }
                System.out.println();
            }
        }
    }

    /**
     * Returns this Map's height
     * @return Map's height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns this Map's width
     * @return Map's width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns specified Cell of this Map
     * @param x x coordinate of the Cell
     * @param y y coordinate of the Cell
     */
    public Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

    /**
     * Sets specified Cell of this Map
     * @param x x coordinate of the Cell
     * @param y y coordinate of the Cell
     * @param cell cell to replace with
     */
    public void setCell(int x, int y, Cell cell) {
        this.cells[x][y] = cell;
    }
}
