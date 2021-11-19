package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.Color;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

/**
 * ChessPanel holds all components of gameplay mode display.
 * 
 * @author Chelsie Bajic
 */
public class ChessPanel extends ChessGameView implements MouseListener {

    private JLayeredPane layeredPane = new JLayeredPane();
    private MainLayer mainLayer = new MainLayer();
    private PopupLayer endGameOptions = new PopupLayer();
    private PopupLayer pawnPromotionScreen = new PopupLayer();
    private ChessGame chessGame;
    private ChessGameGUIView guiView = new ChessGameGUIView();
    private ChessGameAlgebraicView algebraicView = new ChessGameAlgebraicView();
    // private ChessGameSaveView saveView;
    private ChessGameMessagesView messagesView;
    // private ChessGameLoadView loadView;

    private ClockView player1Clock = new ClockView();
    private ClockView player2Clock = new ClockView();
    private ClockView totalGameTime = new ClockView();
    private ButtonContainer buttonContainer = new ButtonContainer();

    private CustomButton undoButton = new CustomButton("Undo");
    private CustomButton redoButton = new CustomButton("Redo");
    private CustomButton moveHintButton = new CustomButton("Hint");
    private CustomButton resignButton = new CustomButton("Resign");
    private CustomButton pieceInfo = new CustomButton("About Piece");
    private CustomButton showEndGameOptionsButton = new CustomButton("View End Game Options");

    // endGameState is used to determine if the main panel should display the "View
    // End Game Options" button instead of the "Resign" button.
    private boolean endGameState = false;

    private ApplicationFrame container;
    private int containerHeight;
    private int containerWidth;

    private boolean boardHighlightOnOff;

    /**
     * Constructs the primary JPanel to display gameplay (mainLayer) and endgame
     * options (popupLayer), alternated using LayeredPane.
     * 
     * @author Chelsie Bajic
     * @since 10/2021
     */
    public ChessPanel(ApplicationFrame container) {

        this.container = container;

        //////////////// For Skeleton only //////////////////

        player1Clock.addPlayerName("Player"); // ***ATTENTION*** To dynamically set these names, get info from ChessGame
        player2Clock.addPlayerName("A.I. (Easy)");

        // *** These are for skeleton view only, time needs to be made dynamic *****
        player1Clock.updatePlayerTime("5:01   ");
        player2Clock.updatePlayerTime("1:08   ");
        totalGameTime.updateTotalGameTime("23:00");

        /////////////////////////////////////////////////////////

        // Set layout for layeredPanes.
        // LayeredPane is used to give z index for mainLayer and the two popuplayers:
        // endGameOptions, pawnPromotionScreen.
        this.setLayout(new GridLayout(0, 1));

        this.add(layeredPane);
        layeredPane.add(mainLayer, Integer.valueOf(0));

        // mainLayer layout.
        mainLayer.setLayout(new GridBagLayout());
        GridBagConstraints gridBagForMainLayer = new GridBagConstraints();
        gridBagForMainLayer.fill = GridBagConstraints.BOTH;

        // Begin adding all view components to mainLayer;

        // Add Player 1 clock to mainLayer.
        gridBagForMainLayer.gridy = 0;
        gridBagForMainLayer.gridx = 0;
        gridBagForMainLayer.weightx = 0.35;
        gridBagForMainLayer.weighty = 0;
        gridBagForMainLayer.gridwidth = 1;
        gridBagForMainLayer.gridheight = 1;
        gridBagForMainLayer.insets = new Insets(5, 30, 5, 5);
        mainLayer.add(player1Clock, gridBagForMainLayer);

        // Add Player 2 clock to mainLayer.
        gridBagForMainLayer.gridy = 0;
        gridBagForMainLayer.gridx = 1;
        gridBagForMainLayer.weightx = 0.35;
        gridBagForMainLayer.weighty = 0;
        gridBagForMainLayer.gridwidth = 1;
        gridBagForMainLayer.gridheight = 1;
        gridBagForMainLayer.insets = new Insets(5, 5, 5, 5);
        mainLayer.add(player2Clock, gridBagForMainLayer);

        // Add total game clock to mainLayer.
        gridBagForMainLayer.gridy = 0;
        gridBagForMainLayer.gridx = 2;
        gridBagForMainLayer.weightx = 0.3;
        gridBagForMainLayer.weighty = 0;
        gridBagForMainLayer.gridwidth = 1;
        gridBagForMainLayer.gridheight = 1;
        gridBagForMainLayer.insets = new Insets(5, 30, 5, 30);
        mainLayer.add(totalGameTime, gridBagForMainLayer);

        // Add guiView to mainLayer.
        // Add mouseListeners to the board squares within the guiView.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                guiView.getSquares(i, j).addMouseListener(this);
            }
        }

        gridBagForMainLayer.gridy = 1;
        gridBagForMainLayer.gridx = 0;
        gridBagForMainLayer.gridwidth = 2;
        gridBagForMainLayer.gridheight = 4;
        gridBagForMainLayer.insets = new Insets(20, 30, 20, 5);
        mainLayer.add(guiView, gridBagForMainLayer);

        // Add algebraicView to mainLayer.
        gridBagForMainLayer.gridy = 1;
        gridBagForMainLayer.gridx = 2;
        gridBagForMainLayer.gridheight = 4;
        gridBagForMainLayer.insets = new Insets(20, 30, 20, 30);
        mainLayer.add(algebraicView, gridBagForMainLayer);

        // Add messagesView to mainLayer.
        messagesView = new ChessGameMessagesView();
        gridBagForMainLayer.gridy = 5;
        gridBagForMainLayer.gridx = 0;
        gridBagForMainLayer.weightx = 0.7;
        gridBagForMainLayer.weighty = 0;
        gridBagForMainLayer.gridwidth = 2;
        gridBagForMainLayer.gridheight = 1;
        gridBagForMainLayer.insets = new Insets(5, 30, 5, 5);
        mainLayer.add(messagesView, gridBagForMainLayer);

        // Add buttons to button panel.
        JPanel invisbleButtonContainer = new JPanel();
        invisbleButtonContainer.setLayout(new GridBagLayout());
        GridBagConstraints gbForButtonPanel = new GridBagConstraints();
        gbForButtonPanel.fill = GridBagConstraints.BOTH;
        gbForButtonPanel.gridy = 0;
        gbForButtonPanel.gridx = 0;
        gbForButtonPanel.weightx = 0.25;
        gbForButtonPanel.weighty = 0.5;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 1;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        invisbleButtonContainer.add(undoButton, gbForButtonPanel);

        gbForButtonPanel.gridy = 0;
        gbForButtonPanel.gridx = 1;
        gbForButtonPanel.weightx = 0.5;
        gbForButtonPanel.weighty = 0.5;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 2;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        invisbleButtonContainer.add(moveHintButton, gbForButtonPanel);

        gbForButtonPanel.gridy = 0;
        gbForButtonPanel.gridx = 3;
        gbForButtonPanel.weightx = 0.25;
        gbForButtonPanel.weighty = 0.5;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 1;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        invisbleButtonContainer.add(redoButton, gbForButtonPanel);

        gbForButtonPanel.gridy = 1;
        gbForButtonPanel.gridx = 0;
        gbForButtonPanel.weightx = 1;
        gbForButtonPanel.weighty = 1;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 4;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        invisbleButtonContainer.add(pieceInfo, gbForButtonPanel);

        gbForButtonPanel.gridy = 2;
        gbForButtonPanel.gridx = 0;
        gbForButtonPanel.weightx = 1;
        gbForButtonPanel.weighty = 1;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 4;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        invisbleButtonContainer.add(resignButton, gbForButtonPanel);

        buttonContainer.setLayout(new GridBagLayout());
        GridBagConstraints igb = new GridBagConstraints();
        igb.fill = GridBagConstraints.BOTH;
        igb.gridx = 0;
        igb.gridy = 0;
        igb.weightx = 0.25;
        invisbleButtonContainer.setOpaque(false);
        JPanel buttonSpacer1 = new JPanel();
        buttonSpacer1.setOpaque(false);
        JPanel buttonSpacer2 = new JPanel();
        buttonSpacer2.setOpaque(false);
        buttonContainer.add(buttonSpacer1, igb);
        igb.gridx = 1;
        igb.weightx = 0.5;
        buttonContainer.add(invisbleButtonContainer, igb);
        igb.gridx = 2;
        igb.weightx = 0.25;
        buttonContainer.add(buttonSpacer2, igb);

        // Add button panel to mainLayer.
        gridBagForMainLayer.gridy = 5;
        gridBagForMainLayer.gridx = 2;
        gridBagForMainLayer.weightx = 0.3;
        gridBagForMainLayer.weighty = 0;
        gridBagForMainLayer.gridwidth = 1;
        gridBagForMainLayer.gridheight = 1;
        gridBagForMainLayer.insets = new Insets(5, 30, 5, 30);
        mainLayer.add(buttonContainer, gridBagForMainLayer);

        makePopupsResizeable();

        endGameOptions.setVisible(false);
        pawnPromotionScreen.setVisible(false);

        // First notification.
        addNotification("Select a chess piece to begin...");

        layeredPane.revalidate();
        layeredPane.repaint();

    }

    /**
     * Initialize the board at the start of a new game.
     */
    public void initialize(ChessGame chessGame) { // Needs to be edited to read from GameState.

        guiView.setChessGame(chessGame);
        algebraicView.setChessGame(chessGame);
        messagesView.setChessGame(chessGame);
        this.setChessGame(chessGame);

        chessGame.registerView(guiView);
        chessGame.registerView(algebraicView);
        chessGame.registerView(messagesView);
        chessGame.registerView(this);

        endGameOptions.setChessGame(chessGame);
        pawnPromotionScreen.setChessGame(chessGame);
        endGameOptions.setChessPanel(this);
        pawnPromotionScreen.setChessPanel(this);
        guiView.setChessPanel(this);

        endGameOptions.makeIntoEndGameOptionsScreen("Game Over!");
        layeredPane.add(endGameOptions, Integer.valueOf(1));
        pawnPromotionScreen.makeIntoPawnPromotionScreen();
        layeredPane.add(pawnPromotionScreen, Integer.valueOf(2));

        for (ActionListener al : undoButton.getActionListeners()) {
            undoButton.removeActionListener(al);
        }
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chessGame.undo();
            }
        });
        for (ActionListener al : redoButton.getActionListeners()) {
            redoButton.removeActionListener(al);
        }
        redoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chessGame.redo();
            }
        });
    }

    /**
     * Displays the end game options popup screen.
     */
    public void showEndGameOptions() { // Needs to be edited to read from GameState.
        endGameOptions.setVisible(true);
        endGameState = true; // If the user undoes a move, this should be set back to false.
    }

    /**
     * Hides the end game options popup screen.
     */
    public void hideEndGameOptions() { // Needs to be edited to read from GameState.
        endGameOptions.setVisible(false);

        // If the game is in the end game state, and hideEndGameOptions() is called,
        // replace the "resign button"
        // with the "Show end game options" button.
        if (endGameState == true) {
            buttonContainer.remove(resignButton);
            GridBagConstraints gb = new GridBagConstraints();
            gb.gridy = 2;
            gb.gridx = 0;
            gb.weightx = 1;
            gb.weighty = 1;
            gb.gridheight = 1;
            gb.gridwidth = 4;
            gb.insets = new Insets(5, 5, 5, 5);
            buttonContainer.add(showEndGameOptionsButton, gb);

        }
    }

    /**
     * Displays the pawn promotion popup screen.
     */
    public void showPawnPromotionScreen(int fromRow, int fromCol, int toRow, int toCol) {
        if (pawnPromotionScreen != null) {
            pawnPromotionScreen.showPawnPromotionScreen(fromRow, fromCol, toRow, toCol);
            pawnPromotionScreen.setVisible(true);
        }
    }

    /**
     * Hides the pawn promotion popup screen.
     */
    public void hidePawnPromotionScreen() {
        pawnPromotionScreen.setVisible(false);
    }

    /**
     * Adds a notification to the notifications screen (replaces previous
     * notification)
     * 
     * @param notificationToAdd the notification string to add.
     */
    public void addNotification(String notificationToAdd) {
        messagesView.addToNotifications(notificationToAdd);
    }

    // public void disableNotifications(){}

    // public void disableBoardHighlight(){}

    // public void disableMoveHint(){}

    // public void disable_Undo_Redo_Move(){}

    // public void loadSavedGame(ChessGameLoadView savedGame){}

    /**
     * Prints the location of the selected square to the notifications screen.
     * 
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Square copyButton = (Square) e.getSource();
        String squareNotification = "You have selected square: " + copyButton.getSquareLocation();
        addNotification(squareNotification);

    }

    /**
     * Responsible for dynamically resizing the popup layers (endGameOptions &
     * pawnPromotionScreen) if the user resizes the application frame.
     */
    public void makePopupsResizeable() {

        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = layeredPane.getSize(); // get size
                mainLayer.setSize(size);
                int popupScale = 2; // Determines the scale of the popup relative to mainLayer.
                // Makes the popupLayer rescale dynamically in the centre of ChessPanel.
                Dimension popupSize = new Dimension((int) (size.getWidth() / popupScale),
                        (int) (size.getHeight() / popupScale));
                pawnPromotionScreen.setBounds((int) ((mainLayer.getWidth() - popupSize.getWidth()) / 2),
                        (int) ((mainLayer.getHeight() - popupSize.getHeight()) / 2), 0, 0);
                endGameOptions.setBounds((int) ((mainLayer.getWidth() - popupSize.getWidth()) / 2),
                        (int) ((mainLayer.getHeight() - popupSize.getHeight()) / 2), 0, 0);
                endGameOptions.setSize(popupSize);
                pawnPromotionScreen.setSize(new Dimension(popupSize));

                layeredPane.revalidate();
                layeredPane.repaint();
            }
        });
    }

    /**
     * Updates the variables representing the current size of the application frame.
     * 
     * @param width  Current frame width.
     * @param height Current frame height.
     */
    public void updateContainerDimensions(int width, int height) {

        this.containerWidth = width;
        this.containerHeight = height;
        updatePieceSizes();

    }

    /**
     * If the user changes the application frame size, this function updates the
     * piece sizes.
     */
    public void updatePieceSizes() {
        guiView.setContainerSize((int) container.getBounds().getWidth(), (int) container.getBounds().getHeight());
        guiView.update();
    }

    /**
     * Checks the state of the game to see if ChessPanel needs to display pawn
     * promotion pop up screen or end game options pop up screen.
     */
    @Override
    public void update() {

        if (false) { // Check if pawn promotion here.

            addNotification("Pawn promotion!");
            // this.showPawnPromotionScreen();

        } else if (false) { // Check if 50 moves stalemate here.

            addNotification("50 move stalemate");
            this.showEndGameOptions();

        } else if (false) { // Check if no moves stalemate here.

            addNotification("Stalemate");
            this.showEndGameOptions();

        } else if (false) { // Check if repetition stalemate here.

            addNotification("Repetition stalemate");
            this.showEndGameOptions();

        } else if (false) { // Check if white check here.

            addNotification("White is in check!");
            // Add code for white check here.

        } else if (false) { // Check if black check here.

            addNotification("Black is in check!");
            // Add code for black check here.

        } else if (false) { // Check if white checkmate here.

            addNotification("Checkmate!");
            this.showEndGameOptions();

        } else if (false) { // Check if black checkmate here.

            addNotification("Checkmate!");
            this.showEndGameOptions();

        } else if (false) { // Check if white resign here.

            addNotification("White has resigned");
            this.showEndGameOptions();

        } else if (false) { // Check if black resign here.

            addNotification("Black has resigned");
            this.showEndGameOptions();

        }

    }

    public void boardHighlightOnOff() {

        if (boardHighlightOnOff == true) {
            this.boardHighlightOnOff = false;
            addNotification("Board Highlight OFF");
        } else {
            this.boardHighlightOnOff = true;
            addNotification("Board Highlight ON");
        }

        guiView.turnBoardHighlightOff(this.boardHighlightOnOff);
    }

    @Override
    public void addListeners() {

    }

    // The following are unused but required to implement MouseListener.
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }
}
