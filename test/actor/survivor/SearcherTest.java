package test.actor.survivor;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.actor.survivor.Searcher;

public class SearcherTest {

    @Test
    public void initSearcher() {
        Searcher searcher = new Searcher();
        assertEquals(5, searcher.getHealthPoints());
        assertEquals(1, searcher.getLevel());
    }
}