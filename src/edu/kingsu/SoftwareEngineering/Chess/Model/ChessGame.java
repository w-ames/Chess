package edu.kingsu.SoftwareEngineering.Chess.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import edu.kingsu.SoftwareEngineering.Chess.GUI.ChessGameView;
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
    private final Object playerTurnLock;
    private List<Move> moveHistory;
    private List<String> algebraicHistory;
    private int moveNo;
    private List<ChessGameView> views;

    // Overall game time
    private int interval;
    private Timer timer;

    public ChessGame(int whiteAI, int blackAI, int playerInterval, int playerIncrement) {
        playerTurnLock = new Object();
        views = new ArrayList<ChessGameView>();
        initialize(whiteAI, blackAI, playerInterval, playerIncrement);
    }

    public void initialize() {
        initialize(-1, 0, -1, -1);
    }

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

    public char[][] getBoardChars() { return board.getChars(); }

    public int getPlayerInterval() { return playerInterval; }

    public int getPlayerIncrement() { return playerIncrement; }

    public Map<String, String> tagPairMap() { return tagPairs; }

    public Board getBoard() { return board; }

    public Player getPlayerTurn() {
        synchronized (playerTurnLock) {
            return playerTurn;
        }
    }

    public List<Move> getMoveHistory() { return moveHistory; }

    public List<String> getAlgebraicHistory() { return algebraicHistory; }

    public GameState getState() { return GameState.ACTIVE; }

    public void registerView(ChessGameView view) {
        views.add(view);
    }

    public void notifyViews() {
        for (ChessGameView view : views) {
            view.update();
        }
    }

    public boolean performMove(int rowFrom, int colFrom, int rowTo, int colTo, boolean humanMoveMaker) {
        for (Move m : getBoard().getMoves(rowFrom, colFrom)) {
            if (m.hasDestination(rowTo, colTo)) {
                return performMove(m, humanMoveMaker);
            }
        }
        return false;
    }

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

    public boolean performMove(String movePgn, boolean humanMoveMaker) {
        return true;
    }

    private boolean validateMove(Move move) {
        return (board.getPiece(move.getRowFrom(), move.getColFrom()).isWhite() == getPlayerTurn().isWhite()) && (board.getMoves(move.getRowFrom(), move.getColFrom()).contains(move));
    }

    public void start() {
        synchronized (this) {
            notifyViews();
            whitePlayerThread.start();
            blackPlayerThread.start();
            this.notifyAll();
        }
    }

    public void stop() {
        whitePlayerThread.interrupt();
        blackPlayerThread.interrupt();
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
