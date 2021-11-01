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

/**
 * ChessPanel holds all components of gameplay mode display.
 */
public class ChessPanel extends JPanel {

    private JLayeredPane layeredPane = new JLayeredPane();
    private MainLayer mainLayer = new MainLayer();
    private JPanel popupLayer = new JPanel();
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
        // LayeredPane is used to give z index for mainLayer and popupLayer.
        this.setLayout(new GridLayout(0, 1));

        popupLayer.setBackground(new Color(0, 94, 128));
        popupLayer.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(0, 112, 153)));
        this.add(layeredPane);
        layeredPane.add(mainLayer, Integer.valueOf(0));
        layeredPane.add(popupLayer, Integer.valueOf(1));

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
        gridBagForMainLayer.gridy = 1;
        gridBagForMainLayer.gridx = 0;
        gridBagForMainLayer.weightx = 0.875;
        gridBagForMainLayer.weighty = 0.7;
        gridBagForMainLayer.gridwidth = 2;
        gridBagForMainLayer.gridheight = 2;
        gridBagForMainLayer.insets = new Insets(30, 30, 30, 30);
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
        // layer if the user resizes the application frame.
        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = layeredPane.getSize(); // get size
                mainLayer.setSize(size);
                int popupScale = 2; // Determines the scale of the popup relative to mainLayer.
                // Makes the popupLayer rescale dynamically in the centre of ChessPanel.
                Dimension popupSize = new Dimension((int) (size.getWidth() / popupScale),
                        (int) (size.getHeight() / popupScale));
                popupLayer.setBounds((int) ((mainLayer.getWidth() - popupSize.getWidth()) / 2),
                        (int) ((mainLayer.getHeight() - popupSize.getHeight()) / 2), 0, 0);
                popupLayer.setSize(popupSize);

                layeredPane.revalidate();
                layeredPane.repaint();
            }
        });

        popupLayer.setVisible(false);
        layeredPane.revalidate();
        layeredPane.repaint();

    }

    public void initialize() {
        // Need to add parameters
        // Get times
    }

    public void endGameOptions() {
        // Need to add parameters
    }

}
