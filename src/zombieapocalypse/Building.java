package zombieapocalypse;

/* Class representing a building */
public class Building {
    // name of this Building
    private String name;
    // rooms of this Building
    private RoomCell[] rooms;

    /**
     * Builds a Building
     * 
     * @param name      name of this Building
     * @param rooms     rooms of this Building
     */
    public Building(String name, RoomCell[] rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    /**
     * Returns this Building's rooms
     * 
     * @return Building's rooms
     */
    public RoomCell[] getRooms() {
        return this.rooms;
    }

    /**
     * Returns this Building's name
     * 
     * @return Building's name
     */
    public String getName() {
        return this.name;
    }
}
