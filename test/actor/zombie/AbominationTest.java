package test.actor.zombie;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.actor.zombie.Abomination;
import zombieapocalypse.actor.zombie.Zombie;

public class AbominationTest {

    @Test
    public void initAbomination() {
        Abomination abomination = new Abomination();
        assertEquals(3, abomination.getDamage());
        assertEquals(6, abomination.getHealthPoints());
    }
}