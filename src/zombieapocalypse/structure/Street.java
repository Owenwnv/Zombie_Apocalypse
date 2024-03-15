package zombieapocalypse.structure;

/* Class representing a street */
public class Street {
    /** name of this Street */
    private String name;
    /** whether this Street is one of the mainroads */
    private boolean isMainroads;

    /**
     * Builds a Street
     * 
     * @param name        name of this Street
     * @param isMainroads if this Street is one of the mainroads
     */
    public Street(String name, boolean isMainroads) {
        this.name = name;
        this.isMainroads = isMainroads;
    }

    /**
     * Returns whether this Street is one of the mainroads
     * 
     * @return true if street is one of the mainroads, false otherwise
     */
    public boolean getIsMainroads() {
        return this.isMainroads;
    }

    /**
     * Sets the boolean value indicating whether this road is a main road or not.
     * 
     * @param isMainroads true if it is a main road, false otherwise
     */
    public void setIsMainroads(boolean isMainroads) {
        this.isMainroads = isMainroads;
    }

    /**
     * Returns this Street's name
     * 
     * @return Street's name
     */
    public String getName() {
        return this.name;
    }
}
