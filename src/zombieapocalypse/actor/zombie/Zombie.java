package zombieapocalypse.actor.zombie;

import zombieapocalypse.actor.Actor;
import zombieapocalypse.actor.survivor.Survivor;

/**
 * Represents a zombie in the game, inheriting from Actor.
 */
public class Zombie extends Actor {
    /**
     * Zombie's damage.
     */
    protected int damage;

    /**
     * Constructs a Zombie with the specified damage and health points.
     * 
     * @param damage       The damage of the Zombie
     * @param healthPoints The health points of the Zombie
     */
    public Zombie(int damage, int healthPoints, String name) {
        super(healthPoints, name);
        this.damage = damage;
    }

    /**
     * Returns this Zombie's damage.
     * 
     * @return The Zombie's damage
     */
    public int getDamage() {
        return this.damage;
    }

    public void attackSurvivor(Survivor survivor) {
        survivor.decreaseHealthPoints(damage);
        System.out.println(this.name + " deals " + this.damage + " damages to " + survivor.getName() + ".");
    }
}