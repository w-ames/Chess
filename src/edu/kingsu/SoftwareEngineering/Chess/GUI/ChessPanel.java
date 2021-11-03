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

/**
 * ChessPanel holds all components of gameplay mode display.
 */
public class ChessPanel extends JPanel implements MouseListener {

    private JLayeredPane layeredPane = new JLayeredPane();
    private MainLayer mainLayer = new MainLayer();
    private PopupLayer endGameOptions = new PopupLayer();
    private PopupLayer pawnPromotionScreen = new PopupLayer();
    // private ChessGame chessGame;
    private ChessGameGUIView guiView = new ChessGameGUIView();
    private ChessGameAlgebraicView algebraicView = new ChessGameAlgebraicView();
    // private ChessGameSaveView saveView;
    private ChessGameMessagesView messagesView = new ChessGameMessagesView();
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

    /**
     * Constructs the primary JPanel to display gameplay (mainLayer) and endgame
     * options (popupLayer), alternated using LayeredPane.
     */
    public ChessPanel() {

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
        endGameOptions.makeIntoEndGameOptionsScreen("Game Over!");
        layeredPane.add(endGameOptions, Integer.valueOf(1));
        pawnPromotionScreen.makeIntoPawnPromotionScreen();
        layeredPane.add(pawnPromotionScreen, Integer.valueOf(2));

        // mainLayer layout.
        mainLayer.setLayout(new GridBagLayout());
        GridBagConstraints gridBagForMainLayer = new GridBagConstraints();
        gridBagForMainLayer.fill = GridBagConstraints.BOTH;

        // Begin adding all view components to mainLayer;

        // Add Player 1 clock to mainLayer.
        gridBagForMainLayer.gridy = 0;
        gridBagForMainLayer.gridx = 0;
        gridBagForMainLayer.weightx = 0.35;
        gridBagForMainLayer.weighty = 0.025;
        gridBagForMainLayer.gridwidth = 1;
        gridBagForMainLayer.gridheight = 1;
        gridBagForMainLayer.insets = new Insets(30, 30, 5, 5);
        mainLayer.add(player1Clock, gridBagForMainLayer);

        // Add Player 2 clock to mainLayer.
        gridBagForMainLayer.gridy = 0;
        gridBagForMainLayer.gridx = 1;
        gridBagForMainLayer.weightx = 0.35;
        gridBagForMainLayer.weighty = 0.025;
        gridBagForMainLayer.gridwidth = 1;
        gridBagForMainLayer.gridheight = 1;
        gridBagForMainLayer.insets = new Insets(30, 5, 5, 5);
        mainLayer.add(player2Clock, gridBagForMainLayer);

        // Add total game clock to mainLayer.
        gridBagForMainLayer.gridy = 0;
        gridBagForMainLayer.gridx = 2;
        gridBagForMainLayer.weightx = 0.3;
        gridBagForMainLayer.weighty = 0.025;
        gridBagForMainLayer.gridwidth = 1;
        gridBagForMainLayer.gridheight = 1;
        gridBagForMainLayer.insets = new Insets(30, 30, 5, 30);
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
        gridBagForMainLayer.weightx = 0.875;
        gridBagForMainLayer.weighty = 0.7;
        gridBagForMainLayer.gridwidth = 2;
        gridBagForMainLayer.gridheight = 2;
        gridBagForMainLayer.insets = new Insets(30, 30, 30, 5);
        mainLayer.add(guiView, gridBagForMainLayer);

        // Add algebraicView to mainLayer.
        gridBagForMainLayer.gridy = 1;
        gridBagForMainLayer.gridx = 2;
        gridBagForMainLayer.weightx = 0.3;
        gridBagForMainLayer.weighty = 0.875;
        gridBagForMainLayer.gridwidth = 1;
        gridBagForMainLayer.gridheight = 2;
        gridBagForMainLayer.insets = new Insets(30, 30, 30, 30);
        mainLayer.add(algebraicView, gridBagForMainLayer);

        // Add messagesView to mainLayer.
        gridBagForMainLayer.gridy = 3;
        gridBagForMainLayer.gridx = 0;
        gridBagForMainLayer.weightx = 0.7;
        gridBagForMainLayer.weighty = 0.1;
        gridBagForMainLayer.gridwidth = 2;
        gridBagForMainLayer.gridheight = 1;
        gridBagForMainLayer.insets = new Insets(5, 30, 30, 5);
        mainLayer.add(messagesView, gridBagForMainLayer);

        // Add buttons to button panel.
        buttonContainer.setLayout(new GridBagLayout());
        GridBagConstraints gbForButtonPanel = new GridBagConstraints();
        gbForButtonPanel.fill = GridBagConstraints.BOTH;
        gbForButtonPanel.gridy = 0;
        gbForButtonPanel.gridx = 0;
        gbForButtonPanel.weightx = 0.25;
        gbForButtonPanel.weighty = 0.5;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 1;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        buttonContainer.add(undoButton, gbForButtonPanel);

        gbForButtonPanel.gridy = 0;
        gbForButtonPanel.gridx = 1;
        gbForButtonPanel.weightx = 0.5;
        gbForButtonPanel.weighty = 0.5;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 2;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        buttonContainer.add(moveHintButton, gbForButtonPanel);

        gbForButtonPanel.gridy = 0;
        gbForButtonPanel.gridx = 3;
        gbForButtonPanel.weightx = 0.25;
        gbForButtonPanel.weighty = 0.5;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 1;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        buttonContainer.add(redoButton, gbForButtonPanel);

        gbForButtonPanel.gridy = 1;
        gbForButtonPanel.gridx = 0;
        gbForButtonPanel.weightx = 1;
        gbForButtonPanel.weighty = 1;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 4;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        buttonContainer.add(pieceInfo, gbForButtonPanel);

        gbForButtonPanel.gridy = 2;
        gbForButtonPanel.gridx = 0;
        gbForButtonPanel.weightx = 1;
        gbForButtonPanel.weighty = 1;
        gbForButtonPanel.gridheight = 1;
        gbForButtonPanel.gridwidth = 4;
        gbForButtonPanel.insets = new Insets(5, 5, 5, 5);
        buttonContainer.add(resignButton, gbForButtonPanel);

        // Add button panel to mainLayer.
        gridBagForMainLayer.gridy = 3;
        gridBagForMainLayer.gridx = 2;
        gridBagForMainLayer.weightx = 0.3;
        gridBagForMainLayer.weighty = 0.05;
        gridBagForMainLayer.gridwidth = 1;
        gridBagForMainLayer.gridheight = 1;
        gridBagForMainLayer.insets = new Insets(5, 30, 30, 30);
        mainLayer.add(buttonContainer, gridBagForMainLayer);

        // The following code block is responsible for dynamically resizing the popup
        // layers (endGameOptions & pawnPromotionScreen) if the user resizes the
        // application frame.
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

        endGameOptions.setVisible(false);
        pawnPromotionScreen.setVisible(false);
        layeredPane.revalidate();
        layeredPane.repaint();

        // First notification.
        addNotification("Select a chess piece to begin...");

        initialize();
    }

    /**
     * Initialize the board at the start of the game.
     */
    public void initialize() {
        int width = 80;
        int height = 80;
        try {

            // Add Start New Game Black Pawns.
            for (int i = 0; i < 8; i++) {
                BufferedImage bufferedImage = ImageIO.read(new File("src/assets/piece_images/black_pawn.png"));
                Image pieceImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon pieceImageIcon = new ImageIcon(pieceImage);
                guiView.getSquares(1, i).add(new JLabel(pieceImageIcon));
                guiView.getSquares(1, i).setCurrentPiece("Black Pawn");
            }

            // Add Start New Game White Pawns.
            for (int i = 0; i < 8; i++) {
                BufferedImage bufferedImage = ImageIO.read(new File("src/assets/piece_images/white_pawn.png"));
                Image pieceImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon pieceImageIcon = new ImageIcon(pieceImage);
                guiView.getSquares(6, i).add(new JLabel(pieceImageIcon));
                guiView.getSquares(6, i).setCurrentPiece("White Pawn");
            }

            // Add black rooks.
            BufferedImage bufferedImage4 = ImageIO.read(new File("src/assets/piece_images/black_rook.png"));
            Image pieceImage = bufferedImage4.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIconBlackRook = new ImageIcon(pieceImage);
            guiView.getSquares(0, 7).add(new JLabel(pieceImageIconBlackRook));
            guiView.getSquares(0, 0).add(new JLabel(pieceImageIconBlackRook));
            guiView.getSquares(0, 7).setCurrentPiece("Black Rook");
            guiView.getSquares(0, 0).setCurrentPiece("Black Rook");

            // Add white rooks.
            BufferedImage bufferedImagewRook1 = ImageIO.read(new File("src/assets/piece_images/white_rook.png"));
            Image pieceImagewrook1 = bufferedImagewRook1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIconwrook1 = new ImageIcon(pieceImagewrook1);
            guiView.getSquares(7, 7).add(new JLabel(pieceImageIconwrook1));
            guiView.getSquares(7, 0).add(new JLabel(pieceImageIconwrook1));
            guiView.getSquares(7, 7).setCurrentPiece("White Rook");
            guiView.getSquares(7, 0).setCurrentPiece("White Rook");

            // Add black kinghts.
            BufferedImage bufferedImagebKingt1 = ImageIO.read(new File("src/assets/piece_images/black_knight.png"));
            Image pieceImagebKnight1 = bufferedImagebKingt1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIconbKnight1 = new ImageIcon(pieceImagebKnight1);
            guiView.getSquares(0, 6).add(new JLabel(pieceImageIconbKnight1));
            guiView.getSquares(0, 1).add(new JLabel(pieceImageIconbKnight1));
            guiView.getSquares(0, 6).setCurrentPiece("Black Knight");
            guiView.getSquares(0, 1).setCurrentPiece("Black Knight");

            // Add white kinghts.
            BufferedImage bufferedImagewKingt1 = ImageIO.read(new File("src/assets/piece_images/white_knight.png"));
            Image pieceImagewKnight1 = bufferedImagewKingt1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIconwKnight1 = new ImageIcon(pieceImagewKnight1);
            guiView.getSquares(7, 6).add(new JLabel(pieceImageIconwKnight1));
            guiView.getSquares(7, 1).add(new JLabel(pieceImageIconwKnight1));
            guiView.getSquares(7, 6).setCurrentPiece("White Knight");
            guiView.getSquares(7, 1).setCurrentPiece("White Knight");

            // Add black bishops.
            BufferedImage bufferedImageBlackBishop = ImageIO.read(new File("src/assets/piece_images/black_bishop.png"));
            Image pieceImageBlackBishop = bufferedImageBlackBishop.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIconBlackBishop = new ImageIcon(pieceImageBlackBishop);
            guiView.getSquares(0, 5).add(new JLabel(pieceImageIconBlackBishop));
            guiView.getSquares(0, 2).add(new JLabel(pieceImageIconBlackBishop));
            guiView.getSquares(0, 5).setCurrentPiece("Black Bishop");
            guiView.getSquares(0, 2).setCurrentPiece("Black Bishop");

            // Add white bishops.
            BufferedImage bufferedImageWhiteBishop = ImageIO.read(new File("src/assets/piece_images/white_bishop.png"));
            Image pieceImageWhiteBishop = bufferedImageWhiteBishop.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIconWhiteBishop = new ImageIcon(pieceImageWhiteBishop);
            guiView.getSquares(7, 5).add(new JLabel(pieceImageIconWhiteBishop));
            guiView.getSquares(7, 2).add(new JLabel(pieceImageIconWhiteBishop));
            guiView.getSquares(7, 5).setCurrentPiece("White Bishop");
            guiView.getSquares(7, 2).setCurrentPiece("White Bishop");

            // Add black queen.
            BufferedImage bufferedImageBlackQueen = ImageIO.read(new File("src/assets/piece_images/black_queen.png"));
            Image pieceImageBlackQueen = bufferedImageBlackQueen.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIconBlackQueen = new ImageIcon(pieceImageBlackQueen);
            guiView.getSquares(0, 3).add(new JLabel(pieceImageIconBlackQueen));
            guiView.getSquares(0, 3).setCurrentPiece("Black Queen");

            // Add white queen.
            BufferedImage bufferedImageWhiteQueen = ImageIO.read(new File("src/assets/piece_images/white_queen.png"));
            Image pieceImageWhiteQueen = bufferedImageWhiteQueen.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIconWhiteQueen = new ImageIcon(pieceImageWhiteQueen);
            guiView.getSquares(7, 3).add(new JLabel(pieceImageIconWhiteQueen));
            guiView.getSquares(7, 3).setCurrentPiece("Black Queen");

            // Add black king.
            BufferedImage bufferedImageBlackKing = ImageIO.read(new File("src/assets/piece_images/black_king.png"));
            Image pieceImageBlackKing = bufferedImageBlackKing.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIconBlackKing = new ImageIcon(pieceImageBlackKing);
            guiView.getSquares(0, 4).add(new JLabel(pieceImageIconBlackKing));
            guiView.getSquares(0, 4).setCurrentPiece("Black King");

            // Add white king.
            BufferedImage bufferedImageWhiteKing = ImageIO.read(new File("src/assets/piece_images/white_king.png"));
            Image pieceImageWhiteKing = bufferedImageWhiteKing.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon pieceImageIconWhiteKing = new ImageIcon(pieceImageWhiteKing);
            guiView.getSquares(7, 4).add(new JLabel(pieceImageIconWhiteKing));
            guiView.getSquares(7, 4).setCurrentPiece("White King");

        } catch (Exception e) {
            addNotification(e.toString());
        }

    }

    /**
     * Displays the end game options popup screen.
     */
    public void endGameOptions() {
        endGameOptions.setVisible(true);
    }

    /**
     * Displays the pawn promotion popup screen.
     */
    public void showPawnPromotionScreen() {
        pawnPromotionScreen.setVisible(true);
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

    // public void turnOffNotifications(){}

    // public void turnOffBoardHighlight(){}

    // public void turnOffMoveHint(){}

    // public void turnOff_Undo_Redo(){}

    /**
     * Prints the location of the selected square to the notifications screen.
     * 
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Square copyButton = (Square) e.getSource();
        addNotification("You have selected square: " + copyButton.getSquareLocation() + ". Contains piece: "
                + copyButton.getPiece());
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
