package test.actor.survivor;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.actor.survivor.Healer;

public class HealerTest {

    @Test
    public void initHealer() {
        Healer healer = new Healer();
        assertEquals(5, healer.getHealthPoints());
        assertEquals(1, healer.getLevel());
    }
}