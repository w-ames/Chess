package edu.kingsu.SoftwareEngineering.Chess.Tests.Model.Moves;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestPawnPromotionMove {

    private PawnPromotionMove testMove1;
    private PawnPromotionMove testMove2;
    private PawnPromotionMove testMove3;
    private PawnPromotionMove testMove4;
    private char[][] promoBoardChars;

    @Before
    public void setUp() {
        promoBoardChars = new char[][] {
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ','p',' ','p',' ','p',' ','p'},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '}
        };
        testMove1 = new PawnPromotionMove(1, 1, 0, 1, PieceType.KNIGHT);
        testMove2 = new PawnPromotionMove(1, 3, 0, 3, PieceType.BISHOP);
        testMove3 = new PawnPromotionMove(1, 5, 0, 5, PieceType.ROOK);
        testMove4 = new PawnPromotionMove(1, 7, 0, 7, PieceType.QUEEN);
    }

    @After
    public void tearDown() {
        testMove1 = null;
        testMove2 = null;
        testMove3 = null;
        testMove4 = null;
    }

    @Test
    public void testMove() {
        assertNotNull("New move instance became null.", testMove1);
        assertNotNull("New move instance became null.", testMove2);
        assertNotNull("New move instance became null.", testMove3);
        assertNotNull("New move instance became null.", testMove4);
    }

    @Test
    public void testPerform() {
        movePieceAndCheck(testMove1, PieceType.KNIGHT);
        movePieceAndCheck(testMove2, PieceType.BISHOP);
        movePieceAndCheck(testMove3, PieceType.ROOK);
        movePieceAndCheck(testMove4, PieceType.QUEEN);
    }

    private void movePieceAndCheck(Move m, PieceType promoType) {
        Board board = new Board();
        board.initializeBoard(promoBoardChars);
        Piece startPiece = board.getPiece(m.getRowFrom(), m.getColFrom());
        m.perform(board);
        Piece endPiece = board.getPiece(m.getRowTo(), m.getColTo());
        assertNotSame("Piece was not moved to new location.", startPiece, endPiece);
        assertEquals("Promoted type piece not found at destination", promoType, endPiece.getPieceType());
        assertNull("Board returns the moved piece at the starting location.", board.getPiece(m.getRowFrom(), m.getColFrom()));
    }

    @Test
    public void testGetType() {
        assertEquals("Move returns incorrect move type.", MoveType.PAWN_PROMOTION, testMove1.getType());
        assertEquals("Move returns incorrect move type.", MoveType.PAWN_PROMOTION, testMove2.getType());
        assertEquals("Move returns incorrect move type.", MoveType.PAWN_PROMOTION, testMove3.getType());
        assertEquals("Move returns incorrect move type.", MoveType.PAWN_PROMOTION, testMove4.getType());
    }
    
}
