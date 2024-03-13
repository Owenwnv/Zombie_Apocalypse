package zombieapocalypse.actor;

public class Actor {
    /*
     * Actors's HealhPoints
     */
    protected int healthPoints;

    public Actor(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * Returns this Actor's HealthPoints
     * 
     * @return Atcor's HealthPoints
     */
    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void alterHealthPoints(int nbHP) {
        this.healthPoints = healthPoints + nbHP;
    }

}
