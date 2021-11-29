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
 * @since 11/2021
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
    private JPanel invisbleButtonContainer;

    private ImageIcon undoIcon = ChessGameGUIView.openPieceImageFile("images/white_undo.png", 40);
    private ImageIcon redoIcon = ChessGameGUIView.openPieceImageFile("images/white_redo.png", 40);
    private ImageIcon hintIcon = ChessGameGUIView.openPieceImageFile("images/white_hint.png", 40);
    private ImageIcon resignIcon = ChessGameGUIView.openPieceImageFile("images/white_resign.png", 40);
    private ImageIcon aboutPieceIcon = ChessGameGUIView.openPieceImageFile("images/about.png", 40);
    private ImageIcon endGameIcon = ChessGameGUIView.openPieceImageFile("images/view_end_game.png", 40);

    private CustomButton undoButton = new CustomButton(undoIcon);
    // private CustomButton undoButton = new CustomButton("Undo");
    private CustomButton redoButton = new CustomButton(redoIcon);
    // private CustomButton redoButton = new CustomButton("Redo");
    private CustomButton moveHintButton = new CustomButton(hintIcon);
    // private CustomButton moveHintButton = new CustomButton("Hint");
    private CustomButton resignButton = new CustomButton(resignIcon);
    JPanel resignAndPieceInfoHolder = new JPanel();
    // private CustomButton resignButton = new CustomButton("Resign");
    private CustomButton pieceInfo = new CustomButton(aboutPieceIcon);
    // private CustomButton pieceInfo = new CustomButton("About Piece");
    private CustomButton showEndGameOptionsButton = new CustomButton(endGameIcon);

    // endGameState is used to determine if the main panel should display the "View
    // End Game Options" button instead of the "Resign" button.
    private boolean endGameState = false;

    private ApplicationFrame container;
    private int containerHeight;
    private int containerWidth;

    private boolean boardHighlightOnOff;
    private boolean notificationsOnOff;
    private boolean moveHintSwitch;
    private boolean undoRedoSwitch;

    private String player1Name;
    private String player2Name;

    private boolean inTutorialMode; 
    private int tutorialNotificaions = 0;
    

    /**
     * Constructs the primary JPanel to display gameplay (mainLayer) and endgame
     * options (popupLayer), alternated using LayeredPane.
     * 
     */
    public ChessPanel(ApplicationFrame container) {

        this.container = container;

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
        invisbleButtonContainer = new JPanel();
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
        undoButton.setToolTipText("Undo move");
        invisbleButtonContainer.add(undoButton, gbForButtonPanel);

        gbForButtonPanel.gridy = 0;
        gbForButtonPanel.gridx = 1;
        gbForButtonPanel.weightx = 0.5;
        gbForButtonPanel.weighty = 0.5;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 2;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        moveHintButton.setToolTipText("Move hint");
        invisbleButtonContainer.add(moveHintButton, gbForButtonPanel);

        gbForButtonPanel.gridy = 0;
        gbForButtonPanel.gridx = 3;
        gbForButtonPanel.weightx = 0.25;
        gbForButtonPanel.weighty = 0.5;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 1;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        redoButton.setToolTipText("Redo move");
        invisbleButtonContainer.add(redoButton, gbForButtonPanel);

        resignAndPieceInfoHolder.setLayout(new GridBagLayout());
        GridBagConstraints resigngb = new GridBagConstraints();

        resigngb.gridx = 0;
        resigngb.gridy = 0;
        resigngb.fill = GridBagConstraints.BOTH;
        resigngb.weightx = 1;
        resigngb.weighty = 1;
        resigngb.insets = new Insets(5, 5, 5, 5);
        pieceInfo.setToolTipText("View information about the selected piece");

        resignAndPieceInfoHolder.add(pieceInfo, resigngb);

        resigngb.gridx = 1;
        resignButton.setToolTipText("Resign");
        resignAndPieceInfoHolder.add(resignButton, resigngb);

        gbForButtonPanel.gridy = 1;
        gbForButtonPanel.gridx = 0;
        gbForButtonPanel.weightx = 1;
        gbForButtonPanel.weighty = 1;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 4;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        resignAndPieceInfoHolder.setOpaque(false);
        invisbleButtonContainer.add(resignAndPieceInfoHolder, gbForButtonPanel);

        buttonContainer.setLayout(new GridBagLayout());
        GridBagConstraints igb = new GridBagConstraints();
        igb.fill = GridBagConstraints.BOTH;
        igb.gridx = 0;
        igb.gridy = 0;
        igb.weightx = 0.25;

        invisbleButtonContainer.setOpaque(false);

        igb.gridx = 1;
        igb.weightx = 0.5;
        buttonContainer.add(invisbleButtonContainer, igb);

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

        layeredPane.revalidate();
        layeredPane.repaint();

    }

    /**
     * Initialize the board at the start of a new game.
     * @param chessGame sets the chessGame of the ChessGame
     */
    public void initialize(ChessGame chessGame) { // Needs to be edited to read from GameState.

        container.returnLoadGameMenuItem().setEnabled(true);
            container.returnSaveGameMenuItem().setEnabled(true);

        if (this.chessGame != null) {
            this.chessGame.removeClocks();
        }

        this.clearNotifications();

        // Sets player names on clock
        player1Clock.addPlayerName(player1Name, "White: ");
        player2Clock.addPlayerName(player2Name, "Black: ");

        // *** These are for skeleton view only, time needs to be made dynamic *****
        player1Clock.updatePlayerTime("0:00   ");
        player2Clock.updatePlayerTime("0:00   ");
        totalGameTime.updateTotalGameTime(" 0:00");

        chessGame.registerPlayerClock(player1Clock, true);
        chessGame.registerPlayerClock(player2Clock, false);
        chessGame.registerTotalGameTimeClock(totalGameTime);

        /////////////////////////////////////////////////////////

        container.showMenuBarOnChessPanel();

        guiView.setChessGame(chessGame);
        guiView.setSelected(-1, -1);
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
        if (layeredPane.getComponentCountInLayer(1) == 0)
            layeredPane.add(endGameOptions, Integer.valueOf(1));
        pawnPromotionScreen.makeIntoPawnPromotionScreen();
        if (layeredPane.getComponentCountInLayer(2) == 0)
            layeredPane.add(pawnPromotionScreen, Integer.valueOf(2));

        for (ActionListener al : undoButton.getActionListeners()) {
            undoButton.removeActionListener(al);
        }
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (undoRedoSwitch) {
                    chessGame.undo();
                }
            }
        });

        for (ActionListener al : redoButton.getActionListeners()) {
            redoButton.removeActionListener(al);
        }
        redoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (undoRedoSwitch) {
                    chessGame.redo();
                }
            }
        });

        for (ActionListener al : moveHintButton.getActionListeners()) {
            moveHintButton.removeActionListener(al);
        }
        moveHintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiView.moveHint(guiView.getSelectedRow(), guiView.getSelectedCol());
            }
        });

        for (ActionListener al : resignButton.getActionListeners()) {
            resignButton.removeActionListener(al);
        }
        resignButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int userSelection= JOptionPane.showConfirmDialog(container, "Are you sure you want to resign?", "Resign?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(userSelection == JOptionPane.YES_OPTION) getChessGame().resign();
            }
        });

        for (ActionListener al : showEndGameOptionsButton.getActionListeners()) {
            showEndGameOptionsButton.removeActionListener(al);
        }
        showEndGameOptionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEndGameOptions();
            }
        });

        for (ActionListener al : pieceInfo.getActionListeners()) {
            pieceInfo.removeActionListener(al);
        }
        pieceInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                char pieceType = ' ';
                int tabPageSwitch = 0;

                // Check which piece the user has selected and open the corresponding about
                // piece dialog
                int selectedRow = -1;
                int selectedCol = -1;

                if (guiView.getSelectedCol() != -1) {
                    selectedRow = guiView.getSelectedRow();
                    selectedCol = guiView.getSelectedCol();
                }

                if (selectedRow != -1) {
                    pieceType = guiView.getPieceTypeOnThisSquare(selectedRow, selectedCol);
                }

                if (pieceType == ' ') {
                    // No piece is selcted
                } else if (pieceType == 'p' || pieceType == 'P') {
                    tabPageSwitch = 0;
                } else if (pieceType == 'r' || pieceType == 'R') {
                    tabPageSwitch = 1;
                } else if (pieceType == 'b' || pieceType == 'B') {
                    tabPageSwitch = 2;
                } else if (pieceType == 'n' || pieceType == 'N') {
                    tabPageSwitch = 3;
                } else if (pieceType == 'q' || pieceType == 'Q') {
                    tabPageSwitch = 4;
                } else if (pieceType == 'k' || pieceType == 'K') {
                    tabPageSwitch = 5;
                }

                HelpWindow helpWindow = new HelpWindow("pieceInfo", 1, tabPageSwitch);
                helpWindow.setLocation(1200, 300);
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));
            }
        });

    }

    /**
     * Displays the end game options popup screen.
     */
    public void showEndGameOptions() { // Needs to be edited to read from GameState.
        if (!resignAndPieceInfoHolder.isAncestorOf(resignButton)) {
            resignAndPieceInfoHolder.remove(showEndGameOptionsButton);
            GridBagConstraints resigngb = new GridBagConstraints();
            resigngb.gridx = 1;
            resigngb.gridy = 0;
            resigngb.fill = GridBagConstraints.BOTH;
            resigngb.weightx = 1;
            resigngb.weighty = 1;
            resigngb.insets = new Insets(5, 5, 5, 5);
            resignAndPieceInfoHolder.add(resignButton, resigngb);
            resignAndPieceInfoHolder.revalidate();
            resignAndPieceInfoHolder.repaint();
        }
        showEndGameOptionsButton.enable(false);
        endGameOptions.setVisible(true);
        endGameState = true; // If the user undoes a move, this should be set back to false.
        disableButtons();
        disableBoardClick();
        notificationsOnOff();

    }

    /**
     * Disables buttons.
     */
    public void disableButtons() {
        redoButton.setEnabled(false);
        undoButton.setEnabled(false);
        resignButton.setEnabled(false);
        moveHintButton.setEnabled(false);
        pieceInfo.setEnabled(false);
    }

    /**
     * Enable buttons
     */
    public void enableButtons() {
        redoButton.setEnabled(true);
        undoButton.setEnabled(true);
        resignButton.setEnabled(true);
        moveHintButton.setEnabled(true);
        pieceInfo.setEnabled(true);
    }

    /**
     * Make board unclickable
     */
    public void disableBoardClick() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                guiView.getSquares(i, j).setEnabled(false);
            }
        }

    }

    /**
     * Make board unclickable
     */
    public void enableBoardClick() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                guiView.getSquares(i, j).setEnabled(true);
            }
        }

    }

    /**
     * Hides the end game options popup screen.
     */
    public void hideEndGameOptions() { // Needs to be edited to read from GameState.
        showEndGameOptionsButton.enable(true);
        endGameOptions.setVisible(false);
        enableButtons();
        enableBoardClick();
        notificationsOnOff();

        // If the game is in the end game state, and hideEndGameOptions() is called,
        // replace the "resign button"
        // with the "Show end game options" button.
        if (endGameState == true && !buttonContainer.isAncestorOf(showEndGameOptionsButton)) {
            resignAndPieceInfoHolder.remove(resignButton);
            GridBagConstraints resigngb = new GridBagConstraints();
            resigngb.gridx = 1;
            resigngb.gridy = 0;
            resigngb.fill = GridBagConstraints.BOTH;
            resigngb.weightx = 1;
            resigngb.weighty = 1;
            showEndGameOptionsButton.setToolTipText("View end game options");
            resignAndPieceInfoHolder.add(showEndGameOptionsButton, resigngb);
            resignAndPieceInfoHolder.revalidate();
            resignAndPieceInfoHolder.repaint();

        }
    }

    /**
     * Hides the end game options popup screen.
     */
    public void hideEndGameOptionsForRematch() { // Needs to be edited to read from GameState.
        showEndGameOptionsButton.enable(true);
        endGameOptions.setVisible(false);
        enableButtons();
        enableBoardClick();
        notificationsOnOff();

        // If the game is in the end game state, and hideEndGameOptions() is called,
        // replace the "resign button"
        // with the "Show end game options" button.
        if (endGameState == true && !buttonContainer.isAncestorOf(showEndGameOptionsButton)) {
            resignAndPieceInfoHolder.remove(showEndGameOptionsButton);
            GridBagConstraints resigngb = new GridBagConstraints();
            resigngb.gridx = 1;
            resigngb.gridy = 0;
            resigngb.fill = GridBagConstraints.BOTH;
            resigngb.weightx = 1;
            resigngb.weighty = 1;
            resigngb.insets = new Insets(5, 5, 5, 5);
            resignAndPieceInfoHolder.add(resignButton, resigngb);
            resignAndPieceInfoHolder.revalidate();
            resignAndPieceInfoHolder.repaint();

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
        disableButtons();
        disableBoardClick();
        notificationsOnOff();
    }

    /**
     * Hides the pawn promotion popup screen.
     */
    public void hidePawnPromotionScreen() {
        pawnPromotionScreen.setVisible(false);
        enableButtons();
        enableBoardClick();
        notificationsOnOff();
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

    /**
     * Prints the location of the selected square to the notifications screen.
     * 
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // Square copyButton = (Square) e.getSource();
        // String squareNotification = "You have selected square: " + copyButton.getSquareLocation();
        // addNotification(squareNotification);

    }

    /**
     * Clears all notifications.
     */
    public void clearNotifications() {
        messagesView.clearNotifications();
    }

    /**
     * Hide menu bar.
     */
    public void hideMenuBar() {
        container.hideMenuBar();
    }

    /**
     * Responsible for dynamically resizing the popup layers (endGameOptions &amp; pawnPromotionScreen) if the user resizes the application frame.
     * 
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
     * Determines if current game play is in tutorial mode. 
     * @param inTutorialMode ture = in tutorial mode. false = not in tutorial mode. 
     */
    public void inTutorialMode(boolean inTutorialMode){
        this.inTutorialMode = inTutorialMode; 
    }

       /**
     * Returns whether the current game is in tutorial mode or not. 
     * @return Tutorial status. 
     */
    public boolean returnTutorialStatus(){
        return inTutorialMode; 
    }

    /**
     * Returns the messages view object. 
     * @return The messages view object. 
     */
    public ChessGameMessagesView returnMessageView(){
        return messagesView;
    }

    /**
     * Checks the state of the game to see if ChessPanel needs to display pawn
     * promotion pop up screen or end game options pop up screen.
     */
    @Override
    public void update() {

         // Tenth tutorial notification.
         if(inTutorialMode == true && tutorialNotificaions == 20){
            messagesView.turnNotificationSwitch(true); // Turn off turn notifications.
        }

        // Ninth tutorial notification.
        if(inTutorialMode == true && tutorialNotificaions == 18){
            addNotification("If one of your pawns makes it to the final rank, it can be promoted");
            addNotification("into a superior piece!");
        }

            // Ninth tutorial notification.
            if(inTutorialMode == true && tutorialNotificaions == 16){
                addNotification(" ");
                addNotification("FYI: Rows are known as \"rank\" and columns are known as \"file\".");
                addNotification(" ");
            }

        // Eighth tutorial notification.
        if(inTutorialMode == true && tutorialNotificaions == 14){
            addNotification(" ");
            addNotification("Try not to lose your Queen!");
            addNotification("Due to its move versatillity it is the most valuable piece after the King.");
        }

        // Seventh tutorial notification.
        if(inTutorialMode == true && tutorialNotificaions == 12){
            addNotification(" ");
            addNotification("Try not to let the oppenent capture your Queen!");
            addNotification("Due to its move versatillity it is the most valuable piece after the King.");
        }

        // Seventh tutorial notification.
        if(inTutorialMode == true && tutorialNotificaions == 10){
            addNotification(" ");
            addNotification("When certain conditions are met, special moves may be made such as castling & en passant.");
            addNotification("Please select the \"Game Rules\" option from the help menu to read more about special moves.");
           
        }

         // Sixth tutorial notification.
         if(inTutorialMode == true && tutorialNotificaions == 8){
            addNotification(" ");
            addNotification("If you wish to undo or redo a move, you can do so by clicking the undo or redo button");
            addNotification("located on the top left (undo) or top right (redo) of the button panel -->");
        }

          // Fifth tutorial notification.
          if(inTutorialMode == true && tutorialNotificaions == 6){
            addNotification(" ");
            addNotification("You may accept defeat at any time by clicking the resign button -->");
            addNotification("indicated by the white flag icon on the button panel.");
        }

         // Fourth tutorial notification.
         if(inTutorialMode == true && tutorialNotificaions == 6){
            addNotification("To receive more information about each piece, first select the piece you wish to ");
            addNotification("receive information about, and then click the piece information button -->");
            addNotification("located on the bottom left of the button panel.");
        }

         // Third tutorial notification.
         if(inTutorialMode == true && tutorialNotificaions == 4){
            addNotification("The purpose of the game is to capture the opponent's King.");
            addNotification("To capture an opponent piece, move one of your pieces on top of an opponent piece");
            addNotification("when the oppourtunity presents itself (indicated with red square illumination).");
        }

        // Second tutorial notification.
        if(inTutorialMode == true && tutorialNotificaions == 2){
            addNotification("To receive a move hint click the move hint button on the button panel,");
            addNotification("indicated by the lightbulb icon -->");
            addNotification("Your suggested move will be illuminated in green.");
        }

        // First tutorial notification.
        if (inTutorialMode == true && tutorialNotificaions == 0) {
            messagesView.turnNotificationSwitch(false); // Turn off turn notifications.
            addNotification("Welcome to tutorial mode! You will be taught how to play chess.");
            addNotification("White always moves first, you are white, select a chess piece to begin...");
            addNotification("You may move your selected piece to any square that illuminates blue.");
            container.returnLoadGameMenuItem().setEnabled(false);;
            container.returnSaveGameMenuItem().setEnabled(false);;
        }

        tutorialNotificaions++; 

        GameState state = null;
        if (chessGame != null) {
            state = chessGame.getState();
        }

        if (state == GameState.STALEMATE_50MOVES) { // Check if 50 moves stalemate here.

            addNotification("Game Over: 50 move stalemate");
            this.showEndGameOptions();
            if(inTutorialMode == true){
                addNotification("The game has ended in 50 move stalemate because ");
                addNotification("neither player makes progress in the form of moving their pawns ");
                addNotification("and no pieces have been captured over the span of fifty moves.");
            }

        } else if (state == GameState.STALEMATE_NOMOVES) { // Check if no moves stalemate here.

            addNotification("Game Over: Stalemate");
            this.showEndGameOptions();
            if(inTutorialMode == true){
                addNotification("The game has ended in stalemate because ");
                addNotification("one of the players has no legal move options.");
                addNotification("This is considered to be a draw (no winners, no losers)");
            }

        } else if (state == GameState.STALEMATE_REPITITION) { // Check if repetition stalemate here.

            addNotification("Game Over: Repetition stalemate");
            this.showEndGameOptions();
            if(inTutorialMode == true){
                addNotification("The game has ended in repetition stalemate because ");
                addNotification("a player made the same move three times in a row and ");
                addNotification("cannot make further game progress");
            }

        } else if (state == GameState.STALEMATE_NOMATERIAL) { // Check if no material stalemate here.

            addNotification("Game Over: Insufficient material stalemate");
            this.showEndGameOptions();
            if(inTutorialMode == true){
                addNotification("The game has ended in insufficient material stalemate because ");
                addNotification("the game came to a state where checkmate is not possible");
                addNotification(" due to an insufficient number of pieces on the board");
            }

        } else if (state == GameState.BLACK_CHECK) { // Check if white check here.

            addNotification("White is in check!");
            endGameState = false;
            if(inTutorialMode == true){
                addNotification("This means that your king is under threat of capture!");
                addNotification("your next move must return the king to safety (use the move hint option for help).");
            }

        } else if (state == GameState.WHITE_CHECK) { // Check if black check here.

            addNotification("Black is in check!");
            endGameState = false;
            if(inTutorialMode == true){
                addNotification("This means that the black king is under threat of capture");
                addNotification("Black's next move must return the king to safety");
            }


        } else if (state == GameState.WHITE_CHECKMATE) { // Check if white checkmate here.

            addNotification("Game Over: Checkmate, White wins!");
            this.showEndGameOptions();
            if(inTutorialMode == true){
                addNotification("You win because the black king has no escape");
            }

        } else if (state == GameState.BLACK_CHECKMATE) { // Check if black checkmate here.

            addNotification("Game Over: Checkmate, Black wins!");
            this.showEndGameOptions();
            if(inTutorialMode == true){
                addNotification("You lose because your king has no escape!");
            }

        } else if (state == GameState.WHITE_RESIGN) { // Check if white resign here.

            addNotification("Game Over: White has resigned");
            this.showEndGameOptions();

        } else if (state == GameState.BLACK_RESIGN) { // Check if black resign here.

            addNotification("Game Over: Black has resigned");
            this.showEndGameOptions();

        } else if (state == GameState.WHITE_TIMEOUT) { // Check if white time out here.
            addNotification("White has run out of time");
            this.showEndGameOptions();
        } else if (state == GameState.BLACK_TIMEOUT) { // Check if black time out here.
            addNotification("Black has run out of time");
            this.showEndGameOptions();

        } else {
            endGameState = false;
        }

        if (!endGameState) {
            if (!resignAndPieceInfoHolder.isAncestorOf(resignButton)) {
                resignAndPieceInfoHolder.remove(showEndGameOptionsButton);
                GridBagConstraints resigngb = new GridBagConstraints();
                resigngb.gridx = 1;
                resigngb.gridy = 0;
                resigngb.fill = GridBagConstraints.BOTH;
                resigngb.weightx = 1;
                resigngb.weighty = 1;
                resigngb.insets = new Insets(5, 5, 5, 5);
                resignAndPieceInfoHolder.add(resignButton, resigngb);
                resignAndPieceInfoHolder.revalidate();
                resignAndPieceInfoHolder.repaint();
                showEndGameOptionsButton.setEnabled(false);
            }
        }

    }

    /**
     * Turn the board highlight option on or off.
     */
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

    /**
     * Checks the current state of the selected tutorial options - whether they are
     * on or off.
     */
    public void checkTutorialSelections() {
        guiView.turnBoardHighlightOff(this.boardHighlightOnOff);
        guiView.moveHintSwitch(this.moveHintSwitch);
        messagesView.turnNotificationsOnOff(this.notificationsOnOff);
    }

    /**
     * Turn the move hint option on or off.
     */
    public void moveHintOnOff() {

        if (moveHintSwitch == true) {
            this.moveHintSwitch = false;
            addNotification("Move Hints OFF");
        } else {
            this.moveHintSwitch = true;
            addNotification("Move Hints ON");
        }
        guiView.moveHintSwitch(this.moveHintSwitch);
    }

    /**
     * Reset tutorial notifications when tutorial game is exited. 
     */
    public void resetTutorialNotifications(){
        this.tutorialNotificaions = 0; 
    }

    /**
     * Turn the move hint option on or off.
     */
    public void undoRedoOnOff() {

        if (undoRedoSwitch == true) {
            this.undoRedoSwitch = false;
            addNotification("Undo Redo OFF");
        } else {
            this.undoRedoSwitch = true;
            addNotification("Undo Redo ON");
        }
    }

    /**
     * Turn the notifications on or off.
     */
    public void notificationsOnOff() {

        if (notificationsOnOff == true) {
            this.notificationsOnOff = false;
            // addNotification("Notifications OFF");
        } else {
            this.notificationsOnOff = true;
            // addNotification("Notifications ON");
        }

        messagesView.turnNotificationsOnOff(this.notificationsOnOff);
    }

    /**
     * Allows the player names to be set for use in the player game clock display
     * 
     * @param player1 The name of the first player.
     * @param player2 the name of the second player.
     */
    public void setPlayerNames(String player1, String player2) {
        this.player1Name = player1;
        this.player2Name = player2;
    }

    /**
     * Allows other classes (set up screen) to change hint switch.
     * 
     * @param hintSwitch true = on, false = off
     */
    public void setMoveHintSwitch(boolean hintSwitch) {
        this.moveHintSwitch = hintSwitch;
    }

    /**
     * Allows other classes (set up screen)to change notifications switch
     * 
     * @param notificationsSwitch true = on, false = off
     */
    public void setNotificationsSwitch(boolean notificationsSwitch) {
        this.notificationsOnOff = notificationsSwitch;
    }

    /**
     * Allows other classes (set up screen)to change board highlight switch
     * 
     * @param boardHighlightSwitch true = on, false = off
     */
    public void setboardHighlightSwitch(boolean boardHighlightSwitch) {
        this.boardHighlightOnOff = boardHighlightSwitch;
    }

    /**
     * Allows set up screen to turn on or off undo/redo move option.
     * 
     * @param undoRedo_Switch true = on, false = off.
     */
    public void setundoRedoSwitch(boolean undoRedo_Switch) {
        this.undoRedoSwitch = undoRedo_Switch;
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

    @Override
    public void setChessGame(ChessGame chessGame) {
        super.setChessGame(chessGame);
        this.chessGame = chessGame;
    }

    public ApplicationFrame getApplicationFrame() {
        return this.container;
    }

    public void changePlayerTypes(int whiteType, int blackType) {
        if (getChessGame() != null) {
            getChessGame().changePlayerTypes(whiteType, blackType);
        }
    }
}
