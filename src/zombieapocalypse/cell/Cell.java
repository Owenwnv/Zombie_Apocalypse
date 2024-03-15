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

    public List<Survivor> getSurvivors() {
        return this.survivors;
    }

    public List<Zombie> getZombies() {
        return this.zombies;
    }

    public void addSurvivor(Survivor survivor) {
        this.survivors.add(survivor);
    }

    public void addZombie(Zombie zombie) {
        this.zombies.add(zombie);
    }

    public void removeSurvivor(Survivor survivor) {
        this.survivors.remove(survivor);
    }

    public void removeZombie(Zombie zombie) {
        this.zombies.remove(zombie);
    }

    /**
     * Returns a description of this Cell
     * 
     * @return description of this Cell
     */
    public abstract String toString();
}