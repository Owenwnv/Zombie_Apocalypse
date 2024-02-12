package zombieapocalypse;

public class TestMain {
    public static void main(String[] args) {
        Map map = new Map(5, 5);
        map.addStreets();
        map.addRooms();
        map.showMap();
    }
}
