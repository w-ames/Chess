package edu.kingsu.SoftwareEngineering.Chess.Tests.Model.Pieces;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestRook {

    private Rook whiteRook;
    private Rook blackRook;

    @Before
    public void setUp() {
        whiteRook = new Rook("white");
        blackRook = new Rook("black");
    }

    @After
    public void tearDown() {
        whiteRook = null;
        blackRook = null;
    }

    @Test
    public void testRook() {
        assertNotNull("Constructor returns null", whiteRook);
        assertNotNull("Constructor returns null", blackRook);
    }

    @Test
    public void testCopyPiece() {
        Rook whiteCopy = (Rook)whiteRook.copyPiece();
        assertEquals("Copy of Rook did not have same whiteness.", whiteRook.isWhite(), whiteCopy.isWhite());
        assertEquals("Copy of Rook did not have same castling status.", whiteRook.isDoneCastling(), whiteCopy.isDoneCastling());
        whiteRook.doneCastling();
        whiteCopy = (Rook)whiteRook.copyPiece();
        assertEquals("Copy of Rook did not have same castling status after setting.", whiteRook.isDoneCastling(), whiteCopy.isDoneCastling());

        Rook blackCopy = (Rook)blackRook.copyPiece();
        assertEquals("Copy of Rook did not have same whiteness.", blackRook.isWhite(), blackCopy.isWhite());
        assertEquals("Copy of Rook did not have same castling status.", blackRook.isDoneCastling(), blackCopy.isDoneCastling());
        blackRook.doneCastling();
        blackCopy = (Rook)blackRook.copyPiece();
        assertEquals("Copy of Rook did not have same castling status after setting.", blackRook.isDoneCastling(), blackCopy.isDoneCastling());
    }

    @Test
    public void testGetPieceType() {
        assertEquals("Rook returned incorrect PieceType.", PieceType.ROOK, whiteRook.getPieceType());
    }

    @Test
    public void testIsDoneCastling() {
        assertFalse("Piece returned incorrect castle-ability boolean", whiteRook.isDoneCastling());
    }

    @Test
    public void testDoneCastling() {
        whiteRook.doneCastling();
        assertTrue("Piece castle-ability boolean did not change.", whiteRook.isDoneCastling());
    }
    
}
