package zombieapocalypse.actor.survivor;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import zombieapocalypse.actor.Actor;
import zombieapocalypse.actor.zombie.Zombie;
import zombieapocalypse.item.Item;
import zombieapocalypse.item.weapon.Axe;
import zombieapocalypse.item.weapon.Chainsaw;
import zombieapocalypse.item.weapon.Crowbar;
import zombieapocalypse.item.weapon.Gun;
import zombieapocalypse.item.weapon.Rifle;
import zombieapocalypse.item.weapon.Weapon;

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
     * Constructs a Survivor with default health points, level, action points and
     * experience points, and an empty
     * backpack.
     */
    public Survivor(String name) {
        super(5, name);
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

    public void removeItemFromBackpack(Item item) {
        this.backpack.remove(item);
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
     * Check if the attack is valid.
     * 
     * @param zombie the zombie that we want to attack
     * 
     * @return boolean
     */
    public boolean checkAttackValidity(Zombie zombie){
       
        int range;
        if(this.horizontalCoordinates == zombie.getHorizontalCoordinates()){
            range = Math.abs(this.verticalCoordinates - zombie.getVerticalCoordinates());
        } else if(this.verticalCoordinates == zombie.getVerticalCoordinates()){
            range = Math.abs(this.horizontalCoordinates - zombie.getHorizontalCoordinates());
        } else {
            range = -1;
        }
        if(range != -1 && inHand instanceof Weapon){
            if ((inHand instanceof Rifle && range > 0 && range < 4) ||
                (inHand instanceof Gun && range <= 1 && range >= 0) ||
                ((inHand instanceof Axe || inHand instanceof Chainsaw || inHand instanceof Crowbar) && range == 0)) {
                return true;
            }
        }
        return false;
    }

    public boolean attackZombie(Zombie zombie){
        boolean result = false;
    
        if(checkAttackValidity(zombie)){
            Random random = new Random();
            int randDice1 = random.nextInt(6) + 1;
            int randDice2 = random.nextInt(6) + 1;
            if(this.inHand instanceof Crowbar){
                if(randDice1 >= 4){
                    zombie.decreaseHealthPoints(1);
                    result = true;
                }
            } else if(this.inHand instanceof Axe){
                if(randDice1 >= 4){
                    zombie.decreaseHealthPoints(2);
                    result = true;
                }
            } else if(this.inHand instanceof Chainsaw){
                if(randDice1 >= 5){
                    zombie.decreaseHealthPoints(3);
                    result = true;
                } else if(randDice2 >= 5){
                    zombie.decreaseHealthPoints(3);
                    result = true;
                }
            } else if(this.inHand instanceof Gun){
                if(randDice1 >= 4){
                    zombie.decreaseHealthPoints(1);
                    result = true;
                }
            } else if(this.inHand instanceof Rifle){
                if(randDice1 >= 4){
                    zombie.decreaseHealthPoints(1);
                    result = true;
                }else{
                    if(randDice2 >= 4){
                        zombie.decreaseHealthPoints(1);
                        result = true;
                    }
                }
            }
        }
        return result;
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