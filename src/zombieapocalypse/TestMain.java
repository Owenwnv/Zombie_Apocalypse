package zombieapocalypse;

public class TestMain {
    public static void main(String[] args) {
        Map map = new Map(5, 10);
        map.initBoardToRoomCells();
        // cutBoard(map);

        map.showMap();
    }

}
