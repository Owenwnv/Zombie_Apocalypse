package zombieapocalypse.cell;

import java.util.ArrayList;
import java.util.List;

import zombieapocalypse.structure.Door;
import java.util.Random;

import zombieapocalypse.item.Item;
import zombieapocalypse.item.tool.HealthPotion;
import zombieapocalypse.item.tool.MedKit;
import zombieapocalypse.item.tool.SkeletonKey;
import zombieapocalypse.item.weapon.Axe;
import zombieapocalypse.item.weapon.Gun;
import zombieapocalypse.item.weapon.Rifle;

/**
 * Represents a room cell in the game, extending from Cell.
 */
public class RoomCell extends Cell {

    protected List<Item> items;

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
        this.items = generateItems();
        this.doors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.doors.add(new Door(false, true));
        }
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
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
     * Randomly generates items for this RoomCell
     */
    private List<Item> generateItems() {
        List<Item> items = new ArrayList<>();
        Random rand = new Random();
        int numberOfItems = rand.nextInt(3);

        if (numberOfItems != 0) {
            for (int i = 0; i < numberOfItems; i++) {
                Item item;
                int itemType = rand.nextInt(6);

                if (itemType == 0) {
                    item = new HealthPotion();
                } else if (itemType == 1) {
                    item = new MedKit();
                } else if (itemType == 2) {
                    item = new SkeletonKey();
                } else if (itemType == 3) {
                    item = new Axe();
                } else if (itemType == 4) {
                    item = new Gun();
                } else {
                    item = new Rifle();
                }

                items.add(item);
            }
        }
        return items;
    }
}