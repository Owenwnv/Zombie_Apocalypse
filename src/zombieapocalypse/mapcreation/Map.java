package zombieapocalypse.mapcreation;

import java.util.List;

import zombieapocalypse.cell.*;
import zombieapocalypse.structure.Door;
import zombieapocalypse.style.PimpStyle;

/**
 * Represents a map in the game.
 */
public class Map {
    /**
     * The height of this Map.
     */
    private int height;

    /**
     * The width of this Map.
     */
    private int width;

    /**
     * The cells of this Map.
     */
    private Cell[][] cells;

    /**
     * The main roads of this Map.
     */
    private int[] mainroads;

    /**
     * Constructs a Map with the specified width and height.
     * 
     * @param width  The width of this Map
     * @param height The height of this Map
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

    /**
     * Returns the main roads of this Map.
     * 
     * @return The main roads of this Map
     */
    public int[] getMainroads() {
        return this.mainroads;
    }

    /**
     * Sets the main roads of this Map to the specified coordinates.
     * 
     * @param x The x-coordinate of the main road
     * @param y The y-coordinate of the main road
     */
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
            return PimpStyle.BOLD + "+";
        } else if (cell instanceof HotelRoomCell) {
            return PimpStyle.BOLD + "C";
        } else if (cell instanceof StreetCell) {
            if (((StreetCell) cell).getStreet().getIsMainroads()) {
                return PimpStyle.BOLD + "S";
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

    /**
     * Returns the color of the door at the specified position.
     * 
     * @param i    The x-coordinate of the Cell
     * @param j    The y-coordinate of the Cell
     * @param side The side of the door
     * @return The color of the door
     */
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

    /**
     * Returns the number of survivors in the Cell at the specified position.
     * 
     * @param i The x-coordinate of the Cell
     * @param j The y-coordinate of the Cell
     * @return The number of survivors
     */
    public String getCellNbSurvivor(int i, int j) {
        Cell cell = this.cells[i][j];
        int nb = cell.getSurvivors().size();
        return nb > 0 ? Integer.toString(nb) : " ";
    }

    /**
     * Returns the number of zombies in the Cell at the specified position.
     * 
     * @param i The x-coordinate of the Cell
     * @param j The y-coordinate of the Cell
     * @return The number of zombies
     */
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

    /**
     * resets noiseLevel for every Cell in the map
     */
    public void resetMapNoiseLevel(){
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                getCell(i, j).resetNoiseLevel();
            }
        }
    }
}
