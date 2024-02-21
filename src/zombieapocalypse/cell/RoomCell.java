package zombieapocalypse.cell;

import java.util.ArrayList;
import java.util.List;

import zombieapocalypse.structure.Door;

/* Class representing a room cell, implements the Cell interface */
public class RoomCell implements Cell {
    /** name of this RoomCell */
    protected String name;
    /**
     * doors of this RoomCell
     * 0 -> top Door
     * 1 -> right Door
     * 2 -> bottom Door
     * 3 -> left Door
     */
    protected List<Door> doors;

    /**
     * Builds a RoomCell
     * 
     * @param name name of this RoomCell
     */
    public RoomCell(String name) {
        this.name = name;
        this.doors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.doors.add(new Door(false, true));
        }
    }

    /**
     * Sets a Door to this RoomCell at index
     * 
     * @param door  Door to replace with
     * @param index index to put in
     */
    public void setDoor(Door door, int index) {
        this.doors.set(index, door);
    }

    /**
     * Returns this RoomCell's doors
     * 
     * @return RoomCell's doors
     */
    public List<Door> getDoors() {
        return this.doors;
    }

    /**
     * Returns this RoomCell's name
     * 
     * @return RoomCell's name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns a description of this RoomCell
     * 
     * @return description of this RoomCell
     */
    @Override
    public String toString() {
        return "This is a RoomCell";
    }
}
