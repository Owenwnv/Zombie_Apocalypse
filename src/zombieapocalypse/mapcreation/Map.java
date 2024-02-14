package zombieapocalypse.mapcreation;

import zombieapocalypse.cell.*;
import zombieapocalypse.style.PimpStyle;

/* Class representing a map */
public class Map {
    /** height of this Map */
    private int height;
    /** width of this Map */
    private int width;
    /** cells of this Map */
    private Cell[][] cells;

    /**
     * Builds a Map
     * 
     * @param height height of this Map
     * @param width  width of this Map
     */
    public Map(int width, int height) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.cells[i][j] = new EmptyCell();
            }
        }
    }

    /**
     * Returns this Map's height
     * 
     * @return Map's height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns this Map's width
     * 
     * @return Map's width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns specified Cell of this Map
     * 
     * @param x x coordinate of the Cell
     * @param y y coordinate of the Cell
     */
    public Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

    /**
     * Sets specified Cell of this Map
     * 
     * @param x    x coordinate of the Cell
     * @param y    y coordinate of the Cell
     * @param cell cell to replace with
     */
    public void setCell(int x, int y, Cell cell) {
        this.cells[x][y] = cell;
    }

    /**
     * Returns symbol depending on type of Cell
     * 
     * @return symbol of the Cell
     */
    public String getCellSymbol(Cell cell) {
        if (cell instanceof EmptyCell) {
            return "·";
        } else if (cell instanceof PharmacyRoomCell) {
            return "🞥";
        } else if (cell instanceof HotelRoomCell) {
            return "C";
        } else if (cell instanceof StreetCell) {
            return "S";
        } else {
            return "R";
        }
    }

    /**
     * Returns color depending on type of Cell
     * 
     * @return color of the Cell
     */
    public String getCellColor(Cell cell) {
        if (cell instanceof StreetCell) {
            return PimpStyle.BLUE;
        } else if (cell instanceof PharmacyRoomCell) {
            return PimpStyle.GREEN;
        } else if (cell instanceof HotelRoomCell) {
            return PimpStyle.YELLOW;
        } else if (cell instanceof RoomCell) {
            return PimpStyle.RED;
        }
        return "";
    }

    /**
     * Prints out this Map
     */
    public void showMap() {
        for (int j = 0; j < this.height; j++) {
            System.out.print("______");
        }
        System.out.println();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                System.out.print("|     ");
            }
            System.out.print("|");
            System.out.println();
            for (int j = 0; j < this.height; j++) {
                System.out.print("|  " + getCellColor(this.cells[i][j]) + getCellSymbol(this.cells[i][j])
                        + PimpStyle.RESET + "  ");
            }
            System.out.print("|");
            System.out.println();
            for (int j = 0; j < this.height; j++) {
                System.out.print("|_____");
            }
            System.out.print("|");
            System.out.println();
        }
    }
}
