package edu.kingsu.SoftwareEngineering.Chess.Tests.Model.Pieces;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestQueen {

    private Queen whiteQueen;
    private Queen blackQueen;

    @Before
    public void setUp() {
        whiteQueen = new Queen("white");
        blackQueen = new Queen("black");
    }

    @After
    public void tearDown() {
        whiteQueen = null;
        blackQueen = null;
    }

    @Test
    public void testQueen() {
        assertNotNull("Constructor returns null", whiteQueen);
        assertNotNull("Constructor returns null", blackQueen);
    }

    @Test
    public void testCopyPiece() {
        Queen whiteCopy = (Queen)whiteQueen.copyPiece();
        assertEquals("Copy of Queen did not have same whiteness.", whiteQueen.isWhite(), whiteCopy.isWhite());
        Queen blackCopy = (Queen)blackQueen.copyPiece();
        assertEquals("Copy of Queen did not have same whiteness.", blackQueen.isWhite(), blackCopy.isWhite());
    }

    @Test
    public void testGetPieceType() {
        assertEquals("Queen returned incorrect PieceType.", PieceType.QUEEN, whiteQueen.getPieceType());
    }
    
}
