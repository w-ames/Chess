package edu.kingsu.SoftwareEngineering.Chess.Tests.Model.Moves;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestEnPassantMove {

    private PawnDoubleMove testMove1; // white double push
    private Move testMove2; // black push
    private Move testMove3; // white push to enpassant position
    private PawnDoubleMove testMove4; // black double push vulnerable to enpassant
    private EnPassantMove testMove5; // white enpassants

    @Before
    public void setUp() {
        testMove1 = new PawnDoubleMove(6, 4, 4, 4);
        testMove2 = new Move(1, 0, 2, 0);
        testMove3 = new Move(4, 4, 3, 4);
        testMove4 = new PawnDoubleMove(1, 3, 3, 3);
        testMove5 = new EnPassantMove(3, 4, 2, 3, 3, 3);
    }

    @After
    public void tearDown() {
        testMove1 = null;
        testMove2 = null;
        testMove3 = null;
        testMove4 = null;
        testMove5 = null;
    }

    @Test
    public void testMove() {
        assertNotNull("New move instance became null.", testMove1);
        assertNotNull("New move instance became null.", testMove2);
        assertNotNull("New move instance became null.", testMove3);
        assertNotNull("New move instance became null.", testMove4);
        assertNotNull("New move instance became null.", testMove5);
    }

    @Test
    public void testGetRowPassant() {
        assertEquals("Incorrect passant row given.", 3, testMove5.getRowPassant());
    }

    @Test
    public void testGetColPassant() {
        assertEquals("Incorrect passant column given.", 3, testMove5.getColPassant());
    }

    @Test
    public void testPerform() {
        movePieceAndCheck(testMove1);
        movePieceAndCheck(testMove2);
        movePieceAndCheck(testMove3);
        movePieceAndCheck(testMove4);
        movePieceAndCheck(testMove5);
    }

    private void movePieceAndCheck(Move m) {
        Board board = new Board();
        Piece startPiece = board.getPiece(m.getRowFrom(), m.getColFrom());
        m.perform(board);
        Piece endPiece = board.getPiece(m.getRowTo(), m.getColTo());
        assertSame("Piece was not moved to new location.", startPiece, endPiece);
        assertNull("Board returns the moved piece at the starting location.", board.getPiece(m.getRowFrom(), m.getColFrom()));
        if (m instanceof EnPassantMove) {
            EnPassantMove em = (EnPassantMove)m;
            assertNull("En-passanted piece was not captured.", board.getPiece(em.getRowPassant(), em.getColPassant()));
        }
    }

    @Test
    public void testGetType() {
        assertEquals("Move returns incorrect move type.", MoveType.EN_PASSANT, testMove5.getType());
    }
    
}
