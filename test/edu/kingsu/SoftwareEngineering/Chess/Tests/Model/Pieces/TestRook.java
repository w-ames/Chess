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
