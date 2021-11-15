package edu.kingsu.SoftwareEngineering.Chess.Tests.PGN;

import edu.kingsu.SoftwareEngineering.Chess.PGN.PGNTranslator;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.Pieces.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;


public class TestPGNTranslator {
    private Board testBoard;

    @Before
    public void setup(){
        testBoard= new Board();
    }

    //Several different test boards that test all possible kinds of moves. Use them to test both translateMoveToPGN and translatePGNToMove
    private static final char[][] PAWN_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ','p',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testPawnMoveToPGN() {
        testBoard.initializeBoard(PAWN_MOVE);
        assertEquals("Incorrect PGN String returned for pawn move", "c3", PGNTranslator.translateMoveToPGN(new Move(6,2,5,2), testBoard));
    }

    @Test
    public void testPawnSinglePGNToMove(){
        testBoard.initializeBoard(PAWN_MOVE);
        assertEquals("Incorrect Move returned for single pawn move", new Move(6,2,5,2), PGNTranslator.translatePGNToMove("c3", testBoard, true));
    }

    @Test
    public void testPawnDoublePGNToMove(){
        testBoard.initializeBoard(PAWN_MOVE);
        assertEquals("Incorrect Move returned for double pawn move", new PawnDoubleMove(6,2,4,2), PGNTranslator.translatePGNToMove("c4", testBoard, true));
    }

    private static final char[][] KNIGHT_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ','n',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testKnightMoveToPGN() {
        testBoard.initializeBoard(KNIGHT_MOVE);
        assertEquals("Incorrect PGN String returned for knight move", "Nd4", PGNTranslator.translateMoveToPGN(new Move(6,2,4,3), testBoard));
    }

    @Test
    public void testKnightPGNToMove(){
        testBoard.initializeBoard(KNIGHT_MOVE);
        assertEquals("Incorrect Move returned for knight move", new Move(6,2,4,3), PGNTranslator.translatePGNToMove("Nd4", testBoard, true));
    }

    private static final char[][] BISHOP_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ','b',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testBishopMoveToPGN() {
        testBoard.initializeBoard(BISHOP_MOVE);
        assertEquals("Incorrect PGN String returned for bishop move", "Bd3", PGNTranslator.translateMoveToPGN(new Move(6,2,5,3), testBoard));
    }

    @Test
    public void testBishopPGNToMove(){
        testBoard.initializeBoard(BISHOP_MOVE);
        assertEquals("Incorrect Move returned for bishop move", new Move(6,2,5,3), PGNTranslator.translatePGNToMove("Bd3", testBoard, true));
    }

    private static final char[][] ROOK_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ','r',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testRookMoveToPGN() {
        testBoard.initializeBoard(ROOK_MOVE);
        assertEquals("Incorrect PGN String returned for rook move", "Rb2", PGNTranslator.translateMoveToPGN(new Move(6,2,6,1), testBoard));
    }

    @Test
    public void testRookPGNToMove(){
        testBoard.initializeBoard(ROOK_MOVE);
        assertEquals("Incorrect Move returned for rook move", new Move(6,2,6,1), PGNTranslator.translatePGNToMove("Rb2", testBoard, true));
    }

    private static final char[][] QUEEN_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ','q',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testQueenMoveToPGN() {
        testBoard.initializeBoard(QUEEN_MOVE);
        assertEquals("Incorrect PGN String returned for queen move", "Qb2", PGNTranslator.translateMoveToPGN(new Move(6,2,6,1), testBoard));
    }

    @Test
    public void testQueenPGNToMove(){
        testBoard.initializeBoard(QUEEN_MOVE);
        assertEquals("Incorrect Move returned for queen move", new Move(6,2,6,1), PGNTranslator.translatePGNToMove("Qb2", testBoard, true));
    }

    private static final char[][] KING_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'p',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testKingMoveToPGN() {
        testBoard.initializeBoard(KING_MOVE);
        assertEquals("Incorrect PGN String returned for king move", "Kc1", PGNTranslator.translateMoveToPGN(new Move(7,3,7,2), testBoard));
    }

    @Test
    public void testKingPGNToMove(){
        testBoard.initializeBoard(KING_MOVE);
        assertEquals("Incorrect Move returned for king move", new Move(7,3,7,2), PGNTranslator.translatePGNToMove("Kc1", testBoard, true));
    }

    private static final char[][] CAPTURE_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ','p',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testCaptureMoveToPGN(){
        testBoard.initializeBoard(CAPTURE_MOVE);
        assertEquals("Incorrect PGN String returned for capture move", "Kxd7", PGNTranslator.translateMoveToPGN(new Move(0,3,1,3), testBoard));
    }

    @Test
    public void testCapturePGNToMove(){
        testBoard.initializeBoard(CAPTURE_MOVE);
        assertEquals("Incorrect Move returned for capture move", new Move(0,3,1,3), PGNTranslator.translatePGNToMove("Kxd7", testBoard, false));
    }

    private static final char[][] DISAMB_FILE_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'r',' ',' ',' ',' ',' ',' ','r'},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testDisambFileMoveToPGN() {
        testBoard.initializeBoard(DISAMB_FILE_MOVE);
        assertEquals("Incorrect PGN String returned for disambiguation file move", "Rab2", PGNTranslator.translateMoveToPGN(new Move(6,0,6,1), testBoard));
    }

    @Test
    public void testDisambFilePGNToMove(){
        testBoard.initializeBoard(DISAMB_FILE_MOVE);
        assertEquals("Incorrect Move returned for disambiguation file move", new Move(6,0,6,1), PGNTranslator.translatePGNToMove("Rab2", testBoard, true));
    }

    private static final char[][] DISAMB_RANK_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ','r'},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ','r'},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testDisambRankMoveToPGN() {
        testBoard.initializeBoard(DISAMB_RANK_MOVE);
        assertEquals("Incorrect PGN String returned for disambiguation rank move", "R2h3", PGNTranslator.translateMoveToPGN(new Move(6,7,5,7), testBoard));
    }

    @Test
    public void testDisambRankPGNToMove(){
        testBoard.initializeBoard(DISAMB_RANK_MOVE);
        assertEquals("Incorrect Move returned for disambiguation rank move", new Move(6,7,5,7), PGNTranslator.translatePGNToMove("R2h3", testBoard, true));
    }

    private static final char[][] DISAMB_BOTH_QUEEN_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ','P','P','P',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ','q',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ','q',' ','q'},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testDisambBothQueenMoveToPGN() {
        testBoard.initializeBoard(DISAMB_BOTH_QUEEN_MOVE);
        assertEquals("Incorrect PGN String returned for disambiguation both move", "Qf2h4", PGNTranslator.translateMoveToPGN(new Move(6,5,4,7), testBoard));
    }

    @Test
    public void testDisambBothQueenPGNToMove(){
        testBoard.initializeBoard(DISAMB_BOTH_QUEEN_MOVE);
        assertEquals("Incorrect Move returned for disambiguation both move", new Move(6,5,4,7), PGNTranslator.translatePGNToMove("Qf2h4", testBoard, true));
    }

    private static final char[][] DISAMB_FILE_KNIGHT_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ','P','P','P',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ','n'},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ','n',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testDisambFileKnightMoveToPGN() {
        testBoard.initializeBoard(DISAMB_FILE_KNIGHT_MOVE);
        assertEquals("Incorrect PGN String returned for disambiguation both move", "Nhg4", PGNTranslator.translateMoveToPGN(new Move(2,7,4,6), testBoard));
    }

    @Test
    public void testDisambFileKnightPGNToMove(){
        testBoard.initializeBoard(DISAMB_FILE_KNIGHT_MOVE);
        assertEquals("Incorrect Move returned for disambiguation both move", new Move(2,7,4,6), PGNTranslator.translatePGNToMove("Nhg4", testBoard, true));
    }

    private static final char[][] DISAMB_RANK_KNIGHT_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ','P','P','P',' ',' '},
        {'n',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'n',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testDisambRankKnightMoveToPGN() {
        testBoard.initializeBoard(DISAMB_RANK_KNIGHT_MOVE);
        assertEquals("Incorrect PGN String returned for disambiguation both move", "N2b4", PGNTranslator.translateMoveToPGN(new Move(6,0,4,1), testBoard));
    }

    @Test
    public void testDisambRankKnightPGNToMove(){
        testBoard.initializeBoard(DISAMB_RANK_KNIGHT_MOVE);
        assertEquals("Incorrect Move returned for disambiguation both move", new Move(6,0,4,1), PGNTranslator.translatePGNToMove("N2b4", testBoard, true));
    }

    private static final char[][] PAWN_PROMO_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ','p',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'K',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testPawnPromoMoveToPGN() {
        testBoard.initializeBoard(PAWN_PROMO_MOVE);
        assertEquals("Incorrect PGN String returned for pawn promo move", "f8=Q", PGNTranslator.translateMoveToPGN(new PawnPromotionMove(1,5,0,5,PieceType.QUEEN), testBoard));
    }

    @Test
    public void testPawnPromoPGNToMove(){
        testBoard.initializeBoard(PAWN_PROMO_MOVE);
        assertEquals("Incorrect Move returned for pawn promo move", new PawnPromotionMove(1,5,0,5,PieceType.QUEEN), PGNTranslator.translatePGNToMove("f8=Q", testBoard, true));
    }

    private static final char[][] CHECK_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'r',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testCheckMoveToPGN() {
        testBoard.initializeBoard(CHECK_MOVE);
        assertEquals("Incorrect PGN String returned for check move", "Rd2+", PGNTranslator.translateMoveToPGN(new Move(6,0,6,3), testBoard));
    }

    @Test
    public void testCheckPGNToMove(){
        testBoard.initializeBoard(CHECK_MOVE);
        assertEquals("Incorrect Move returned for check move", new Move(6,0,6,3), PGNTranslator.translatePGNToMove("Rd2+", testBoard, true));
    }

    private static final char[][] CHECKMATE_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ','q',' '},
        {' ',' ',' ',' ',' ',' ',' ','r'},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testCheckmateMoveToPGN() {
        testBoard.initializeBoard(CHECKMATE_MOVE);
        assertEquals("Incorrect PGN String returned for checkmate move", "Rh8#", PGNTranslator.translateMoveToPGN(new Move(2,7,0,7), testBoard));
    }

    @Test
    public void testCheckmateVariation1PGNToMove(){
        testBoard.initializeBoard(CHECKMATE_MOVE);
        assertEquals("Incorrect Move returned for checkmate move", new Move(2,7,0,7), PGNTranslator.translatePGNToMove("Rh8#", testBoard, true));
    }

    @Test
    public void testCheckmateVariation2PGNToMove(){
        testBoard.initializeBoard(CHECKMATE_MOVE);
        assertEquals("Incorrect Move returned for checkmate move", new Move(2,7,0,7), PGNTranslator.translatePGNToMove("Rh8++", testBoard, true));
    }

    private static final char[][] EN_PASSANT_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ','P','p',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ','k',' ',' ',' ',' '},
    };

    @Test
    public void testEnPassantMoveToPGN() {
        testBoard.initializeBoard(EN_PASSANT_MOVE);
        testBoard.setEnPassantable(2,4);
        assertEquals("Incorrect PGN String returned for en passant move", "xe6 e.p.", PGNTranslator.translateMoveToPGN(new EnPassantMove(3,5,2,4,3,4), testBoard));
    }

    @Test
    public void testEnPassantVariation1PGNToMove(){
        testBoard.initializeBoard(EN_PASSANT_MOVE);
        testBoard.setEnPassantable(2,4);
        assertEquals("Incorrect Move returned for en passant move", new EnPassantMove(3,5,2,4,3,4), PGNTranslator.translatePGNToMove("xe6 e.p.", testBoard, true));
    }

    @Test
    public void testEnPassantVariation2PGNToMove(){
        testBoard.initializeBoard(EN_PASSANT_MOVE);
        testBoard.setEnPassantable(2,4);
        assertEquals("Incorrect Move returned for en passant move", new EnPassantMove(3,5,2,4,3,4), PGNTranslator.translatePGNToMove("xe6 ep", testBoard, true));
    }

    @Test
    public void testEnPassantVariation3PGNToMove(){
        testBoard.initializeBoard(EN_PASSANT_MOVE);
        testBoard.setEnPassantable(2,4);
        assertEquals("Incorrect Move returned for en passant move", new EnPassantMove(3,5,2,4,3,4), PGNTranslator.translatePGNToMove("xe6e.p.", testBoard, true));
    }

    @Test
    public void testEnPassantVariation4PGNToMove(){
        testBoard.initializeBoard(EN_PASSANT_MOVE);
        testBoard.setEnPassantable(2,4);
        assertEquals("Incorrect Move returned for en passant move", new EnPassantMove(3,5,2,4,3,4), PGNTranslator.translatePGNToMove("xe6ep", testBoard, true));
    }

    private static final char[][] CASTLE_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ','P','P','P',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'r',' ',' ',' ','k',' ',' ','r'},
    };

    @Test
    public void testCastleVariation1MoveToPGN() {
        testBoard.initializeBoard(CASTLE_MOVE);
        assertEquals("Incorrect PGN String returned for castle move", "O-O-O", PGNTranslator.translateMoveToPGN(new CastlingMove(7,4,7,2,7,0,7,3), testBoard));
    }

    @Test
    public void testCastleVariation2MoveToPGN() {
        testBoard.initializeBoard(CASTLE_MOVE);
        assertEquals("Incorrect PGN String returned for castle move", "O-O", PGNTranslator.translateMoveToPGN(new CastlingMove(7,4,7,6,7,7,7,5), testBoard));
    }

    @Test
    public void testCastleVariation1PGNToMove(){
        System.err.println("Castle test");
        testBoard.initializeBoard(CASTLE_MOVE);
        assertEquals("Incorrect Move returned for castle move", new CastlingMove(7,4,7,2,7,0,7,3), PGNTranslator.translatePGNToMove("O-O-O", testBoard, true));
    }

    @Test
    public void testCastleVariation2PGNToMove(){
        testBoard.initializeBoard(CASTLE_MOVE);
        assertEquals("Incorrect Move returned for castle move", new CastlingMove(7,4,7,2,7,0,7,3), PGNTranslator.translatePGNToMove("OOO", testBoard, true));
    }

    @Test
    public void testCastleVariation3PGNToMove(){
        testBoard.initializeBoard(CASTLE_MOVE);
        assertEquals("Incorrect Move returned for castle move", new CastlingMove(7,4,7,6,7,7,7,5), PGNTranslator.translatePGNToMove("O-O", testBoard, true));
    }

    @Test
    public void testCastleVariation4PGNToMove(){
        testBoard.initializeBoard(CASTLE_MOVE);
        assertEquals("Incorrect Move returned for castle move", new CastlingMove(7,4,7,6,7,7,7,5), PGNTranslator.translatePGNToMove("OO", testBoard, true));
    }

    private static final char[][] CASTLE_CHECK_MOVE = { //capital=black, lowercase=white
        {' ',' ',' ','K',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'r',' ',' ',' ','k',' ',' ','r'},
    };

    @Test
    public void testCastleCheckMoveToPGN() {
        testBoard.initializeBoard(CASTLE_CHECK_MOVE);
        assertEquals("Incorrect PGN String returned for castle check move", "O-O-O+", PGNTranslator.translateMoveToPGN(new CastlingMove(7,4,7,2,7,0,7,3), testBoard));
    }

    @Test
    public void testCastleCheckVariation1PGNToMove(){
        testBoard.initializeBoard(CASTLE_CHECK_MOVE);
        assertEquals("Incorrect Move returned for castle check move", new CastlingMove(7,4,7,2,7,0,7,3), PGNTranslator.translatePGNToMove("O-O-O+", testBoard, true));
    }

    @Test
    public void testCastleCheckVariation2PGNToMove(){
        testBoard.initializeBoard(CASTLE_CHECK_MOVE);
        assertEquals("Incorrect Move returned for castle check move", new CastlingMove(7,4,7,2,7,0,7,3), PGNTranslator.translatePGNToMove("OOO+", testBoard, true));
    }

    private static final char[][] CASTLE_CHECKMATE_MOVE = { //capital=black, lowercase=white
        {' ',' ','R','K','R',' ',' ',' '},
        {' ',' ','P',' ','P',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'r',' ',' ',' ','k',' ',' ','r'},
    };

    @Test
    public void testCastleCheckmateMoveToPGN() {
        testBoard.initializeBoard(CASTLE_CHECKMATE_MOVE);
        assertEquals("Incorrect PGN String returned for castle check move", "O-O-O#", PGNTranslator.translateMoveToPGN(new CastlingMove(7,4,7,2,7,0,7,3), testBoard));
    }

    @Test
    public void testCastleCheckmateVariation1PGNToMove(){
        testBoard.initializeBoard(CASTLE_CHECKMATE_MOVE);
        assertEquals("Incorrect Move returned for castle check move", new CastlingMove(7,4,7,2,7,0,7,3), PGNTranslator.translatePGNToMove("O-O-O#", testBoard, true));
    }

    @Test
    public void testCastleCheckmateVariation2PGNToMove(){
        testBoard.initializeBoard(CASTLE_CHECKMATE_MOVE);
        assertEquals("Incorrect Move returned for castle check move", new CastlingMove(7,4,7,2,7,0,7,3), PGNTranslator.translatePGNToMove("OOO#", testBoard, true));
    }

    @Test
    public void testCastleCheckmateVariation3PGNToMove(){
        testBoard.initializeBoard(CASTLE_CHECKMATE_MOVE);
        assertEquals("Incorrect Move returned for castle check move", new CastlingMove(7,4,7,2,7,0,7,3), PGNTranslator.translatePGNToMove("O-O-O++", testBoard, true));
    }

    @Test
    public void testCastleCheckmateVariation4PGNToMove(){
        testBoard.initializeBoard(CASTLE_CHECKMATE_MOVE);
        assertEquals("Incorrect Move returned for castle check move", new CastlingMove(7,4,7,2,7,0,7,3), PGNTranslator.translatePGNToMove("OOO++", testBoard, true));
    }

    @After
    public void teardown(){
        testBoard= null;
    }
}
