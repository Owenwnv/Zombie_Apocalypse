package zombieapocalypse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
     * Prints out this Map
     */
    public void showMap() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                System.out.print("______");
            }
            System.out.println();
            for (int j = 0; j < this.height; j++) {
                System.out.print("|     ");
            }
            System.out.print("|");
            System.out.println();
            for (int j = 0; j < this.height; j++) {
                System.out.print("|  " + getCellSymbol(this.cells[i][j]) + "  ");
            }
            System.out.print("|");
            System.out.println();
            for (int j = 0; j < this.height; j++) {
                System.out.print("|     ");
            }
            System.out.print("|");
            System.out.println();
            if (i == width - 1) {
                for (int j = 0; j < this.height; j++) {
                    System.out.print("______");
                }
                System.out.println();
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

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public void addStreetX(Street street, int index) {
        int i = 0;
        while (i < this.width) {
            this.cells[i][index] = new StreetCell("street", street);
            i++;
        }
    }

    public void addStreetY(Street street, int index) {
        int i = 0;
        while (i < this.height) {
            this.cells[index][i] = new StreetCell("street", street);
            i++;
        }
    }

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

    public int generateRandomInt(int x, int y) {
        Random random = new Random();
        return random.nextInt(y - x + 1) + x;
    }

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
}
