package zombieapocalypse.mapcreation;

import java.util.List;

import zombieapocalypse.cell.*;
import zombieapocalypse.structure.Door;
import zombieapocalypse.style.PimpStyle;

/* Class representing a map */
public class Map {
    /** height of this Map */
    private int height;
    /** width of this Map */
    private int width;
    /** cells of this Map */
    private Cell[][] cells;
    /** main roads of this Map */
    private int[] mainroads;

    /**
     * Builds a Map
     * 
     * @param height height of this Map
     * @param width  width of this Map
     */
    public Map(int width, int height) {
        this.height = height;
        this.width = width;
        this.mainroads = new int[2];
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

    public int[] getMainroads() {
        return this.mainroads;
    }

    public void setMainroads(int x, int y) {
        this.mainroads[0] = x;
        this.mainroads[1] = y;
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
            return "+";
        } else if (cell instanceof HotelRoomCell) {
            return "C";
        } else if (cell instanceof StreetCell) {
            if (((StreetCell) cell).getStreet().getIsMainroads()) {
                return "S";
            }
            return "s";
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
            if (((StreetCell) cell).getHasSewer()) {
                return PimpStyle.YELLOW;
            }
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

    public String getDoorColor(int i, int j, int side) {
        Cell cell = this.cells[i][j];
        if (cell instanceof RoomCell) {
            RoomCell room = (RoomCell) cell;
            List<Door> doors = room.getDoors();
            if (!doors.get(side).getIsBorder()) {
                return PimpStyle.CYAN;
            }
        } else if (side == 2) {
            if (i + 1 != this.width) {
                Cell c = this.cells[i + 1][j];
                if (c instanceof RoomCell) {
                    return PimpStyle.CYAN;
                }
            }
        } else if (side == 1) {
            if (j + 1 != this.height) {
                Cell c = this.cells[i][j + 1];
                if (c instanceof RoomCell) {
                    return PimpStyle.CYAN;
                }
            }
        }
        return "";
    }

    public String getCellNbSurvivor(int i, int j) {
        Cell cell = this.cells[i][j];
        int nb = cell.getSurvivors().size();
        return nb > 0 ? Integer.toString(nb) : " ";
    }

    public String getCellNbZombie(int i, int j) {
        Cell cell = this.cells[i][j];
        int nb = cell.getZombies().size();
        return nb > 0 ? Integer.toString(nb) : " ";
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
                System.out.print("|" + getCellNbZombie(i, j) + "   " + getCellNbSurvivor(i, j));
            }
            System.out.print("|");
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < this.height; j++) {
                System.out.print("  " + getCellColor(this.cells[i][j]) + getCellSymbol(this.cells[i][j])
                        + PimpStyle.RESET + getDoorColor(i, j, 1) + "  |" + PimpStyle.RESET);
            }
            System.out.println();
            for (int j = 0; j < this.height; j++) {
                System.out.print("|__" + getDoorColor(i, j, 2) + "_" + PimpStyle.RESET + "__");
            }
            System.out.print("|");
            System.out.println();
        }
    }
}
