package edu.kingsu.SoftwareEngineering.Chess.Tests.Model;

import org.junit.*;

import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

import static org.junit.Assert.*;

public class TestChessAIThread {

    private ChessAIThread testThread;
    private Move testMove;

    @Before
    public void setUp() {
        // impossible move for testing purposes
        testMove = new Move(0,1,4,7);
        testThread = new ChessAIThread() {
            public Move calculateMove() {
                return testMove;
            }
        };
    }

    @After
    public void tearDown() {
        testMove = null;
        testThread = null;
    }

    @Test
    public void testChessAIThread() {
        assertNotNull("New test ChessAIThread instance is null.", testThread);
    }

    @Test
    public void testGetResult() {
        try {
            // start calls run on thread
            testThread.start();
            testThread.join();
            assertNotNull("ChessAIThread result from running is null.", testThread.getResult());
            assertSame("ChessAIThread result is incorrect move.", testMove, testThread.getResult());
        } catch(InterruptedException e){
            fail("ChessAIThread was interrupted.");
        }
    }

    @Test
    public void testCalculateMove() {
        // TODO make this private, we never want to directly call this
    }

    @Test
    public void testRun() {
        try {
            // start calls run on thread
            testThread.start();
            testThread.join();
            assertNotNull("ChessAIThread result from running is null.", testThread.getResult());
        } catch(InterruptedException e){
            fail("ChessAIThread was interrupted.");
        }
    }
    
}
