package edu.kingsu.SoftwareEngineering.Chess.GUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;




public class GameSetUp extends JPanel {
    private ApplicationFrame container;
    public GameSetUp(ApplicationFrame container) {
        super();
        this.container=container;

        this.setBackground(Color.cyan);

        JPanel player1Panel = new JPanel();
        JPanel player2Panel = new JPanel();
        JPanel timePanel = new JPanel();
        JPanel settingsPanel = new JPanel();
        JPanel setupPanel = new JPanel();
        JLabel player1Label = new JLabel("Player 1");
        JLabel player2Label = new JLabel("Player 2");
        JLabel timeLabel = new JLabel("Time");
        JLabel settingsLabel = new JLabel("Settings");
        String [] playerList = {"Human","AI(Easy)","AI(Medium)","AI(Hard)"};


        GridBagConstraints player1Constraints = new GridBagConstraints();
        GridBagConstraints player2Constraints = new GridBagConstraints();
        GridBagConstraints timeConstraints = new GridBagConstraints();
        GridBagConstraints settingsConstraints = new GridBagConstraints();
        GridBagConstraints setupConstraints = new GridBagConstraints();
        

        player1Label.setFont(new Font("Arial", Font.PLAIN, 35));
        player2Label.setFont(new Font("Arial", Font.PLAIN, 35));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        settingsLabel.setFont(new Font("Arial", Font.PLAIN, 35));
    


        this.setLayout(new GridBagLayout());
        GridBagConstraints setup = new GridBagConstraints();
        setup.fill = GridBagConstraints.BOTH;

 



        JButton goToMainMenu = new JButton("Return");
        goToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // here
                container.show("menu");
            }
        });  
        
        
        ///Player 1 Panel
        player1Panel.setLayout(new GridBagLayout());
        player1Panel.setBackground(Color.GRAY);

        player1Constraints.fill = GridBagConstraints.BOTH;
        player1Constraints.gridx = 0;
        player1Constraints.gridy = 0;
        player1Panel.add(player1Label,player1Constraints);
        
        JComboBox player1Box = new JComboBox(playerList);
        player1Box.setSelectedIndex(0);
        // playerBox.addActionListener(player1Panel);
        player1Constraints.fill = GridBagConstraints.BOTH;
        player1Constraints.gridx = 0;
        player1Constraints.gridy = 1; 
        player1Panel.add(player1Box,player1Constraints);

        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 0;
        setup.gridy = 0;
        this.add(player1Panel,setup);
        
        //Player 2 Panel
        player2Panel.setLayout(new GridBagLayout());
        player2Panel.setBackground(Color.LIGHT_GRAY);

        player2Constraints.fill = GridBagConstraints.BOTH;
        player2Constraints.gridx = 0;
        player2Constraints.gridy = 0;
        player2Panel.add(player2Label,player2Constraints);

        JComboBox player2Box = new JComboBox(playerList);
        player2Box.setSelectedIndex(0);
        player2Constraints.fill = GridBagConstraints.BOTH;
        player2Constraints.gridx = 0;
        player2Constraints.gridy = 1;
        player2Panel.add(player2Box,player2Constraints);

        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 1;
        setup.gridy = 0;
        this.add(player2Panel,setup);

        //Time Panel
        timePanel.setLayout(new GridBagLayout());
        timePanel.setBackground(Color.WHITE);

        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 0;
        timePanel.add(timeLabel,timeConstraints);

        JRadioButton timeOn = new JRadioButton("On");
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 1;
        timePanel.add(timeOn, timeConstraints);

        JRadioButton timeOff = new JRadioButton("Off");
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 2;
        timePanel.add(timeOff,timeConstraints);

        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 1;
        setup.gridy = 1;
        this.add(timePanel,setup);

        //Settings Panel
        settingsPanel.setLayout(new GridBagLayout());
        settingsPanel.setBackground(Color.GRAY);

        JCheckBox highlightMove = new JCheckBox("Highlight Move");
        JCheckBox moveHint = new JCheckBox("Move Hint");
        JCheckBox undoRedo = new JCheckBox("Undo / Redo");
        JCheckBox notification = new JCheckBox("Notifications");

        settingsConstraints.fill = GridBagConstraints.BOTH;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 0;
        settingsPanel.add(settingsLabel,settingsConstraints);

        settingsConstraints.fill = GridBagConstraints.BOTH;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 1;
        settingsPanel.add(highlightMove,settingsConstraints);

        settingsConstraints.fill = GridBagConstraints.BOTH;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 2;
        settingsPanel.add(moveHint,settingsConstraints);

        settingsConstraints.fill = GridBagConstraints.BOTH;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 3;
        settingsPanel.add(undoRedo,settingsConstraints);

        settingsConstraints.fill = GridBagConstraints.BOTH;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 4;
        settingsPanel.add(notification,settingsConstraints);


        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 2;
        setup.gridy = 1;
        this.add(settingsPanel,setup);

        //Load
        setupPanel.setLayout(new GridBagLayout());
        setupPanel.setBackground(Color.RED);
        
        JButton loadButton = new JButton("Load Game");
        JButton startGame = new JButton("Start Game");

        setupConstraints.fill = GridBagConstraints.BOTH;
        setupConstraints.gridx = 0;
        setupConstraints.gridy = 0;
        setupPanel.add(loadButton,setupConstraints);

        setupConstraints.fill = GridBagConstraints.BOTH;
        setupConstraints.gridx = 0;
        setupConstraints.gridy = 1;
        setupPanel.add(startGame,setupConstraints);

        setupConstraints.fill = GridBagConstraints.BOTH;
        setupConstraints.gridx = 0;
        setupConstraints.gridy = 2;
        setupPanel.add(goToMainMenu,setupConstraints);

        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 3;
        setup.gridy = 1;
        this.add(setupPanel,setup);





        
    }
}
