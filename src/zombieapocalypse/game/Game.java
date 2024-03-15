package zombieapocalypse.game;

import java.util.Iterator;

import zombieapocalypse.actor.survivor.Survivor;
import zombieapocalypse.actor.zombie.Zombie;
import zombieapocalypse.mapcreation.Map;
import zombieapocalypse.cell.Cell;

/**
 * Represents the game engine.
 */
public class Game {
    private Map map;

    /**
     * Constructs a Game with the specified map.
     * 
     * @param map The map of the game
     */
    public Game(Map map) {
        this.map = map;
    }

    /**
     * Spawns a survivor at the specified coordinates.
     * 
     * @param survivor The survivor to spawn
     * @param i        The x-coordinate
     * @param j        The y-coordinate
     */
    public void spawnSurvivor(Survivor survivor, int i, int j) {
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
        this.map.getCell(i, j).addZombie(zombie);
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