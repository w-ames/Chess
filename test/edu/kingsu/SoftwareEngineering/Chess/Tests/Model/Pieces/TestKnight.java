package edu.kingsu.SoftwareEngineering.Chess.Tests.Model.Pieces;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestKnight {

    private Knight whiteKnight;
    private Knight blackKnight;

    @Before
    public void setUp() {
        whiteKnight = new Knight("white");
        blackKnight = new Knight("black");
    }

    @After
    public void tearDown() {
        whiteKnight = null;
        blackKnight = null;
    }

    @Test
    public void testKnight() {
        assertNotNull("Constructor returns null", whiteKnight);
        assertNotNull("Constructor returns null", blackKnight);
    }

    @Test
    public void testCopyPiece() {
        Knight whiteCopy = (Knight)whiteKnight.copyPiece();
        assertEquals("Copy of Knight did not have same whiteness.", whiteKnight.isWhite(), whiteCopy.isWhite());
        Knight blackCopy = (Knight)blackKnight.copyPiece();
        assertEquals("Copy of Knight did not have same whiteness.", blackKnight.isWhite(), blackCopy.isWhite());
    }

    @Test
    public void testGetPieceType() {
        assertEquals("Knight returned incorrect PieceType.", PieceType.KNIGHT, whiteKnight.getPieceType());
    }
    
}
