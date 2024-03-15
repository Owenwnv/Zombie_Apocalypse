package zombieapocalypse.cell;

import java.util.ArrayList;
import java.util.List;

import zombieapocalypse.actor.survivor.Survivor;
import zombieapocalypse.actor.zombie.Zombie;

/**
 * Represents an abstract cell in the game.
 */
public abstract class Cell {
    /**
     * The name of the cell.
     */
    protected String name;

    /**
     * List of survivors in the cell.
     */
    protected List<Survivor> survivors;

    /**
     * List of zombies in the cell.
     */
    protected List<Zombie> zombies;

    /**
     * Constructs a Cell with the specified name and initializes lists for survivors
     * and zombies.
     * 
     * @param name The name of the Cell
     */
    public Cell(String name) {
        this.name = name;
        this.survivors = new ArrayList<>();
        this.zombies = new ArrayList<>();
    }

    /**
     * Returns this Cell's name.
     * 
     * @return The Cell's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the list of survivors in this Cell.
     * 
     * @return The list of survivors
     */
    public List<Survivor> getSurvivors() {
        return this.survivors;
    }

    /**
     * Returns the list of zombies in this Cell.
     * 
     * @return The list of zombies
     */
    public List<Zombie> getZombies() {
        return this.zombies;
    }

    /**
     * Adds a survivor to this Cell.
     * 
     * @param survivor The survivor to be added
     */
    public void addSurvivor(Survivor survivor) {
        this.survivors.add(survivor);
    }

    /**
     * Adds a zombie to this Cell.
     * 
     * @param zombie The zombie to be added
     */
    public void addZombie(Zombie zombie) {
        this.zombies.add(zombie);
    }

    /**
     * Removes a survivor from this Cell.
     * 
     * @param survivor The survivor to be removed
     */
    public void removeSurvivor(Survivor survivor) {
        this.survivors.remove(survivor);
    }

    /**
     * Removes a zombie from this Cell.
     * 
     * @param zombie The zombie to be removed
     */
    public void removeZombie(Zombie zombie) {
        this.zombies.remove(zombie);
    }

    /**
     * Returns a description of this Cell.
     * 
     * @return A description of this Cell
     */
    public abstract String toString();
}