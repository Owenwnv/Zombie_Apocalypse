package zombieapocalypse.game;

import java.util.Iterator;

import zombieapocalypse.actor.survivor.Survivor;
import zombieapocalypse.actor.zombie.Zombie;
import zombieapocalypse.mapcreation.Map;
import zombieapocalypse.cell.Cell;

public class Game {
    private Map map;

    public Game(Map map) {
        this.map = map;
    }

    public void spawnSurvivor(Survivor survivor, int i, int j) {
        this.map.getCell(i, j).addSurvivor(survivor);
    }

    public void spawnZombie(Zombie zombie, int i, int j) {
        this.map.getCell(i, j).addZombie(zombie);
    }

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

    public Map getMap() {
        return this.map;
    }
}
