package zombieapocalypse.structure;

/* Class representing a street */
public class Street {
    /** name of this Street */
    private String name;
    /** whether this building has sewers */
    private boolean hasSewer;

    /**
     * Builds a Street
     * 
     * @param name     name of this Street
     * @param hasSewer if this Street has sewers
     */
    public Street(String name, boolean hasSewer) {
        this.name = name;
        this.hasSewer = hasSewer;
    }

    /**
     * Returns whether this Street has sewers or not
     * 
     * @return true if street has sewers, false otherwise
     */
    public boolean getHasSewer() {
        return this.hasSewer;
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
