package edu.kingsu.SoftwareEngineering.Chess.GUI;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {

    private static final String WINDOW_TITLE = "Chess Game";
    private static final Dimension WINDOW_SIZE = new Dimension(1500, 1000);

    private JPanel contentPanel;
    private CardLayout layout;

    public ApplicationFrame() {
        super(WINDOW_TITLE);
        contentPanel = new JPanel();
        layout = new CardLayout();

        contentPanel.setLayout(layout);

        setPreferredSize(WINDOW_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        pack();
        setVisible(true);
    }
}
