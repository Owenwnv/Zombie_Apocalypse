package zombieapocalypse.actor.survivor;

/**
 * Represents a healer survivor, a specialized type of Survivor, in the game.
 */
public class Healer extends Survivor {
    /**
     * Constructs a Healer survivor with default health points, level, and an empty
     * backpack.
     */
    public Healer() {
        super();
    }

    /**
     * Heals the specified survivor by 1 health point.
     * 
     * @param survivor The survivor to be healed
     */
    public void heal(Survivor survivor) {
        survivor.alterHealthPoints(1);
    }
}