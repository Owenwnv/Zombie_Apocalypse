package zombieapocalypse.cell;

/* Class representing an empty cell, implements the Cell interface */
public class EmptyCell extends Cell {

    /**
     * Builds an EmptyCell
     */
    public EmptyCell() {
        super("Empty");
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