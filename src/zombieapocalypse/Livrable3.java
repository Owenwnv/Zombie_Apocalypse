package zombieapocalypse;

import zombieapocalypse.mapcreation.MapGenerator;
import zombieapocalypse.actor.survivor.*;
import zombieapocalypse.actor.zombie.*;
import zombieapocalypse.cell.Cell;
import zombieapocalypse.cell.RoomCell;
import zombieapocalypse.game.Game;
import zombieapocalypse.item.tool.HandheldMap;
import zombieapocalypse.item.tool.HealthPotion;
import zombieapocalypse.item.weapon.Axe;
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

        // Place des zombies sur chaque zone
        game.spawnZombie(new Abomination(), 2, 2);
        game.spawnZombie(new Bigboy(), 0, 0);
        game.spawnZombie(new Runner(), 0, 2);
        game.spawnZombie(new Walker(), 3, 3);

        // Place un survivant de chaque rôle sur la zone au nord du carrefour principal
        Survivor fighter = new Fighter("Pierre");
        Healer healer = new Healer("Jean");
        Survivor lucky = new Lucky("Zebi");
        Survivor searcher = new Searcher("Double monstre");

        fighter.addItemToBackpack(new HandheldMap());
        healer.addItemToBackpack(new HandheldMap());
        lucky.addItemToBackpack(new HandheldMap());
        searcher.addItemToBackpack(new HandheldMap());

        game.spawnSurvivor(fighter, 1, 1);
        game.spawnSurvivor(healer, 1, 2);
        game.spawnSurvivor(lucky, 1, 3);
        game.spawnSurvivor(searcher, 1, 2);

        // Affiche une représentation de la ville
        game.getMap().showMap();

        // Place une hache dans la main du second survivant
        healer.putItemInHand(new Axe());

        // Place une fiole dans la main du troisième survivant
        lucky.putItemInHand(new HealthPotion());

        // Exécute une action par survivant
        RoomCell room = (RoomCell) game.getMap().getCell(1, 1);
        room.search(fighter);
        healer.heal(fighter);
        room = (RoomCell) game.getMap().getCell(1, 3);
        room.search(lucky);
        Cell cell = game.getMap().getCell(1, 2);
        System.out.println(cell.lookAround());
        // Exécute l'action d’attaquer et de se déplacer pour l'ensemble des zombies

        // Affiche une représentation de la ville
        game.getMap().showMap();

        // Affiche l'état des survivants
        System.out.println(fighter);
        System.out.println(healer);
        System.out.println(lucky);
        System.out.println(searcher);
    }
}
