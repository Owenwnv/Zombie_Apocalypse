package zombieapocalypse.cell;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PharmacyRoomCellTest {

    @Test
    public void testPharmacyRoomCellName() {
        PharmacyRoomCell pharmacyRoomCell = new PharmacyRoomCell();
        assertEquals("Pharmacy", pharmacyRoomCell.getName());
    }

}
