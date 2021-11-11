package edu.kingsu.SoftwareEngineering.Chess.Tests.Model.Moves;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestPawnDoubleMove {

    private PawnDoubleMove testMove1;

    @Before
    public void setUp() {
        testMove1 = new PawnDoubleMove(6, 4, 4, 4);
    }

    @After
    public void tearDown() {
        testMove1 = null;
    }

    @Test
    public void testMove() {
        assertNotNull("New move instance became null.", testMove1);
    }

    @Test
    public void testPerform() {
        movePieceAndCheck(testMove1);
    }

    private void movePieceAndCheck(PawnDoubleMove m) {
        Board board = new Board();
        Pawn startPiece = (Pawn)board.getPiece(m.getRowFrom(), m.getColFrom());
        m.perform(board);
        Pawn endPiece = (Pawn)board.getPiece(m.getRowTo(), m.getColTo());
        assertSame("Piece was not moved to new location.", startPiece, endPiece);
        assertNull("Board returns the moved piece at the starting location.", board.getPiece(m.getRowFrom(), m.getColFrom()));
        assertTrue("Board is not enpassantable after pawn double move.", board.getEnPassantable());
        assertTrue("Pawn double move completion flag not set after double move.", startPiece.isDoneDoubleMove());
    }

    @Test
    public void testGetType() {
        assertEquals("Move returns incorrect move type.", MoveType.PAWN_DOUBLE, testMove1.getType());
    }
    
}
