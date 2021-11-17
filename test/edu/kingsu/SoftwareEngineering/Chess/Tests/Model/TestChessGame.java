package edu.kingsu.SoftwareEngineering.Chess.Tests.Model;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;

import edu.kingsu.SoftwareEngineering.Chess.GUI.ChessGameView;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestChessGame {

    private ChessGame testGame;

    class TestView extends ChessGameView {
        private boolean updatedFlag = false;
        public void update() { updatedFlag = true; }
        public void addListeners() { }
        public boolean flag() { return updatedFlag; }
    }

    @Before
    public void setUp() {
        testGame = new ChessGame(-1, 0, -1, -1);
    }

    @After
    public void tearDown() {
        testGame.stop();
        testGame = null;
    }

    @Test
    public void testChessGame() {
        assertNotNull("ChessGame constructor returned null.", testGame);
    }

    @Test
    public void testInitialize() {
        testGame.initialize(-1, 0, 1, 2);
        assertTrue("Game initializer did not set white to human.", testGame.getPlayerTurn().isHuman());
        assertEquals("Game initializer did not set the player interval.", 1, testGame.getPlayerInterval());
        assertEquals("Game initializer did not set the player increment.", 2, testGame.getPlayerIncrement());
        testGame.initialize(0, -1, 3, 4);
        assertFalse("Game initializer did not set white to AI.", testGame.getPlayerTurn().isHuman());
        assertEquals("Game initializer did not set the player interval.", 3, testGame.getPlayerInterval());
        assertEquals("Game initializer did not set the player increment.", 4, testGame.getPlayerIncrement());
    }

    @Test
    public void testGetBoardChars() {
        char[][] chars = testGame.getBoardChars();
        assertNotNull("Null returned for game board chars.", chars);
        assertEquals("Game board chars not correct length.", Board.ROWS, chars.length);
        assertEquals("Game board chars not correct length.", Board.COLS, chars[0].length);
        char[][] crossReferenceBoard = (new Board()).getChars();
        for (int i=0; i<chars.length; i++) {
            assertArrayEquals("Incorrect board row found in new game.", crossReferenceBoard[i], chars[i]);
        }
    }

    @Test
    public void testGetPlayerInterval() {
        ChessGame cg = new ChessGame(-1, -1, 1234, 5678);
        assertEquals("Retrieved incorrect player interval value.", 1234, cg.getPlayerInterval());
    }

    @Test
    public void testGetPlayerIncrement() {
        ChessGame cg = new ChessGame(-1, -1, 1234, 5678);
        assertEquals("Retrieved incorrect player increment value.", 5678, cg.getPlayerIncrement());
    }

    @Ignore
    @Test
    public void testTagPairMap() {
    }

    @Test
    public void testGetBoard() {
        Board b = testGame.getBoard();
        char[][] chars = b.getChars();
        assertNotNull("New game board is retrieved as null.", b);
        char[][] crossReferenceBoard = (new Board()).getChars();
        for (int i=0; i<chars.length; i++) {
            assertArrayEquals("Incorrect board row found in new game.", crossReferenceBoard[i], chars[i]);
        }
    }

    @Test
    public void testGetPlayerTurn() {
        Player p1 = testGame.getPlayerTurn();
        assertTrue("Black player given as having the first turn.", p1.isWhite());
        testGame.performMove(new Move(6,4,5,4), true);
        assertFalse("White player given as having the second turn.", testGame.getPlayerTurn().isWhite());
        assertNotSame("Same Player is given 2 turns.", testGame.getPlayerTurn());
    }

    @Test
    public void testGetMoveHistory() {
        List<Move> history;
        history = testGame.getMoveHistory();
        assertNotNull("Move history of new game is null.", history);
        assertTrue("Move history of new game is not empty.", history.isEmpty());
        Move m = new Move(6,4,5,4);
        testGame.performMove(m, true);
        history = testGame.getMoveHistory();
        assertNotNull("Move history of game is null after move made.", history);
        assertFalse("Move history of game is empty after move made.", history.isEmpty());
        assertSame("Move made not found in move history.", m, history.get(0));
    }

    @Ignore
    @Test
    public void testGetAlgebraicHistory() {
    }

    @Ignore
    @Test
    public void testGetState() {
    }

    @Ignore
    @Test
    public void testRegisterView() {
        // the effect of this is not easily observable,
        // testNotifyViews() will ensure this works
    }

    @Test
    public void testNotifyViews() {
        TestView testView = new TestView();
        testGame.registerView(testView);
        testGame.notifyViews();
        assertTrue("Registered view not updated.", testView.flag());
    }

    @Test
    public void testPerformMove() {
        // testGame.start();
        assertFalse("Black was able to move first.", testGame.performMove(new PawnDoubleMove(1, 4, 3, 4), false));
        assertFalse("Human was able to move during AI pieces.", testGame.performMove(new PawnDoubleMove(1, 4, 3, 4), true));
        assertTrue("White was unable to move during its turn.", testGame.performMove(new PawnDoubleMove(6, 4, 4, 4), true));
    }

    @Test
    public void testStart() {
        ChessGame startGameTest = new ChessGame(0, -1, -1, -1);
        startGameTest.start();
        waitForAIMove(startGameTest, true);
        assertTrue("Unable to make move during black human turn.", startGameTest.performMove(new PawnDoubleMove(1,4,3,4), true));
        waitForAIMove(startGameTest, true);
        startGameTest.stop();
    }

    private void waitForAIMove(ChessGame cg, boolean whiteAI) {
        int maxAIWait = 30;
        while(cg.getPlayerTurn().isWhite() == whiteAI) {
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e){
                fail("Interrupted while waiting for AI move.");
            }
            maxAIWait--;
            if (maxAIWait <= 0) {
                // Technically if we were just running really slow this could
                // fail, but it shouldn't under normal circumstances
                fail("Timed out waiting for an AI move");
            }
        }
    }

    @Test
    public void testStop() {
        ChessGame stopGameTest = new ChessGame(0, -1, -1, -1);
        stopGameTest.start();
        waitForAIMove(stopGameTest, true);
        stopGameTest.stop();
        assertTrue("Unable to make move during black human turn.", stopGameTest.performMove(new PawnDoubleMove(1,4,3,4), true));
        // Smoke test-esque wait to hopefully catch if the AI is still making
        // moves. Technically the AI might not be stopped but for some reason
        // be running very slow, but this shouldn't happen under normal
        // circumstances
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e){
            fail("Interrupted while waiting for AI move.");
        }
        assertFalse("AI made move after game stopped.", stopGameTest.getPlayerTurn().isHuman());
    }

    @Ignore
    @Test
    public void testGameOver() {
    }

    @Ignore
    @Test
    public void testSetInterval() {
    }

    @Test
    public void testUndo() {
        ChessGame cg = new ChessGame(-1, -1, -1, -1);
        cg.start();
        cg.performMove(6,4,4,4,true); // e4
        assertTrue("Unable to undo first move.", cg.undo());
        cg.performMove(6,4,4,4,true); // e4
        cg.performMove(1,4,3,4,true); // e5
        cg.performMove(6,3,4,3,true); // d4
        cg.performMove(1,3,3,3,true); // d5
        assertTrue("Unable to undo 4/4 move (black).", cg.undo());
        assertTrue("Unable to undo 3/4 move (white).", cg.undo());
        assertTrue("Unable to undo 2/4 move (black).", cg.undo());
        assertTrue("Unable to undo 1/4 move (white).", cg.undo());
        cg.stop();
    }

    @Test
    public void testRedo() {
        ChessGame cg = new ChessGame(-1, -1, -1, -1);
        cg.start();
        cg.performMove(6,4,4,4,true); // e4
        assertTrue("Unable to undo first move.", cg.undo());
        assertTrue("Unable to redo first move.", cg.redo());
        cg.performMove(6,4,4,4,true); // e4
        cg.performMove(1,4,3,4,true); // e5
        cg.performMove(6,3,4,3,true); // d4
        cg.performMove(1,3,3,3,true); // d5

        assertTrue("Unable to undo 4/4 move (black).", cg.undo());
        assertTrue("Unable to undo 3/4 move (white).", cg.undo());
        assertTrue("Unable to undo 2/4 move (black).", cg.undo());
        assertTrue("Unable to undo 1/4 move (white).", cg.undo());
        assertTrue("Unable to redo 1/4 move (white).", cg.redo());
        assertTrue("Unable to redo 2/4 move (black).", cg.redo());
        assertTrue("Unable to redo 3/4 move (white).", cg.redo());
        assertTrue("Unable to redo 4/4 move (black).", cg.redo());

        assertTrue("Unable to undo 4/4 move (black).", cg.undo());
        assertTrue("Unable to undo 3/4 move (white).", cg.undo());
        assertTrue("Unable to undo 2/4 move (black).", cg.undo());
        assertTrue("Unable to undo 1/4 move (white).", cg.undo());
        cg.performMove(6,4,4,4,true); // e4
        assertTrue("Unable to redo 2/4 move (black) after manual redo.", cg.redo());
        assertTrue("Unable to redo 3/4 move (white) after manual redo.", cg.redo());
        assertTrue("Unable to redo 4/4 move (black) after manual redo.", cg.redo());
        cg.stop();
    }

    @Ignore
    @Test
    public void testResetTimers() {
    }
}
