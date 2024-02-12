package zombieapocalypse;

/* Class representing a room cell, implements the Cell interface */
public class RoomCell implements Cell {
    // name of this RoomCell
    protected String name;
    // Doors of this RoomCell
    protected Door[] doors;

    /**
     * Builds a RoomCell
     * 
     * @param name  name of this RoomCell
     * @param doors doors of this RoomCell
     */
    public RoomCell(String name) {
        this.name = name;
    }

    /**
     * Returns this Cell's name
     * 
     * @return Cell's name
     */
    @Override
    public String getName() {
        return this.name;
    }
}
