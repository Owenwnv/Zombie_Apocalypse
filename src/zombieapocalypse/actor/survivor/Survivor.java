package zombieapocalypse.actor.survivor;

import zombieapocalypse.actor.Actor;

public class Survivor extends Actor {

    // public Slots[] backpack;
    /* Survivor's level */
    protected int level;

    public Survivor() {
        super(5);
        this.level = 1;
    }

    /**
     * Returns this Survivor's Level
     * 
     * @return Survivor's HealthPoints
     */
    public int getLevel() {
        return this.level;
    }
}
