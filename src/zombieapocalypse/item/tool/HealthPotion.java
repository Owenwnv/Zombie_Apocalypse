package zombieapocalypse.item.tool;

import zombieapocalypse.actor.survivor.Survivor;

/**
 * Represents a health potion item in the game, extending from Tool.
 */
public class HealthPotion extends Tool {
    /**
     * Constructs a HealthPotion with the specified name and an empty description.
     */
    public HealthPotion() {
        super("Health potion", "The heal potion allows a survivor to heal himself 1 health points.");
    }

    public void heal(Survivor survivor) {
        survivor.increaseHealthPoints(1);
        survivor.putItemInHand(null);
        System.out.println(survivor.getName() + " heals himself with health potion.");
    }
}