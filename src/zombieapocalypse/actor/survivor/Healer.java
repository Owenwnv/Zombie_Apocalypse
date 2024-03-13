package zombieapocalypse.actor.survivor;

public class Healer extends Survivor {

    /**
     * Healer's feature
     */
    public Healer() {
        super();
    }

    public void heal(Survivor survivor) {
        survivor.alterHealthPoints(1);
    }
}
