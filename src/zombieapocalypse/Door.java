package zombieapocalypse;

/* Class representing a door */
public class Door {
    /** whether this Door is open */
    private boolean isOpen;

    /**
     * Builds a Door
     * 
     * @param isOpen if the door is open
     */
    public Door(boolean isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * Returns whether the Door is open or not
     * 
     * @return true if door is open, false otherwise
     */
    public boolean getIsOpen() {
        return this.isOpen;
    }
}
