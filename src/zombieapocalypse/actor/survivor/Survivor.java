package zombieapocalypse.actor.survivor;

import java.util.ArrayList;
import java.util.List;

import zombieapocalypse.actor.Actor;
import zombieapocalypse.item.Item;

/**
 * Represents a survivor in the game, inheriting from Actor.
 */
public class Survivor extends Actor {
    /**
     * Survivor's level.
     */
    protected int level;

    /**
     * List of items in the survivor's backpack.
     */
    protected List<Item> backpack;

    /**
     * Item currently held in the survivor's hand.
     */
    protected Item inHand;

    /**
     * Constructs a Survivor with default health points and level, and an empty
     * backpack.
     */
    public Survivor() {
        super(5);
        this.level = 1;
        this.backpack = new ArrayList<>();
        this.inHand = null;
    }

    /**
     * Returns this Survivor's level.
     * 
     * @return The Survivor's level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Returns the list of items in the Survivor's backpack.
     * 
     * @return The Survivor's backpack
     */
    public List<Item> getBackpack() {
        return this.backpack;
    }
    
    /**
     * Adds an item to the Survivor's backpack.
     * 
     * @param item The item to be added
     */
    public void addItemToBackpack(Item item) {
        if (this.backpack.size() < 5) {
            this.backpack.add(item);
        }
    }

    /**
     * Puts an item in the Survivor's hand.
     * 
     * @param item The item to be put in hand
     */
    public void putItemInHand(Item item) {
        this.inHand = item;
    }

    public Item getInHand() {
        return this.inHand;
    }
}