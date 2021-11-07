package edu.kingsu.SoftwareEngineering.Chess.Tests.Model.Pieces;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestBishop {

    private Bishop whiteBishop;
    private Bishop blackBishop;

    @Before
    public void setUp() {
        whiteBishop = new Bishop("white");
        blackBishop = new Bishop("black");
    }

    @After
    public void tearDown() {
        whiteBishop = null;
        blackBishop = null;
    }

    @Test
    public void testBishop() {
        assertNotNull("Constructor returns null", whiteBishop);
        assertNotNull("Constructor returns null", blackBishop);
    }

    @Test
    public void testGetPieceType() {
        assertEquals("Bishop returned incorrect PieceType.", PieceType.BISHOP, whiteBishop.getPieceType());
    }

}
