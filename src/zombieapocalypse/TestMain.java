package zombieapocalypse;

import zombieapocalypse.cell.HotelRoomCell;
import zombieapocalypse.cell.PharmacyRoomCell;
import zombieapocalypse.mapcreation.Map;

public class TestMain {
    public static void main(String[] args) {
        int width = 5;
        int height = 5;
        if (args.length > 1) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        }
        Map map = new Map(width, height);
        map.addStreets();
        map.addRooms();
        map.addCellRandom(new PharmacyRoomCell());
        map.addCellRandom(new HotelRoomCell());
        map.showMap();
    }
}
