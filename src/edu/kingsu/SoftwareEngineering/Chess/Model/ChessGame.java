package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.List;
import java.util.Map;
import java.util.Timer;

import edu.kingsu.SoftwareEngineering.Chess.Model.Moves.*;

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
    private List<Move> moveHistory;
    private List<String> algebraicHistory;
    private int moveNo;
    // private List<ChessGameView> views;

    // Overall game time
    private int interval;
    private Timer timer;

    public ChessGame(int playerInterval, int playerIncrement) {
        this.playerInterval = playerInterval;
        this.playerIncrement = playerIncrement;
        initialize();
    }

    public void initialize() {
        initialize(true, false);
    }

    public void initialize(boolean whiteIsHuman, boolean blackIsHuman) {
        if (whiteIsHuman) {
            whitePlayer = new PlayerHuman(this, true, playerInterval, playerIncrement);
        } else {
            whitePlayer = new PlayerAI(this, true, playerInterval, playerIncrement);
        }
        if (blackIsHuman) {
            blackPlayer = new PlayerHuman(this, false, playerInterval, playerIncrement);
        } else {
            blackPlayer = new PlayerAI(this, false, playerInterval, playerIncrement);
        }
        whitePlayerThread = new Thread(whitePlayer);
        blackPlayerThread = new Thread(blackPlayer);
        board = new Board();
    }

    public int getPlayerInterval() { return playerInterval; }

    public int getPlayerIncrement() { return playerIncrement; }

    public Map<String, String> tagPairMap() { return tagPairs; }

    public Board getBoard() { return board; }

    public Player getPlayerTurn() { return playerTurn; }

    public List<Move> getMoveHistory() { return moveHistory; }

    public List<String> getAlgebraicHistory() { return algebraicHistory; }

    public GameState getState() { return GameState.ACTIVE; }

    // public void registerView(ChessGameView view) {
    // }

    public void notifyViews() {
    }

    public synchronized boolean performMove(Move move) {
        if (validateMove(move)) {
            move.perform(board);
            moveHistory.add(move);
            // algebraicHistory.add("");
            (new Thread(new Runnable() {
                public void run() {
                    playerTurn.stop();
                }
            })).start();
            playerTurn = playerTurn == whitePlayer ? blackPlayer : whitePlayer;
            playerTurn.getLock().notify();
            return true;
        } else {
            return false;
        }
    }

    public boolean performMove(String movePgn) {
        return true;
    }

    private boolean validateMove(Move move) {
        return board.getAllMoves(getPlayerTurn().isWhite()).contains(move);
    }

    public void start() {
        whitePlayerThread.start();
        blackPlayerThread.start();
        playerTurn.getLock().notify();
    }

    public void gameOver() {
    }

    private void setInterval() {
    }

    public boolean undo() {
        return true;
    }

    public boolean redo() {
        return true;
    }

    public void resetTimers() {
    }
}
