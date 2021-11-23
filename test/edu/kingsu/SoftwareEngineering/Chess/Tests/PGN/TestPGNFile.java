package edu.kingsu.SoftwareEngineering.Chess.Tests.PGN;

import edu.kingsu.SoftwareEngineering.Chess.PGN.PGNFile;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.util.*;

public class TestPGNFile{
    //files for using constructor with file param
    File nullFile, txtFile, adamsFile, checkmateFile, stalemateFile, illegalMoveFile, missingBlackFile,
        missingWhiteFile, missingDateFile, missingEventFile, missingResultFile, missingRoundFile,
        missingSiteFile, missingEndGameResultFile, nonsenseMoveFile, noSpaceBWTagPairsAndMovesFile,
        tooFewMovesInTurnFile, tooManyMovesInTurnFile, wrongPlayerWonFile, wrongTurnCounterFile,
        wrongFormatFile, illegalTagPairValueFile, inProgressWithMovesFile, inProgressNoMovesFile;

    //PGNFiles for use with constructor with file param
    PGNFile adams, checkmate, stalemate, noSpaceBWTagPairsAndMoves, inProgressWithMoves1, inProgressNoMoves1;

    //PGNFiles for use with constructor with members parameters
    PGNFile testGame, testGame2, stalemate1, inProgressWithMoves, inProgressNoMoves;

    //objects for use as data members when using constructor which requires them as params
    Map<String, String> testGameTP, testGame2TP, stalemate1TP, inProgressTP,
        quotesInTagPairValueTP, missingEventTP, missingSiteTP, missingDateTP, missingRoundTP, missingWhiteTP,
        missingBlackTP, missingResultTP;

    List<String> testGameMoves, testGame2Moves, stalemate1Moves, inProgressWithMovesMoves,
        inProgressNoMovesMoves, nonsenseMoveMoves, invalidMoveMoves;

    String whiteWin, blackWin, stalemateResult, inProgress;

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
        tooFewMovesInTurnFile= new File("test/assets/PGN/tooFewMovesInTurn.pgn");
        tooManyMovesInTurnFile= new File("test/assets/PGN/tooManyMovesInTurn.pgn");
        wrongPlayerWonFile= new File("test/assets/PGN/wrongPlayerWon.pgn");
        wrongTurnCounterFile= new File("test/assets/PGN/wrongTurnCounter.pgn");
        wrongFormatFile= new File("test/assets/PGN/wrongFormat.pgn");
        illegalTagPairValueFile= new File("test/assets/PGN/illegalTagPairValue.pgn");
        inProgressWithMovesFile= new File("test/assets/PGN/inProgressWithMoves.pgn");
        inProgressNoMovesFile= new File("test/assets/PGN/inProgressNoMoves.pgn");

        testGameTP= new LinkedHashMap<String, String>();
        testGameTP.put("Event", "BCF-ch");
        testGameTP.put("Site", "Edinburgh");
        testGameTP.put("Date", "1985.??.??");
        testGameTP.put("Round", "1");
        testGameTP.put("White", "Adams, Michael");
        testGameTP.put("Black", "Singh, Sukh Dave");
        testGameTP.put("Result", "1-0");
        testGameTP.put("WhiteElo", "2360");
        testGameTP.put("BlackElo", "2080");
        testGameTP.put("ECO", "B70");

        testGame2TP= new LinkedHashMap<String, String>();
        testGame2TP.put("Event", "TestEvent");
        testGame2TP.put("Site", "Edmonton");
        testGame2TP.put("Date", "2021.??.??");
        testGame2TP.put("Round", "");
        testGame2TP.put("White", "Player, White");
        testGame2TP.put("Black", "Player, Black");
        testGame2TP.put("Result", "0-1");

        stalemate1TP= new LinkedHashMap<String, String>();
        stalemate1TP.put("Event", "TestEvent");
        stalemate1TP.put("Site", "Edmonton");
        stalemate1TP.put("Date", "2021.??.??");
        stalemate1TP.put("Round", "");
        stalemate1TP.put("White", "Player, White");
        stalemate1TP.put("Black", "Player, Black");
        stalemate1TP.put("Result", "1/2-1/2");

        inProgressTP= new LinkedHashMap<String, String>();
        inProgressTP.put("Event", "TestEvent");
        inProgressTP.put("Site", "Edmonton");
        inProgressTP.put("Date", "2021.??.??");
        inProgressTP.put("Round", "");
        inProgressTP.put("White", "Player, White");
        inProgressTP.put("Black", "Player, Black");
        inProgressTP.put("Result", "*");

        quotesInTagPairValueTP= new LinkedHashMap<String, String>();
        quotesInTagPairValueTP.put("Event", "\"TestEvent\"");
        quotesInTagPairValueTP.put("Site", "Edmonton");
        quotesInTagPairValueTP.put("Date", "2021.??.??");
        quotesInTagPairValueTP.put("Round", "");
        quotesInTagPairValueTP.put("White", "Player,\" White");
        quotesInTagPairValueTP.put("Black", "Player, Black");
        quotesInTagPairValueTP.put("Result", "1-0");

        missingEventTP= new LinkedHashMap<String, String>();
        missingEventTP.put("Site", "Edmonton");
        missingEventTP.put("Date", "2021.??.??");
        missingEventTP.put("Round", "");
        missingEventTP.put("White", "Player, White");
        missingEventTP.put("Black", "Player, Black");
        missingEventTP.put("Result", "1-0");

        missingSiteTP= new LinkedHashMap<String, String>();
        missingSiteTP.put("Event", "TestEvent");
        missingSiteTP.put("Date", "2021.??.??");
        missingSiteTP.put("Round", "");
        missingSiteTP.put("White", "Player, White");
        missingSiteTP.put("Black", "Player, Black");
        missingSiteTP.put("Result", "1-0");

        missingDateTP= new LinkedHashMap<String, String>();
        missingDateTP.put("Event", "TestEvent");
        missingDateTP.put("Site", "Edmonton");
        missingDateTP.put("Round", "");
        missingDateTP.put("White", "Player, White");
        missingDateTP.put("Black", "Player, Black");
        missingDateTP.put("Result", "1-0");

        missingRoundTP= new LinkedHashMap<String, String>();
        missingRoundTP.put("Event", "TestEvent");
        missingRoundTP.put("Site", "Edmonton");
        missingRoundTP.put("Date", "2021.??.??");
        missingRoundTP.put("White", "Player, White");
        missingRoundTP.put("Black", "Player, Black");
        missingRoundTP.put("Result", "1-0");

        missingWhiteTP= new LinkedHashMap<String, String>();
        missingWhiteTP.put("Event", "TestEvent");
        missingWhiteTP.put("Site", "Edmonton");
        missingWhiteTP.put("Date", "2021.??.??");
        missingWhiteTP.put("Round", "");
        missingWhiteTP.put("Black", "Player, Black");
        missingWhiteTP.put("Result", "1-0");

        missingBlackTP= new LinkedHashMap<String, String>();
        missingBlackTP.put("Event", "TestEvent");
        missingBlackTP.put("Site", "Edmonton");
        missingBlackTP.put("Date", "2021.??.??");
        missingBlackTP.put("Round", "");
        missingBlackTP.put("White", "Player, White");
        missingBlackTP.put("Result", "1-0");

        missingResultTP= new LinkedHashMap<String, String>();
        missingResultTP.put("Event", "TestEvent");
        missingResultTP.put("Site", "Edmonton");
        missingResultTP.put("Date", "2021.??.??");
        missingResultTP.put("Round", "");
        missingResultTP.put("White", "Player, White");
        missingResultTP.put("Black", "Player, Black");

        testGameMoves= new ArrayList<String>();
        testGameMoves.add("e4");
        testGameMoves.add("e5");
        testGameMoves.add("Bc4");
        testGameMoves.add("Bc5");
        testGameMoves.add("Nf3");
        testGameMoves.add("Nf6");
        testGameMoves.add("O-O");
        testGameMoves.add("OO");    //castle variation
        testGameMoves.add("Ba6");
        testGameMoves.add("Be3");
        testGameMoves.add("d4");
        testGameMoves.add("exd4");
        testGameMoves.add("e5");
        testGameMoves.add("d5");
        testGameMoves.add("exd6 e.p.");
        testGameMoves.add("Qe7");
        testGameMoves.add("c4");
        testGameMoves.add("dxc3 ep");  //en passant variation
        testGameMoves.add("d7");
        testGameMoves.add("c2");
        testGameMoves.add("Bd2");
        testGameMoves.add("c1=Q");
        testGameMoves.add("d8=Q");
        testGameMoves.add("Qc6");
        testGameMoves.add("Qxe7");
        testGameMoves.add("Re8");
        testGameMoves.add("Bb5");
        testGameMoves.add("Nh5");
        testGameMoves.add("Bxc6");
        testGameMoves.add("Bf4");
        testGameMoves.add("Qxe8#");

        testGame2Moves= new ArrayList<String>();
        testGame2Moves.add("d4");
        testGame2Moves.add("d5");
        testGame2Moves.add("Bg5");
        testGame2Moves.add("Bg4");
        testGame2Moves.add("Qd3");
        testGame2Moves.add("Qd6");
        testGame2Moves.add("Nc3");
        testGame2Moves.add("Nc6");
        testGame2Moves.add("O-O-O");
        testGame2Moves.add("OOO");  //castle variation
        testGame2Moves.add("Na4");
        testGame2Moves.add("Na5");
        testGame2Moves.add("Qg6");
        testGame2Moves.add("e5");
        testGame2Moves.add("dxe5");
        testGame2Moves.add("d4");
        testGame2Moves.add("c4");
        testGame2Moves.add("dxc3e.p."); //en passant variation
        testGame2Moves.add("Qh6");
        testGame2Moves.add("f5");
        testGame2Moves.add("exf6");     //en passant variation
        testGame2Moves.add("Qxd1++");   //checkmate variation

        stalemate1Moves= new ArrayList<String>();
        stalemate1Moves.add("e3");
        stalemate1Moves.add("a5");
        stalemate1Moves.add("Qh5");
        stalemate1Moves.add("Ra6");
        stalemate1Moves.add("Qxa5");
        stalemate1Moves.add("h5");
        stalemate1Moves.add("Qxc7");
        stalemate1Moves.add("Rah6");
        stalemate1Moves.add("h4");
        stalemate1Moves.add("f6");
        stalemate1Moves.add("Qxd7+");
        stalemate1Moves.add("Kf7");
        stalemate1Moves.add("Qxb7");
        stalemate1Moves.add("Qd3");
        stalemate1Moves.add("Qxb8");
        stalemate1Moves.add("Qh7");
        stalemate1Moves.add("Qxc8");
        stalemate1Moves.add("Kg6");
        stalemate1Moves.add("Qe6");

        inProgressWithMovesMoves= new ArrayList<String>();
        inProgressWithMovesMoves.add("e3");
        inProgressWithMovesMoves.add("a5");
        inProgressWithMovesMoves.add("Qh5");
        inProgressWithMovesMoves.add("Ra6");
        inProgressWithMovesMoves.add("Qxa5");
        inProgressWithMovesMoves.add("h5");

        inProgressNoMovesMoves= new ArrayList<String>();

        nonsenseMoveMoves= new ArrayList<String>();
        nonsenseMoveMoves.add("e3");
        nonsenseMoveMoves.add("Q2c");
        nonsenseMoveMoves.add("e4");
        nonsenseMoveMoves.add("a5Q");

        invalidMoveMoves= new ArrayList<String>();
        invalidMoveMoves.add("e3");
        invalidMoveMoves.add("Qd4");
        invalidMoveMoves.add("a3");
        invalidMoveMoves.add("Rb6");

        whiteWin= "1-0";
        blackWin= "0-1";
        stalemateResult= "1/2-1/2";
        inProgress= "*";
    }

    @Test
    public void constructorWithFileParam(){
        assertFalse("nullFile is not null", nullFile.exists());
        assertTrue("txtFile does not exist", txtFile.exists());
        assertTrue("adamsFile does not exist", adamsFile.exists());
        assertTrue("checkmateFile does not exist", checkmateFile.exists());
        assertTrue("stalemateFile does not exist", stalemateFile.exists());
        assertTrue("illegalMoveFile does not exist", illegalMoveFile.exists());
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
        assertTrue("tooFewMovesInTurnFile does not exist", tooFewMovesInTurnFile.exists());
        assertTrue("tooManyMovesInTurnFile does not exist", tooManyMovesInTurnFile.exists());
        assertTrue("wrongPlayerWonFile does not exist", wrongPlayerWonFile.exists());
        assertTrue("wrongTurnCounterFile does not exist", wrongTurnCounterFile.exists());
        assertTrue("wrongFormatFile does not exist", wrongFormatFile.exists());
        assertTrue("illegalTagPairValueFile does not exist", illegalTagPairValueFile.exists());
        assertTrue("inProgressWithMovesFile does not exist", inProgressWithMovesFile.exists());
        assertTrue("inProgressNoMovesFile does not exist", inProgressNoMovesFile.exists());

        // try{
        //     PGNFile txt= new PGNFile(txtFile);
        //     fail("Constructor didn't fail for txtFile");
        // }catch(IllegalArgumentException e){}
        // catch(FileNotFoundException e){
        //     fail("FileNotFoundException for txtFile");
        // }

        try{
            adams= new PGNFile(adamsFile);
        }catch(Exception e){
            e.printStackTrace();
            fail("adamsFile constructor failed");
        }

        try{
            checkmate= new PGNFile(checkmateFile);
        }catch(Exception e){
            fail("checkmateFile constructor failed");
        }

        try{
            stalemate= new PGNFile(stalemateFile);
        }catch(Exception e){
            fail("stalemateFile constructor failed");
        }

        try{
            noSpaceBWTagPairsAndMoves= new PGNFile(noSpaceBWTagPairsAndMovesFile);
        }catch(Exception e){
            fail("noSpaceBWTagPairsAndMovesFile constructor failed");
        }

        try{
            inProgressWithMoves1= new PGNFile(inProgressWithMovesFile);
        }catch(Exception e){
            fail("inProgressWithMoves1 constructor failed");
        }

        try{
            inProgressNoMoves1= new PGNFile(inProgressNoMovesFile);
        }catch(Exception e){
            fail("inProgressNoMoves1 constructor failed");
        }

        try{
            PGNFile illegalMove= new PGNFile(illegalMoveFile);
            fail("Constructor didn't fail for illegalMoveFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for illegalMoveFile");
        }

        try{
            PGNFile missingBlack= new PGNFile(missingBlackFile);
            fail("Constructor didn't fail for missingBlackFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for missingBlackFile");
        }

        try{
            PGNFile missingWhite= new PGNFile(missingWhiteFile);
            fail("Constructor didn't fail for missingWhiteFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for missingWhiteFile");
        }

        try{
            PGNFile missingDate= new PGNFile(missingDateFile);
            fail("Constructor didn't fail for missingDateFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for missingDateFile");
        }

        try{
            PGNFile missingEvent= new PGNFile(missingEventFile);
            fail("Constructor didn't fail for missingEventFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for missingEventFile");
        }

        try{
            PGNFile missingResult= new PGNFile(missingResultFile);
            fail("Constructor didn't fail for missingResultFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for missingResultFile");
        }

        try{
            PGNFile missingRound= new PGNFile(missingRoundFile);
            fail("Constructor didn't fail for missingRoundFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for missingRoundFile");
        }

        try{
            PGNFile missingSite= new PGNFile(missingSiteFile);
            fail("Constructor didn't fail for missingSiteFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for missingSiteFile");
        }

        try{
            PGNFile missingEndGameResult= new PGNFile(missingEndGameResultFile);
            fail("Constructor didn't fail for missingEndGameResultFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for missingEndGameResultFile");
        }

        try{
            PGNFile nonsenseMove= new PGNFile(nonsenseMoveFile);
            fail("Constructor didn't fail for nonsenseMoveFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for nonsenseMoveFile");
        }

        try{
            PGNFile tooFiewMovesInTurn= new PGNFile(tooFewMovesInTurnFile);
            fail("Constructor didn't fail for tooFewMovesInTurnFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for tooFewMovesInTurnFile");
        }

        try{
            PGNFile tooManyMovesInTurn= new PGNFile(tooManyMovesInTurnFile);
            fail("Constructor didn't fail for tooManyMovesInTurnFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for tooManyMovesInTurnFile");
        }

        try{
            PGNFile wrongPlayerWon= new PGNFile(wrongPlayerWonFile);
            fail("Constructor didn't fail for wrongPlayerWonFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for wrongPlayerWonFile");
        }

        try{
            PGNFile wrongTurnCounter= new PGNFile(wrongTurnCounterFile);
            fail("Constructor didn't fail for wrongTurnCounterFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for missingBlawrongTurnCounterFileckFile");
        }

        try{
            PGNFile wrongFormat= new PGNFile(wrongFormatFile);
            fail("Constructor didn't fail for wrongFormatFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for wrongFormatFile");
        }

        try{
            PGNFile illegalTagPairValue= new PGNFile(illegalTagPairValueFile);
            fail("Constructor didn't fail for illegalTagPairValueFile");
        }catch(IllegalArgumentException e){}
        catch(FileNotFoundException e){
            fail("FileNotFoundException for illegalTagPairValueFile");
        }
    }

    @Test
    public void constructorWithMembersParam(){
        //construct PGNFiles created using map and list params here
        try{
            testGame= new PGNFile(testGameTP, testGameMoves, whiteWin);
        }catch(Exception e){
            fail("Constructor failed for testGame");
        }

        try{
            testGame2= new PGNFile(testGame2TP, testGame2Moves, blackWin);
        }catch(Exception e){
            fail("Constructor failed for testGame2");
        }

        try{
            stalemate1= new PGNFile(stalemate1TP, stalemate1Moves, stalemateResult);
        }catch(Exception e){
            fail("Constructor failed for stalemate1");
        }

        try{
            inProgressWithMoves= new PGNFile(inProgressTP, inProgressWithMovesMoves, inProgress);
        }catch(Exception e){
            fail("Constructor failed for inProgressWithMoves");
        }

        try{
            inProgressNoMoves= new PGNFile(inProgressTP, inProgressNoMovesMoves, inProgress);
        }catch(Exception e){
            fail("Constructor failed for inProgressNoMoves");
        }

        try{
            PGNFile quotesInTagPairValue= new PGNFile(quotesInTagPairValueTP, testGameMoves, whiteWin);
            fail("Constructor didn't fail for quotesInTagPairValue");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingEvent= new PGNFile(missingEventTP, testGameMoves, whiteWin);
            fail("Constructor didn't fail for missingEvent");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingSite= new PGNFile(missingSiteTP, testGameMoves, whiteWin);
            fail("Constructor didn't fail for missingSite");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingDate= new PGNFile(missingDateTP, testGameMoves, whiteWin);
            fail("Constructor didn't fail for missingDate");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingRound= new PGNFile(missingRoundTP, testGameMoves, whiteWin);
            fail("Constructor didn't fail for missingRound");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingWhite= new PGNFile(missingWhiteTP, testGameMoves, whiteWin);
            fail("Constructor didn't fail for missingWhite");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingBlack= new PGNFile(missingBlackTP, testGameMoves, whiteWin);
            fail("Constructor didn't fail for missingBlack");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile missingResult= new PGNFile(missingResultTP, testGameMoves, whiteWin);
            fail("Constructor didn't fail for missingResult");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile nonsenseMove= new PGNFile(inProgressTP, nonsenseMoveMoves, inProgress);
            fail("Constructor didn't fail for nonsenseMove");
        }catch(IllegalArgumentException e){}

        try{
            PGNFile invalidMove= new PGNFile(inProgressTP, invalidMoveMoves, inProgress);
            fail("Constructor didn't fail for invalidMove");
        }catch(IllegalArgumentException e){}
    }

    @Test
    public void testIterator(){
        Iterator<String> adamsIterator= adams.iterator();
        int moveCounter= 1;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "e4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "e6", adamsIterator.next());
        moveCounter++;
        
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "d4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "d5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Nd2", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Nf6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "e5", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Nfd7", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "f4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "c5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "c3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Nc6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Ndf3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "cxd4", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "cxd4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "f6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Bd3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Bb4+", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Bd2", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Qb6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Ne2", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "fxe5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "fxe5", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "O-O", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "a3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Be7", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Qc2", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Rxf3", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "gxf3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Nxd4", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Nxd4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Qxd4", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "O-O-O", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Nxe5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Bxh7+", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Kh8", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Kb1", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Qh4", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Bc3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Bf6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "f4", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Nc4", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Bxf6", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Qxf6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Bd3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "b5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Qe2", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Bd7", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Rhg1", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Be8", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Rde1", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Bf7", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Rg3", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Rc8", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Reg1", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Nd6", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Rxg7", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Nf5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "R7g5", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "Rc7", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Bxf5", adamsIterator.next());
        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "b incorrect", "exf5", adamsIterator.next());
        moveCounter++;

        assertTrue("adams has no next move", adamsIterator.hasNext());
        assertEquals("adams move " + moveCounter + "w incorrect", "Rh5+", adamsIterator.next());
        assertFalse("adams has next move", adamsIterator.hasNext());

        Iterator<String> checkmateIterator= checkmate.iterator();
        moveCounter= 1;

        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEquals("checkmate move " + moveCounter + "w incorrect", "e4", checkmateIterator.next());
        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEquals("checkmate move " + moveCounter + "b incorrect", "a6", checkmateIterator.next());
        moveCounter++;

        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEquals("checkmate move " + moveCounter + "w incorrect", "Qf3", checkmateIterator.next());
        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEquals("checkmate move " + moveCounter + "b incorrect", "a5", checkmateIterator.next());
        moveCounter++;

        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEquals("checkmate move " + moveCounter + "w incorrect", "Bc4", checkmateIterator.next());
        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEquals("checkmate move " + moveCounter + "b incorrect", "a4", checkmateIterator.next());
        moveCounter++;

        assertTrue("checkmate has no next move", checkmateIterator.hasNext());
        assertEquals("checkmate move " + moveCounter + "w incorrect", "Af7#", checkmateIterator.next());
        assertFalse("checkmate has next move", checkmateIterator.hasNext());

        Iterator<String> stalemateIterator= stalemate.iterator();
        moveCounter= 1;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "w incorrect", "e3", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "b incorrect", "a5", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "w incorrect", "Qh5", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "b incorrect", "Ra6", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "w incorrect", "Qxa5", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "b incorrect", "h5", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "w incorrect", "Qxc7", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "b incorrect", "Rah6", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "w incorrect", "h4", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "b incorrect", "f6", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "w incorrect", "Qxd7+", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "b incorrect", "Kf7", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "w incorrect", "Qxb7", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "b incorrect", "Qd3", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "w incorrect", "Qxb8", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "b incorrect", "Qh7", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "w incorrect", "Qxc8", stalemateIterator.next());
        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "b incorrect", "Kg6", stalemateIterator.next());
        moveCounter++;

        assertTrue("stalemate has no next move", stalemateIterator.hasNext());
        assertEquals("stalemate move " + moveCounter + "w incorrect", "Qe6", stalemateIterator.next());
        assertFalse("stalemate has next move", stalemateIterator.hasNext());

        Iterator<String> noSpaceBWTagPairsAndMovesIterator= noSpaceBWTagPairsAndMoves.iterator();
        moveCounter= 1;

        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + "w incorrect", "a3", noSpaceBWTagPairsAndMovesIterator.next());
        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + "b incorrect", "e5", noSpaceBWTagPairsAndMovesIterator.next());
        moveCounter++;

        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + "w incorrect", "a4", noSpaceBWTagPairsAndMovesIterator.next());
        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + "b incorrect", "Qf6", noSpaceBWTagPairsAndMovesIterator.next());
        moveCounter++;

        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + "w incorrect", "a5", noSpaceBWTagPairsAndMovesIterator.next());
        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + "b incorrect", "Bc5", noSpaceBWTagPairsAndMovesIterator.next());
        moveCounter++;

        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + "w incorrect", "a6", noSpaceBWTagPairsAndMovesIterator.next());
        assertTrue("noSpaceBWTagPairsAndMoves has no next move", noSpaceBWTagPairsAndMovesIterator.hasNext());
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + "b incorrect", "Qf2#", noSpaceBWTagPairsAndMovesIterator.next());
        
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
        assertEquals("checkmate Black incorrect", "Player, Black", checkmateTagPairs.get("Black"));
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

        Map<String, String> inProgressWithMoves1TagPairs= inProgressWithMoves1.getTagPairMap();
        assertEquals("inProgressWithMoves1TagPairs Event incorrect", "TestEvent", inProgressWithMoves1TagPairs.get("Event"));
        assertEquals("inProgressWithMoves1TagPairs Site incorrect", "Edmonton", inProgressWithMoves1TagPairs.get("Site"));
        assertEquals("inProgressWithMoves1TagPairs Date incorrect", "2021.??.??", inProgressWithMoves1TagPairs.get("Date"));
        assertEquals("inProgressWithMoves1TagPairs Round incorrect", "", inProgressWithMoves1TagPairs.get("Round"));
        assertEquals("inProgressWithMoves1TagPairs White incorrect", "Player, White", inProgressWithMoves1TagPairs.get("White"));
        assertEquals("inProgressWithMoves1TagPairs Black incorrect", "Player, Black", inProgressWithMoves1TagPairs.get("Black"));
        assertEquals("inProgressWithMoves1TagPairs Result incorrect", "*", inProgressWithMoves1TagPairs.get("Result"));

        Map<String, String> inProgressNoMoves1TagPairs= inProgressNoMoves1.getTagPairMap();
        assertEquals("inProgressNoMoves1TagPairs Event incorrect", "TestEvent", inProgressNoMoves1TagPairs.get("Event"));
        assertEquals("inProgressNoMoves1TagPairs Site incorrect", "Edmonton", inProgressNoMoves1TagPairs.get("Site"));
        assertEquals("inProgressNoMoves1TagPairs Date incorrect", "2021.??.??", inProgressNoMoves1TagPairs.get("Date"));
        assertEquals("inProgressNoMoves1TagPairs Round incorrect", "", inProgressNoMoves1TagPairs.get("Round"));
        assertEquals("inProgressNoMoves1TagPairs White incorrect", "Player, White", inProgressNoMoves1TagPairs.get("White"));
        assertEquals("inProgressNoMoves1TagPairs Black incorrect", "Player, Black", inProgressNoMoves1TagPairs.get("Black"));
        assertEquals("inProgressNoMoves1TagPairs Result incorrect", "*", inProgressNoMoves1TagPairs.get("Result"));

        Map<String, String> testGameTagPairs= testGame.getTagPairMap();
        assertEquals("testGame Event incorrect", "BCF-ch", testGameTagPairs.get("Event"));
        assertEquals("testGame Site incorrect", "Edinburgh", testGameTagPairs.get("Site"));
        assertEquals("testGame Date incorrect", "1985.??.??", testGameTagPairs.get("Date"));
        assertEquals("testGame Round incorrect", "1", testGameTagPairs.get("Round"));
        assertEquals("testGame White incorrect", "Adams, Michael", testGameTagPairs.get("White"));
        assertEquals("testGame Black incorrect", "Singh, Sukh Dave", testGameTagPairs.get("Black"));
        assertEquals("testGame Result incorrect", "1-0", testGameTagPairs.get("Result"));
        assertEquals("testGame WhiteElo incorrect", "2360", testGameTagPairs.get("WhiteElo"));
        assertEquals("testGame BlackElo incorrect", "2080", testGameTagPairs.get("BlackElo"));
        assertEquals("testGame ECO incorrect", "B70", testGameTagPairs.get("ECO"));

        Map<String, String> testGame2TagPairs= testGame2.getTagPairMap();
        assertEquals("testGame2 Event incorrect", "TestEvent", testGame2TagPairs.get("Event"));
        assertEquals("testGame2 Site incorrect", "Edmonton", testGame2TagPairs.get("Site"));
        assertEquals("testGame2 Date incorrect", "2021.??.??", testGame2TagPairs.get("Date"));
        assertEquals("testGame2 Round incorrect", "", testGame2TagPairs.get("Round"));
        assertEquals("testGame2 White incorrect", "Player, White", testGame2TagPairs.get("White"));
        assertEquals("testGame2 Black incorrect", "Player, Black", testGame2TagPairs.get("Black"));
        assertEquals("testGame2 Result incorrect", "0-1", testGame2TagPairs.get("Result"));

        Map<String, String> stalemate1TagPairs= stalemate1.getTagPairMap();
        assertEquals("stalemate1 Event incorrect", "TestEvent", stalemate1TagPairs.get("Event"));
        assertEquals("stalemate1 Site incorrect", "Edmonton", stalemate1TagPairs.get("Site"));
        assertEquals("stalemate1 Date incorrect", "2021.??.??", stalemate1TagPairs.get("Date"));
        assertEquals("stalemate1 Round incorrect", "", stalemate1TagPairs.get("Round"));
        assertEquals("stalemate1 White incorrect", "Player, White", stalemate1TagPairs.get("White"));
        assertEquals("stalemate1 Black incorrect", "Player, Black", stalemate1TagPairs.get("Black"));
        assertEquals("stalemate1 Result incorrect", "1/2-1/2", stalemate1TagPairs.get("Result"));

        Map<String, String> inProgressTagPairs= inProgressWithMoves.getTagPairMap();
        assertEquals("stalemate1 Event incorrect", "TestEvent", stalemate1TagPairs.get("Event"));
        assertEquals("stalemate1 Site incorrect", "Edmonton", stalemate1TagPairs.get("Site"));
        assertEquals("stalemate1 Date incorrect", "2021.??.??", stalemate1TagPairs.get("Date"));
        assertEquals("stalemate1 Round incorrect", "", stalemate1TagPairs.get("Round"));
        assertEquals("stalemate1 White incorrect", "Player, White", stalemate1TagPairs.get("White"));
        assertEquals("stalemate1 Black incorrect", "Player, Black", stalemate1TagPairs.get("Black"));
        assertEquals("stalemate1 Result incorrect", "*", stalemate1TagPairs.get("Result"));
    }

    @Test
    public void testGetMoveTextList(){
        List<String> adamsMoveText= adams.getMoveTextList();
        assertEquals("adamsMoveText wrong size", 63, adamsMoveText.size());

        int moveCounter= 0;
        assertEquals("adams move " + moveCounter + " incorrect", "e4", adamsMoveText.get(0));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "e6", adamsMoveText.get(1));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "d4", adamsMoveText.get(2));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "d5", adamsMoveText.get(3));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Nd2", adamsMoveText.get(4));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Nf6", adamsMoveText.get(5));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "e5", adamsMoveText.get(6));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Nfd7", adamsMoveText.get(7));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "f4", adamsMoveText.get(8));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "c5", adamsMoveText.get(9));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "c3", adamsMoveText.get(10));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Nc6", adamsMoveText.get(11));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Ndf3", adamsMoveText.get(12));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "cxd4", adamsMoveText.get(13));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "cxd4", adamsMoveText.get(14));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "f6", adamsMoveText.get(15));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Bd3", adamsMoveText.get(16));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Bb4+", adamsMoveText.get(17));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Bd2", adamsMoveText.get(18));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Qb6", adamsMoveText.get(19));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Ne2", adamsMoveText.get(20));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "fxe5", adamsMoveText.get(21));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "fxe5", adamsMoveText.get(22));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "O-O", adamsMoveText.get(23));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "a3", adamsMoveText.get(24));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Be7", adamsMoveText.get(25));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Qc2", adamsMoveText.get(26));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Rxf3", adamsMoveText.get(27));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "gxf3", adamsMoveText.get(28));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Nxd4", adamsMoveText.get(29));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Nxd4", adamsMoveText.get(30));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Qxd4", adamsMoveText.get(31));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "O-O-O", adamsMoveText.get(32));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Nxe5", adamsMoveText.get(33));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Bxh7+", adamsMoveText.get(34));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Kh8", adamsMoveText.get(35));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Kb1", adamsMoveText.get(36));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Qh4", adamsMoveText.get(37));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Bc3", adamsMoveText.get(38));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Bf6", adamsMoveText.get(39));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "f4", adamsMoveText.get(40));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Nc4", adamsMoveText.get(41));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Bxf6", adamsMoveText.get(42));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Qxf6", adamsMoveText.get(43));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Bd3", adamsMoveText.get(44));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "b5", adamsMoveText.get(45));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Qe2", adamsMoveText.get(46));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Bd7", adamsMoveText.get(47));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Rhg1", adamsMoveText.get(48));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Be8", adamsMoveText.get(49));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Rde1", adamsMoveText.get(50));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Bf7", adamsMoveText.get(51));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Rg3", adamsMoveText.get(52));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Rc8", adamsMoveText.get(53));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Reg1", adamsMoveText.get(54));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Nd6", adamsMoveText.get(55));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Rxg7", adamsMoveText.get(56));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Nf5", adamsMoveText.get(57));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "R7g5", adamsMoveText.get(58));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Rc7", adamsMoveText.get(59));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Bxf5", adamsMoveText.get(60));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "exf5", adamsMoveText.get(61));
        moveCounter++;
        assertEquals("adams move " + moveCounter + " incorrect", "Rh5+", adamsMoveText.get(62));

        moveCounter= 1;
        List<String> checkmateMoveText= checkmate.getMoveTextList();
        assertEquals("checkmateMoveText wrong size", 7, checkmateMoveText.size());
        
        assertEquals("checkmate move " + moveCounter + " incorrect", "e4", checkmateMoveText.get(0));
        moveCounter++;
        assertEquals("checkmate move " + moveCounter + " incorrect", "a6", checkmateMoveText.get(1));
        moveCounter++;
        assertEquals("checkmate move " + moveCounter + " incorrect", "Qf3", checkmateMoveText.get(2));
        moveCounter++;
        assertEquals("checkmate move " + moveCounter + " incorrect", "a5", checkmateMoveText.get(3));
        moveCounter++;
        assertEquals("checkmate move " + moveCounter + " incorrect", "Bc4", checkmateMoveText.get(4));
        moveCounter++;
        assertEquals("checkmate move " + moveCounter + " incorrect", "a4", checkmateMoveText.get(5));
        moveCounter++;
        assertEquals("checkmate move " + moveCounter + " incorrect", "Qf7#", checkmateMoveText.get(6));

        moveCounter= 1;
        List<String> stalemateMoveText= stalemate.getMoveTextList();
        assertEquals("stalemateMoveText wrong size", 19, stalemateMoveText.size());

        assertEquals("stalemate move " + moveCounter + " incorrect", "e3", stalemateMoveText.get(0));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "a5", stalemateMoveText.get(1));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Qh5", stalemateMoveText.get(2));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Ra6", stalemateMoveText.get(3));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Qxa5", stalemateMoveText.get(4));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "h5", stalemateMoveText.get(5));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Qxc7", stalemateMoveText.get(6));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Rah6", stalemateMoveText.get(7));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "h4", stalemateMoveText.get(8));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "f6", stalemateMoveText.get(9));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Qxd7+", stalemateMoveText.get(10));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Kf7", stalemateMoveText.get(11));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Qxb7", stalemateMoveText.get(12));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Qd3", stalemateMoveText.get(13));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Qxb8", stalemateMoveText.get(14));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Qh7", stalemateMoveText.get(15));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Qxc8", stalemateMoveText.get(16));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Kg6", stalemateMoveText.get(17));
        moveCounter++;
        assertEquals("stalemate move " + moveCounter + " incorrect", "Qe6", stalemateMoveText.get(18));
        

        moveCounter= 1;
        List<String> noSpaceBWTagPairsAndMovesMoveText= noSpaceBWTagPairsAndMoves.getMoveTextList();
        assertEquals("noSpaceBWTagPairsAndMovesMoveText wrong size", 8, noSpaceBWTagPairsAndMovesMoveText.size());

        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "a3", noSpaceBWTagPairsAndMovesMoveText.get(0));
        moveCounter++;
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "e5", noSpaceBWTagPairsAndMovesMoveText.get(1));
        moveCounter++;
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "a4", noSpaceBWTagPairsAndMovesMoveText.get(2));
        moveCounter++;
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "Qf6", noSpaceBWTagPairsAndMovesMoveText.get(3));
        moveCounter++;
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "a5", noSpaceBWTagPairsAndMovesMoveText.get(4));
        moveCounter++;
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "Bc5", noSpaceBWTagPairsAndMovesMoveText.get(5));
        moveCounter++;
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "a6", noSpaceBWTagPairsAndMovesMoveText.get(6));
        moveCounter++;
        assertEquals("noSpaceBWTagPairsAndMoves move " + moveCounter + " incorrect", "Qf2#", noSpaceBWTagPairsAndMovesMoveText.get(7));

        moveCounter= 1;
        List<String> inProgressWithMoves1MoveText= inProgressWithMoves1.getMoveTextList();
        assertEquals("inProgressWithMoves1MoveText wrong size", 4, inProgressWithMoves1MoveText.size());

        assertEquals("inProgressWithMoves1 move " + moveCounter + " incorrect", "e4", inProgressWithMoves1MoveText.get(0));
        moveCounter++;
        assertEquals("inProgressWithMoves1 move " + moveCounter + " incorrect", "a6", inProgressWithMoves1MoveText.get(1));
        moveCounter++;
        assertEquals("inProgressWithMoves1 move " + moveCounter + " incorrect", "Qf3", inProgressWithMoves1MoveText.get(2));
        moveCounter++;
        assertEquals("inProgressWithMoves1 move " + moveCounter + " incorrect", "a5", inProgressWithMoves1MoveText.get(3));

        List<String> inProgressNoMoves1MoveText= inProgressNoMoves1.getMoveTextList();
        assertEquals("inProgressNoMoves1MoveText wrong size", 0, inProgressNoMoves1MoveText.size());

        moveCounter= 1;
        List<String> testGameMoveText= testGame.getMoveTextList();
        assertEquals("testGameMoveText wrong size", 28, testGameMoveText.size());

        assertEquals("testGame move " + moveCounter + " incorrect", "e4", testGameMoveText.get(0));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "e5", testGameMoveText.get(1));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Bc4", testGameMoveText.get(2));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Bc5", testGameMoveText.get(3));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Nf3", testGameMoveText.get(4));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Nf6", testGameMoveText.get(5));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "O-O", testGameMoveText.get(6));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "O-O", testGameMoveText.get(7));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Ba6", testGameMoveText.get(8));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Be3", testGameMoveText.get(9));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "d4", testGameMoveText.get(10));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "exd4", testGameMoveText.get(11));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "e5", testGameMoveText.get(12));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "d5", testGameMoveText.get(13));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "exd6 e.p.", testGameMoveText.get(14));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "d7", testGameMoveText.get(15));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "c2", testGameMoveText.get(16));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Bd2", testGameMoveText.get(17));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "c1=Q", testGameMoveText.get(18));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "d8=Q", testGameMoveText.get(19));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Qc6", testGameMoveText.get(20));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Qxe7", testGameMoveText.get(21));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Re8", testGameMoveText.get(22));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Bb5", testGameMoveText.get(23));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Nh5", testGameMoveText.get(24));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Bxc6", testGameMoveText.get(25));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Bf4", testGameMoveText.get(26));
        moveCounter++;
        assertEquals("testGame move " + moveCounter + " incorrect", "Qxe8#", testGameMoveText.get(27));

        moveCounter= 1;
        List<String> testGame2MoveText= testGame2.getMoveTextList();
        assertEquals("testGame2MoveText wrong size", 22, testGame2MoveText.size());
        
        assertEquals("testGame2 move " + moveCounter + " incorrect", "d4", testGame2MoveText.get(0));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Qd5xe8", testGame2MoveText.get(1));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Bg5", testGame2MoveText.get(2));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Bg4", testGame2MoveText.get(3));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Qd3", testGame2MoveText.get(4));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Qd6", testGame2MoveText.get(5));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Nc3", testGame2MoveText.get(6));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Nc6", testGame2MoveText.get(7));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "O-O-O", testGame2MoveText.get(8));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "O-O-O", testGame2MoveText.get(9));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Na4", testGame2MoveText.get(10));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Na5", testGame2MoveText.get(11));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Qg6", testGame2MoveText.get(12));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "e5", testGame2MoveText.get(13));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "dxe5", testGame2MoveText.get(14));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "d4", testGame2MoveText.get(15));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "c4", testGame2MoveText.get(16));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "dxc3 e.p.", testGame2MoveText.get(17));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Qh6", testGame2MoveText.get(18));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "f5", testGame2MoveText.get(19));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "exf6 e.p.", testGame2MoveText.get(20));
        moveCounter++;
        assertEquals("testGame2 move " + moveCounter + " incorrect", "Qxd1#", testGame2MoveText.get(21));
        
        moveCounter= 1;
        List<String> stalemate1MoveText= stalemate1.getMoveTextList();
        assertEquals("stalemate1MoveText wrong size", 19, stalemate1MoveText.size());

        assertEquals("stalemate1 move " + moveCounter + " incorrect", "e3", stalemate1MoveText.get(0));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "a5", stalemate1MoveText.get(1));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Qh5", stalemate1MoveText.get(2));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Ra6", stalemate1MoveText.get(3));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Qxa5", stalemate1MoveText.get(4));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "h5", stalemate1MoveText.get(5));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Qxc7", stalemate1MoveText.get(6));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Rah6", stalemate1MoveText.get(7));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "h4", stalemate1MoveText.get(8));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "f6", stalemate1MoveText.get(9));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Qxd7+", stalemate1MoveText.get(10));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Kf7", stalemate1MoveText.get(11));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Qxb7", stalemate1MoveText.get(12));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Qd3", stalemate1MoveText.get(13));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Qxb8", stalemate1MoveText.get(14));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Qh7", stalemate1MoveText.get(15));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Qxc8", stalemate1MoveText.get(16));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Kg6", stalemate1MoveText.get(17));
        moveCounter++;
        assertEquals("stalemate1 move " + moveCounter + " incorrect", "Qe6", stalemate1MoveText.get(18));

        moveCounter= 1;
        List<String> inProgressWithMovesMoveText= inProgressWithMoves.getMoveTextList();
        assertEquals("inProgressWithMovesMoveText wrong size", 6, inProgressWithMovesMoveText.size());
        
        assertEquals("inProgressWithMoves move " + moveCounter + " incorrect", "e3", inProgressWithMovesMoveText.get(0));
        moveCounter++;
        assertEquals("inProgressWithMoves move " + moveCounter + " incorrect", "a5", inProgressWithMovesMoveText.get(1));
        moveCounter++;
        assertEquals("inProgressWithMoves move " + moveCounter + " incorrect", "Qh5", inProgressWithMovesMoveText.get(2));
        moveCounter++;
        assertEquals("inProgressWithMoves move " + moveCounter + " incorrect", "Ra6", inProgressWithMovesMoveText.get(3));
        moveCounter++;
        assertEquals("inProgressWithMoves move " + moveCounter + " incorrect", "Qxa5", inProgressWithMovesMoveText.get(4));
        moveCounter++;
        assertEquals("inProgressWithMoves move " + moveCounter + " incorrect", "h5", inProgressWithMovesMoveText.get(5));
        
        List<String> inProgressNoMovesMoveText= inProgressNoMoves.getMoveTextList();
        assertEquals("inProgressNoMovesMoveText wrong size", 0, inProgressNoMovesMoveText.size());
    }

    //PGNFiles for use with constructor with file param
    //PGNFile adams, checkmate, stalemate, noSpaceBWTagPairsAndMoves, inProgressWithMoves1, inProgressNoMoves1;

    //PGNFiles for use with constructor with members parameters
    //PGNFile testGame, testGame2, stalemate1, inProgressWithMoves, inProgressNoMoves;

    @Test
    public void testGetResult(){
        String adamsResult= adams.getResult();
        assertEquals("adamsResult wrong", "1-0", adamsResult);

        String checkmateResult= checkmate.getResult();
        assertEquals("checkmateResult wrong", "1-0", checkmateResult);

        String stalemateResult= stalemate.getResult();
        assertEquals("stalemateResult wrong", "1/2-1/2", stalemateResult);

        String noSpaceBWTagPairsAndMovesResult= noSpaceBWTagPairsAndMoves.getResult();
        assertEquals("noSpaceBWTagPairsAndMovesResult wrong", "0-1", noSpaceBWTagPairsAndMovesResult);

        String inProgressWithMoves1Result= inProgressWithMoves1.getResult();
        assertEquals("inProgressWithMoves1Result wrong", "*", inProgressWithMoves1Result);

        String inProgressNoMoves1Result= inProgressNoMoves1.getResult();
        assertEquals("inProgressNoMoves1Result wrong", "*", inProgressNoMoves1Result);

        String testGameResult= testGame.getResult();
        assertEquals("testGameResult wrong", "1-0", testGameResult);

        String testGame2Result= testGame2.getResult();
        assertEquals("testGame2Result wrong", "0-1", testGame2Result);

        String inProgressWithMovesResult= inProgressWithMoves.getResult();
        assertEquals("inProgressWithMovesResult wrong", "*", inProgressWithMovesResult);

        String inProgressNoMovesResult= inProgressNoMoves.getResult();
        assertEquals("inProgressNoMovesResult wrong", "*", inProgressNoMovesResult);
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

        assertEquals("adams file text incorrect", adamsExpectedText, adamsFileText);

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
        
        assertEquals("checkmate file text incorrect", checkmateExpectedText, checkmateFileText);

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
        
        assertEquals("stalemate file text incorrect", stalemateExpectedText, stalemateFileText);

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
        
        assertEquals("noSpaceBWTagPairsAndMoves file text incorrect", noSpaceBWTagPairsAndMovesExpectedText, noSpaceBWTagPairsAndMovesFileText);

        String inProgressWithMoves1FileText= inProgressWithMoves1.getFileText();
        String inProgressWithMoves1ExpectedText=
            "[Event \"TestEvent\"]\n" +
            "[Site \"Edmonton\"]\n" +
            "[Date \"2021.??.??\"]\n" +
            "[Round \"\"]\n" +
            "[White \"Player, White\"]\n" +
            "[Black \"Player, Black\"]\n" +
            "[Result \"*\"]\n" +
            "\n" +
            "1. e4 a6 2. Qf3 a5 *";
        
        assertEquals("inProgressWithMoves1 file text incorrect", inProgressWithMoves1ExpectedText, inProgressWithMoves1FileText);

        String inProgressNoMoves1FileText= inProgressNoMoves1.getFileText();
        String inProgressNoMoves1ExpectedText=
            "[Event \"TestEvent\"]\n" +
            "[Site \"Edmonton\"]\n" +
            "[Date \"2021.??.??\"]\n" +
            "[Round \"\"]\n" +
            "[White \"Player, White\"]\n" +
            "[Black \"Player, Black\"]\n" +
            "[Result \"*\"]\n" +
            "\n" +
            "*";
        
        assertEquals("inProgressNoMoves1 file text incorrect", inProgressNoMoves1ExpectedText, inProgressNoMoves1FileText);
    }

    @After
    public void teardown(){

    }
}