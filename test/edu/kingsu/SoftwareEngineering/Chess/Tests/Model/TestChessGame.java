package edu.kingsu.SoftwareEngineering.Chess.Tests.Model;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kingsu.SoftwareEngineering.Chess.GUI.ChessGameView;
import edu.kingsu.SoftwareEngineering.Chess.GUI.ClockView;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;
import edu.kingsu.SoftwareEngineering.Chess.PGN.PGNFile;

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

    @Test
    public void testTagPairMap() {
        Map<String,String> tags = testGame.tagPairMap();
        assertNotNull("Tag pair map is null.", tags);
        tags.put("Event", "test event");
        assertEquals("Incorrect tag pair map value returned after setting", "test event", testGame.tagPairMap().get("Event"));
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
    public void testForceSetPlayerTurn() {
        Player p1 = testGame.getPlayerTurn();
        testGame.forceSetPlayerTurn(false);
        Player p2 = testGame.getPlayerTurn();
        assertNotSame("New player side not given the current turn.", p1, p2);
        assertFalse("Black side not given the current turn.", p2.isWhite());
        testGame.forceSetPlayerTurn(true);
        Player p3 = testGame.getPlayerTurn();
        assertNotSame("New player side not given the current turn.", p2, p3);
        assertTrue("White side not given the current turn.", p3.isWhite());
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

    @Test
    public void testGetAlgebraicHistory() {
        List<String> algHistory;
        algHistory = testGame.getAlgebraicHistory();
        assertNotNull("Move algebraic history of new game is null.", algHistory);
        assertTrue("Move algebraic history of new game is not empty.", algHistory.isEmpty());
        Move m = new Move(6,4,5,4);
        testGame.performMove(m, true);
        algHistory = testGame.getAlgebraicHistory();
        assertNotNull("Move algebraic history of game is null after move made.", algHistory);
        assertFalse("Move algebraic history of game is empty after move made.", algHistory.isEmpty());
        assertEquals("Move made not found in move algebraic history.", "e3", algHistory.get(0));
    }

    @Test
    public void testGetState() {
        ChessGame cg = new ChessGame(-1,-1,-1,-1);
        cg.getBoard().initializeBoard(new char[][] {
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ','K',' ',' ',' '},
            {' ',' ',' ',' ','Q',' ',' ',' '},
            {' ',' ',' ',' ','k',' ',' ',' '}
        });
        cg.updateState();
        assertEquals("Game in black checkmate did not return correct enum.", GameState.BLACK_CHECKMATE, cg.getState());
        cg.getBoard().initializeBoard(new char[][] {
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ','K',' ','Q',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ','k',' ',' ',' '}
        });
        cg.updateState();
        assertEquals("Game in black check did not return correct enum.", GameState.BLACK_CHECK, cg.getState());
        cg.getBoard().initializeBoard(new char[][] {
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ','K','Q',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ','k',' ',' ',' '}
        });
        cg.updateState();
        assertEquals("Game in stalemate (white has no moves) did not return correct enum.", GameState.STALEMATE_NOMOVES, cg.getState());
        cg.getBoard().initializeBoard(new char[][] {
            {' ',' ',' ',' ','K',' ',' ',' '},
            {' ',' ',' ',' ','q',' ',' ',' '},
            {' ',' ',' ',' ','k',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '}
        });
        cg.forceSetPlayerTurn(false);
        cg.updateState();
        assertEquals("Game in white checkmate did not return correct enum.", GameState.WHITE_CHECKMATE, cg.getState());
        cg.getBoard().initializeBoard(new char[][] {
            {' ',' ',' ',' ','K',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ','k',' ','q',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '}
        });
        cg.forceSetPlayerTurn(false);
        cg.updateState();
        assertEquals("Game in white check did not return correct enum.", GameState.WHITE_CHECK, cg.getState());
        cg.getBoard().initializeBoard(new char[][] {
            {' ',' ',' ',' ','K',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ','k','q',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '}
        });
        cg.forceSetPlayerTurn(false);
        cg.updateState();
        assertEquals("Game in stalemate (black has no moves) did not return correct enum.", GameState.STALEMATE_NOMOVES, cg.getState());

        cg.getBoard().initializeBoard(new char[][] {
            {' ',' ',' ',' ','K',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ','b',' '},
            {' ',' ',' ',' ','k',' ','B',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '}
        });
        cg.forceSetPlayerTurn(true);
        cg.updateState();
        assertEquals("Game in stalemate (insufficient material) did not return correct enum.", GameState.STALEMATE_NOMATERIAL, cg.getState());
    }

    @Test
    public void testUpdateState() {
        ChessGame cg = new ChessGame(-1,-1,-1,-1);
        cg.getBoard().initializeBoard(new char[][] {
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ','K',' ',' ',' '},
            {' ',' ',' ',' ','Q',' ',' ',' '},
            {' ',' ',' ',' ','k',' ',' ',' '}
        });
        assertEquals("Incorrect initial board state given.", GameState.ACTIVE, cg.getState());
        cg.updateState();
        assertEquals("updateState did not change game state.", GameState.BLACK_CHECKMATE, cg.getState());
    }

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

    @Test
    public void testGameOver() {
        ChessGame cg = new ChessGame(-1, -1, -1, -1);
        cg.start();
        try {
            cg.gameOver();
        } catch (Exception e) {
            fail("Could not stop chess game with gameOver");
        }
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

    @Test
    public void testResetTimers() {
        ChessGame cg = new ChessGame(-1, -1, -1, -1);
        cg.start();
        cg.stop();
        try {
            cg.resetTimers();
        } catch (Exception e) {
            fail("Could not reset player's timers");
        }
    }

    @Test
    public void testGetMoveHighlights() {
        char[][] pawnTestExpected = {
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ','d',' ',' ',' '},
            {' ',' ',' ',' ','t',' ',' ',' '},
            {' ',' ',' ',' ','f',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '}
        };
        char[][] pawnTestResult = testGame.getMoveHighlights(6, 4);
        assertHighlights(pawnTestExpected, pawnTestResult);
        char[][] knightTestExpected = {
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {'t',' ','t',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ','f',' ',' ',' ',' ',' ',' '}
        };
        char[][] knightTestResult = testGame.getMoveHighlights(7, 1);
        assertHighlights(knightTestExpected, knightTestResult);
    }

    @Test(timeout = 3000)
    public void testGetHumanHint() {
        // TODO this sometimes fails...
        ChessGame cg = new ChessGame(-1, -1 , -1, -1);
        cg.start();
        cg.performMove(6, 4, 4, 4, true);
        cg.performMove(1, 4, 3, 4, true);
        cg.performMove(6, 6, 5, 6, true);
        cg.performMove(0, 3, 4, 7, true);
        char[][] queenTakeExpected = {
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ','x'},
            {' ',' ',' ',' ',' ',' ','f',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '}
        };
        char[][] queenTakeResult = cg.getHumanHint();
        assertNotNull("Hint function returned null.", queenTakeResult);
        assertHighlights(queenTakeExpected, queenTakeResult);
        cg.stop();
    }

    private void assertHighlights(char[][] expected, char[][] given) {
        assertEquals("2 dimensional character array has an incorrect number of rows.", expected.length, given.length);
        for (int i=0; i<expected.length; i++) {
            assertArrayEquals("Row "+i+" of 2 dimensional character array does not return the correct character array", expected[i], given[i]);
        }
    }

    @Test
    public void testCheckPawnPromotion() {
        ChessGame cg = new ChessGame(-1,-1,-1,-1);
        cg.getBoard().initializeBoard(new char[][] {
            {' ',' ',' ',' ','K',' ',' ',' '},
            {'p',' ',' ',' ',' ',' ',' ','P'},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {'p',' ',' ',' ',' ',' ',' ','P'},
            {' ',' ',' ',' ','k',' ',' ',' '}
        });
        assertTrue("White pawn did not return correct pawn promotion validity.", cg.checkPawnPromotion(1, 0, 0, 0));
        assertFalse("White pawn did not return correct pawn promotion validity.", cg.checkPawnPromotion(6, 0, 5, 0));
        assertTrue("Black pawn did not return correct pawn promotion validity.", cg.checkPawnPromotion(6, 7, 7, 7));
        assertFalse("Black pawn did not return correct pawn promotion validity.", cg.checkPawnPromotion(1, 7, 2, 7));
    }

    @Test
    public void testLatestMoveIndex() {
        ChessGame cg = new ChessGame(-1,-1,-1,-1);
        assertEquals("Incorrect latest move index given at game start.", -1, cg.latestMoveIndex());
        cg.performMove(6, 4, 4, 4, true);
        assertEquals("Incorrect latest move index given at first move.", 0, cg.latestMoveIndex());
        cg.undo();
        cg.stop();
        assertEquals("Incorrect latest move index given after undo.", -1, cg.latestMoveIndex());
        cg.redo();
        cg.stop();
        assertEquals("Incorrect latest move index given after redo.", 0, cg.latestMoveIndex());
    }

    @Test
    public void testResign() {
        ChessGame cg;
        cg = new ChessGame(-1,-1,-1,-1);
        cg.resign();
        assertEquals("Incorrect game state given.", GameState.WHITE_RESIGN, cg.getState());
        cg = new ChessGame(-1,-1,-1,-1);
        cg.forceSetPlayerTurn(false);
        cg.resign();
        assertEquals("Incorrect game state given.", GameState.BLACK_RESIGN, cg.getState());
        cg = new ChessGame(0,0,-1,-1);
        cg.resign();
        assertEquals("Incorrect game state given.", GameState.ACTIVE, cg.getState());
    }

    @Test
    public void testGetPGNFile() {
        PGNFile f = testGame.getPGNFile();
        assertNotNull("File tag pairs is null.", f.getTagPairMap());
        assertTrue("File does not contain Event tag pair.", f.getTagPairMap().containsKey("Event"));
        assertTrue("File does not contain Site tag pair.", f.getTagPairMap().containsKey("Site"));
        assertTrue("File does not contain Date tag pair.", f.getTagPairMap().containsKey("Date"));
        assertTrue("File does not contain Round tag pair.", f.getTagPairMap().containsKey("Round"));
        assertTrue("File does not contain White tag pair.", f.getTagPairMap().containsKey("White"));
        assertTrue("File does not contain Black tag pair.", f.getTagPairMap().containsKey("Black"));
        assertTrue("File does not contain Result tag pair.", f.getTagPairMap().containsKey("Result"));
        assertEquals("File has incorrect result.", "*", f.getResult());
        assertNotNull("File move list is null.", f.getMoveTextList());
        assertEquals("Algebraic move histories are not of the same length.", testGame.getAlgebraicHistory().size(), f.getMoveTextList().size());
        for (int i=0; i<testGame.getAlgebraicHistory().size(); i++) {
            assertEquals("File does not have same algebraic move history object.", testGame.getAlgebraicHistory().get(i), f.getMoveTextList().get(i));
        }
    }

    @Test
    public void testLoadPGNFile() {
        Map<String,String> tags = new HashMap<String,String>();
        tags.put("Event", "TEST EVENT");
        tags.put("Site", "TEST SITE");
        tags.put("Date", "TEST DATE");
        tags.put("Round", "TEST ROUND");
        tags.put("White", "TEST WHITE");
        tags.put("Black", "TEST BLACK");
        tags.put("Result", "*");
        List<String> moves = new ArrayList<String>();
        moves.add("e4");
        moves.add("e5");
        moves.add("d4");
        moves.add("d5");
        testGame.loadPGNFile(new PGNFile(tags, moves, "*"));
        testGame.stop();
        assertHighlights(new char[][] {
            {'R','N','B','Q','K','B','N','R'},
            {'P','P','P',' ',' ','P','P','P'},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ','P','P',' ',' ',' '},
            {' ',' ',' ','p','p',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {'p','p','p',' ',' ','p','p','p'},
            {'r','n','b','q','k','b','n','r'}
        }, testGame.getBoardChars());
    }

    @Test
    public void testRematch() {
        Map<String,String> tags = new HashMap<String,String>();
        tags.put("Event", "TEST EVENT");
        tags.put("Site", "TEST SITE");
        tags.put("Date", "TEST DATE");
        tags.put("Round", "TEST ROUND");
        tags.put("White", "TEST WHITE");
        tags.put("Black", "TEST BLACK");
        tags.put("Result", "*");
        List<String> moves = new ArrayList<String>();
        moves.add("e4");
        moves.add("e5");
        moves.add("d4");
        moves.add("d5");
        testGame.loadPGNFile(new PGNFile(tags, moves, "*"));
        testGame.stop();
        assertHighlights(new char[][] {
            {'R','N','B','Q','K','B','N','R'},
            {'P','P','P',' ',' ','P','P','P'},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ','P','P',' ',' ',' '},
            {' ',' ',' ','p','p',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {'p','p','p',' ',' ','p','p','p'},
            {'r','n','b','q','k','b','n','r'}
        }, testGame.getBoardChars());
        testGame.rematch();
        testGame.stop();
        assertHighlights(new char[][] {
            {'R','N','B','Q','K','B','N','R'},
            {'P','P','P','P','P','P','P','P'},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {'p','p','p','p','p','p','p','p'},
            {'r','n','b','q','k','b','n','r'}
        }, testGame.getBoardChars());
    }

    @Test
    public void testRegisterPlayerClock() {
        ChessGame cg;
        cg = new ChessGame(-1,-1,10,10);
        TestClockView v = new TestClockView();
        String originalS = v.getS();
        cg.registerPlayerClock(v, true);
        cg.getPlayerTurn().incrementTimer();
        assertNotEquals("Registered clock does not respond to increment.", v.getS(), originalS);
    }

    @Test
    public void testRegisterTotalGameTimeClock() {
        TestClockView v = new TestClockView();
        try {
            testGame.registerTotalGameTimeClock(v);
        } catch (Exception e) {
            fail("Unable to register total game time clock.");
        }
    }

    @Test
    public void testTimeOutGame() {
        ChessGame cg;
        cg = new ChessGame(-1,-1,10,10);
        cg.timeOutGame(cg.getPlayerTurn());
        assertEquals("Incorrect game state given.", GameState.WHITE_TIMEOUT, cg.getState());
        cg = new ChessGame(-1,-1,10,10);
        cg.forceSetPlayerTurn(false);
        cg.timeOutGame(cg.getPlayerTurn());
        assertEquals("Incorrect game state given.", GameState.BLACK_TIMEOUT, cg.getState());
    }

    @Test
    public void testRemoveClocks() {
        ChessGame cg;
        cg = new ChessGame(-1,-1,10,10);
        TestClockView v = new TestClockView();
        String originalS = v.getS();
        cg.registerPlayerClock(v, true);
        cg.getPlayerTurn().incrementTimer();
        assertNotEquals("Registered clock does not respond to increment.", v.getS(), originalS);
        cg.registerTotalGameTimeClock(new ClockView());
        try {
            cg.removeClocks();
        } catch(Exception e) {
            fail("Could not remove clocks");
        }
        String unchangingS = v.getS();
        cg.getPlayerTurn().incrementTimer();
        assertEquals("Player clock updated after clocks removed.", unchangingS, v.getS());
    }

    @Test
    public void testChangePlayerTypes() {
        ChessGame cg = new ChessGame(-1,-1,-1,-1);
        cg.start();
        cg.forceSetPlayerTurn(true);
        assertTrue("White player is not initially human.", cg.getPlayerTurn().isHuman());
        cg.forceSetPlayerTurn(false);
        assertTrue("Black player is not initially human.", cg.getPlayerTurn().isHuman());

        cg.changePlayerTypes(0, 0);
        cg.stop();

        cg.forceSetPlayerTurn(true);
        assertFalse("White player is not set to AI.", cg.getPlayerTurn().isHuman());
        cg.forceSetPlayerTurn(false);
        assertFalse("Black player is not set to AI.", cg.getPlayerTurn().isHuman());
        cg.changePlayerTypes(-1, 2);
        cg.stop();

        cg.forceSetPlayerTurn(true);
        assertEquals("Incorrect white AI depth.", Player.MAX_AI_DEPTH, cg.getPlayerTurn().getAIDepth());
        cg.forceSetPlayerTurn(false);
        assertEquals("Incorrect black AI depth.", 2, cg.getPlayerTurn().getAIDepth());

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
