package edu.kingsu.SoftwareEngineering.Chess.Tests.Pieces;

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
    public void testGetPieceType() {
        assertEquals("Knight returned incorrect PieceType.", PieceType.KNIGHT, whiteKnight.getPieceType());
    }
    
}
