package zombieapocalypse;

public class Building {
    private String name;
    private RoomCell[] rooms;
    private boolean isSpecial;

    public Building(String name, RoomCell[] rooms, boolean isSpecial) {
        this.name = name;
        this.isSpecial = isSpecial;
        this.rooms = rooms;
    }

    public RoomCell[] getRooms() {
        return this.rooms;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsSpecial() {
        return this.isSpecial;
    }
}
