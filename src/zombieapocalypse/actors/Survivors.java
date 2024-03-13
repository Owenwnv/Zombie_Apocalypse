package zombieapocalypse.actors;

public class Survivors {

    // public Slots[] backpack;
    /* Survivor's level */
    protected int level;

    public Survivors(int level) {
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
