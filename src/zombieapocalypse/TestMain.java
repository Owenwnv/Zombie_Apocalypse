package zombieapocalypse;

public class TestMain {
    public static void main(String[] args) {
        Map map = new Map(10, 10);
        map.addStreets();
        map.showMap();
        System.out.println(map.canBeSplitX());
        System.out.println(map.canBeSplitY());
    }
}
