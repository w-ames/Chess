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
    File nullFile, txtFile, adamsFile, checkmateFile, stalemateFile, illegalMoveFile, missingBlackFile,
        missingWhiteFile, missingDateFile, missingEventFile, missingResultFile, missingRoundFile,
        missingSiteFile, missingEndGameResultFile, nonsenseMoveFile, noSpaceBWTagPairsAndMovesFile,
        tooFiewMovesInTurnFile, tooManyMovesInTurnFile, wrongPlayerWonFile, wrongTurnCounterFile,
        wrongFormatFile, illegalTagPairValueFile;

    PGNFile adams, checkmate, stalemate, noSpaceBWTagPairsAndMoves;

    @Before
    public void setup(){
        nullFile= new File("test/assets/PGN/doesnotexist.pgn");
        txtFile= new File("test/assets/PGN/txtFile.txt");
        adamsFile= new File("test/assets/PGN/Adams.pgn");   //a pgn file obtained externally
        checkmateFile= new File("test/assets/PGN/checkmate.pgn");
        stalemateFile= new File("test/assets/PGN/stalemate.pgn");
        illegalMoveFile= new File("test/assets/PGN/illegalMove.pgn");
        missingBlackFile= new File("test/assets/PGN/missingBlack.pgn");
        missingWhiteFile= new File("test/assets/PGN/missingWhite.pgn");
        missingDateFile= new File("test/assets/PGN/missingDate.pgn");
        missingEventFile= new File("test/assets/PGN/missingEvent.pgn");
        missingResultFile= new File("test/assets/PGN/missingResult.pgn");
        missingRoundFile= new File("test/assets/PGN/missingRound.pgn");
        missingSiteFile= new File("test/assets/PGN/missingSite.pgn");
        missingEndGameResultFile= new File("test/assets/PGN/missingEndGameResult.pgn");
        nonsenseMoveFile= new File("test/assets/PGN/nonsenseMove.pgn");
        noSpaceBWTagPairsAndMovesFile= new File("test/assets/PGN/noSpaceBWTagPairsAndMoves.pgn");
        tooFiewMovesInTurnFile= new File("test/assets/PGN/tooFiewMovesInTurn.pgn");
        tooManyMovesInTurnFile= new File("test/assets/PGN/tooManyMovesInTurn.pgn");
        wrongPlayerWonFile= new File("test/assets/PGN/wrongPlayerWon.pgn");
        wrongTurnCounterFile= new File("test/assets/PGN/wrongTurnCounter.pgn");
        wrongFormatFile= new File("test/assets/PGN/wrongFormat.pgn");
        illegalTagPairValueFile= new File("test/assets/PGN/illegalTagPairValue.pgn");
    }

    @Test
    public void constructorWithFileParam(){
        assertFalse("nullFile is not null", nullFile.exists());
        assertTrue("txtFile does not exist", txtFile.exists());
        assertTrue("adamsFile does not exist", adamsFile.exists());
        assertTrue("checkmateFile does not exist", checkmateFile.exists());
        assertTrue("stalemateFile does not exist", stalemateFile.exists());
        assertTrue("illegalMove does not exist", illegalMove.exists());
        assertTrue("missingBlackFile does not exist", missingBlackFile.exists());
        assertTrue("missingWhiteFile does not exist", missingWhiteFile.exists());
        assertTrue("missingDateFile does not exist", missingDateFile.exists());
        assertTrue("missingEventFile does not exist", missingEventFile.exists());
        assertTrue("missingResultFile does not exist", missingResultFile.exists());
        assertTrue("missingRoundFile does not exist", missingRoundFile.exists());
        assertTrue("missingSiteFile does not exist", missingSiteFile.exists());
        assertTrue("missingEndGameResultFile does not exist", missingEndGameResultFile.exists());
        assertTrue("nonsenseMoveFile does not exist", nonsenseMoveFile.exists());
        assertTrue("noSpaceBWTagPairsAndMovesFile does not exist", noSpaceBWTagPairsAndMovesFile.exists());
        assertTrue("tooFiewMovesInTurnFile does not exist", tooFiewMovesInTurnFile.exists());
        assertTrue("tooManyMovesInTurnFile does not exist", tooManyMovesInTurnFile.exists());
        assertTrue("wrongPlayerWonFile does not exist", wrongPlayerWonFile.exists());
        assertTrue("wrongTurnCounterFile does not exist", wrongTurnCounterFile.exists());
        assertTrue("wrongFormatFile does not exist", wrongFormatFile.exists());
        assertTrue("illegalTagPairValueFile does not exist", illegalTagPairValueFile.exists());

        try{
            PGNFile txt= new PGNFile(txtFile);
            fail("Constructor didn't fail for txtFile");
        }catch(IllegalArgumentException e){}

        try{
            adams= new PGNFile(adamsFile);
        }catch(IllegalArgumentException e){
            fail("adamsFile constructor failed");
        }

        try{
            checkmate= new PGNFile(checkmateFile);
        }catch(IllegalArgumentException e){
            fail("checkmateFile constructor failed");
        }

        try{
            stalemate= new PGNFile(stalemateFile);
        }catch(IllegalArgumentException e){
            fail("stalemateFile constructor failed");
        }

        try{
            noSpaceBWTagPairsAndMoves= new PGNFile(noSpaceBWTagPairsAndMovesFile);
        }catch(IllegalArgumentException e){
            fail("noSpaceBWTagPairsAndMovesFile constructor failed");
        }

        try{
            PGNFile illegalMove= new PGNFile(illegalMoveFile);
            fail("Constructor didn't fail for illegalMoveFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingBlack= new PGNFile(missingBlackFile);
            fail("Constructor didn't fail for missingBlackFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingWhite= new PGNFile(missingWhiteFile);
            fail("Constructor didn't fail for missingWhiteFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingDate= new PGNFile(missingDateFile);
            fail("Constructor didn't fail for missingDateFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingEvent= new PGNFile(missingEventFile);
            fail("Constructor didn't fail for missingEventFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingResult= new PGNFile(missingResultFile);
            fail("Constructor didn't fail for missingResultFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingRound= new PGNFile(missingRoundFile);
            fail("Constructor didn't fail for missingRoundFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingSite= new PGNFile(missingSiteFile);
            fail("Constructor didn't fail for missingSiteFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingEndGameResult= new PGNFile(missingEndGameResultFile);
            fail("Constructor didn't fail for missingEndGameResultFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile nonsenseMove= new PGNFile(nonsenseMoveFile);
            fail("Constructor didn't fail for nonsenseMoveFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile tooFiewMovesInTurn= new PGNFile(tooFiewMovesInTurnFile);
            fail("Constructor didn't fail for tooFiewMovesInTurnFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile tooManyMovesInTurn= new PGNFile(tooManyMovesInTurnFile);
            fail("Constructor didn't fail for tooManyMovesInTurnFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile wrongPlayerWon= new PGNFile(wrongPlayerWonFile);
            fail("Constructor didn't fail for wrongPlayerWonFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile wrongTurnCounter= new PGNFile(wrongTurnCounterFile);
            fail("Constructor didn't fail for wrongTurnCounterFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile wrongFormat= new PGNFile(wrongFormatFile);
            fail("Constructor didn't fail for wrongFormatFile");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile illegalTagPairValue= new PGNFile(illegalTagPairValueFile);
            fail("Constructor didn't fail for illegalTagPairValueFile");
        }catch(IllegalArgumentException e){}
    }

    @Test
    public void constructorWithMembersParam(){
        //construct PGNFiles created using map and list params here
    }

    @Test
    public void testIterator(){
        Iterator<String> adamsIterator= adams.iterator();
        int moveCounter= 1;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "e4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "e6", adamsIterator.next());
        moveCounter++;
        
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "d4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "d5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Nd2", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nf6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "e5", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nfd7", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "f4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "c5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "c3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nc6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Ndf3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "cxd4", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "cxd4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "f6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bd3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Bb4+", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bd2", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Qb6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Ne2", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "fxe5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "fxe5", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "O-O", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "a3", adaadamsIteratorms.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Be7", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Qc2", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Rxf3", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "gxf3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nxd4", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Nxd4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Qxd4", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "O-O-O", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nxe5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bxh7+", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Kh8", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Kb1", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Qh4", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bc3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Bf6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "f4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nc4", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bxf6", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Qxf6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bd3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "b5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Qe2", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Bd7", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Rhg1", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Be8", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Rde1", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Bf7", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Rg3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Rc8", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Reg1", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nd6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Rxg7", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Nf5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "R7g5", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "Rc7", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Bxf5", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "b incorrect", "exf5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEqual("adams move " + moveCounter + "w incorrect", "Rh5+", adamsIterator.next());
        assertFalse("adams has next move", adamsIterator.hasNext());

        Iterator<String> checkmateIterator= checkmate.iterator();
        moveCounter= 1;

        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEqual("checkmate move " + moveCounter + "w incorrect", "e4", checkmateIterator.next());
        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEqual("checkmate move " + moveCounter + "b incorrect", "a6", checkmateIterator.next());
        moveCounter++;

        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEqual("checkmate move " + moveCounter + "w incorrect", "Qf3", checkmateIterator.next());
        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEqual("checkmate move " + moveCounter + "b incorrect", "a5", checkmateIterator.next());
        moveCounter++;

        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEqual("checkmate move " + moveCounter + "w incorrect", "Bc4", checkmateIterator.next());
        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEqual("checkmate move " + moveCounter + "b incorrect", "a4", checkmateIterator.next());
        moveCounter++;

        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEqual("checkmate move " + moveCounter + "w incorrect", "Af7#", checkmateIterator.next());
        assertFalse("checkmate has next move", checkmateIterator.hasNext());

        Iterator<String> stalemateIterator= stalemate.iterator();
        moveCounter= 1;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "w incorrect", "e3", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "b incorrect", "a5", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "w incorrect", "Qh5", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "b incorrect", "Ra6", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "w incorrect", "Qxa5", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "b incorrect", "h5", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "w incorrect", "Qxc7", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "b incorrect", "Rah6", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "w incorrect", "h4", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "b incorrect", "f6", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "w incorrect", "Qxd7+", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "b incorrect", "Kf7", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "w incorrect", "Qxb7", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "b incorrect", "Qd3", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "w incorrect", "Qxb8", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "b incorrect", "Qh7", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "w incorrect", "Qxc8", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "b incorrect", "Kg6", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEqual("stalemate move " + moveCounter + "w incorrect", "Qe6", stalemateIterator.next());
        assertFalse("stalemate has next move", stalemateIterator.hasNext());

        Iterator<String> noSpaceBWTagPairsAndMovesIterator= noSpaceBWTagPairsAndMoves.iterator();
        moveCounter= 1;

        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + "w incorrect", "a3", noSpaceBWTagPairsAndMovesIterator.next());
        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + "b incorrect", "e5", noSpaceBWTagPairsAndMovesIterator.next());
        moveCounter++;

        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + "w incorrect", "a4", noSpaceBWTagPairsAndMovesIterator.next());
        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + "b incorrect", "Qf6", noSpaceBWTagPairsAndMovesIterator.next());
        moveCounter++;

        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + "w incorrect", "a5", noSpaceBWTagPairsAndMovesIterator.next());
        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + "b incorrect", "Bc5", noSpaceBWTagPairsAndMovesIterator.next());
        moveCounter++;

        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + "w incorrect", "a6", noSpaceBWTagPairsAndMovesIterator.next());
        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + "b incorrect", "Qf2#", noSpaceBWTagPairsAndMovesIterator.next());
        
        assertFalse("noSpaceBWTagPairsAndMoves has next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
    }

    @Test
    public void testGetTagPairMap(){
        Map<String, String> adamsTagPairs= adams.getTagPairMap();
        assertEquals("adams Event incorrect", "Lloyds Bank op", adamsTagPairs.get("Event"));
        assertEquals("adams Site incorrect", "London", adamsTagPairs.get("Site"));
        assertEquals("adams Date incorrect", "1984.??.??", adamsTagPairs.get("Date"));
        assertEquals("adams Round incorrect", "1", adamsTagPairs.get("Round"));
        assertEquals("adams White incorrect", "Adams, Michael", adamsTagPairs.get("White"));
        assertEquals("adams Black incorrect", "Sedgwick, David", adamsTagPairs.get("Black"));
        assertEquals("adams Result incorrect", "1-0", adamsTagPairs.get("Result"));
        assertEquals("adams WhiteElo incorrect", "", adamsTagPairs.get("WhiteElo"));
        assertEquals("adams BlackElo incorrect", "", adamsTagPairs.get("BlackElo"));
        assertEquals("adams ECO incorrect", "C05", adamsTagPairs.get("ECO"));

        Map<String, String> checkmateTagPairs= checkmate.getTagPairMap();
        assertEquals("checkmate Event incorrect", "TestEvent", checkmateTagPairs.get("Event"));
        assertEquals("checkmate Site incorrect", "Edmonton", checkmateTagPairs.get("Site"));
        assertEquals("checkmate Date incorrect", "2021.??.??", checkmateTagPairs.get("Date"));
        assertEquals("checkmate Round incorrect", "", checkmateTagPairs.get("Round"));
        assertEquals("checkmate White incorrect", "Player, White", checkmateTagPairs.get("White"));
        assertEquals("checkmate Black incorrect", "Player, Black", checkcheckmateTagPairsmate.get("Black"));
        assertEquals("checkmate Result incorrect", "1-0", checkmateTagPairs.get("Result"));

        Map<String, String> stalemateTagPairs= stalemate.getTagPairMap();
        assertEquals("stalemate Event incorrect", "TestEvent", stalemateTagPairs.get("Event"));
        assertEquals("stalemate Site incorrect", "Edmonton", stalemateTagPairs.get("Site"));
        assertEquals("stalemate Date incorrect", "2021.??.??", stalemateTagPairs.get("Date"));
        assertEquals("stalemate Round incorrect", "", stalemateTagPairs.get("Round"));
        assertEquals("stalemate White incorrect", "Player, White", stalemateTagPairs.get("White"));
        assertEquals("stalemate Black incorrect", "Player, Black", stalemateTagPairs.get("Black"));
        assertEquals("stalemate Result incorrect", "1/2-1/2", stalemateTagPairs.get("Result"));

        Map<String, String> noSpaceBWTagPairsAndMovesTagPairs= noSpaceBWTagPairsAndMoves.getTagPairMap();
        assertEquals("noSpaceBWTagPairsAndMoves Event incorrect", "TestEvent", noSpaceBWTagPairsAndMovesTagPairs.get("Event"));
        assertEquals("noSpaceBWTagPairsAndMoves Site incorrect", "Edmonton", noSpaceBWTagPairsAndMovesTagPairs.get("Site"));
        assertEquals("noSpaceBWTagPairsAndMoves Date incorrect", "2021.??.??", noSpaceBWTagPairsAndMovesTagPairs.get("Date"));
        assertEquals("noSpaceBWTagPairsAndMoves Round incorrect", "", noSpaceBWTagPairsAndMovesTagPairs.get("Round"));
        assertEquals("noSpaceBWTagPairsAndMoves White incorrect", "Player, White", noSpaceBWTagPairsAndMovesTagPairs.get("White"));
        assertEquals("noSpaceBWTagPairsAndMoves Black incorrect", "Player, Black", noSpaceBWTagPairsAndMovesTagPairs.get("Black"));
        assertEquals("noSpaceBWTagPairsAndMoves Result incorrect", "0-1", noSpaceBWTagPairsAndMovesTagPairs.get("Result"));

        //Test other tag pairs here
    }

    @Test
    public void testGetMoveTextList(){
        List<String> adamsMoveText= adams.getMoveTextList();
        
        int moveCounter= 0;
        assertEqual("adams move " + moveCounter + " incorrect", "e4", adamsMoveText.get(0));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "e6", adamsMoveText.get(1));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "d4", adamsMoveText.get(2));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "d5", adamsMoveText.get(3));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nd2", adamsMoveText.get(4));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nf6", adamsMoveText.get(5));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "e5", adamsMoveText.get(6));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nfd7", adamsMoveText.get(7));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "f4", adamsMoveText.get(8));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "c5", adamsMoveText.get(9));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "c3", adamsMoveText.get(10));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nc6", adamsMoveText.get(11));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Ndf3", adamsMoveText.get(12));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "cxd4", adamsMoveText.get(13));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "cxd4", adamsMoveText.get(14));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "f6", adamsMoveText.get(15));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bd3", adamsMoveText.get(16));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bb4+", adamsMoveText.get(17));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bd2", adamsMoveText.get(18));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qb6", adamsMoveText.get(19));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Ne2", adamsMoveText.get(20));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "fxe5", adamsMoveText.get(21));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "fxe5", adamsMoveText.get(22));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "O-O", adamsMoveText.get(23));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "a3", adamsMoveText.get(24));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Be7", adamsMoveText.get(25));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qc2", adamsMoveText.get(26));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rxf3", adamsMoveText.get(27));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "gxf3", adamsMoveText.get(28));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nxd4", adamsMoveText.get(29));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nxd4", adamsMoveText.get(30));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qxd4", adamsMoveText.get(31));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "O-O-O", adamsMoveText.get(32));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nxe5", adamsMoveText.get(33));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bxh7+", adamsMoveText.get(34));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Kh8", adamsMoveText.get(35));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Kb1", adamsMoveText.get(36));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qh4", adamsMoveText.get(37));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bc3", adamsMoveText.get(38));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bf6", adamsMoveText.get(39));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "f4", adamsMoveText.get(40));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nc4", adamsMoveText.get(41));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bxf6", adamsMoveText.get(42));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qxf6", adamsMoveText.get(43));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bd3", adamsMoveText.get(44));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "b5", adamsMoveText.get(45));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Qe2", adamsMoveText.get(46));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bd7", adamsMoveText.get(47));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rhg1", adamsMoveText.get(48));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Be8", adamsMoveText.get(49));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rde1", adamsMoveText.get(50));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bf7", adamsMoveText.get(51));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rg3", adamsMoveText.get(52));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rc8", adamsMoveText.get(53));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Reg1", adamsMoveText.get(54));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nd6", adamsMoveText.get(55));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rxg7", adamsMoveText.get(56));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Nf5", adamsMoveText.get(57));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "R7g5", adamsMoveText.get(58));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rc7", adamsMoveText.get(59));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Bxf5", adamsMoveText.get(60));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "exf5", adamsMoveText.get(61));
        moveCounter++;
        assertEqual("adams move " + moveCounter + " incorrect", "Rh5+", adamsMoveText.get(62));

        moveCounter= 1;
        List<String> checkmateMoveText= checkmate.getMoveTextList();

        assertEqual("checkmate move " + moveCounter + " incorrect", "e4", checkmateMoveText.get(0));
        moveCounter++;
        assertEqual("checkmate move " + moveCounter + " incorrect", "a6", checkmateMoveText.get(1));
        moveCounter++;
        assertEqual("checkmate move " + moveCounter + " incorrect", "Qf3", checkmateMoveText.get(2));
        moveCounter++;
        assertEqual("checkmate move " + moveCounter + " incorrect", "a5", checkmateMoveText.get(3));
        moveCounter++;
        assertEqual("checkmate move " + moveCounter + " incorrect", "Bc4", checkmateMoveText.get(4));
        moveCounter++;
        assertEqual("checkmate move " + moveCounter + " incorrect", "a4", checkmateMoveText.get(5));
        moveCounter++;
        assertEqual("checkmate move " + moveCounter + " incorrect", "Qf7#", checkmateMoveText.get(6));

        moveCounter= 1;
        List<String> stalemateMoveText= stalemate.getMoveTextList();
        assertEqual("stalemate move " + moveCounter + " incorrect", "e3", stalemateMoveText.get(0));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "a5", stalemateMoveText.get(1));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Qh5", stalemateMoveText.get(2));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Ra6", stalemateMoveText.get(3));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Qxa5", stalemateMoveText.get(4));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "h5", stalemateMoveText.get(5));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Qxc7", stalemateMoveText.get(6));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Rah6", stalemateMoveText.get(7));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "h4", stalemateMoveText.get(8));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "f6", stalemateMoveText.get(9));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Qxd7+", stalemateMoveText.get(10));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Kf7", stalemateMoveText.get(11));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Qxb7", stalemateMoveText.get(12));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Qd3", stalemateMoveText.get(13));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Qxb8", stalemateMoveText.get(14));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Qh7", stalemateMoveText.get(15));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Qxc8", stalemateMoveText.get(16));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Kg6", stalemateMoveText.get(17));
        moveCounter++;
        assertEqual("stalemate move " + moveCounter + " incorrect", "Qe6", stalemateMoveText.get(18));
        

        moveCounter= 1;
        List<String> noSpaceBWTagPairsAndMovesMoveText= noSpaceBWTagPairsAndMoves.getMoveTextList();
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "a3", noSpaceBWTagPairsAndMovesMoveText.get(0));
        moveCounter++;
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "e5", noSpaceBWTagPairsAndMovesMoveText.get(1));
        moveCounter++;
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "a4", noSpaceBWTagPairsAndMovesMoveText.get(2));
        moveCounter++;
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "Qf6", noSpaceBWTagPairsAndMovesMoveText.get(3));
        moveCounter++;
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "a5", noSpaceBWTagPairsAndMovesMoveText.get(4));
        moveCounter++;
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "Bc5", noSpaceBWTagPairsAndMovesMoveText.get(5));
        moveCounter++;
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "a6", noSpaceBWTagPairsAndMovesMoveText.get(6));
        moveCounter++;
        assertEqual("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "Qf2#", noSpaceBWTagPairsAndMovesMoveText.get(7));
        moveCounter++;
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

        String checkmateFileText= checkmate.getFileText();
        String checkmateExpectedText=
            "[Event \"TestEvent\"]\n" +
            "[Site \"Edmonton\"]\n" +
            "[Date \"2021.??.??\"]\n" +
            "[Round \"\"]\n" +
            "[White \"Player, White\"]\n" +
            "[Black \"Player, Black\"]\n" +
            "[Result \"1-0\"]\n" +
            "\n" +
            "1. e4 a6 2. Qf3 a5 3. Bc4 a4 4. Qf7# 1-0";
        
        assertEqual("checkmate file text incorrect", checkmateExpectedText, checkmateFileText);

        String stalemateFileText= stalemate.getFileText();
        String stalemateExpectedText=
            "[Event \"TestEvent\"]\n" +
            "[Site \"Edmonton\"]\n" +
            "[Date \"2021.??.??\"]\n" +
            "[Round \"\"]\n" +
            "[White \"Player, White\"]\n" +
            "[Black \"Player, Black\"]\n" +
            "[Result \"1/2-1/2\"]\n" +
            "\n" +
            "1. e3 a5 2. Qh5 Ra6 3. Qxa5 h5 4. Qxc7 Rah6 5. h4 f6 6. Qxd7+ Kf7 7. Qxb7 Qd3\n" +
            "8. Qxb8 Qh7 9. Qxc8 Kg6 10. Qe6 1/2-1/2";
        
        assertEqual("checkmate file text incorrect", stalemateExpectedText, stalemateFileText);

        String noSpaceBWTagPairsAndMovesFileText= noSpaceBWTagPairsAndMoves.getFileText();
        String noSpaceBWTagPairsAndMovesExpectedText=
            "[Event \"TestEvent\"]\n" +
            "[Site \"Edmonton\"]\n" +
            "[Date \"2021.??.??\"]\n" +
            "[Round \"\"]\n" +
            "[White \"Player, White\"]\n" +
            "[Black \"Player, Black\"]\n" +
            "[Result \"0-1\"]\n" +
            "\n" +
            "1. a3 e5  2. a4 Qf6  3. a5 Bc5  4. a6 Qf2# 0-1";
        
        assertEqual("checkmate file text incorrect", noSpaceBWTagPairsAndMovesExpectedText, noSpaceBWTagPairsAndMovesFileText);
    }

    @After
    public void teardown(){

    }
}