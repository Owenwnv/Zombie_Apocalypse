package Test;

import static org.junit.Assert.*;
import org.junit.Test;


import zombieapocalypse.actor.survivor.Fighter;
import zombieapocalypse.actor.survivor.Healer;
import zombieapocalypse.actor.survivor.Lucky;
import zombieapocalypse.actor.survivor.Searcher;
import zombieapocalypse.actor.zombie.Abomination;
import zombieapocalypse.actor.zombie.Bigboy;
import zombieapocalypse.actor.zombie.Runner;
import zombieapocalypse.actor.zombie.Walker;
import zombieapocalypse.item.*;
import zombieapocalypse.item.tool.HandheldMap;
import zombieapocalypse.item.tool.HealthPotion;

public class ActorTest {

    @Test
    public void FighterTest() {
        Fighter fighter = new Fighter();
        assertEquals(5, fighter.getHealthPoints());
        assertEquals(1, fighter.getLevel());
        assertTrue(fighter.getBackpack().isEmpty());
        assertNull(fighter.getInHand());
    }

    @Test
    public void HealerTest() {
        Healer healer = new Healer();
        assertEquals(5, healer.getHealthPoints());
        assertEquals(1, healer.getLevel());
        assertTrue(healer.getBackpack().isEmpty());
        assertNull(healer.getInHand());
    }

    @Test
    public void LuckyTest() {
        Lucky lucky = new Lucky();
        assertEquals(5, lucky.getHealthPoints());
        assertEquals(1, lucky.getLevel());
        assertTrue(lucky.getBackpack().isEmpty());
        assertNull(lucky.getInHand());
    }

    @Test
    public void SearcherTest() {
        Searcher searcher = new Searcher();
        assertEquals(5, searcher.getHealthPoints());
        assertEquals(1, searcher.getLevel());
        assertTrue(searcher.getBackpack().isEmpty());
        assertNull(searcher.getInHand());
    }

    @Test
    public void AbominationTest() {
        Abomination abomination = new Abomination();
        assertEquals(3, abomination.getDamage());
        assertEquals(6, abomination.getHealthPoints());
    }

    @Test
    public void BigboyTest() {
        Bigboy bigboy = new Bigboy();
        assertEquals(2, bigboy.getDamage());
        assertEquals(4, bigboy.getHealthPoints());
    }

    @Test
    public void RunnerTest() {
        Runner runner = new Runner();
        assertEquals(1, runner.getDamage());
        assertEquals(2, runner.getHealthPoints());
    }

    @Test
    public void WalkerTest() {
        Walker walker = new Walker();
        assertEquals(1, walker.getDamage());
        assertEquals(1, walker.getHealthPoints());
    }

    @Test
    public void AddItemToSurvivorBackpackTest() {
        Fighter fighter = new Fighter();
        Item handMap = new HandheldMap();
        fighter.addItemToBackpack(handMap);
        assertEquals(1, fighter.getBackpack().size());
        assertTrue(fighter.getBackpack().contains(handMap));
    }

    @Test
    public void PutItemInSurvivorHandTest() {
        Healer healer = new Healer();
        Item healthPotion = new HealthPotion();
        healer.putItemInHand(healthPotion);
        assertEquals(healthPotion, healer.getInHand());
    }
}
