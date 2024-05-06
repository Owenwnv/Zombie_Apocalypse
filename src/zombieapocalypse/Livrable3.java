package zombieapocalypse;

import zombieapocalypse.mapcreation.MapGenerator;
import zombieapocalypse.actor.survivor.*;
import zombieapocalypse.actor.zombie.*;
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
        Game game = new Game(map);

        Zombie z = new Abomination(0);
        Survivor fighter = new Fighter("Pierre");

        game.spawnZombie(z, 3, 2);
        game.spawnSurvivor(fighter, 3, 2);

        game.getMap().showMap();

        game.survivorTurn(fighter);
    }
}
