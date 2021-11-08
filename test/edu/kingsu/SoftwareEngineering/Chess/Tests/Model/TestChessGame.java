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
        testGame = new ChessGame(true, false, -1, -1);
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
    public void testPerformMove() {
        testGame.start();
        assertFalse("Black was able to move first.", testGame.performMove(new PawnDoubleMove(1, 4, 3, 4), false));
        assertFalse("Human was able to move during AI pieces.", testGame.performMove(new PawnDoubleMove(1, 4, 3, 4), true));
        assertTrue("White was unable to move during its turn.", testGame.performMove(new PawnDoubleMove(6, 4, 4, 4), true));
    }

}
