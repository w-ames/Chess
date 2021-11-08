package edu.kingsu.SoftwareEngineering.Chess.GUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

public class GameSetUp extends JPanel {
    private ApplicationFrame container;

    public GameSetUp(ApplicationFrame container) {
        super();
        this.container = container;

        this.setBackground(Color.cyan);

        JPanel player1Panel = new JPanel();
        JPanel player2Panel = new JPanel();
        JPanel timePanel = new JPanel();
        JPanel settingsPanel = new JPanel();
        JPanel setupPanel = new JPanel();
        JLabel player1Label = new JLabel("Player 1");
        JLabel player2Label = new JLabel("Player 2");
        JLabel timeLabel = new JLabel("Time");
        JLabel timerLabel1 = new JLabel("10:10");
        JLabel incrementLabel = new JLabel("Increment");
        JLabel timerLabel2 = new JLabel("10:10");
        JLabel settingsLabel = new JLabel("Settings");
        String[] playerList = { "Human", "AI(Easy)", "AI(Medium)", "AI(Hard)" };

        int minTime = 10;
        int maxTime = 600;

        GridBagConstraints player1Constraints = new GridBagConstraints();
        GridBagConstraints player2Constraints = new GridBagConstraints();
        GridBagConstraints timeConstraints = new GridBagConstraints();
        GridBagConstraints settingsConstraints = new GridBagConstraints();
        GridBagConstraints setupConstraints = new GridBagConstraints();

        player1Label.setFont(new Font("Arial", Font.PLAIN, 35));
        player2Label.setFont(new Font("Arial", Font.PLAIN, 35));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        timerLabel1.setFont(new Font("Arial", Font.PLAIN, 35));
        incrementLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        timerLabel2.setFont(new Font("Arial", Font.PLAIN, 35));
        settingsLabel.setFont(new Font("Arial", Font.PLAIN, 35));

        this.setLayout(new GridBagLayout());
        GridBagConstraints setup = new GridBagConstraints();
        setup.fill = GridBagConstraints.BOTH;

        JButton goToMainMenu = new JButton("Return");
        goToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.show("menu");
            }
        });

        JButton startGame = new JButton("Start Game");
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // here
                ChessGame chessGame = new ChessGame(true, false, -1, -1);
                container.initializeChessPanel(chessGame);
                chessGame.start();
                container.show("chesspanel");
            }
        });

        /// Player 1 Panel
        player1Panel.setLayout(new GridBagLayout());
        player1Panel.setBackground(Color.GRAY);

        player1Constraints.fill = GridBagConstraints.BOTH;
        player1Constraints.gridx = 0;
        player1Constraints.gridy = 0;
        player1Panel.add(player1Label, player1Constraints);

        JComboBox player1Box = new JComboBox(playerList);
        player1Box.setSelectedIndex(0);
        player1Constraints.fill = GridBagConstraints.BOTH;
        player1Constraints.gridx = 0;
        player1Constraints.gridy = 1;
        player1Panel.add(player1Box, player1Constraints);

        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 0;
        setup.gridy = 0;
        setup.weightx = 0.45;
        setup.weighty = 0.4;
        setup.gridwidth = 1;
        setup.gridheight = 1;
        this.add(player1Panel, setup);

        // Player 2 Panel
        player2Panel.setLayout(new GridBagLayout());
        player2Panel.setBackground(Color.LIGHT_GRAY);

        player2Constraints.fill = GridBagConstraints.BOTH;
        player2Constraints.gridx = 0;
        player2Constraints.gridy = 0;
        player2Panel.add(player2Label, player2Constraints);

        JComboBox player2Box = new JComboBox(playerList);
        player2Box.setSelectedIndex(0);
        player2Constraints.fill = GridBagConstraints.BOTH;
        player2Constraints.gridx = 0;
        player2Constraints.gridy = 1;
        player2Panel.add(player2Box, player2Constraints);

        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 1;
        setup.gridy = 0;
        setup.weightx = 0.45;
        setup.weighty = 0.4;
        setup.gridwidth = 1;
        setup.gridheight = 1;
        this.add(player2Panel, setup);

        // Time Panel
        timePanel.setLayout(new GridBagLayout());
        timePanel.setBackground(Color.WHITE);

        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 0;
        timePanel.add(timeLabel, timeConstraints);

        JRadioButton timeOn = new JRadioButton("On");
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 1;
        timePanel.add(timeOn, timeConstraints);

        JRadioButton timeOff = new JRadioButton("Off");
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 2;
        timePanel.add(timeOff, timeConstraints);

        ButtonGroup timeGroup1 = new ButtonGroup();
        timeGroup1.add(timeOn);
        timeGroup1.add(timeOff);

        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 3;
        timePanel.add(timerLabel1, timeConstraints);

        JSlider timeSlider = new JSlider(minTime, maxTime);
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 4;
        timePanel.add(timeSlider, timeConstraints);

        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 5;
        timePanel.add(incrementLabel, timeConstraints);

        JRadioButton timeOn2 = new JRadioButton("On");
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 6;
        timePanel.add(timeOn2, timeConstraints);

        JRadioButton timeOff2 = new JRadioButton("Off");
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 7;
        timePanel.add(timeOff2, timeConstraints);

        ButtonGroup timeGroup2 = new ButtonGroup();
        timeGroup2.add(timeOn2);
        timeGroup2.add(timeOff2);

        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 8;
        timePanel.add(timerLabel2, timeConstraints);

        JSlider timeSlider2 = new JSlider(minTime, maxTime);
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 9;
        timePanel.add(timeSlider2, timeConstraints);

        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 0;
        setup.gridy = 1;
        setup.weightx = 0.3;
        setup.weighty = 0.4;
        setup.gridwidth = 1;
        setup.gridheight = 1;
        this.add(timePanel, setup);

        // Settings Panel
        settingsPanel.setLayout(new GridBagLayout());
        settingsPanel.setBackground(Color.GRAY);

        JCheckBox highlightMove = new JCheckBox("Highlight Move");
        JCheckBox moveHint = new JCheckBox("Move Hint");
        JCheckBox undoRedo = new JCheckBox("Undo / Redo");
        JCheckBox notification = new JCheckBox("Notifications");

        settingsConstraints.fill = GridBagConstraints.BOTH;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 0;
        settingsPanel.add(settingsLabel, settingsConstraints);

        settingsConstraints.fill = GridBagConstraints.BOTH;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 1;
        settingsPanel.add(highlightMove, settingsConstraints);

        settingsConstraints.fill = GridBagConstraints.BOTH;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 2;
        settingsPanel.add(moveHint, settingsConstraints);

        settingsConstraints.fill = GridBagConstraints.BOTH;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 3;
        settingsPanel.add(undoRedo, settingsConstraints);

        settingsConstraints.fill = GridBagConstraints.BOTH;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 4;
        settingsPanel.add(notification, settingsConstraints);

        JButton checkAll = new JButton("Check all / Uncheck All");
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 5;
        timePanel.add(checkAll, timeConstraints);

        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 1;
        setup.gridy = 1;
        setup.weightx = 0.3;
        setup.weighty = 0.4;
        setup.gridwidth = 1;
        setup.gridheight = 1;
        this.add(settingsPanel, setup);

        // Load
        setupPanel.setLayout(new GridBagLayout());
        setupPanel.setBackground(Color.RED);

        JButton loadButton = new JButton("Load Game");

        setupConstraints.fill = GridBagConstraints.BOTH;
        setupConstraints.gridx = 0;
        setupConstraints.gridy = 0;
        setupPanel.add(startGame, setupConstraints);

        setupConstraints.fill = GridBagConstraints.BOTH;
        setupConstraints.gridx = 0;
        setupConstraints.gridy = 1;
        setupPanel.add(loadButton, setupConstraints);

        setupConstraints.fill = GridBagConstraints.BOTH;
        setupConstraints.gridx = 0;
        setupConstraints.gridy = 2;
        setupPanel.add(goToMainMenu, setupConstraints);

        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 2;
        setup.gridy = 1;
        setup.weightx = 0.3;
        setup.weighty = 0.4;
        setup.gridwidth = 1;
        setup.gridheight = 1;
        this.add(setupPanel, setup);

    }
}
