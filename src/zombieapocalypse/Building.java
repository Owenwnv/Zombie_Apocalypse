package zombieapocalypse;

/* Class representing a building */
public class Building {
    // name of this Building
    private String name;
    // rooms of this Building
    private RoomCell[] rooms;
    // if this Building is special
    private boolean isSpecial;

    /**
     * Builds a Building
     * 
     * @param name      name of this Building
     * @param rooms     rooms of this Building
     * @param isSpecial if this Building is special
     */
    public Building(String name, RoomCell[] rooms, boolean isSpecial) {
        this.name = name;
        this.isSpecial = isSpecial;
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

    /**
     * Returns whether this Building is special or not
     * 
     * @return true if Building is special, false otherwise
     */
    public boolean getIsSpecial() {
        return this.isSpecial;
    }
}
