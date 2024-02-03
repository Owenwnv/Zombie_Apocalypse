package zombieapocalypse;

/* Class representing a room cell, implements the Cell interface */
public class RoomCell implements Cell {
    // name of this RoomCell
    protected String name;
    // Doors of this RoomCell
    protected Door[] doors;
    // Building of this RoomCell
    protected Building building;

    /**
     * Builds a RoomCell
     * @param name name of this RoomCell
     * @param doors doors of this RoomCell
     * @param building building of this RoomCell
     */
    public RoomCell(String name, Door[] doors, Building building) {
        this.name = name;
        this.doors = doors;
        this.building = building;
    }

    /**
     * Returns this Cell's name
     * @return Cell's name
     */
    @Override
    public String getName() {
        return this.name;
    }
}
