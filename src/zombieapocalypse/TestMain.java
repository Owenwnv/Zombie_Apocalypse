package zombieapocalypse;

public class TestMain {
    public static void main(String[] args) {
        Map map = new Map(5, 10);
        Street street = new Street("test", false);
        map.addStreetX(street, 2, false);
        map.addStreetY(street, 2, true);
        map.showMap();
    }

}
