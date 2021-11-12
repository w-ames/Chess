package edu.kingsu.SoftwareEngineering.Chess.Tests.Model.Pieces;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestKing {

    private King whiteKing;
    private King blackKing;

    @Before
    public void setUp() {
        whiteKing = new King("white");
        blackKing = new King("black");
    }

    @After
    public void tearDown() {
        whiteKing = null;
        blackKing = null;
    }

    @Test
    public void testKing() {
        assertNotNull("Constructor returns null", whiteKing);
        assertNotNull("Constructor returns null", blackKing);
        assertFalse("Piece returned incorrect castle-ability boolean", whiteKing.isDoneCastling());
        assertFalse("Piece returned incorrect castle-ability boolean", blackKing.isDoneCastling());
    }

    @Test
    public void testCopyPiece() {
        King whiteCopy = (King)whiteKing.copyPiece();
        assertEquals("Copy of King did not have same whiteness.", whiteKing.isWhite(), whiteCopy.isWhite());
        assertEquals("Copy of King did not have same castling status.", whiteKing.isDoneCastling(), whiteCopy.isDoneCastling());
        whiteKing.doneCastling();
        whiteCopy = (King)whiteKing.copyPiece();
        assertEquals("Copy of King did not have same castling status after setting.", whiteKing.isDoneCastling(), whiteCopy.isDoneCastling());

        King blackCopy = (King)blackKing.copyPiece();
        assertEquals("Copy of King did not have same whiteness.", blackKing.isWhite(), blackCopy.isWhite());
        assertEquals("Copy of King did not have same castling status.", blackKing.isDoneCastling(), blackCopy.isDoneCastling());
        blackKing.doneCastling();
        blackCopy = (King)blackKing.copyPiece();
        assertEquals("Copy of King did not have same castling status after setting.", blackKing.isDoneCastling(), blackCopy.isDoneCastling());
    }

    @Test
    public void testGetPieceType() {
        assertEquals("King returned incorrect PieceType.", PieceType.KING, whiteKing.getPieceType());
    }

    @Test
    public void testIsDoneCastling() {
        assertFalse("Piece returned incorrect castle-ability boolean", whiteKing.isDoneCastling());
    }

    @Test
    public void testDoneCastling() {
        whiteKing.doneCastling();
        assertTrue("Piece castle-ability boolean did not change.", whiteKing.isDoneCastling());
    }

}
