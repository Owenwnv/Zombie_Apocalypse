package zombieapocalypse.cell;

import java.util.ArrayList;
import java.util.List;

import zombieapocalypse.actor.survivor.Survivor;
import zombieapocalypse.actor.zombie.Zombie;

/* Class representing a cell */
public abstract class Cell {
    protected String name;
    protected List<Survivor> survivors;
    protected List<Zombie> zombies;

    public Cell(String name) {
        this.name = name;
        this.survivors = new ArrayList<>();
        this.zombies = new ArrayList<>();
    }
    /**
     * Returns this Cell's name
     * 
     * @return Cell's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a description of this Cell
     * 
     * @return description of this Cell
     */
    public abstract String toString();
}