package edu.kingsu.SoftwareEngineering.Chess.GUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel {

    private ApplicationFrame container;

    public MainMenu(ApplicationFrame container) {
        //Add super constructor and parameter to go in between cards
        super();
        this.container = container;
        //

        JButton newGameButton = new JButton("New Game");
        JButton tutorialButton = new JButton("Tutorial");
        JButton loadGameButton = new JButton("Load Game");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");
        this.setBackground(Color.CYAN);

        //Set a JLabel for the LOGO
        ImageIcon chessIcon = new ImageIcon("./src/assets/chesslogo.png");
        Image image = chessIcon.getImage();
        Image newimage = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
        chessIcon = new ImageIcon(newimage);
        JLabel chessTitle = new JLabel(chessIcon);
        chessTitle.setBackground(Color.black);

        //Set the Font sizes for buttons
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 35));
        loadGameButton.setFont(new Font("Arial", Font.PLAIN, 35));
        tutorialButton.setFont(new Font("Arial", Font.PLAIN, 35));
        helpButton.setFont(new Font("Arial", Font.PLAIN, 35));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 35));
        

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
                JOptionPane.showMessageDialog(container, "Under Construction", "Sorry",JOptionPane.ERROR_MESSAGE );
            }
        });
        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // here
                JOptionPane.showMessageDialog(container, "Under Construction", "Sorry",JOptionPane.ERROR_MESSAGE );
            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // here
                JOptionPane.showMessageDialog(container, "Under Construction", "Sorry",JOptionPane.ERROR_MESSAGE );
            }
        });
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        
        //// Button test
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 50;
        c.ipady = 50;
        this.add(chessTitle, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 50;
        c.ipady = 50;
        this.add(newGameButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        // c.gridheight = 1;
        // c.gridwidth = 1;
        c.ipadx = 50;
        c.ipady=50;

        this.add(loadGameButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.ipadx = 50;
        c.ipady = 50;
        // c.gridheight = 1;
        // c.gridwidth = 1;
        this.add(tutorialButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.ipadx = 50;
        c.ipady = 50;
        // c.gridheight = 1;
        // c.gridwidth = 1;
        this.add(helpButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.ipadx = 50;
        c.ipady = 50;
        // c.gridheight = 1;
        // c.gridwidth = 1;
        this.add(exitButton, c);
        
        
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
