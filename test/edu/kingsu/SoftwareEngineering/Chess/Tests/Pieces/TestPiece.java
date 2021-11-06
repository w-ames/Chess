package edu.kingsu.SoftwareEngineering.Chess.Tests.Pieces;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestPiece {

    private Piece whitePiece;
    private Piece blackPiece;

    @Before
    public void setUp() {
        // Pawn is a stand-in for any generic piece
        whitePiece = new Pawn("white");
        blackPiece = new Pawn("black");
    }

    @After
    public void tearDown() {
        whitePiece = null;
        blackPiece = null;
    }

    @Test
    public void testIsWhite() {
        assertTrue("White piece does not return that it is white.", whitePiece.isWhite());
        assertFalse("Black piece does not return that it is black.", blackPiece.isWhite());
    }
    
}
