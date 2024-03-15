package zombieapocalypse.item.weapon;

import zombieapocalypse.item.Item;

/**
 * Represents a weapon item in the game, extending from Item.
 */
public class Weapon extends Item {
    /**
     * The number of dice rolls for the weapon.
     */
    protected int nbDiceRoll;

    /**
     * The threshold for the weapon.
     */
    protected int threshold;

    /**
     * The damage inflicted by the weapon.
     */
    protected int damage;

    /**
     * The range of the weapon.
     */
    protected int range;

    /**
     * Constructs a Weapon with the specified attributes.
     * 
     * @param name        The name of the weapon
     * @param description The description of the weapon
     * @param nbDiceRoll  The number of dice rolls for the weapon
     * @param threshold   The threshold for the weapon
     * @param damage      The damage inflicted by the weapon
     * @param range       The range of the weapon
     */
    public Weapon(String name, String description, int nbDiceRoll, int threshold, int damage, int range) {
        super(name, description);
        this.nbDiceRoll = nbDiceRoll;
        this.threshold = threshold;
        this.damage = damage;
        this.range = range;
    }
}