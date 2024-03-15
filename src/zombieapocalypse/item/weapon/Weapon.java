package zombieapocalypse.item.weapon;

import zombieapocalypse.item.Item;;

public class Weapon extends Item {
    protected int damage;

    public Weapon(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;
    }
}
