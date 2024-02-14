package zombieapocalypse.structure;

import java.util.ArrayList;
import java.util.List;

import zombieapocalypse.cell.RoomCell;

/* Class representing a building */
public class Building {
    /** name of this Building */
    private String name;
    /** rooms of this Building */
    private List<RoomCell> rooms;

    /**
     * Builds a Building
     * 
     * @param name name of this Building
     * @param rooms rooms of this Building
     */
    public Building(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    /**
     * Adds a RoomCell to this Building
     */
    public void addRoom(RoomCell room) {
        this.rooms.add(room);
    }

    /**
     * Returns this Building's rooms
     * 
     * @return Building's rooms
     */
    public List<RoomCell> getRooms() {
        return this.rooms;
    }

    /**
     * Returns this Building's name
     * 
     * @return Building's name
     */
    public String getName() {
        return this.name;
    }
}
