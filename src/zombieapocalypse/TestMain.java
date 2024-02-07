package zombieapocalypse;

public class TestMain {
    public static void main(String[] args) {
        Map map = new Map(5, 5);
        map.initBoardToRoomCells();
        //map.createStreet(map.getWidth(), map.getHeight());

        map.showMap();
    }

}
