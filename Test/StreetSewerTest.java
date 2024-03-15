package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import zombieapocalypse.cell.StreetCell;

public class StreetSewerTest {

    @Test
    public void testStreetCellHasSewer() {
        StreetCell streetCell = new StreetCell("Main Street", null);
        assertFalse(streetCell.getHasSewer());
    }

    @Test
    public void testSetHasSewer() {
        StreetCell streetCell = new StreetCell("Main Street", null);
        streetCell.setHasSewer(true);
        assertTrue(streetCell.getHasSewer());
    }
}
