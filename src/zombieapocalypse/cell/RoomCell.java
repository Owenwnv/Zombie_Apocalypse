package zombieapocalypse.cell;

import java.util.ArrayList;
import java.util.List;

import zombieapocalypse.structure.Door;
import java.util.Random;

import zombieapocalypse.item.ConcreteItem;
import zombieapocalypse.item.Item;
import zombieapocalypse.actor.survivor.Survivor;

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

    /**
     * Method for the survivor to search the room.
     *
     * @param survivor The survivor who is searching the room
     */
    public void search(Survivor survivor) {
        if (!(this instanceof RoomCell)) {
            System.out.println("You can only search in a room.");
            return;
        }

        if (survivor.getBackpack().size() >= 5) {
            System.out.println("Your backpack is full. You can't take more items.");
            return;
        }

        List<Item> itemsFound = new ArrayList<>();
        Random rand = new Random();
        int numberOfItems = rand.nextInt(3) + 1;
        for (int i = 0; i < numberOfItems; i++) {
            Item item = new ConcreteItem("Item " + (i + 1), "Description of Item " + (i + 1));
            itemsFound.add(item);
        }

        System.out.println("You have found the following items:");
        for (Item item : itemsFound) {
            System.out.println("- " + item.getName());
        }

        for (Item item : itemsFound) {
            survivor.addItemToBackpack(item);
        }
    }
}