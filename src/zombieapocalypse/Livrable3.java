package zombieapocalypse;

import zombieapocalypse.mapcreation.MapGenerator;
import zombieapocalypse.actor.survivor.*;
import zombieapocalypse.actor.zombie.*;
import zombieapocalypse.game.Game;
import zombieapocalypse.item.tool.HandheldMap;
import zombieapocalypse.item.tool.HealthPotion;
import zombieapocalypse.mapcreation.Map;

public class Livrable3 {
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

        Survivor fighter = new Fighter();
        Survivor healer = new Healer();
        Survivor lucky = new Lucky();
        Survivor searcher = new Searcher();

        fighter.addItemToBackpack(new HandheldMap());
        healer.addItemToBackpack(new HandheldMap());
        lucky.addItemToBackpack(new HandheldMap());
        searcher.addItemToBackpack(new HandheldMap());

        fighter.putItemInHand(new HealthPotion());
        healer.putItemInHand(new HealthPotion());
        lucky.putItemInHand(new HealthPotion());
        searcher.putItemInHand(new HealthPotion());

        game.spawnSurvivor(fighter, 2, 2);
        game.spawnSurvivor(healer, 2, 2);
        game.spawnSurvivor(lucky, 2, 2);
        game.spawnSurvivor(searcher, 2, 2);

        game.spawnZombie(new Abomination(), 2, 2);
        game.spawnZombie(new Bigboy(), 0, 0);
        game.spawnZombie(new Runner(), 0, 2);
        game.spawnZombie(new Walker(), 3, 3);

        game.moveUpSurvivors();
        game.getMap().showMap();
    }
}
