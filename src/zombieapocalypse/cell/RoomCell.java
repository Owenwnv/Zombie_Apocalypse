package zombieapocalypse.cell;

import java.util.ArrayList;
import java.util.List;

import zombieapocalypse.structure.Door;

/**
 * Represents a room cell in the game, extending from Cell.
 */
public class RoomCell extends Cell {
    /**
     * List of doors in this RoomCell.
     * 0 -> top Door
     * 1 -> right Door
     * 2 -> bottom Door
     * 3 -> left Door
     */
    protected List<Door> doors;

    /**
     * Constructs a RoomCell with the specified name and initializes doors with
     * default values.
     * 
     * @param name The name of the RoomCell
     */
    public RoomCell(String name) {
        super(name);
        this.doors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.doors.add(new Door(false, true));
        }
    }

    /**
     * Sets a Door to this RoomCell at the specified index.
     * 
     * @param door  The Door to replace with
     * @param index The index to put in
     */
    public void setDoor(Door door, int index) {
        this.doors.set(index, door);
    }

    /**
     * Returns this RoomCell's doors.
     * 
     * @return The RoomCell's doors
     */
    public List<Door> getDoors() {
        return this.doors;
    }

    /**
     * Returns a description of this RoomCell.
     * 
     * @return A description of this RoomCell
     */
    @Override
    public String toString() {
        return "This is a RoomCell";
    }
}