package zombieapocalypse;

/* Class representing an empty cell, implements the Cell interface */
public class EmptyCell implements Cell {
    /** name of this EmptyCell */
    private String name;

    /**
     * Builds an EmptyCell
     */
    public EmptyCell() {
        this.name = "Empty";
    }

    /**
     * Returns this EmptyCell's name
     * 
     * @return EmptyCell's name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns a description of this EmptyCell
     * 
     * @return description of this EmptyCell
     */
    @Override
    public String toString() {
        return "This is a EmptyCell";
    }
}