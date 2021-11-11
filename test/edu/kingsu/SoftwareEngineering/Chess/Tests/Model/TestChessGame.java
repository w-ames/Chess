package edu.kingsu.SoftwareEngineering.Chess.Tests.Model;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestChessGame {

    private ChessGame testGame;

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
        //
    }

    @Test
    public void testGetBoardChars() {
        //
    }

    @Test
    public void testGetPlayerInterval() {
        //
    }

    @Test
    public void testGetPlayerIncrement() {
        //
    }

    @Test
    public void testTagPairMap() {
    }

    @Test
    public void testGetBoard() {
        //
    }

    @Test
    public void testGetPlayerTurn() {
        //
    }

    @Test
    public void testGetMoveHistory() {
        //
    }

    @Test
    public void testGetAlgebraicHistory() {
    }

    @Test
    public void testGetState() {
    }

    @Test
    public void testRegisterView() {
        //
    }

    @Test
    public void testNotifyViews() {
        //
    }

    @Test
    public void testPerformMove() {
        testGame.start();
        assertFalse("Black was able to move first.", testGame.performMove(new PawnDoubleMove(1, 4, 3, 4), false));
        assertFalse("Human was able to move during AI pieces.", testGame.performMove(new PawnDoubleMove(1, 4, 3, 4), true));
        assertTrue("White was unable to move during its turn.", testGame.performMove(new PawnDoubleMove(6, 4, 4, 4), true));
    }

    @Test
    public void testValidateMove() {
        //
    }

    @Test
    public void testStart() {
        //
    }

    @Test
    public void testStop() {
    }

    @Test
    public void testGameOver() {
    }

    @Test
    public void testSetInterval() {
    }

    @Test
    public void testUndo() {
    }

    @Test
    public void testRedo() {
    }

    @Test
    public void testResetTimers() {
    }
}
