package test.actor.survivor;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.actor.survivor.Survivor;
import zombieapocalypse.item.tool.HealthPotion;

public class SurvivorTest {

    @Test
    public void initSurvivor() {
        Survivor survivor = new Survivor();
        assertEquals(5, survivor.getHealthPoints());
        assertEquals(1, survivor.getLevel());
        assertEquals(0, survivor.getBackpack().size());
        assertEquals(null, survivor.getInHand());
    }

    @Test
    public void canPutItemInBackpack() {
        Survivor survivor = new Survivor();
        HealthPotion item = new HealthPotion();
        assertEquals(0, survivor.getBackpack().size());
        survivor.addItemToBackpack(item);
        assertEquals(1, survivor.getBackpack().size());
        assertEquals("Health potion", survivor.getBackpack().get(0).getName());
    }

    @Test
    public void canPutItemInHand() {
        Survivor survivor = new Survivor();
        HealthPotion item = new HealthPotion();
        assertEquals(null, survivor.getInHand());
        survivor.putItemInHand(item);
        assertEquals(item, survivor.getInHand());
        assertEquals("Health potion", survivor.getInHand().getName());
    }
}
