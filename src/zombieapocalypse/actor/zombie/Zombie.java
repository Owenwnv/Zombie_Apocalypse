package zombieapocalypse.actor.zombie;

import zombieapocalypse.actor.Actor;

public class Zombie extends Actor {

    /**
     * Zombie's damage
     */
    protected int damage;

    public Zombie(int damage, int healthPoints) {
        super(healthPoints);
        this.damage = damage;
    }

    /**
     * Returns this Zombie's Damages
     * 
     * @return Zombie's Damages
     */
    public int getDamage() {
        return this.damage;
    }
}
