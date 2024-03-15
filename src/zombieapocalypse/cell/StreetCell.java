package zombieapocalypse.cell;

import zombieapocalypse.structure.Street;

/* Class representing a street cell, implements the Cell interface */
public class StreetCell implements Cell {
    /** name of this StreetCell */
    private String name;
    /** Street of this StreetCell */
    private Street street;
    private boolean hasSewer;

    /**
     * Builds a StreetCell
     * 
     * @param name   name of this StreetCell
     * @param street street of this StreetCell
     */
    public StreetCell(String name, Street street) {
        this.name = name;
        this.street = street;
        this.hasSewer = false;
    }

    /**
     * Returns this StreetCell's name
     * 
     * @return StreetCell's name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns this StreetCell's Street
     * 
     * @return StreetCell's Street
     */
    public Street getStreet() {
        return this.street;
    }

    public boolean getHasSewer() {
        return this.hasSewer;
    }

    public void setHasSewer(boolean hasSewer) {
        this.hasSewer = hasSewer;
    }

    /**
     * Returns a description of this StreetCell
     * 
     * @return description of this StreetCell
     */
    @Override
    public String toString() {
        return "This is a StreetCell";
    }

}
