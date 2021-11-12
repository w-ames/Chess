package edu.kingsu.SoftwareEngineering.Chess.Tests.Model;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

public class TestPlayerAI {

    PlayerAI testPlayer;

    @Before
    public void setUp() {
        testPlayer = new PlayerAI(null, true, -1, -1, 2);
    }

    @After
    public void tearDown() {
        testPlayer = null;
    }
    
    @Test
    public void testPlayerAI() {
        assertNotNull("New AI player is null.", testPlayer);
    }

    @Ignore
    @Test
    public void testRun() {
        // tested by chessgame
    }

}
