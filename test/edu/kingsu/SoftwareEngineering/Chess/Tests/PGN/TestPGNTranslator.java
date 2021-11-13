package edu.kingsu.SoftwareEngineering.Chess.Tests.PGN;

import edu.kingsu.SoftwareEngineering.Chess.PGN.PGNTranslator;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;


public class TestPGNTranslator {
    private Board testBoard;
    private static final char[][] TEST_POSITION = { //capital=black, lowercase=white
        {' ',' ','K',' ',' ',' ',' ',' '},
        {' ','Q',' ',' ',' ','B',' ','p'},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ','P',' ','n',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ','R',' '},
        {' ',' ',' ',' ','r',' ',' ',' '},
        {' ',' ','q',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Before
    public void setup(){
        testBoard= new Board();
    }

    @After
    public void teardown(){
        testBoard= null;
    }

    @Test
    public void testTranslateMoveToPGN() {
        testBoard.initialize(TEST_POSITION);
        assertEquals("Incorrect PGN String given for x move", "Qd2", PGNTranslator.translateMoveToPGN(new Move(6,2,6,3), testBoard));
    }
}
