package zombieapocalypse;

import zombieapocalypse.mapcreation.MapGenerator;
import zombieapocalypse.actor.survivor.Fighter;
import zombieapocalypse.game.Game;
import zombieapocalypse.mapcreation.Map;

public class Livrable3 {
    public static void main(String[] args) {
        int width = 5;
        int height = 5;

        if (args.length > 1) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        }

        // Génére la ville d'entraînement
        MapGenerator mapg = new MapGenerator(width, height);
        Map map = mapg.generateMap();
        Game game = new Game(map, 1);
        game.spawnSurvivor(new Fighter("null"), 2, 2);
        game.getMap().showMap();

        game.gameLoop(1);
    }
}
