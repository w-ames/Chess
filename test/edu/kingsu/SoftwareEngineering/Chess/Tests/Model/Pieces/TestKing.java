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
