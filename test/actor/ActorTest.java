package test.actor;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.actor.Actor;

public class ActorTest {

    @Test
    public void initActor() {
        Actor actor = new Actor(5);
        assertEquals(5, actor.getHealthPoints());
    }

    @Test
    public void canAlterHealthPoints() {
        Actor actor = new Actor(5);
        actor.alterHealthPoints(2);
        assertEquals(7, actor.getHealthPoints());
        actor.alterHealthPoints(-2);
        assertEquals(5, actor.getHealthPoints());
    }
}
