package zombieapocalypse.actor.survivor;

import java.util.ArrayList;
import java.util.List;

import zombieapocalypse.actor.Actor;
import zombieapocalypse.item.Item;;

public class Survivor extends Actor {

    // public Slots[] backpack;
    /* Survivor's level */
    protected int level;
    protected List<Item> backpack;
    protected Item inHand;

    public Survivor() {
        super(5);
        this.level = 1;
        this.backpack = new ArrayList<>();
        this.inHand = null;
    }

    /**
     * Returns this Survivor's Level
     * 
     * @return Survivor's HealthPoints
     */
    public int getLevel() {
        return this.level;
    }

    public List<Item> getBackpack() {
        return this.backpack;
    }

    public void addItemToBackpack(Item item) {
        this.backpack.add(item);
    }

    public void putItemInHand(Item item) {
        this.inHand = item;
    }
}
