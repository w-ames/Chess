package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.Color;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Insets;
import java.awt.FlowLayout;

/**
 * ChessGameAlgebraicView class holds the graphical view of the game board.
 * 
 * @author Chelsie Baic
 * @author Greg Cal
 */
public class ChessGameAlgebraicView extends ChessGameView {

    private JPanel algebraicDisplayPanel = new JPanel();
    private JTextField algebricInputPanel = new JTextField();
    private CustomButton algebraicMoveSubmitButton = new CustomButton("Submit");

    /**
     * Draws the algebraic view panel to be added to ChessPanel.
     * 
     */
    public ChessGameAlgebraicView() {
        algebraicDisplayPanel.setLayout(new FlowLayout());
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(191, 191, 191)));

        // Adding algebraicDisplayPanel to the ChessGameAlgebraicView panel.
        this.setLayout(new GridBagLayout());

        algebraicDisplayPanel.setBackground(new Color(232, 232, 232));
        algebraicDisplayPanel.setOpaque(true);
        algebraicDisplayPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(224, 224, 224)));

        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.BOTH;
        gb.gridy = 0;
        gb.gridx = 0;
        gb.weightx = 1;
        gb.weighty = 0.95;
        gb.gridwidth = 2;
        gb.insets = new Insets(10, 10, 5, 10);
        this.add(algebraicDisplayPanel, gb);

        // Adding label to the algraic move input JTextField.
        JLabel algebraicMoveInputLabel = new JLabel("Enter Algebraic Move: ");
        algebraicMoveInputLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        algebraicMoveInputLabel.setForeground(new Color(16, 46, 60));
        gb.gridy = 1;
        gb.gridx = 0;
        gb.weightx = 1;
        gb.weighty = 0.025;
        gb.gridwidth = 1;
        gb.insets = new Insets(5, 5, 1, 7);
        this.add(algebraicMoveInputLabel, gb);

        // Adding algebraicInputPanel to the ChessGameAlgebraicView panel.
        // algebricInputPanel.setMinimumSize(new Dimension(20, 100));
        gb.fill = GridBagConstraints.BOTH;
        gb.gridy = 2;
        gb.gridx = 0;
        gb.weightx = 0.75;
        gb.weighty = 0.025;
        gb.gridwidth = 1;
        gb.insets = new Insets(0, 5, 5, 5);
        this.add(algebricInputPanel, gb);

        // Adding the submit button for the algebraic view input.
        gb.fill = GridBagConstraints.BOTH;
        gb.gridy = 2;
        gb.gridx = 1;
        gb.weightx = 0.25;
        gb.weighty = 0.025;
        gb.insets = new Insets(0, 5, 5, 5);
        this.add(algebraicMoveSubmitButton, gb);

    }

    /**
     * Overrides the JPanel paint component to allow for gradient paint.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, new Color(153, 153, 153), 0, h, new Color(242, 242, 242));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
    }

    /**
     * handles move validation and submits valid moves, or displays to the user that
     * their move was invalid.
     */
    public void submitAlgebraicMove() {
        String userInputMove = algebricInputPanel.getText();
    }

    @Override
    public void update() {
        algebraicDisplayPanel.removeAll();
        List<String> pgnMoves = getChessGame().getAlgebraicHistory();
        for (String move : pgnMoves) {
            algebraicDisplayPanel.add(new JLabel(move));
        }
    }

    @Override
    public void addListeners() {
        algebricInputPanel.addActionListener(new ChessGameAlgebraicController(this, getChessGame()));
    }

}
