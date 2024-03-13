package zombieapocalypse.actor;

public class Survivor extends Actor {

    // public Slots[] backpack;
    /* Survivor's level */
    protected int level;

    public Survivor(int level) {
        super(5);
        this.level = level;
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
