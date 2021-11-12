package edu.kingsu.SoftwareEngineering.Chess.Tests.Model;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public class TestPlayer {

    PlayerHuman testPlayerWhite;
    PlayerAI testPlayerBlack;
    ChessAIThread testThread;

    @Before
    public void setUp() {
        // PlayerHuman/AI is used to let us parts of the abstract parent, Player
        testPlayerWhite = new PlayerHuman(null, true, 123, 456);
        testPlayerBlack = new PlayerAI(null, false, 124, 457, 2);
        testThread = new ChessAIThread() {
            public Move calculateMove() {
                return new Move(1,2,3,4);
            }
        };
    }

    @After
    public void tearDown() {
        testPlayerWhite = null;
        testPlayerBlack = null;
        testThread = null;
    }

    @Ignore
    @Test
    public void testPlayer() {
        // tested by subclasses
    }

    @Test
    public void testGetChessGame() {
        assertNull("Player instantiated without a chess game returned a chess game.", testPlayerWhite.getChessGame());
    }

    @Test
    public void testGetAIThread() {
        testPlayerWhite.setAIThread(testThread);
        assertSame("Player did not return correct ChessAIThread after setting.", testThread, testPlayerWhite.getAIThread());
    }

    @Test
    public void testSetAIThread() {
        testPlayerWhite.setAIThread(testThread);
        assertSame("Player did not return correct ChessAIThread after setting.", testThread, testPlayerWhite.getAIThread());
        testPlayerWhite.setAIThread(null);
        assertNull("SetAIThread did not set the player's ChessAIThread instance to null.", testPlayerWhite.getAIThread());
    }

    @Test
    public void testIsWhite() {
        assertTrue("White Player returns boolean indicating black.", testPlayerWhite.isWhite());
        assertFalse("Black Player returns boolean indicating white.", testPlayerBlack.isWhite());
    }

    @Test
    public void testIsHuman() {
        assertTrue("Human Player returns boolean indicating it is AI.", testPlayerWhite.isHuman());
        assertFalse("AI Player returns boolean indicating it is human.", testPlayerBlack.isHuman());
    }

    @Ignore
    @Test
    public void testRun() {
        // tested by ChessGame
    }

    @Ignore
    @Test
    public void testResumeTimer() {
    }

    @Ignore
    @Test
    public void testResetTimer() {
    }

    @Test
    public void testGetAIDepth() {
        assertEquals("Human player returned incorrect AI depth", Player.MAX_AI_DEPTH, testPlayerWhite.getAIDepth());
        assertEquals("AI player returned incorrect AI depth", 2, testPlayerBlack.getAIDepth());
    }

    @Test
    public void testSetAIDepth() {
        testPlayerWhite.setAIDepth(3);
        assertEquals("Human player returned incorrect AI depth after setting", 3, testPlayerWhite.getAIDepth());
        testPlayerBlack.setAIDepth(5);
        assertEquals("AI player returned incorrect AI depth after setting", 5, testPlayerBlack.getAIDepth());
    }

}
