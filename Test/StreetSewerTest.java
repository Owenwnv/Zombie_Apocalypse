package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import zombieapocalypse.cell.StreetCell;

public class StreetSewerTest {

    @Test
    public void StreetCellHasSewerTest() {
        StreetCell streetCell = new StreetCell("Main Street", null);
        assertFalse(streetCell.getHasSewer());
    }

    @Test
    public void SetHasSewerTest() {
        StreetCell streetCell = new StreetCell("Main Street", null);
        streetCell.setHasSewer(true);
        assertTrue(streetCell.getHasSewer());
    }
}
