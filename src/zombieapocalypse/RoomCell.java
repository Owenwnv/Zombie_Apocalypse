package zombieapocalypse;

import java.util.ArrayList;
import java.util.List;

/* Class representing a room cell, implements the Cell interface */
public class RoomCell implements Cell {
    /** name of this RoomCell */
    protected String name;
    /** doors of this RoomCell */
    protected List<Door> doors;

    /**
     * Builds a RoomCell
     * 
     * @param name  name of this RoomCell
     */
    public RoomCell(String name) {
        this.name = name;
        this.doors = new ArrayList<>();
    }

    /**
     * Adds a Door to this RoomCell
     */
    public void addRoom(Door door) {
        this.doors.add(door);
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
