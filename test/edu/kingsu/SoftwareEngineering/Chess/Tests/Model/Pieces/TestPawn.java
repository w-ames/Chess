package edu.kingsu.SoftwareEngineering.Chess.Tests.Model.Pieces;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestPawn {

    private Pawn whitePawn;
    private Pawn blackPawn;

    @Before
    public void setUp() {
        whitePawn = new Pawn("white");
        blackPawn = new Pawn("black");
    }

    @After
    public void tearDown() {
        whitePawn = null;
        blackPawn = null;
    }

    @Test
    public void testPawn() {
        assertNotNull("Constructor returns null", whitePawn);
        assertNotNull("Constructor returns null", blackPawn);
    }

    @Test
    public void testCopyPiece() {
        Pawn whiteCopy = (Pawn)whitePawn.copyPiece();
        assertEquals("Copy of Pawn did not have same whiteness.", whitePawn.isWhite(), whiteCopy.isWhite());
        assertEquals("Copy of Pawn did not have same double-move status.", whitePawn.isDoneDoubleMove(), whiteCopy.isDoneDoubleMove());
        whitePawn.doneDoubleMove();
        whiteCopy = (Pawn)whitePawn.copyPiece();
        assertEquals("Copy of Pawn did not have same double-move status after setting.", whitePawn.isDoneDoubleMove(), whiteCopy.isDoneDoubleMove());

        Pawn blackCopy = (Pawn)blackPawn.copyPiece();
        assertEquals("Copy of Pawn did not have same whiteness.", blackPawn.isWhite(), blackCopy.isWhite());
        assertEquals("Copy of Pawn did not have same double-move status.", blackPawn.isDoneDoubleMove(), blackCopy.isDoneDoubleMove());
        blackPawn.doneDoubleMove();
        blackCopy = (Pawn)blackPawn.copyPiece();
        assertEquals("Copy of Pawn did not have same double-move status after setting.", blackPawn.isDoneDoubleMove(), blackCopy.isDoneDoubleMove());
    }

    @Test
    public void testGetPieceType() {
        assertEquals("Pawn returned incorrect PieceType.", PieceType.PAWN, whitePawn.getPieceType());
    }

    @Test
    public void testIsDoneDoubleMove() {
        assertFalse("Piece returned incorrect double move-ability boolean", whitePawn.isDoneDoubleMove());
    }

    @Test
    public void testDoneDoubleMove() {
        whitePawn.doneDoubleMove();
        assertTrue("Piece double move-ability boolean did not change.", whitePawn.isDoneDoubleMove());
    }
    
}
