package edu.kingsu.SoftwareEngineering.Chess.GUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Insets;
import java.awt.Dimension;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import edu.kingsu.SoftwareEngineering.Chess.PGN.PGNFile;

public class MainMenu extends JPanel {

    private ApplicationFrame container;
    private CustomButton newGameButton = new CustomButton("New Game");
    private CustomButton tutorialButton = new CustomButton("Tutorial");
    private CustomButton loadGameButton = new CustomButton("Load Game");
    private CustomButton helpButton = new CustomButton("Help");
    private CustomButton exitButton = new CustomButton("Exit");
    private MainLayer background = new MainLayer();
    private ButtonContainer buttonContainer = new ButtonContainer();

    public MainMenu(ApplicationFrame container) {
        // Add super constructor and parameter to go in between cards
        super();
        this.container = container;
        //

        // Set a JLabel for the LOGO
        ImageIcon chessIcon = new ImageIcon("./src/assets/images/chesslogo2.png");
        Image image = chessIcon.getImage();
        Image newimage = image.getScaledInstance(450, 200, java.awt.Image.SCALE_SMOOTH);
        chessIcon = new ImageIcon(newimage);
        JLabel chessTitle = new JLabel(chessIcon);
        chessTitle.setBackground(Color.black);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // here
                container.show("gamesetup");
            }
        });
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // here
                JOptionPane.showMessageDialog(container, "Under Construction", "Sorry", JOptionPane.ERROR_MESSAGE);
            }
        });
        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Start tutorial game from the main menu.
                boolean highlightMoveSwitch = true;
                boolean notificationsSwitch = true;
                boolean moveHintSwitch = true;
                boolean undoRedoSwitch = true;

                String player1Name = "Student";
                String player2Name = "Teacher";

                ChessGame chessGame = new ChessGame(-1, 0, -1, -1);

                // Switches allow tutorial options to be turned on or off.
                container.initializeChessPanel(chessGame, highlightMoveSwitch, notificationsSwitch, moveHintSwitch,
                        undoRedoSwitch, player1Name, player2Name);
                chessGame.start();
                container.show("chesspanel");
            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("about", 2);
                helpWindow.setLocation(1200, 300);
                helpWindow.setSize(new Dimension(600, 800));
                helpWindow.setVisible(true);
                helpWindow.setMinimumSize(new Dimension(600, 800));
                helpWindow.setPreferredSize(new Dimension(600, 800));
                helpWindow.setMaximumSize(new Dimension(700, 900));
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.setLayout(new GridBagLayout());

        GridBagConstraints forBackGround = new GridBagConstraints();
        forBackGround.fill = GridBagConstraints.BOTH;
        forBackGround.gridx = 0;
        forBackGround.gridy = 0;
        forBackGround.weightx = 1;
        forBackGround.weighty = 1;
        this.add(background, forBackGround);

        JPanel invisbleContainer = new JPanel();
        invisbleContainer.setLayout(new GridBagLayout());
        background.setLayout(new GridBagLayout());
        forBackGround.gridx = 0;
        forBackGround.gridy = 0;
        forBackGround.weightx = 1;
        forBackGround.weighty = 0.10;
        invisbleContainer.add(chessTitle, forBackGround);

        forBackGround.fill = GridBagConstraints.VERTICAL;
        forBackGround.gridx = 0;
        forBackGround.gridy = 1;
        // forBackGround.weightx = 1;
        // forBackGround.weighty = 0.90;
        forBackGround.insets = new Insets(23, 500, 50, 500);
        buttonContainer.setMinimumSize(new Dimension(300, 500));
        buttonContainer.setPreferredSize(new Dimension(300, 500));
        buttonContainer.setMaximumSize(new Dimension(300, 500));
        invisbleContainer.add(buttonContainer, forBackGround);

        GridBagConstraints c = new GridBagConstraints();
        buttonContainer.setLayout(new GridBagLayout());

        JPanel invisibleButtonHolder = new JPanel();
        invisibleButtonHolder.setLayout(new GridBagLayout());

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        newGameButton.setMinimumSize(new Dimension(150, 50));
        newGameButton.setPreferredSize(new Dimension(150, 50));
        newGameButton.setMaximumSize(new Dimension(150, 50));
        invisibleButtonHolder.add(newGameButton, c);

        c.gridx = 0;
        c.gridy = 1;
        loadGameButton.setMinimumSize(new Dimension(150, 50));
        loadGameButton.setPreferredSize(new Dimension(150, 50));
        loadGameButton.setMaximumSize(new Dimension(150, 50));
        invisibleButtonHolder.add(loadGameButton, c);

        c.gridx = 0;
        c.gridy = 2;
        tutorialButton.setMinimumSize(new Dimension(150, 50));
        tutorialButton.setPreferredSize(new Dimension(150, 50));
        tutorialButton.setMaximumSize(new Dimension(150, 50));
        invisibleButtonHolder.add(tutorialButton, c);

        c.gridx = 0;
        c.gridy = 3;
        helpButton.setMinimumSize(new Dimension(150, 50));
        helpButton.setPreferredSize(new Dimension(150, 50));
        helpButton.setMaximumSize(new Dimension(150, 50));
        invisibleButtonHolder.add(helpButton, c);

        c.gridx = 0;
        c.gridy = 5;
        exitButton.setMinimumSize(new Dimension(150, 50));
        exitButton.setPreferredSize(new Dimension(150, 50));
        exitButton.setMaximumSize(new Dimension(150, 50));
        invisibleButtonHolder.add(exitButton, c);

        GridBagConstraints gb = new GridBagConstraints();
        gb.gridx = 0;
        gb.gridy = 0;
        gb.fill = GridBagConstraints.NONE;
        invisibleButtonHolder.setOpaque(false);
        buttonContainer.add(invisibleButtonHolder, gb);

        gb.weightx = 1;
        gb.weighty = 1;
        invisbleContainer.setOpaque(false);
        background.add(invisbleContainer, gb);

    }
}
