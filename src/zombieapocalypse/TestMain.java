package zombieapocalypse;

public class TestMain {
    public static void main(String[] args) {
        Map map = new Map(10, 10);
        map.addStreets();
        map.addRooms();
        map.showMap();
    }
}
