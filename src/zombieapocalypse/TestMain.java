package zombieapocalypse;

public class TestMain {
    public static void main(String[] args) {
        Map map = new Map(8, 8);
        Street street = new Street("test", false);
        map.addStreetX(street, 2, false);
        map.addStreetX(street, 5, false);
        map.addStreetY(street, 2, true);
        map.showMap();
        System.out.println(map.canBeSplitX());
        System.out.println(map.canBeSplitY());
    }

}
