package zombieapocalypse.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import zombieapocalypse.actor.survivor.Survivor;
import zombieapocalypse.actor.zombie.Zombie;
import zombieapocalypse.mapcreation.Map;
import zombieapocalypse.cell.Cell;

/**
 * Represents the game engine.
 */
public class Game {
    /**
     * the map
     */
    private Map map;

    /**
     * List of survivors in the Game.
     */
    protected List<Survivor> survivors;

    /**
     * List of zombies in the Game.
     */
    protected List<Zombie> zombies;

    /**
     * Constructs a Game with the specified map.
     * 
     * @param map The map of the game
     */
    public Game(Map map) {
        this.map = map;
        this.survivors = new ArrayList<>();
        this.zombies = new ArrayList<>();
    }

    /**
     * Spawns a survivor at the specified coordinates.
     * 
     * @param survivor The survivor to spawn
     * @param i        The x-coordinate
     * @param j        The y-coordinate
     */
    public void spawnSurvivor(Survivor survivor, int i, int j) {
        this.survivors.add(survivor);
        this.map.getCell(i, j).addSurvivor(survivor);
    }

    /**
     * Spawns a zombie at the specified coordinates.
     * 
     * @param zombie The zombie to spawn
     * @param i      The x-coordinate
     * @param j      The y-coordinate
     */
    public void spawnZombie(Zombie zombie, int i, int j) {
        this.zombies.add(zombie);
        this.map.getCell(i, j).addZombie(zombie);
    }

    public void zombieTurn(Zombie zombie) {

    }

    /**
     * Moves survivors up by one cell if possible.
     */
    public void moveUpSurvivors() {
        for (int i = 0; i < this.map.getWidth(); i++) {
            for (int j = 0; j < this.map.getHeight(); j++) {
                Cell cell = this.map.getCell(i, j);
                if (cell.getSurvivors().size() > 0 && i - 1 >= 0) {
                    Cell topCell = this.map.getCell(i - 1, j);
                    Iterator<Survivor> iterator = cell.getSurvivors().iterator();
                    while (iterator.hasNext()) {
                        Survivor survivor = iterator.next();
                        topCell.addSurvivor(survivor);
                        iterator.remove();
                    }
                }
            }
        }
    }

    /**
     * Returns the map of the game.
     * 
     * @return The map of the game
     */
    public Map getMap() {
        return this.map;
    }
}