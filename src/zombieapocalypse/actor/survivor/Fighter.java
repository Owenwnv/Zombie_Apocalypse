package zombieapocalypse.actor.survivor;

import zombieapocalypse.item.Item;

/**
 * Represents a fighter survivor, a specialized type of Survivor, in the game.
 */
public class Fighter extends Survivor {
    /**
     * Constructs a Fighter survivor with default health points, level, and an empty
     * backpack.
     */
    public Fighter(String name, Item inHand) {
        super(name, inHand);
    }
}
