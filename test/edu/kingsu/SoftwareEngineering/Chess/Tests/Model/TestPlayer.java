package edu.kingsu.SoftwareEngineering.Chess.Tests.Model;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.GUI.ClockView;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

public class TestPlayer {

    PlayerHuman testPlayerWhite;
    PlayerAI testPlayerBlack;
    ChessAIThread testThread;
    TestClockView clock;

    @Before
    public void setUp() {
        // PlayerHuman/AI is used to let us parts of the abstract parent, Player
        ChessGame cg = new ChessGame(-1, 2, 30, 10);
        testPlayerWhite = (PlayerHuman)cg.getPlayerTurn();
        cg.forceSetPlayerTurn(false);
        testPlayerBlack = (PlayerAI)cg.getPlayerTurn();
        cg.forceSetPlayerTurn(true);
        // testPlayerWhite = new PlayerHuman(null, true, 123);
        // testPlayerBlack = new PlayerAI(null, false, 124, 2);
        testThread = new ChessAIThread() {
            public Move calculateMove() {
                return new Move(1,2,3,4);
            }
        };
        clock = new TestClockView();
    }

    @After
    public void tearDown() {
        testPlayerWhite.pauseTimer();
        testPlayerWhite = null;
        testPlayerBlack.pauseTimer();
        testPlayerBlack = null;
        testThread = null;
        clock = null;
    }

    @Test
    public void testPlayer() {
        // tested by subclasses
    }

    @Test
    public void testGetChessGame() {
        assertNotNull("Player instantiated with a chess game returned null.", testPlayerWhite.getChessGame());
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

    @Test
    public void testRun() {
        // tested by ChessGame
    }

    @Test
    public void testResumeTimer() {
        clock.updatePlayerTime("");
        testPlayerWhite.registerPlayerClock(clock);
        try {
            testPlayerWhite.resumeTimer();
        } catch(Exception e){
            fail("Could not resume timer.");
        }
    }

    @Test
    public void testIncrementTimer() {
        clock.updatePlayerTime("");
        testPlayerWhite.registerPlayerClock(clock);
        testPlayerWhite.incrementTimer();
        assertEquals("Clock not incremented", "0:40", clock.getS());
    }

    @Test
    public void testResetTimer() {
        clock.updatePlayerTime("");
        testPlayerWhite.registerPlayerClock(clock);
        testPlayerWhite.resetTimer();
        assertEquals("Clock not incremented", "0:30", clock.getS());
    }

    @Test
    public void testPauseTimer() {
        testPlayerWhite.resumeTimer();
        try {
            testPlayerWhite.pauseTimer();
            testPlayerWhite.pauseTimer();
        } catch (Exception e) {
            fail("Could not pause timer.");
        }
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

    @Test
    public void registerPlayerClock() {
        testPlayerWhite.registerPlayerClock(clock);
        testPlayerWhite.incrementTimer();
    }

    @Test
    public void testRemoveClocks() {
    }

    class TestClockView extends ClockView {
        private String s = "";
        public String getS() { return this.s; }
        @Override
        public void updatePlayerTime(String time) {
            s = time;
        }

        @Override
        public void updateTotalGameTime(String time) {
            s = time;
        }
    }

}
