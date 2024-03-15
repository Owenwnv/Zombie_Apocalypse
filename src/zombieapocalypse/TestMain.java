package zombieapocalypse;

import zombieapocalypse.mapcreation.MapGenerator;
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
        map.showMap();
    }
}
