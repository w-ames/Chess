package edu.kingsu.SoftwareEngineering.Chess.Tests.Model;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestBoard {

    private Board initialBoard;

    private static final char[][] defaultChars = {
        {'R','N','B','Q','K','B','N','R'},
        {'P','P','P','P','P','P','P','P'},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'p','p','p','p','p','p','p','p'},
        {'r','n','b','q','k','b','n','r'}
    };

    private static final char[][] testPosition = {
        {'R',' ','B',' ',' ','R','K',' '},
        {'P','P',' ',' ','B','P','P','P'},
        {' ',' ','N',' ','P','N',' ',' '},
        {'Q',' ',' ','P',' ',' ',' ',' '},
        {' ',' ','p',' ',' ','b',' ',' '},
        {'p',' ','n',' ','p','n',' ',' '},
        {' ','p','q',' ',' ','p','p','p'},
        {' ',' ',' ','r','k','b',' ','r'}
    };

    private static final char[][] castleBoard = {
        {'R',' ',' ',' ','K',' ',' ','R'},
        {'P',' ',' ',' ','p',' ',' ','P'},
        {' ',' ',' ','p','p','p',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'p',' ',' ',' ',' ',' ',' ','p'},
        {'r',' ',' ',' ','k',' ',' ','r'}
    };

    private static final char[][] checkBoard = {
        {'R','N','B','Q','K','B','N','R'},
        {'P','P','P',' ',' ',' ','P','P'},
        {' ',' ',' ','P',' ','P',' ',' '},
        {' ','b',' ',' ','P',' ',' ',' '},
        {' ',' ',' ',' ','p',' ',' ',' '},
        {' ',' ','n',' ',' ','p',' ',' '},
        {'p','p','p','p',' ',' ','p','p'},
        {'r',' ','b','q','k',' ','n','r'}
    };

    private static final char[][] checkmateBoard = {
        {'R',' ','B','Q','K',' ','N','R'},
        {'P','P','P','P','P','q','B','P'},
        {' ',' ','N',' ',' ',' ','P',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ','b',' ','p',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'p','p','p','p',' ','p','p','p'},
        {'r','n','b',' ','k',' ','n','r'}
    };
 
    @Before
    public void setUp() {
        initialBoard = new Board();
    }

    @After
    public void tearDown() {
        initialBoard = null;
    }

    @Test
    public void testBoard() {
        assertNotNull("Board constructor returned null", initialBoard);
    }

    @Test
    public void testInitializeBoard() {
        initialBoard.initializeBoard(checkBoard);
        assertChars(checkBoard, initialBoard.getChars());
        initialBoard.initializeBoard();
        assertChars(defaultChars, initialBoard.getChars());
    }

    @Test
    public void testGetChars() {
        assertChars(defaultChars, initialBoard.getChars());
    }

    private void assertChars(char[][] expected, char[][] given) {
        assertEquals("Board instance returns a character array with an incorrect number of rows.", expected.length, given.length);
        for (int i=0; i<expected.length; i++) {
            assertArrayEquals("Row "+i+" of Board instance does not return the correct character array", expected[i], given[i]);
        }
    }

    @Test
    public void testGetPiece() {
        // This test assumes the initial board layout throughout!
        for (int i=0; i<initialBoard.ROWS; i++) {
            for (int j=0; j<initialBoard.COLS; j++) {
                Piece p = initialBoard.getPiece(i, j);
                if (i < 2 || i > 5) { // this is where we expect pieces
                    assertNotNull("Piece was not found on new Board instance at row: "+i+" column: "+j, p);
                    // first rows are black on the initial board, this
                    // check relies on using the initial board layout!!
                    assertEquals("Whiteness of piece on new Board instance is incorrect.", i>initialBoard.ROWS/2, p.isWhite());
                    // we check the types of pieces
                    PieceType expectedPieceType = null;
                    if (i == 1 || i == 6) {
                        expectedPieceType = PieceType.PAWN;
                    } else {
                        if (j == 0 || j == 7) {
                            expectedPieceType = PieceType.ROOK;
                            assertFalse("New rook at standard initial location row: "+i+" column: "+j+" unable to castle.", ((Rook)p).isDoneCastling());
                        } else if (j == 1 || j == 6) {
                            expectedPieceType = PieceType.KNIGHT;
                        } else if (j == 2 || j == 5) {
                            expectedPieceType = PieceType.BISHOP;
                        } else if (j == 3) {
                            expectedPieceType = PieceType.QUEEN;
                        } else if (j == 4) {
                            expectedPieceType = PieceType.KING;
                            assertFalse("New king at standard initial location row: "+i+" column: "+j+" unable to castle.", ((King)p).isDoneCastling());
                        }
                    }
                    assertEquals("Unexpected Type of piece found at row: "+i+" column: "+j, expectedPieceType, p.getPieceType());
                } else {
                    assertNull("Piece was found on new Board instance at row: "+i+" column: "+j, null);
                }
            }
        }
    }

    @Test
    public void testSetPiece() {
        Piece p = new Pawn(true);
        initialBoard.setPiece(4, 4, p);
        assertSame("Non-same piece returned at location after setting the location piece via setPiece.", p, initialBoard.getPiece(4, 4));
    }

    @Test
    public void testGetEnPassantable() {
        assertFalse("New Board instance returned boolean indicating enpassantability.", initialBoard.getEnPassantable());
    }

    @Test
    public void testSetEnPassantable() {
        initialBoard.setEnPassantable(5, 3);
        assertTrue("Board returned boolean indicating non-enpassantability directly after enpassantability was set.", initialBoard.getEnPassantable());
        initialBoard.setEnPassantable();
        assertFalse("Board returned boolean indicating enpassantability directly after enpassantability was reset.", initialBoard.getEnPassantable());
        Move m = new PawnDoubleMove(6, 4, 4, 4);
        m.perform(initialBoard);
        assertTrue("Board returned boolean indicating non-enpassantability directly after a pawn double move was made.", initialBoard.getEnPassantable());
    }

    @Test
    public void testGetMoves() {
        // --- Initial Board Check ---
        assertMoves(0, 0, 0);
        assertMoves(2, 0, 1);
        assertMoves(0, 0, 2);
        assertMoves(0, 0, 3);
        assertMoves(0, 0, 4);
        assertMoves(0, 0, 5);
        assertMoves(2, 0, 6);
        assertMoves(0, 0, 7);
        assertMoves(2, 1, 0); // pawns
        assertMoves(2, 1, 1);
        assertMoves(2, 1, 2);
        assertMoves(2, 1, 3);
        assertMoves(2, 1, 4);
        assertMoves(2, 1, 5);
        assertMoves(2, 1, 6);
        assertMoves(2, 1, 7);

        assertMoves(0, 7, 0);
        assertMoves(2, 7, 1);
        assertMoves(0, 7, 2);
        assertMoves(0, 7, 3);
        assertMoves(0, 7, 4);
        assertMoves(0, 7, 5);
        assertMoves(2, 7, 6);
        assertMoves(0, 7, 7);
        assertMoves(2, 6, 0); // pawns
        assertMoves(2, 6, 1);
        assertMoves(2, 6, 2);
        assertMoves(2, 6, 3);
        assertMoves(2, 6, 4);
        assertMoves(2, 6, 5);
        assertMoves(2, 6, 6);
        assertMoves(2, 6, 7);
        // --- End Initial Board Check ---

        initialBoard.initializeBoard(testPosition);
        assertMoves(2, 4, 2);
        assertMoves(7, 4, 5);
        assertMoves(1, 5, 0);
        assertMoves(0, 5, 2);
        assertMoves(1, 5, 4);
        assertMoves(6, 5, 5);
        assertMoves(2, 6, 1);
        assertMoves(11, 6, 2);
        assertMoves(0, 6, 5);
        assertMoves(2, 6, 6);
        assertMoves(2, 6, 7);
        assertMoves(7, 7, 3);
        assertMoves(2, 7, 4);
        assertMoves(2, 7, 5);
        assertMoves(1, 7, 7);

        initialBoard.initializeBoard();
        assertMoves(2, 6, 4);
        (new Move(6, 4, 5, 4)).perform(initialBoard);
        assertMoves(1, 5, 4);

        initialBoard.initializeBoard(castleBoard);
        assertMoves(7, 7, 4);
        assertMoves(0, 0, 4);

        initialBoard.initializeBoard(new char[][] {
            {'R',' ',' ',' ','K',' ',' ','R'},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ','B',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {'r',' ',' ',' ','k',' ',' ','r'}
        });
        assertMoves(4, 7, 4);

        initialBoard.initializeBoard(castleBoard);
        (new Move(7,4,6,4)).perform(initialBoard);
        (new Move(6,4,7,4)).perform(initialBoard);
        assertMoves(5, 7, 4);
    }

    private void assertMoves(int expected, int r, int c) {
        assertEquals("An incorrect number of possible moves was returned at row: "+r+" column: "+c, expected, initialBoard.getMoves(r, c).size());
    }

    @Test
    public void testGetAllMoves() {
        assertEquals("An incorrect number of possible moves was returned.", 20, initialBoard.getAllMoves(true).size());
        assertEquals("An incorrect number of possible moves was returned.", 20, initialBoard.getAllMoves(false).size());
        initialBoard.initializeBoard(testPosition);
        assertEquals("An incorrect number of possible moves was returned.", 46, initialBoard.getAllMoves(true).size());
    }

    @Test
    public void testGetAttackers() {
        initialBoard.initializeBoard(testPosition);
        ArrayList<List<Integer>> expectedAttackers = new ArrayList<List<Integer>>();
        ArrayList<Integer> expectedAttacker = new ArrayList<Integer>();
        expectedAttacker.add(4);
        expectedAttacker.add(2);
        expectedAttackers.add(new ArrayList<Integer>(expectedAttacker));
        expectedAttacker.clear();
        expectedAttacker.add(5);
        expectedAttacker.add(2);
        expectedAttackers.add(new ArrayList<Integer>(expectedAttacker));
        expectedAttacker.clear();
        expectedAttacker.add(7);
        expectedAttacker.add(3);
        expectedAttackers.add(new ArrayList<Integer>(expectedAttacker));
        expectedAttacker.clear();
        assertEquals("Incorrect number of attackers calculated.", expectedAttackers.size(), initialBoard.getAttackers(false, 3, 3).size());
        assertTrue("Incorrect attacking squares calculated.", expectedAttackers.containsAll(initialBoard.getAttackers(false, 3, 3)));
    }

    @Test
    public void testGetCheck() {
        assertFalse("New Board instance returned boolean indicating check.", initialBoard.getCheck(true));
        assertFalse("New Board instance returned boolean indicating check.", initialBoard.getCheck(false));
        initialBoard.initializeBoard(checkBoard);
        assertTrue("Board instance with check conditions returned boolean indicating no check.", initialBoard.getCheck(true));
        assertFalse("Board instance without check conditions returned boolean indicating check.", initialBoard.getCheck(false));
    }

    @Test
    public void testGetCheckmate() {
        assertFalse("New Board instance returned boolean indicating checkmate.", initialBoard.getCheckmate(true));
        assertFalse("New Board instance returned boolean indicating checkmate.", initialBoard.getCheckmate(false));
        initialBoard.initializeBoard(checkmateBoard);
        assertTrue("Board instance with checkmate conditions returned boolean indicating no checkmate.", initialBoard.getCheckmate(true));
        assertFalse("Board instance without checkmate conditions returned boolean indicating checkmate.", initialBoard.getCheckmate(false));
    }

    @Test
    public void testIsColoredSquare() {
        assertFalse("Board instance returned boolean indicating incorrect square color", initialBoard.isColoredSquare(0, 0));
        assertTrue("Board instance returned boolean indicating incorrect square color", initialBoard.isColoredSquare(0, 7));
        assertFalse("Board instance returned boolean indicating incorrect square color", initialBoard.isColoredSquare(7, 7));
        assertTrue("Board instance returned boolean indicating incorrect square color", initialBoard.isColoredSquare(7, 0));
    }

    @Test
    public void testValidPromotion() {
        Board promoBoard = new Board();
        promoBoard.initializeBoard(new char[][] {
            {' ',' ',' ',' ','K',' ',' ',' '},
            {'p',' ',' ',' ',' ',' ',' ','P'},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {'p',' ',' ',' ',' ',' ',' ','P'},
            {' ',' ',' ',' ','k',' ',' ',' '}
        });
        assertTrue("White pawn did not return correct pawn promotion validity.", promoBoard.validPromotion(1, 0, 0, 0));
        assertFalse("White pawn did not return correct pawn promotion validity.", promoBoard.validPromotion(6, 0, 5, 0));
        assertTrue("Black pawn did not return correct pawn promotion validity.", promoBoard.validPromotion(6, 7, 7, 7));
        assertFalse("Black pawn did not return correct pawn promotion validity.", promoBoard.validPromotion(1, 7, 2, 7));
    }

}
