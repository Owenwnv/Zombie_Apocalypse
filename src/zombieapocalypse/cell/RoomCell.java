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
        if (survivor.getActionPoints() <= 0) {
            System.out.println("You don't have enough action points to search.");
            return;
        }

        if (survivor.getBackpack().size() >= 5) {
            System.out.println("Your backpack is full. You can't take more items.");
            return;
        }

        survivor.setActionPoints(survivor.getActionPoints() - 1);

        List<Item> itemsFound = new ArrayList<>();
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
                itemsFound.add(item);
            }

            System.out.println("You have found the following items:");
            for (Item item : itemsFound) {
                System.out.println("- " + item.getName());
            }

            for (Item item : itemsFound) {
                survivor.addItemToBackpack(item);
            }
        } else {
            System.out.println("There is no item in this room.");
        }
    }
}