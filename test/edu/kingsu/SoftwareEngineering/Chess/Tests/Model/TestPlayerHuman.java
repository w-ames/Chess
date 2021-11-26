package edu.kingsu.SoftwareEngineering.Chess.Tests.Model;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

public class TestPlayerHuman {

    PlayerHuman testPlayer;

    @Before
    public void setUp() {
        testPlayer = new PlayerHuman(null, true, -1);
    }

    @After
    public void tearDown() {
        testPlayer = null;
    }
    
    @Test
    public void testPlayerHuman() {
        assertNotNull("New Human player is null.", testPlayer);
    }

    @Ignore
    @Test
    public void testRun() {
        // tested by chessgame
    }

}
