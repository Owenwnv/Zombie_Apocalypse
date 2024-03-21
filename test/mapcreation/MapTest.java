package test.mapcreation;

import static org.junit.Assert.*;
import org.junit.Test;

import zombieapocalypse.cell.*;
import zombieapocalypse.mapcreation.*;
import zombieapocalypse.structure.Street;

public class MapTest {

    // Tests map creation, and if the cells at creation are EmptyCell
    @Test
    public void testMapCreation() {
        Map map = new Map(10, 10);
        assertNotNull(map);
        assertEquals(10, map.getWidth());
        assertEquals(10, map.getHeight());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(map.getCell(i, j) instanceof EmptyCell);
            }
        }
    }

    // Tests getWidth, getHeight, setCell, and setMainroads
    @Test
    public void testMapGettersAndSetters() {
        Map map = new Map(5, 5);
        assertEquals(5, map.getWidth());
        assertEquals(5, map.getHeight());

        map.setCell(0, 0, new PharmacyRoomCell());
        assertTrue(map.getCell(0, 0) instanceof PharmacyRoomCell);

        map.setMainroads(1, 2);
        assertEquals(1, map.getMainroads()[0]);
        assertEquals(2, map.getMainroads()[1]);
    }

    // Tests map symbols and color(obvious)
    @Test
    public void testMapSymbolAndColor() {
        Map map = new Map(5, 5);
        Cell cell = new StreetCell("street", new Street("MainStreet", false));
        assertEquals("s", map.getCellSymbol(cell));
        assertEquals("\u001B[34m", map.getCellColor(cell));
    }

    // ensures that mapgenerator is functionning correctly.
    @Test
    public void testMapGenerator() {
        MapGenerator mapGenerator = new MapGenerator(20, 20);
        assertNotNull(mapGenerator);
        Map map = mapGenerator.generateMap();
        assertNotNull(map);
        assertEquals(20, map.getWidth());
        assertEquals(20, map.getHeight());
    }

}
