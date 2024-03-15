package zombieapocalypse.item.weapon;

import zombieapocalypse.item.Item;

public class Weapon extends Item {
    protected int nbDiceRoll;
    protected int threshold;
    protected int damage;
    protected int range;

    public Weapon(String name, String description, int nbDiceRoll, int threshold, int damage, int range) {
        super(name, description);
        this.nbDiceRoll = nbDiceRoll;
        this.threshold = threshold;
        this.damage = damage;
        this.range = range;
    }
}
