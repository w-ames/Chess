package edu.kingsu.SoftwareEngineering.Chess.GUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Insets;

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
        ImageIcon chessIcon = new ImageIcon("./src/assets/chesslogo.png");
        Image image = chessIcon.getImage();
        Image newimage = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
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
                // here
                JOptionPane.showMessageDialog(container, "Under Construction", "Sorry", JOptionPane.ERROR_MESSAGE);
            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // here
                JOptionPane.showMessageDialog(container, "Under Construction", "Sorry", JOptionPane.ERROR_MESSAGE);
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

        background.setLayout(new GridBagLayout());
        forBackGround.gridx = 0;
        forBackGround.gridy = 0;
        forBackGround.weightx = 1;
        forBackGround.weighty = 0.10;
        background.add(chessTitle, forBackGround);

        forBackGround.fill = GridBagConstraints.BOTH;
        forBackGround.gridx = 0;
        forBackGround.gridy = 1;
        forBackGround.weightx = 1;
        forBackGround.weighty = 0.90;
        forBackGround.insets = new Insets(23, 500, 25, 500);
        background.add(buttonContainer, forBackGround);

        GridBagConstraints c = new GridBagConstraints();
        buttonContainer.setLayout(new GridBagLayout());

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        buttonContainer.add(newGameButton, c);

        c.gridx = 0;
        c.gridy = 1;
        // c.gridheight = 1;
        // c.gridwidth = 1;

        buttonContainer.add(loadGameButton, c);

        c.gridx = 0;
        c.gridy = 2;
        // c.gridheight = 1;
        // c.gridwidth = 1;
        buttonContainer.add(tutorialButton, c);

        c.gridx = 0;
        c.gridy = 3;
        // c.gridheight = 1;
        // c.gridwidth = 1;
        buttonContainer.add(helpButton, c);

        c.gridx = 0;
        c.gridy = 5;
        // c.gridheight = 1;
        // c.gridwidth = 1;
        buttonContainer.add(exitButton, c);

        // button = new JButton("Button 2");
        // c.fill = GridBagConstraints.HORIZONTAL;
        // c.weightx = 0.5;
        // c.gridx = 0;
        // c.gridy = 0;
        // this.add(button, c);

        // button = new JButton("Button 3");
        // c.fill = GridBagConstraints.HORIZONTAL;
        // c.weightx = 0.5;
        // c.gridx = 2;
        // c.gridy = 0;
        // this.add(button, c);

        // button = new JButton("Long-Named Button 4");
        // c.fill = GridBagConstraints.HORIZONTAL;
        // c.ipady = 40; // make this component tall
        // c.weightx = 0.0;
        // c.gridwidth = 3;
        // c.gridx = 0;
        // c.gridy = 1;
        // this.add(button, c);

    }

}
