package edu.kingsu.SoftwareEngineering.Chess.GUI;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Insets;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import java.awt.Dimension;

public class GameSetUp extends JPanel {
    private ApplicationFrame container;
    private PGNFile pgnFile;

    public GameSetUp(ApplicationFrame container) {
        super();
        this.container = container;

        MainLayer background = new MainLayer();
        ButtonContainer player1Panel = new ButtonContainer();
        ButtonContainer player2Panel = new ButtonContainer();
        ButtonContainer timePanel = new ButtonContainer();
        ButtonContainer settingsPanel = new ButtonContainer();
        ButtonContainer setupPanel = new ButtonContainer();
        JLabel player1Label = new JLabel("Player 1", SwingConstants.CENTER);
        JLabel player2Label = new JLabel("Player 2", SwingConstants.CENTER);
        JLabel timeLabel = new JLabel("Time", SwingConstants.CENTER);
        JLabel timerLabel1 = new JLabel("10:00");
        JLabel incrementLabel = new JLabel("Increment", SwingConstants.CENTER);
        JLabel timerLabel2 = new JLabel("0:10");
        JLabel settingsLabel = new JLabel("Settings", SwingConstants.CENTER);
        String[] playerList = { "Human", "A.I. (Easy)", "A.I. (Medium)", "A.I. (Hard)" };
        int[] playerDepthList = { -1, 0, 2, 4 };

        int minTime = 10;
        int minTime2 = 10;
        int maxTime = 600;
        int maxTime2 = 600;

        GridBagConstraints player1Constraints = new GridBagConstraints();
        GridBagConstraints player2Constraints = new GridBagConstraints();
        GridBagConstraints timeConstraints = new GridBagConstraints();
        GridBagConstraints settingsConstraints = new GridBagConstraints();
        GridBagConstraints setupConstraints = new GridBagConstraints();

        player1Label.setFont(new Font("Arial", Font.PLAIN, 35));
        player1Label.setForeground(new Color(16, 46, 60));
        player2Label.setFont(new Font("Arial", Font.PLAIN, 35));
        player2Label.setForeground(new Color(16, 46, 60));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        timeLabel.setForeground(new Color(16, 46, 60));
        timerLabel1.setFont(new Font("Arial", Font.PLAIN, 35));
        timerLabel1.setForeground(new Color(16, 46, 60));
        incrementLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        incrementLabel.setForeground(new Color(16, 46, 60));
        timerLabel2.setFont(new Font("Arial", Font.PLAIN, 35));
        timerLabel2.setForeground(new Color(16, 46, 60));
        settingsLabel.setFont(new Font("Arial", Font.PLAIN, 35));

        settingsLabel.setForeground(new Color(16, 46, 60));

        this.setLayout(new GridBagLayout());
        GridBagConstraints setup = new GridBagConstraints();
        setup.fill = GridBagConstraints.BOTH;
        // setup.insets = new Insets(25,100,100,100);

        CustomButton goToMainMenu = new CustomButton("Return");
        goToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.show("menu");
            }
        });

        CustomButton startGame = new CustomButton("Start Game");

        this.setLayout(new GridBagLayout());
        GridBagConstraints mainLayerGB = new GridBagConstraints();
        mainLayerGB.fill = GridBagConstraints.BOTH;
        mainLayerGB.gridx = 0;
        mainLayerGB.gridy = 0;
        mainLayerGB.weightx = 1;
        mainLayerGB.weighty = 1;
        this.add(background, mainLayerGB);

        JPanel holdAllInCentre = new JPanel();
        holdAllInCentre.setLayout(new GridBagLayout());
        GridBagConstraints allGB = new GridBagConstraints();

        //// First Row
        JPanel playerContainer = new JPanel();
        playerContainer.setLayout(new GridBagLayout());
        GridBagConstraints pc = new GridBagConstraints();
        pc.fill = GridBagConstraints.BOTH;
        pc.insets = new Insets(10, 10, 10, 10);

        /// Player 1 Panel
        player1Panel.setLayout(new GridBagLayout());

        player1Constraints.fill = GridBagConstraints.BOTH;
        player1Constraints.gridx = 0;
        player1Constraints.gridy = 0;
        player1Constraints.weightx = 1;
        player1Constraints.weighty = 0.70;
        player1Constraints.insets = new Insets(3, 3, 3, 3);
        player1Panel.add(player1Label, player1Constraints);

        JComboBox player1Box = new JComboBox(playerList);
        player1Box.setSelectedIndex(0);
        player1Constraints.fill = GridBagConstraints.BOTH;
        player1Constraints.gridx = 0;
        player1Constraints.gridy = 1;
        player1Constraints.weightx = 1;
        player1Constraints.weighty = 1;
        player1Constraints.weightx = 1;
        player1Constraints.weighty = 0.30;
        player1Panel.add(player1Box, player1Constraints);

        pc.gridx = 0;
        pc.gridy = 0;
        pc.weightx = 1;
        pc.weighty = 1;
        pc.fill = GridBagConstraints.BOTH;
        playerContainer.add(player1Panel, pc);

        // Player 2 Panel
        player2Panel.setLayout(new GridBagLayout());

        player2Constraints.fill = GridBagConstraints.BOTH;
        player2Constraints.gridx = 0;
        player2Constraints.gridy = 0;
        player2Constraints.weightx = 1;
        player2Constraints.weighty = 0.70;
        player2Constraints.insets = new Insets(3, 3, 3, 3);
        player2Panel.add(player2Label, player2Constraints);

        JComboBox player2Box = new JComboBox(playerList);
        player2Box.setSelectedIndex(0);
        player2Constraints.fill = GridBagConstraints.BOTH;
        player2Constraints.gridx = 0;
        player2Constraints.gridy = 1;
        player2Constraints.weightx = 1;
        player2Constraints.weighty = 1;
        player2Constraints.weightx = 1;
        player2Constraints.weighty = 0.30;
        player2Panel.add(player2Box, player2Constraints);

        pc.gridx = 1;
        pc.gridy = 0;
        pc.gridheight = 1;
        pc.gridwidth = 1;
        pc.fill = GridBagConstraints.BOTH;
        playerContainer.add(player2Panel, pc);

        background.setLayout(new GridBagLayout());
        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 0;
        setup.gridy = 0;
        setup.weightx = 1;
        setup.weighty = 0.10;
        setup.insets = new Insets(25, 250, 5, 250);
        playerContainer.setOpaque(false);
        playerContainer.setMinimumSize(new Dimension(300, 100));
        playerContainer.setPreferredSize(new Dimension(300, 100));
        playerContainer.setMaximumSize(new Dimension(300, 100));
        holdAllInCentre.add(playerContainer, setup);

        // playerContainer.add(Player1Panel,gb)

        JPanel settingContainer = new JPanel();
        settingContainer.setLayout(new GridBagLayout());
        GridBagConstraints sc = new GridBagConstraints();
        sc.fill = GridBagConstraints.BOTH;
        sc.insets = new Insets(10, 10, 10, 10);

        // Time Panel
        timePanel.setLayout(new GridBagLayout());
        // timePanel.setBackground(Color.GRAY);
        timePanel.setOpaque(false);

        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 0;
        timeConstraints.insets = new Insets(3, 3, 3, 3);
        timeConstraints.weightx = 1;
        timeConstraints.weighty = 0.25;
        timePanel.add(timeLabel, timeConstraints);

        JRadioButton timeOn = new JRadioButton("On");
        timeOn.setBackground(new Color(0,0,0,0));
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 1;
        timeConstraints.weightx = 1;
        timeConstraints.weighty = 0.025;
        timeConstraints.insets = new Insets(3, 15, 3, 3);
        timePanel.add(timeOn, timeConstraints);

        JRadioButton timeOff = new JRadioButton("Off");
        timeOff.setBackground(new Color(0,0,0,0));
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 2;
        timeConstraints.weightx = 1;
        timeConstraints.weighty = 0.025;
        timePanel.add(timeOff, timeConstraints);

        ButtonGroup timeGroup1 = new ButtonGroup();
        timeGroup1.add(timeOn);
        timeGroup1.add(timeOff);

        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 3;
        timeConstraints.weightx = 1;
        timeConstraints.weighty = 0.1;
        timePanel.add(timerLabel1, timeConstraints);

        //FIX BUG: After image shows up when background set to 0,0,0,0
        JSlider timeSlider = new JSlider(minTime, maxTime);
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeSlider.setBackground(Color.GRAY);
        timeSlider.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(101, 101, 101)));
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 4;
        timeConstraints.weightx = 1;
        timeConstraints.weighty = 0.1;
        timeConstraints.insets = new Insets(3, 10, 3, 3);
        timePanel.add(timeSlider, timeConstraints);

        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 5;
        timeConstraints.insets = new Insets(3, 3, 3, 3);
        timeConstraints.weightx = 1;
        timeConstraints.weighty = 0.25;
        timePanel.add(incrementLabel, timeConstraints);

        JRadioButton timeOn2 = new JRadioButton("On");
        timeOn2.setBackground(new Color(0,0,0,0));
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 6;
        timeConstraints.weightx = 1;
        timeConstraints.weighty = 0.025;
        timeConstraints.insets = new Insets(3, 15, 3, 3);
        timePanel.add(timeOn2, timeConstraints);

        JRadioButton timeOff2 = new JRadioButton("Off");
        timeOff2.setBackground(new Color(0,0,0,0));
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 7;
        timeConstraints.weightx = 1;
        timeConstraints.weighty = 0.025;
        timePanel.add(timeOff2, timeConstraints);

        ButtonGroup timeGroup2 = new ButtonGroup();
        timeGroup2.add(timeOn2);
        timeGroup2.add(timeOff2);

        timeConstraints.fill = GridBagConstraints.BOTH;
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 8;
        timeConstraints.weightx = 1;
        timeConstraints.weighty = 0.1;
        timePanel.add(timerLabel2, timeConstraints);

        JSlider timeSlider2 = new JSlider(minTime2, maxTime2);
        timeConstraints.fill = GridBagConstraints.BOTH;
        timeSlider2.setBackground(Color.GRAY);
        timeSlider2.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(101, 101, 101)));
        timeConstraints.gridx = 0;
        timeConstraints.gridy = 9;
        timeConstraints.weightx = 1;
        timeConstraints.weighty = 0.1;
        timeConstraints.insets = new Insets(3, 10, 3, 3);
        timePanel.add(timeSlider2, timeConstraints);

        sc.gridx = 0;
        sc.gridy = 0;
        sc.weightx = 1;
        sc.weighty = 1;
        sc.fill = GridBagConstraints.NONE;
        sc.anchor = GridBagConstraints.CENTER;
        timePanel.setMinimumSize(new Dimension(300, 500));
        timePanel.setPreferredSize(new Dimension(300, 500));
        timePanel.setMaximumSize(new Dimension(300, 500));
        settingContainer.add(timePanel, sc);

        // Settings Panel
        settingsPanel.setLayout(new GridBagLayout());
        // settingsPanel.setBackground(Color.GRAY);
        settingsPanel.setOpaque(false);

        JCheckBox highlightMove = new JCheckBox("Highlight Move");
        JCheckBox moveHint = new JCheckBox("Move Hint");
        JCheckBox undoRedo = new JCheckBox("Undo / Redo");
        JCheckBox notification = new JCheckBox("Notifications");

        settingsConstraints.fill = GridBagConstraints.BOTH;
        settingsConstraints.anchor = GridBagConstraints.CENTER;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 0;
        settingsConstraints.weightx = 1;
        settingsConstraints.weighty = 0.3;
        settingsConstraints.insets = new Insets(1, 1, 1, 1);
        settingsPanel.add(settingsLabel, settingsConstraints);
        
        JPanel checkBoxHolder = new JPanel();
        checkBoxHolder.setLayout(new GridBagLayout());
        highlightMove.setBackground(new Color(0,0,0,0));
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 0;
        settingsConstraints.weightx = 1;
        settingsConstraints.weighty = 0.25;
        settingsConstraints.insets = new Insets(1, 1, 1, 1);
        checkBoxHolder.add(highlightMove, settingsConstraints);

        moveHint.setBackground(new Color(0,0,0,0));
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 1;
        settingsConstraints.weightx = 1;
        settingsConstraints.weighty = 0.25;
        settingsConstraints.insets = new Insets(1, 1, 1, 1);
        checkBoxHolder.add(moveHint, settingsConstraints);

        undoRedo.setBackground(new Color(0,0,0,0));
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 2;
        settingsConstraints.weightx = 1;
        settingsConstraints.weighty = 0.25;
        settingsConstraints.insets = new Insets(1, 1, 1, 1);
        checkBoxHolder.add(undoRedo, settingsConstraints);

        notification.setBackground(new Color(0,0,0,0));
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 3;
        settingsConstraints.weightx = 1;
        settingsConstraints.weighty = 0.25;
        settingsConstraints.insets = new Insets(2, 2, 2, 2);
        checkBoxHolder.add(notification, settingsConstraints);

        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 2;
        settingsConstraints.weightx = 1;
        settingsConstraints.weighty = 0.4;
        settingsConstraints.anchor = GridBagConstraints.CENTER;
        settingsConstraints.fill = GridBagConstraints.VERTICAL;
        checkBoxHolder.setOpaque(false);
        settingsPanel.add(checkBoxHolder, settingsConstraints);

        CustomButton checkAll = new CustomButton("Select All");

        settingsConstraints.fill = GridBagConstraints.NONE;
        settingsConstraints.gridx = 0;
        settingsConstraints.gridy = 3;
        settingsConstraints.weightx = 1;
        settingsConstraints.weighty = 0.3;
        settingsConstraints.insets = new Insets(3, 3, 10, 3);
        checkAll.setMinimumSize(new Dimension(150, 50));
        checkAll.setPreferredSize(new Dimension(150, 50));
        checkAll.setMaximumSize(new Dimension(150, 50));
        settingsPanel.add(checkAll, settingsConstraints);

        sc.gridx = 1;
        sc.gridy = 0;
        sc.fill = GridBagConstraints.NONE;
        sc.anchor = GridBagConstraints.CENTER;
        settingsPanel.setMinimumSize(new Dimension(300, 500));
        settingsPanel.setPreferredSize(new Dimension(300, 500));
        settingsPanel.setMaximumSize(new Dimension(300, 500));
        settingContainer.add(settingsPanel, sc);

        // Load
        setupPanel.setLayout(new GridBagLayout());

        CustomButton loadButton = new CustomButton("Load Game");

        JPanel buttonHolder = new JPanel();
        buttonHolder.setLayout(new GridBagLayout());

        setupConstraints.fill = GridBagConstraints.NONE;
        setupConstraints.gridx = 0;
        setupConstraints.gridy = 0;
        setupConstraints.weightx = 1;
        setupConstraints.weighty = 1;
        setupConstraints.insets = new Insets(120, 5, 5, 5);
        startGame.setMinimumSize(new Dimension(150, 50));
        startGame.setPreferredSize(new Dimension(150, 50));
        startGame.setMaximumSize(new Dimension(150, 50));
        buttonHolder.add(startGame, setupConstraints);

        setupConstraints.gridx = 0;
        setupConstraints.gridy = 1;
        setupConstraints.insets = new Insets(5, 5, 5, 5);
        loadButton.setMinimumSize(new Dimension(150, 50));
        loadButton.setPreferredSize(new Dimension(150, 50));
        loadButton.setMaximumSize(new Dimension(150, 50));
        buttonHolder.add(loadButton, setupConstraints);

        setupConstraints.gridx = 0;
        setupConstraints.gridy = 2;
        setupConstraints.insets = new Insets(5, 5, 120, 5);
        goToMainMenu.setMinimumSize(new Dimension(150, 50));
        goToMainMenu.setPreferredSize(new Dimension(150, 50));
        goToMainMenu.setMaximumSize(new Dimension(150, 50));
        buttonHolder.add(goToMainMenu, setupConstraints);

        setupConstraints.fill = GridBagConstraints.HORIZONTAL;
        setupConstraints.gridx = 0;
        setupConstraints.gridy = 0;
        setupConstraints.insets = new Insets(0, 0, 0, 0);
        buttonHolder.setOpaque(false);
        setupPanel.add(buttonHolder, setupConstraints);

        sc.gridx = 2;
        sc.gridy = 0;
        sc.weightx = 1;
        sc.weighty = 1;
        sc.fill = GridBagConstraints.NONE;
        sc.anchor = GridBagConstraints.CENTER;
        setupPanel.setMinimumSize(new Dimension(300, 500));
        setupPanel.setPreferredSize(new Dimension(300, 500));
        setupPanel.setMaximumSize(new Dimension(300, 500));
        settingContainer.add(setupPanel, sc);

        setup.fill = GridBagConstraints.BOTH;
        setup.gridx = 0;
        setup.gridy = 1;
        setup.weightx = 1;
        setup.weighty = 0.90;
        setup.insets = new Insets(5, 150, 25, 150);
        settingContainer.setOpaque(false);
        holdAllInCentre.add(settingContainer, setup);
        allGB.gridx = 0;
        allGB.gridy = 0;
        allGB.fill = GridBagConstraints.NONE;
        holdAllInCentre.setOpaque(false);
        background.add(holdAllInCentre, allGB);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGame chessGame = new ChessGame(playerDepthList[player1Box.getSelectedIndex()],
                        playerDepthList[player2Box.getSelectedIndex()], -1, -1);
                container.initializeChessPanel(chessGame);
                chessGame.start();
                container.show("chesspanel");
            }
        });

    }

    public void setPGNFile(PGNFile file){
        pgnFile= file;
    }

    public PGNFile getPGNFile(){
        return pgnFile;
    }

}
