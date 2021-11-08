package edu.kingsu.SoftwareEngineering.Chess.Tests.Model.Moves;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestMove {

    private Move testMove1;
    private Move testMove2;

    @Before
    public void setUp() {
        testMove1 = new Move(6, 4, 5, 4);
        testMove2 = new Move(7, 5, 4, 2);
    }

    @After
    public void tearDown() {
        testMove1 = null;
        testMove2 = null;
    }

    @Test
    public void testMove() {
        assertNotNull("New move instance became null.", testMove1);
    }

    @Test
    public void testGetRowFrom() {
        assertEquals("Move returned incorrect starting row.", 7, testMove2.getRowFrom());
    }

    @Test
    public void testGetColFrom() {
        assertEquals("Move returned incorrect starting column.", 5, testMove2.getColFrom());
    }

    @Test
    public void testGetRowTo() {
        assertEquals("Move returned incorrect end row.", 4, testMove2.getRowTo());
    }

    @Test
    public void testGetColTo() {
        assertEquals("Move returned incorrect end column.", 2, testMove2.getColTo());
    }

    @Test
    public void testPerform() {
        movePieceAndCheck(testMove1);
        movePieceAndCheck(testMove2);
    }

    private void movePieceAndCheck(Move m) {
        Board board = new Board();
        Piece startPiece = board.getPiece(m.getRowFrom(), m.getColFrom());
        m.perform(board);
        Piece endPiece = board.getPiece(m.getRowTo(), m.getColTo());
        assertSame("Piece was not moved to new location.", startPiece, endPiece);
        assertNull("Board returns the moved piece at the starting location.", board.getPiece(m.getRowFrom(), m.getColFrom()));
    }

    @Test
    public void testGetType() {
        assertEquals("Move returns incorrect move type.", MoveType.NORMAL, testMove1.getType());
    }

    @Test
    public void testHasDestination() {
        assertHasDestination(testMove1, 5, 4);
        assertHasDestination(testMove2, 4, 2);
    }

    private void assertHasDestination(Move m, int r, int c) {
        String msg = "Move returns boolean indicating an incorrect destination.";
        assertTrue(msg, m.hasDestination(r, c));
        assertFalse(msg, m.hasDestination(r+1, c+1));
        assertFalse(msg, m.hasDestination(r+1, c));
        assertFalse(msg, m.hasDestination(r, c+1));
    }
    
}
