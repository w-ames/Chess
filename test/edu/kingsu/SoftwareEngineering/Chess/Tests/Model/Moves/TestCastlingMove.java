package edu.kingsu.SoftwareEngineering.Chess.Tests.Model.Moves;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestCastlingMove {

    private CastlingMove testMove1;
    private CastlingMove testMove2;
    private char[][] castlingChars;

    @Before
    public void setUp() {
        castlingChars = new char[][] {
            {'R',' ',' ',' ','K',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ','k',' ',' ','r'}
        };
        testMove1 = new CastlingMove(7, 4, 7, 6, 7, 7, 7, 5);
        testMove2 = new CastlingMove(0, 4, 0, 2, 0, 0, 0, 3);
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
    public void testGetRowFromCastle() {
        assertEquals("Move returned incorrect rook starting row.", 7, testMove1.getRowFromCastle());
        assertEquals("Move returned incorrect rook starting row.", 0, testMove2.getRowFromCastle());
    }

    @Test
    public void testGetColFromCastle() {
        assertEquals("Move returned incorrect rook starting column.", 7, testMove1.getColFromCastle());
        assertEquals("Move returned incorrect rook starting column.", 0, testMove2.getColFromCastle());
    }

    @Test
    public void testGetRowToCastle() {
        assertEquals("Move returned incorrect rook end row.", 7, testMove1.getRowToCastle());
        assertEquals("Move returned incorrect rook end row.", 0, testMove2.getRowToCastle());
    }

    @Test
    public void testGetColToCastle() {
        assertEquals("Move returned incorrect rook end column.", 5, testMove1.getColToCastle());
        assertEquals("Move returned incorrect rook end column.", 3, testMove2.getColToCastle());
    }

    @Test
    public void testPerform() {
        movePieceAndCheck(testMove1);
        movePieceAndCheck(testMove2);
    }

    private void movePieceAndCheck(CastlingMove m) {
        Board board = new Board();
        board.initializeBoard(castlingChars);
        Piece startPiece1 = board.getPiece(m.getRowFrom(), m.getColFrom());
        Piece startPiece2 = board.getPiece(m.getRowFromCastle(), m.getColFromCastle());
        m.perform(board);
        Piece endPiece1 = board.getPiece(m.getRowTo(), m.getColTo());
        Piece endPiece2 = board.getPiece(m.getRowToCastle(), m.getColToCastle());
        assertSame("Piece was not moved to new location.", startPiece1, endPiece1);
        assertSame("Auxilary piece was not moved to new location.", startPiece2, endPiece2);
        assertNull("Board returns the moved piece at the starting location.", board.getPiece(m.getRowFrom(), m.getColFrom()));
        assertNull("Board returns the moved auxilary piece at the starting location.", board.getPiece(m.getRowFromCastle(), m.getColFromCastle()));
    }

    @Test
    public void testGetType() {
        assertEquals("Move returns incorrect move type.", MoveType.CASTLING, testMove1.getType());
        assertEquals("Move returns incorrect move type.", MoveType.CASTLING, testMove2.getType());
    }
    
}
