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
     * Action Points to execute an action.
     */
    protected int actionPoints;

    /**
     * Experience points to determine the global experience points and the number of
     * zombies to spawn.
     */
    protected int experiencePoints;

    /**
     * Name of this Survivor.
     */
    protected String name;

    /**
     * Constructs a Survivor with default health points, level, action points and
     * experience points, and an empty
     * backpack.
     */
    public Survivor(String name) {
        super(5);
        this.name = name;
        this.level = 1;
        this.backpack = new ArrayList<>();
        this.inHand = null;
        this.actionPoints = 3;
        this.experiencePoints = 0;
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
     * 
     * @return Item that is currenctly in hand
     */
    public Item getItemInHand() {
        return this.inHand;
    }

    /**
     * 
     * @return Experience Points
     */
    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    /**
     * 
     * @return Action Points
     */
    public int getActionPoints() {
        return this.actionPoints;
    }

    /**
     * Sets Action Points to x Action Points
     */
    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    /**
     * Sets experience points to x XP
     */
    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    /**
     * increases experience points by 1 XP
     */
    public void increaseExperiencePoints() {
        this.experiencePoints = this.experiencePoints + 1;
    }

    /**
     * decreases experience points by 1 XP;
     */
    public void decreaseExperiencePoints() {
        if (this.experiencePoints > 0) {
            this.experiencePoints = this.experiencePoints - 1;
        }
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

    /**
     * Describes this Survivor
     * 
     * @return a String describing this Survivor
     */
    @Override
    public String toString() {
        return this.name + " is level " + this.level + " and has " + this.healthPoints + " health points.";
    }
}