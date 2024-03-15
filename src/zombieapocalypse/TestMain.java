package zombieapocalypse;

import zombieapocalypse.mapcreation.MapGenerator;
import zombieapocalypse.actor.survivor.Fighter;
import zombieapocalypse.actor.survivor.Healer;
import zombieapocalypse.actor.survivor.Lucky;
import zombieapocalypse.actor.survivor.Searcher;
import zombieapocalypse.actor.zombie.Abomination;
import zombieapocalypse.actor.zombie.Bigboy;
import zombieapocalypse.actor.zombie.Runner;
import zombieapocalypse.actor.zombie.Walker;
import zombieapocalypse.actor.zombie.Zombie;
import zombieapocalypse.game.Game;
import zombieapocalypse.mapcreation.Map;

public class TestMain {
    public static void main(String[] args) {
        int width = 5;
        int height = 5;
        if (args.length > 1) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        }
        MapGenerator mapg = new MapGenerator(width, height);
        Map map = mapg.generateMap();
        Game game = new Game(map);
        game.spawnSurvivor(new Fighter(), 2, 2);
        game.spawnSurvivor(new Healer(), 2, 2);
        game.spawnSurvivor(new Lucky(), 2, 2);
        game.spawnSurvivor(new Searcher(), 2, 2);
        game.spawnZombie(new Abomination(), 2, 2);
        game.spawnZombie(new Bigboy(), 0, 0);
        game.spawnZombie(new Runner(), 0, 2);
        game.spawnZombie(new Walker(), 3, 3);
        game.moveUpSurvivors();
        game.getMap().showMap();
    }
}
