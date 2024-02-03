package zombieapocalypse;

public class RoomCell implements Cell {
    protected String name;
    protected Door[] doors;
    protected Building building;

    public RoomCell(String name, Door[] doors, Building building) {
        this.name = name;
        this.doors = doors;
        this.building = building;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
