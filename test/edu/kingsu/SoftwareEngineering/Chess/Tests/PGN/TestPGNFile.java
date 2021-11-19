package edu.kingsu.SoftwareEngineering.Chess.Tests.PGN;

import edu.kingsu.SoftwareEngineering.Chess.PGN.PGNFile;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.net.URL;

public class TestPGNFile{
    File nullFile;
    File adamsFile;
    //add any other files you want to test out here
    //other variations of files to try: stalemate, ends in checkmate, space in between turn number and move,
    //missing mandatory tag pair(s), garbage files which throw parsing errors, illegal file type

    PGNFile adams;
    //add any other PGNFiles you want to test out here

    @Before
    public void setup(){
        nullFile= new File("test/assets/PGN/doesnotexist.pgn");
        adamsFile= new File("test/assets/PGN/Adams.pgn");
        //create other files to test here
        adams= new PGNFile(adamsFile);
        //construct other PGNFiles created using file param here

        //construct PGNFiles created using map and list params here
    }

    @Test
    public void constructorWithFileParam(){
        assertFalse("nullFile is not null", nullFile.exists());
        assertTrue("adamsFile does not exist", adamsFile.exists());
        //assert other files exist here

        assertNotNull("adams is null", adams);
        //assert other PGNFiles not null here

    }

    @Test
    public void testIterator(){
        Iterator<String> adamsIterator= adams.iterator();
        assertNotNull("adamsIterator is null", adamsIterator);
        int moveCounter= 1;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "e4", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "e6", adams.next());
        moveCounter++;
        
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "d4", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "d5", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Nd2", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nf6", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "e5", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nfd7", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "f4", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "c5", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "c3", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nc6", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Ndf3", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "cxd4", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "cxd4", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "f6", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bd3", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Bb4+", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bd2", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Qb6", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Ne2", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "fxe5", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "fxe5", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "O-O", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "a3", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Be7", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Qc2", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Rxf3", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "gxf3", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nxd4", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Nxd4", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Qxd4", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "O-O-O", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nxe5", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bxh7+", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Kh8", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Kb1", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Qh4", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bc3", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Bf6", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "f4", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nc4", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bxf6", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Qxf6", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bd3", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "b5", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Qe2", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Bd7", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Rhg1", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Be8", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Rde1", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Bf7", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Rg3", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Rc8", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Reg1", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nd6", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Rxg7", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nf5", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "R7g5", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Rc7", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bxf5", adams.next());
        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "exf5", adams.next());
        moveCounter++;

        assertTrue("adams has no next move", adams.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Rh5+", adams.next());
        assertFalse("adams has next move", adams.hasNext());
    }

    @Test
    public void testGetTagPairMap(){
        Map<String, String> adamsTagPairs= adams.getTagPairMap();
        assertNotNull("adams tag pairs are null", adamsTagPairs);
        assertEquals("adams Event incorrect", "Lloyds Bank op", adams.get("Event"));
        assertEquals("adams Site incorrect", "London", adams.get("Site"));
        assertEquals("adams Date incorrect", "1984.??.??", adams.get("Date"));
        assertEquals("adams Round incorrect", "1", adams.get("Round"));
        assertEquals("adams White incorrect", "Adams, Michael", adams.get("White"));
        assertEquals("adams Black incorrect", "Sedgwick, David", adams.get("Black"));
        assertEquals("adams Result incorrect", "1-0", adams.get("Result"));
        assertEquals("adams WhiteElo incorrect", "", adams.get("WhiteElo"));
        assertEquals("adams BlackElo incorrect", "", adams.get("BlackElo"));
        assertEquals("adams ECO incorrect", "C05", adams.get("ECO"));

        //Test other tag pairs here
    }

    @Test
    public void testGetMoveTextList(){
        List<String> adamsMoveText= adams.getMoveTextList();
        assertNotNull("adams move text is null", adamsMoveText);
        
        int moveCounter= 0;
        assertEqual("adams move " + moveCounter + " incorrect", "e4", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "e6", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "d4", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "d5", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nd2", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nf6", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "e5", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nfd7", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "f4", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "c5", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "c3", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nc6", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Ndf3", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "cxd4", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "cxd4", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "f6", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bd3", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bb4+", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bd2", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qb6", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Ne2", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "fxe5", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "fxe5", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "O-O", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "a3", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Be7", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qc2", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rxf3", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "gxf3", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nxd4", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nxd4", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qxd4", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "O-O-O", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nxe5", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bxh7+", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Kh8", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Kb1", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qh4", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bc3", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bf6", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "f4", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nc4", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bxf6", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qxf6", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bd3", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "b5", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qe2", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bd7", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rhg1", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Be8", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rde1", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bf7", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rg3", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rc8", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Reg1", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nd6", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rxg7", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nf5", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "R7g5", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rc7", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bxf5", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "exf5", adamsMoveText.get(i));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rh5+", adamsMoveText.get(i));
    }

    @Test
    public void testGetFileText(){
        String adamsFileText= adams.getFileText();
        String adamsExpectedText=
            "[Event \"Lloyds Bank op\"]\n" +
            "[Site \"London\"]\n" +
            "[Date \"1984.??.??\"]\n" +
            "[Round \"1\"]\n" +
            "[White \"Adams, Michael\"]\n" +
            "[Black \"Sedgwick, David\"]\n" +
            "[Result \"1-0\"]\n" +
            "[WhiteElo \"\"]\n" +
            "[BlackElo \"\"]\n" +
            "[ECO \"C05\"]\n" +
            "\n" +
            "1. e4 e6 2. d4 d5 3. Nd2 Nf6 4. e5 Nfd7 5. f4 c5 6. c3 Nc6 7. Ndf3 cxd4 8. cxd4 f6\n" +
            "9. Bd3 Bb4+ 10. Bd2 Qb6 11. Ne2 fxe5 12. fxe5 O-O 13. a3 Be7 14. Qc2 Rxf3\n" +
            "15. gxf3 Nxd4 16. Nxd4 Qxd4 17. O-O-O Nxe5 18. Bxh7+ Kh8 19. Kb1 Qh4 20. Bc3 Bf6\n" +
            "21. f4 Nc4 22. Bxf6 Qxf6 23. Bd3 b5 24. Qe2 Bd7 25. Rhg1 Be8 26. Rde1 Bf7 27. Rg3 Rc8\n" +
            "28. Reg1 Nd6 29. Rxg7 Nf5 30. R7g5 Rc7 31. Bxf5 exf5 32 .Rh5+  1-0\n";

        assertEqual("adams file text incorrect", adamsExpectedText, adamsFileText);
    }

    @After
    public void teardown(){

    }
}