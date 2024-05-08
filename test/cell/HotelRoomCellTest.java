package test.cell;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import zombieapocalypse.cell.HotelRoomCell;

public class HotelRoomCellTest {

    @Test
    public void testHotelRoomCellName() {
        HotelRoomCell hotelRoomCell = new HotelRoomCell();
        assertEquals("Continental", hotelRoomCell.getName());
    }

}
