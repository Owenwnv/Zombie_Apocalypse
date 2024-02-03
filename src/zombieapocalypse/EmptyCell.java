package zombieapocalypse;

/* Class representing an empty cell, implements the Cell interface */
public class EmptyCell implements Cell{
    // name of this Cell
    private String name;

    /**
     * Builds an EmptyCell
     */
    public EmptyCell() {
        this.name = "Empty";
    }

    /**
     * Returns this Cell's name
     * @return Cell's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a description of this Cell 
     * @return description of this Cell 
     */
    public String toString() {
        return "This is a EmptyCell";
    }
}