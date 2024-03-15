package zombieapocalypse.mapcreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombieapocalypse.structure.*;
import zombieapocalypse.cell.Cell;
import zombieapocalypse.cell.EmptyCell;
import zombieapocalypse.cell.RoomCell;
import zombieapocalypse.cell.StreetCell;

public class MapGenerator {
    protected Map map;

    public MapGenerator(int width, int height) {
        this.map = new Map(width, height);
    }

    public Map getMap() {
        return this.map;
    }

    /**
     * Adds a column of StreetCells at specified index
     * 
     * @param street street to add
     * @param index  column to add the Street
     */
    public void addStreetX(Street street, int index) {
        int i = 0;
        while (i < this.map.getWidth()) {
            this.map.setCell(i, index, new StreetCell("street", street));
            i++;
        }
    }

    /**
     * Adds a row of StreetCells at specified index
     * 
     * @param street street to add
     * @param index  row to add the Street
     */
    public void addStreetY(Street street, int index) {
        int i = 0;
        while (i < this.map.getHeight()) {
            this.map.setCell(index, i, new StreetCell("street", street));
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
        for (int i = 0; i < this.map.getHeight(); i++) {
            if (this.map.getCell(0, i) instanceof EmptyCell) {
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
        for (int i = 0; i < this.map.getWidth(); i++) {
            if (this.map.getCell(i, 0) instanceof EmptyCell) {
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

    public void addMainroads() {
        Random random = new Random();
        int[] mainroads = {0, 0};
        while (true) {
            int index = random.nextInt(this.map.getWidth());
            Cell cell = this.map.getCell(index, 0);
            if (cell instanceof StreetCell) {
                mainroads[0] = index;
                StreetCell c = (StreetCell) cell;
                c.getStreet().setIsMainroads(true);
                break;
            }
        }
        while (true) {
            int index = random.nextInt(this.map.getHeight());
            Cell cell = this.map.getCell(0, index);
            if (cell instanceof StreetCell) {
                mainroads[1] = index;
                StreetCell c = (StreetCell) cell;
                c.getStreet().setIsMainroads(true);
                break;
            }
        }
        StreetCell cell = (StreetCell) this.map.getCell(mainroads[0], mainroads[1]);
        cell.setHasSewer(true);
        this.map.setMainroads(mainroads[0], mainroads[1]);
    }

    /**
     * Adds the Rooms to this Map
     */
    public void addRooms() {
        for (int i = 0; i < this.map.getWidth(); i++) {
            for (int j = 0; j < this.map.getHeight(); j++) {
                if (this.map.getCell(i, j) instanceof EmptyCell) {
                    this.map.setCell(i, j, new RoomCell("Room" + " " + i + " " + j));
                }
            }
        }
    }

    public void addCellRandom(Cell cell) {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(this.map.getWidth());
            int y = random.nextInt(this.map.getHeight());
            if (this.map.getCell(x, y).getName().substring(0, 4).equals("Room")) {
                this.map.setCell(x, y, cell);
                break;
            }
        }
    }

    public void addDoors() {
        for (int i = 0; i < this.map.getWidth(); i++) {
            for (int j = 0; j < this.map.getHeight(); j++) {
                if (this.map.getCell(i, j) instanceof RoomCell) {
                    RoomCell room = (RoomCell) this.map.getCell(i, j);
                    if (i + 1 != this.map.getWidth()) {
                        room.setDoor(new Door(false, false), 2);
                    }
                    if (j + 1 != this.map.getHeight()) {
                        room.setDoor(new Door(false, false), 1);
                    }
                    if (i > 0 && this.map.getCell(i - 1, j) instanceof RoomCell) {
                        RoomCell topRoom = (RoomCell) this.map.getCell(i - 1, j);
                        List<Door> doors = topRoom.getDoors();
                        room.setDoor(doors.get(2), 0);
                    } else {
                        room.setDoor(new Door(false, false), 0);
                    }
                    if (j > 0 && this.map.getCell(i, j - 1) instanceof RoomCell) {
                        RoomCell leftRoom = (RoomCell) this.map.getCell(i, j - 1);
                        List<Door> doors = leftRoom.getDoors();
                        room.setDoor(doors.get(1), 3);
                    } else {
                        room.setDoor(new Door(false, false), 3);
                    }
                } else {
                    if (i + 1 != this.map.getWidth()) {
                        if (this.map.getCell(i + 1, j) instanceof RoomCell) {
                            RoomCell bottomRoom = (RoomCell) this.map.getCell(i + 1, j);
                            bottomRoom.setDoor(new Door(false, false), 0);
                        }
                    }
                    if (j + 1 != this.map.getHeight()) {
                        if (this.map.getCell(i, j + 1) instanceof RoomCell) {
                            RoomCell rightRoom = (RoomCell) this.map.getCell(i, j + 1);
                            rightRoom.setDoor(new Door(false, false), 3);
                        }
                    }
                }
            }
        }
    }
}
