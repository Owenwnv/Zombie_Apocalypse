package zombieapocalypse;

import zombieapocalypse.cell.HotelRoomCell;
import zombieapocalypse.cell.PharmacyRoomCell;
import zombieapocalypse.mapcreation.MapGenerator;

public class TestMain {
    public static void main(String[] args) {
        int width = 5;
        int height = 5;
        if (args.length > 1) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        }
        MapGenerator mapg = new MapGenerator(width, height);
        mapg.addStreets();
        mapg.addRooms();
        mapg.addCellRandom(new PharmacyRoomCell());
        mapg.addCellRandom(new HotelRoomCell());
        mapg.addMainroads();
        mapg.addDoors();
        mapg.getMap().showMap();
    }
}
