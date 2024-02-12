package zombieapocalypse;

public class TestMain {
    public static void main(String[] args) {
        Map map = new Map(15, 15);
        map.addStreets();
        map.addRooms();
        map.showMap();
    }
}
