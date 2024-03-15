package zombieapocalypse.game;

import zombieapocalypse.actor.survivor.Survivor;
import zombieapocalypse.actor.zombie.Zombie;
import zombieapocalypse.mapcreation.Map;

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

    public Map getMap() {
        return this.map;
    }
}
