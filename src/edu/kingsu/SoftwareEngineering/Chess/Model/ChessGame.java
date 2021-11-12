package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import edu.kingsu.SoftwareEngineering.Chess.GUI.ChessGameView;
import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

/**
 * This class fully represents a game of chess involving all its components.
 */
public class ChessGame {
    private Map<String, String> tagPairs;
    private Player whitePlayer;
    private Thread whitePlayerThread;
    private Player blackPlayer;
    private Thread blackPlayerThread;
    private Board board;
    private int playerInterval;
    private int playerIncrement;
    private Player playerTurn;
    private final Object playerTurnLock;
    private List<Move> moveHistory;
    private List<String> algebraicHistory;
    private int moveNo;
    private List<ChessGameView> views;

    // Overall game time
    private int interval;
    private Timer timer;

    /**
     * Creates a new chess game.
     * @param whiteAI an integer determining the level AI of the white player.
     *  negative one signifies a human player, zero is a random player, and
     *  positive values indicate an AI with the given minimax depth of search
     * @param blackAI an integer determining the level AI of the black player.
     *  negative one signifies a human player, zero is a random player, and
     *  positive values indicate an AI with the given minimax depth of search
     * @param playerInterval the maximum time allotted to player's clocks
     * @param playerIncrement the increment of time given to player's clocks
     *  upon making a move
     */
    public ChessGame(int whiteAI, int blackAI, int playerInterval, int playerIncrement) {
        playerTurnLock = new Object();
        views = new ArrayList<ChessGameView>();
        initialize(whiteAI, blackAI, playerInterval, playerIncrement);
    }

    /**
     * Initializes the chess game using default values.
     * TODO default constants
     */
    public void initialize() {
        initialize(-1, 0, -1, -1);
    }

    /**
     * Initializes the chess game.
     * @param whiteAI an integer determining the level AI of the white player.
     *  negative one signifies a human player, zero is a random player, and
     *  positive values indicate an AI with the given minimax depth of search
     * @param blackAI an integer determining the level AI of the black player.
     *  negative one signifies a human player, zero is a random player, and
     *  positive values indicate an AI with the given minimax depth of search
     * @param playerInterval the maximum time allotted to player's clocks
     * @param playerIncrement the increment of time given to player's clocks
     *  upon making a move
     */
    public void initialize(int whiteAI, int blackAI, int playerInterval, int playerIncrement) {
        moveHistory = new ArrayList<Move>();
        algebraicHistory = new ArrayList<String>();
        this.playerInterval = playerInterval;
        this.playerIncrement = playerIncrement;
        if (whiteAI == -1) {
            whitePlayer = new PlayerHuman(this, true, playerInterval, playerIncrement);
        } else {
            whitePlayer = new PlayerAI(this, true, playerInterval, playerIncrement, whiteAI);
        }
        if (blackAI == -1) {
            blackPlayer = new PlayerHuman(this, false, playerInterval, playerIncrement);
        } else {
            blackPlayer = new PlayerAI(this, false, playerInterval, playerIncrement, blackAI);
        }
        playerTurn = whitePlayer;
        whitePlayerThread = new Thread(whitePlayer);
        blackPlayerThread = new Thread(blackPlayer);
        board = new Board();
    }

    /**
     * Returns a 2-dimensional array of characters indicating the placement of
     * pieces on the board. See {@link Board#getChars} for details.
     */
    public char[][] getBoardChars() { return board.getChars(); }

    /**
     * Returns the maximum time allotted to player's clocks.
     * @return the maximum time allotted to player's clocks
     */
    public int getPlayerInterval() { return playerInterval; }

    /**
     * Returns the time allotted to player's clocks upon making moves.
     * @return the time allotted to player's clocks upon making moves
     */
    public int getPlayerIncrement() { return playerIncrement; }

    /**
     * Returns the map of PGN tag pairs associated with this game.
     * @return the map of PGN tag pairs associated with this game
     */
    public Map<String, String> tagPairMap() { return tagPairs; }

    /**
     * Returns the board associated with this game.
     * @return the board associated with this game
     */
    public Board getBoard() { return board; }

    /**
     * Returns the player whose turn it currently is.
     * @return the player whose turn it currently is
     */
    public Player getPlayerTurn() {
        synchronized (playerTurnLock) {
            return playerTurn;
        }
    }

    /**
     * Returns the list of moves performed in this game, in order of occurrence.
     * @return the list of moves performed in this game, in order of occurrence.
     */
    public List<Move> getMoveHistory() { return moveHistory; }

    /**
     * Returns the list of PGN-format moves performed in this game, in order of occurrence.
     * @return the list of PGN-format moves performed in this game, in order of occurrence.
     */
    public List<String> getAlgebraicHistory() { return algebraicHistory; }

    /**
     * Returns the current state of the game.
     * @return the current state of the game.
     */
    public GameState getState() { return GameState.ACTIVE; }

    /**
     * Registers a view with the board, to be notified of changes.
     * @param view the view to register with this game of chess
     */
    public void registerView(ChessGameView view) {
        views.add(view);
    }

    /**
     * Notifies all registered views to re-query the state of the board.
     */
    public void notifyViews() {
        for (ChessGameView view : views) {
            view.update();
        }
    }

    /**
     * Attempts to perform a move in this game of chess.
     * @param rowFrom the row from which to move a piece
     * @param colFrom the col from which to move a piece
     * @param rowTo the row to which to move a piece
     * @param colTo the col to which to move a piece
     * @param humanMoveMaker <code>true</code> if this move is attempted to be
     *  made by a human, <code>false</code> if AI
     * @return <code>true</code> if the move attempt is successful,
     *  <code>false</code> otherwise
     */
    public boolean performMove(int rowFrom, int colFrom, int rowTo, int colTo, boolean humanMoveMaker) {
        for (Move m : getBoard().getMoves(rowFrom, colFrom)) {
            if (m.hasDestination(rowTo, colTo)) {
                return performMove(m, humanMoveMaker);
            }
        }
        return false;
    }

    /**
     * Attempts to perform a move in this game of chess.
     * @param move the move to perform in this game of chess
     * @param humanMoveMaker <code>true</code> if this move is attempted to be
     *  made by a human, <code>false</code> if AI
     * @return <code>true</code> if the move attempt is successful,
     *  <code>false</code> otherwise
     */
    public  boolean performMove(Move move, boolean humanMoveMaker) {
        synchronized (this) {
            if (humanMoveMaker == getPlayerTurn().isHuman() && validateMove(move)) {
                synchronized (playerTurnLock) {
                    move.perform(board);
                    moveHistory.add(move);
                    // algebraicHistory.add("");
                    // (new Thread(new Runnable() {
                    //     public void run() {
                    //         playerTurn.stop();
                    //     }
                    // })).start();
                    playerTurn = playerTurn == whitePlayer ? blackPlayer : whitePlayer;
                    notifyViews();
                    this.notifyAll();
                }
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Attempts to perform a move in this game of chess.
     * @param movePgn the PGN-format string corresponding to the move to be
     *  made in this game of chess
     * @param humanMoveMaker <code>true</code> if this move is attempted to be
     *  made by a human, <code>false</code> if AI
     * @return <code>true</code> if the move attempt is successful,
     *  <code>false</code> otherwise
     */
    public boolean performMove(String movePgn, boolean humanMoveMaker) {
        return true;
    }

    /**
     * Returns a boolean indicating whether the given move is currently valid
     * in this game of chess.
     * @param move the move to be checked for validity
     * @return <code>true</code> if the given move is currently valid,
     *  <code>false</code> if it is not
     */
    private boolean validateMove(Move move) {
        return (board.getPiece(move.getRowFrom(), move.getColFrom()).isWhite() == getPlayerTurn().isWhite()) && (board.getMoves(move.getRowFrom(), move.getColFrom()).contains(move));
    }

    /**
     * Begins the player threads for this game of chess, such that clocks begin
     * and AI players move when it's their turn.
     */
    public void start() {
        synchronized (this) {
            notifyViews();
            whitePlayerThread.start();
            blackPlayerThread.start();
            this.notifyAll();
        }
    }

    /**
     * Stops the player threads for this game of chess, such that clocks do not
     * tick down, and AI players do not move on their turn.
     */
    public void stop() {
        whitePlayerThread.interrupt();
        blackPlayerThread.interrupt();
    }

    /**
     * Ends this game of chess according to the current game state.
     */
    public void gameOver() {
    }

    private void setInterval() {
    }

    /**
     * Undoes the most recent move in this game of chess.
     * @return <code>true</code> if a move was successfully undone, and
     *  <code>false</code> if unsuccessful, such as due to their being no
     *  moves to undo
     */
    public boolean undo() {
        return true;
    }

    /**
     * Redoes the most recent move in this game of chess.
     * @return <code>true</code> if a move was successfully redone, and
     *  <code>false</code> if unsuccessful, such as due to their being no
     *  moves to redo
     */
    public boolean redo() {
        return true;
    }

    /**
     * Resets the clocks for the players of this game of chess.
     */
    public void resetTimers() {
    }
}
