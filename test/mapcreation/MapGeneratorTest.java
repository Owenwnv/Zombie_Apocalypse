package test.mapcreation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import zombieapocalypse.mapcreation.MapGenerator;

public class MapGeneratorTest {
    
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
