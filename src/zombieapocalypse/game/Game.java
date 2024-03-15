package zombieapocalypse.game;

import zombieapocalypse.actor.Actor;
import zombieapocalypse.mapcreation.Map;

public class Game {
    private Map map;

    public Game(Map map) {
        this.map = map;
    }

    public void spawnActor(Actor actor, int i, int j) {
        this.map.getCell(i, j);
    }
}
