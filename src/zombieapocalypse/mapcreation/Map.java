package zombieapocalypse.mapcreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombieapocalypse.cell.*;
import zombieapocalypse.structure.Street;
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

    /**
     * Adds a column of StreetCells at specified index
     * 
     * @param street street to add
     * @param index column to add the Street
     */
    public void addStreetX(Street street, int index) {
        int i = 0;
        while (i < this.width) {
            this.cells[i][index] = new StreetCell("street", street);
            i++;
        }
    }

    /**
     * Adds a row of StreetCells at specified index
     * 
     * @param street street to add
     * @param index row to add the Street
     */
    public void addStreetY(Street street, int index) {
        int i = 0;
        while (i < this.height) {
            this.cells[index][i] = new StreetCell("street", street);
            i++;
        }
    }

    /**
     * Returns a list of possible columns for a Street
     * empty list if not possible
     * 
     * @return list of possible columns for a Street
     */
    public List<Integer> canBeSplitX() {
        List<Integer> res = new ArrayList<>();
        int temp = 0;
        for (int i = 0; i < this.height; i++) {
            if (this.cells[0][i] instanceof EmptyCell) {
                temp++;
                if (temp > 4) {
                    res.add(i - 2);
                }
            } else {
                temp = 0;
            }
        }
        return res;
    }

    /**
     * Returns a list of possible rows for a Street
     * empty list if not possible
     * 
     * @return list of possible rows for a Street
     */
    public List<Integer> canBeSplitY() {
        List<Integer> res = new ArrayList<>();
        int temp = 0;
        for (int i = 0; i < this.width; i++) {
            if (this.cells[i][0] instanceof EmptyCell) {
                temp++;
                if (temp > 4) {
                    res.add(i - 2);
                }
            } else {
                temp = 0;
            }
        }
        return res;
    }

    /**
     * Adds the Streets to this Map
     */
    public void addStreets() {
        Random random = new Random();
        while (canBeSplitX().size() > 0) {
            List<Integer> possibleStreets = canBeSplitX();
            addStreetX(new Street("Street", false), possibleStreets.get(random.nextInt(possibleStreets.size())));
        }
        while (canBeSplitY().size() > 0) {
            List<Integer> possibleStreets = canBeSplitY();
            addStreetY(new Street("Street", false), possibleStreets.get(random.nextInt(possibleStreets.size())));
        }
    }

    /**
     * Adds the Rooms to this Map
     */
    public void addRooms() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (this.cells[i][j] instanceof EmptyCell) {
                    this.cells[i][j] = new RoomCell("Room");
                }
            }
        }
    }
}
