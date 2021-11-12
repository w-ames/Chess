package edu.kingsu.SoftwareEngineering.Chess.Tests.Model;

import org.junit.*;
import static org.junit.Assert.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;

public class TestChessAI {

    private Board testBoard;
    private static final char[][] TEST_POSITION = {
        {'R',' ','B',' ',' ',' ','N','R'},
        {'P','P','Q','K',' ','b','B','P'},
        {' ',' ','P','P',' ',' ',' ',' '},
        {' ',' ',' ',' ','p',' ','b',' '},
        {' ',' ',' ','N',' ',' ',' ',' '},
        {' ',' ',' ','p',' ',' ',' ',' '},
        {'p','p','p',' ',' ','q','p','p'},
        {'r',' ',' ',' ',' ','r','k',' '}
    };

    @Before
    public void setUp() {
        testBoard = new Board();
    }

    @After
    public void tearDown() {
        testBoard = null;
    }

    @Test
    public void testBestMove() {
        try {
            // QUEEN TAKES KNIGHT IS BEST SAFE MOVE
            testBoard.initializeBoard(TEST_POSITION);
            ChessAIThread safeCapMoveThread = ChessAI.bestMove(testBoard, 2, true);
            assertNotNull("Checkmate move thread is null.", safeCapMoveThread);
            safeCapMoveThread.start();
            safeCapMoveThread.join();
            Move safeCapMove = safeCapMoveThread.getResult();
            assertEquals("Incorrect safe capture move start row.", 6, safeCapMove.getRowFrom());
            assertEquals("Incorrect safe capture move start column.", 5, safeCapMove.getColFrom());
            assertEquals("Incorrect safe capture move end row.", 4, safeCapMove.getRowTo());
            assertEquals("Incorrect safe capture move end column.", 3, safeCapMove.getColTo());

            // SACK THE QUEEN WITH 4+ PLY MINIMAX
            testBoard.initializeBoard(TEST_POSITION);
            ChessAIThread checkmateMoveThread = ChessAI.bestMove(testBoard, 4, true);
            assertNotNull("Checkmate move thread is null.", checkmateMoveThread);
            checkmateMoveThread.start();
            checkmateMoveThread.join();
            Move checkmateMove = checkmateMoveThread.getResult();
            assertNotNull("Checkmate move thread gave null move", checkmateMove);
            assertEquals("Incorrect checkmate move start row.", 6, checkmateMove.getRowFrom());
            assertEquals("Incorrect checkmate move start column.", 5, checkmateMove.getColFrom());
            assertEquals("Incorrect checkmate move end row.", 3, checkmateMove.getRowTo());
            assertEquals("Incorrect checkmate move end column.", 5, checkmateMove.getColTo());
        } catch(InterruptedException e){
            fail("Thread could not be joined.");
        }
    }

    @Test
    public void testRandomMove() {
        try {
            ChessAIThread randomMoveThread = ChessAI.randomMove(testBoard, true);
            assertNotNull("Random move thread is null.", randomMoveThread);
            randomMoveThread.start();
            randomMoveThread.join();
            Move randomMove = randomMoveThread.getResult();
            assertNotNull("Random move thread gave null move", randomMove);
            Piece randomPiece = testBoard.getPiece(randomMove.getRowFrom(), randomMove.getColFrom());
            assertNotNull("Random move thread gave move from empty location.", randomPiece);
            assertTrue("Random move thread gave move for black when white was requested.", randomPiece.isWhite());
        } catch(InterruptedException e){
            fail("Thread could not be joined.");
        }
    }
    
}
