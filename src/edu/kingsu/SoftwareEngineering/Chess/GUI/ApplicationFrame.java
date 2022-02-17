package edu.kingsu.SoftwareEngineering.Chess.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.*;
import java.awt.Dimension;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * ApplicationFrame is a JFrame that holds all the JPanel of the application 
 * 
 * @author Gregory Cal
 * @author Chelsie Bajic
 * @since 11/2021
 */
public class ApplicationFrame extends JFrame {

    private static final String WINDOW_TITLE = "Chess Game";
    private static final Dimension WINDOW_SIZE = new Dimension(1200, 900);

    private JPanel contentPanel = new JPanel();
    private CardLayout layout = new CardLayout();
    private MainMenu mainMenu = new MainMenu(this);
    private GameSetUp gameSetUp;
    private ChessPanel chessPanel;
    private JMenuBar menuBar = new JMenuBar();

    private JMenu file = new JMenu("File");
    private JMenu options = new JMenu("Options");
    private JMenu help = new JMenu("Help");

    private JMenuItem newGameMenuItem = new JMenuItem("New Game");
    private JMenuItem restartGameMenuItem = new JMenuItem("Restart Game");
    private JMenuItem mainMenuMenuItem = new JMenuItem("Main Menu");
    private JMenuItem loadGameMenuItem = new JMenuItem("Load Game");
    private JMenuItem saveGameMenuItem = new JMenuItem("Save Game");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");

    private JRadioButtonMenuItem turnOnOffBoardHighlight = new JRadioButtonMenuItem("Board Highlight", true);
    private JRadioButtonMenuItem turnOnOffNotifications = new JRadioButtonMenuItem("Notifications", true);
    private JRadioButtonMenuItem turnOnOffMoveHints = new JRadioButtonMenuItem("Move Hints", true);
    private JRadioButtonMenuItem turnOnOffUndoRedo = new JRadioButtonMenuItem("Undo/Redo", true);
    private JMenuItem playerOptions = new JMenuItem("Player Options");

    private JMenuItem about = new JMenuItem("About");
    private JMenuItem appHelp = new JMenuItem("Application Help");
    private JMenuItem chessRules = new JMenuItem("Game Rules");
    private JMenuItem pieceInfo = new JMenuItem("Piece Information");

    private ChessGameSaveController saveController;
    private ChessGameLoadController loadController;

    private int width;
    private int height;

    // Members for JOption Pane (player options)
    JComboBox<String> player1ComboBox = new JComboBox<>();
    JComboBox<String> player2ComboBox = new JComboBox<>();
    // JButton playerOptionsSubmit = new JButton("Submit");
    int player1ComboBoxChoice;
    int player2ComboBoxChoice;

    /**
     * Creates the main application frame for Java Chess.
     * 
     * 
     */
    public ApplicationFrame() {

        super(WINDOW_TITLE);
        this.setMinimumSize(new Dimension(1250, 850));
        this.setBackground(new Color(16, 46, 60));
        add(contentPanel);

        player2ComboBox.addItem("Human");
        player2ComboBox.addItem("A.I. (easy)");
        player2ComboBox.addItem("A.I. (medium)");
        player2ComboBox.addItem("A.I. (hard)");

        player1ComboBox.addItem("Human");
        player1ComboBox.addItem("A.I. (easy)");
        player1ComboBox.addItem("A.I. (medium)");
        player1ComboBox.addItem("A.I. (hard)");

        gameSetUp = new GameSetUp(this);
        chessPanel = new ChessPanel(this);

        contentPanel.setLayout(layout);

        // Add cards test
        contentPanel.add(mainMenu, "menu");
        contentPanel.add(gameSetUp, "gamesetup");
        contentPanel.add(chessPanel, "chesspanel");

        layout.show(contentPanel, "menu");

        setPreferredSize(WINDOW_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        saveController = new ChessGameSaveController(null, this);
        loadController = new ChessGameLoadController(null, this, gameSetUp);
        gameSetUp.addLoadListener();
        addMenuBar();
        addActionListenersToMenuBar();

        menuBar.setVisible(false);

        pack();
        setVisible(true);
    }

    /**
     * Tells the layout which card view to show (menu, gamesetup, or chesspanel).
     * 
     * @param card The string name of the card to show.
     */
    public void show(String card) {
        if (card.equalsIgnoreCase("gamesetup")) {
            getLoadController().setChessGame(null);
        }
        layout.show(contentPanel, card);
    }

    /**
     * Shows menu bar.
     */
    public void showMenuBarOnChessPanel() {
        menuBar.setVisible(true);
    }

    /**
     * Hides menu bar.
     */
    public void hideMenuBar() {
        menuBar.setVisible(false);
    }

    /**
     * Toggles the JRadioButtonMenuItem for board highlight.
     * 
     * @param choice True = on, false = off.
     */
    public void toggleBoardHighlightsMenuCheckBox(boolean choice) {
        turnOnOffBoardHighlight.setSelected(choice);
    }

    /**
     * Toggles the JRadioButtonMenuItem for move hint.
     * 
     * @param choice True = on, false = off.
     */
    public void toggleMoveHintMenuCheckBox(boolean choice) {
        turnOnOffMoveHints.setSelected(choice);
    }

    /**
     * Toggles the JRadioButtonMenuItem for notifications.
     * 
     * @param choice True = on, false = off.
     */
    public void toggleNotificationsCheckBox(boolean choice) {
        turnOnOffNotifications.setSelected(choice);
    }

    /**
     * Toggles the JRadioButtonMenuItem for undo/red.
     * 
     * @param choice True = on, false = off.
     */
    public void toggleUndoRedoCheckBox(boolean choice) {
        turnOnOffUndoRedo.setSelected(choice);
    }

    /**
     * Initializes the ChessPanel object. Checks and sets the size of the current
     * frame height and width so that the chess pieces can be sized accordingly when
     * the ChessPanel is first instanciated.
     * 
     * Calls the make PiecesResizable() function so that the chess pieces sizes can
     * dynamically resize if the user resizes the application frame during gameplay
     * mode.
     * 
     * @param chessGame           The chessGame to be represented by this chess
     *                            panel.
     * @param highlightMoveSwitch Turns the board highlight tutorial option on or
     *                            off.
     * @param notificationsSwitch Turns the notifications tutorial option on or off.
     * @param moveHintSwitch      Turns the move hint tutorial options on or off.
     */
    public void initializeChessPanel(ChessGame chessGame, boolean highlightMoveSwitch, boolean notificationsSwitch,
            boolean moveHintSwitch, boolean undoRedoSwitch, String player1Name, String player2Name, boolean inTutorialMode) {

        this.width = (int) this.getBounds().getWidth();
        this.height = (int) this.getBounds().getHeight();

        chessPanel.hideEndGameOptions();
        chessPanel.hidePawnPromotionScreen();
        chessPanel.setPlayerNames(player1Name, player2Name);
        chessPanel.initialize(chessGame);
        chessPanel.updateContainerDimensions(width, height);
        makePiecesResizeable();
        chessPanel.setMoveHintSwitch(moveHintSwitch);
        chessPanel.setNotificationsSwitch(notificationsSwitch);
        chessPanel.setboardHighlightSwitch(highlightMoveSwitch);
        chessPanel.setundoRedoSwitch(undoRedoSwitch);
        chessPanel.checkTutorialSelections();
        chessPanel.inTutorialMode(inTutorialMode);

    }

    /**
     * Builds and adds the menu bar to the application frame.
     */
    public void addMenuBar() {

        file.add(newGameMenuItem);
        file.add(restartGameMenuItem);
        file.add(loadGameMenuItem);
        file.add(saveGameMenuItem);
        file.add(mainMenuMenuItem);
        file.add(exitMenuItem);

        options.add(turnOnOffBoardHighlight);
        options.add(turnOnOffNotifications);
        options.add(turnOnOffMoveHints);
        options.add(turnOnOffUndoRedo);
        options.add(playerOptions);

        help.add(chessRules);
        help.add(pieceInfo);
        help.add(appHelp);
        help.add(about);

        menuBar.add(file);
        menuBar.add(options);
        menuBar.add(help);
        this.setJMenuBar(menuBar);
    }

    /**
     * Makes the chess pieces resize when the application frame is resized by the
     * user.
     */
    public void makePiecesResizeable() {

        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {

                chessPanel.updatePieceSizes();
            }
        });
    }


    /**
     * Disable menu options
     */
    public void disableJMenu(){
        file.setEnabled(false);
        options.setEnabled(false);
        help.setEnabled(false);
    }

    /**
     * Enable menu  options
     */
    public void enableJMenu(){
        file.setEnabled(true);
        options.setEnabled(true);
        help.setEnabled(true);
    }

    /**
     * 0 = Human, 1 = A.I (easy), 2 = A.I. (medium), 3 = A.I (hard)
     * 
     * @return Choice from player options pane combo box for player 1;
     */
    public int getPlayer1OptionsSelection() {
        return player1ComboBoxChoice;
    }

    /**
     * 0 = Human, 1 = A.I (easy), 2 = A.I. (medium), 3 = A.I (hard)
     * 
     * @return Choice from player options pane combo box for player 1;
     */
    public int getPlayer2OptionsSelection() {
        return player2ComboBoxChoice;
    }

    /**
     * Adds action listeners to the menu bar.
     */
    public void addActionListenersToMenuBar() {

        newGameMenuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                    if(chessPanel.returnTutorialStatus() != true){
                        int userSelection= confirmSave();
                    if(userSelection == JOptionPane.YES_OPTION){
                        int saveSelection= ApplicationFrame.this.getSaveController().doAction();
                        if(saveSelection == JFileChooser.APPROVE_OPTION){
                            layout.show(contentPanel, "gamesetup");
                            menuBar.setVisible(false);
                        }
                    }else if(userSelection == JOptionPane.NO_OPTION){
                        layout.show(contentPanel, "gamesetup");
                        menuBar.setVisible(false);
                    }
                } else {
                    layout.show(contentPanel, "gamesetup");
                    menuBar.setVisible(false);
                    chessPanel.resetTutorialNotifications();
                    chessPanel.returnMessageView().turnNotificationSwitch(true);
                }
            }
        });

        restartGameMenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(chessPanel.returnTutorialStatus() != true){
                    int userSelection= confirmSave();
                    if(userSelection == JOptionPane.YES_OPTION){
                        int saveSelection= ApplicationFrame.this.getSaveController().doAction();
                        if(saveSelection == JFileChooser.APPROVE_OPTION){
                            chessPanel.getChessGame().rematch();
                        }
                    }else if(userSelection == JOptionPane.NO_OPTION){
                        chessPanel.getChessGame().rematch();
                        chessPanel.resetTutorialNotifications();
                        chessPanel.clearNotifications();
                    }
                } else {
                    chessPanel.getChessGame().rematch();
                    chessPanel.resetTutorialNotifications();
                    chessPanel.clearNotifications();
                    chessPanel.addNotification("~Tutorial restart~");
                }
                
            }
        });

        mainMenuMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chessPanel.returnTutorialStatus() != true){
                        int userSelection= confirmSave();
                    if(userSelection == JOptionPane.YES_OPTION){
                        int saveSelection= ApplicationFrame.this.getSaveController().doAction();
                        if(saveSelection == JFileChooser.APPROVE_OPTION){
                            layout.show(contentPanel, "menu");
                            menuBar.setVisible(false);
                        }
                    }else if(userSelection == JOptionPane.NO_OPTION){
                        layout.show(contentPanel, "menu");
                        menuBar.setVisible(false);
                    }
                } else {
                    layout.show(contentPanel, "menu");
                    menuBar.setVisible(false);
                    chessPanel.resetTutorialNotifications();
                    chessPanel.returnMessageView().turnNotificationSwitch(true);
                }
                
            }
        });
        

        loadGameMenuItem.addActionListener(getLoadController());
        // loadGameMenuItem.addActionListener(new ActionListener() {

        // @Override
        // public void actionPerformed(ActionEvent e) {

        // // Add code for when "Load Game" is selected from menu bar
        // if(chessPanel.returnTutorialStatus()){
        //     chessPanel.inTutorialMode(false);
        // }

        // }

        // });

        saveGameMenuItem.addActionListener(getSaveController());
        // saveGameMenuItem.addActionListener(new ActionListener() {

        // @Override
        // public void actionPerformed(ActionEvent e) {

        // // Add code for when "Save Game" is selected from menu bar

        // }

        // });

        exitMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chessPanel.returnTutorialStatus() != true){
                        int userSelection= confirmSave();
                    if(userSelection == JOptionPane.YES_OPTION){
                        int saveSelection= ApplicationFrame.this.getSaveController().doAction();
                        if(saveSelection == JFileChooser.APPROVE_OPTION){
                            System.exit(0);
                        }
                    }else if(userSelection == JOptionPane.NO_OPTION){
                        System.exit(0);
                    }
                
                } else {
                    System.exit(0);
                }
                
            }

        });

        turnOnOffBoardHighlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (chessPanel != null) {
                    chessPanel.boardHighlightOnOff();
                }
            }
        });

        turnOnOffNotifications.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (chessPanel != null) {
                    chessPanel.notificationsOnOff();
                }
            }
        });

        turnOnOffMoveHints.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (chessPanel != null) {
                    chessPanel.moveHintOnOff();
                }
            }
        });

        turnOnOffUndoRedo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (chessPanel != null) {
                    chessPanel.undoRedoOnOff();
                }
            }
        });

        playerOptions.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // JComboBox for player options
                JPanel wrapper = new JPanel();
                wrapper.setLayout(new GridBagLayout());
                GridBagConstraints gb = new GridBagConstraints();

                JLabel prompt = new JLabel("Please Select Player Options: ");
                gb.gridx = 0;
                gb.gridy = 0;
                gb.weightx = 1;
                gb.weighty = 1;
                gb.fill = GridBagConstraints.BOTH;
                wrapper.add(prompt, gb);

                JLabel spacer = new JLabel(" ");
                gb.gridy = 1;
                wrapper.add(spacer, gb);

                JLabel white = new JLabel(" Set white");
                gb.gridy = 2;
                wrapper.add(white, gb);

                gb.gridy = 3;
                wrapper.add(player1ComboBox, gb);

                JLabel spacer2 = new JLabel(" ");
                gb.gridy = 4;
                wrapper.add(spacer2, gb);

                JLabel black = new JLabel(" Set black");
                gb.gridy = 5;
                wrapper.add(black, gb);

                gb.gridy = 6;
                wrapper.add(player2ComboBox, gb);

                JLabel spacer3 = new JLabel(" ");
                gb.gridy = 7;
                wrapper.add(spacer3, gb);

                gb.gridy = 8;
                JButton playerOptionsSubmit = new JButton("Submit");
                wrapper.add(playerOptionsSubmit, gb);

                Object[] options = new Object[] {};

                JOptionPane playerOptionsOptionPane = new JOptionPane("", JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.PLAIN_MESSAGE, null, options, null);

                playerOptionsOptionPane.add(wrapper);

                JDialog diag = new JDialog(ApplicationFrame.this, true);
                diag.getContentPane().add(playerOptionsOptionPane);
                diag.pack();
                diag.setLocation(500, 300);

                playerOptionsSubmit.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // Sets player1ComboBoxChoice & player2ComboBoxChoice class members to the int
                        // of the user's choice from the combo box.
                        // 0 = Human, 1 = A.I (easy), 2 = A.I. (medium), 3 = A.I (hard)
                        player1ComboBoxChoice = player1ComboBox.getSelectedIndex();
                        player2ComboBoxChoice = player2ComboBox.getSelectedIndex();
                        chessPanel.changePlayerTypes(GameSetUp.playerDepthList[player1ComboBoxChoice], GameSetUp.playerDepthList[player2ComboBoxChoice]);
                        diag.setVisible(false);
                    }

                });

                diag.setVisible(true);

            }
        });

        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("about", 3);
                helpWindow.setLocation(1200, 300);
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));
                helpWindow.setMinimumSize(new Dimension(600, 800));
                helpWindow.setPreferredSize(new Dimension(600, 800));
                helpWindow.setMaximumSize(new Dimension(700, 900));
            }

        });

        appHelp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("about", 2);
                helpWindow.setLocation(1200, 300);
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));
                helpWindow.setMinimumSize(new Dimension(600, 800));
                helpWindow.setPreferredSize(new Dimension(600, 800));
                helpWindow.setMaximumSize(new Dimension(700, 900));
            }

        });

        chessRules.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("chessRules", 0);
                helpWindow.setLocation(1200, 300);
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));
                helpWindow.setMinimumSize(new Dimension(600, 800));
                helpWindow.setPreferredSize(new Dimension(600, 800));
                helpWindow.setMaximumSize(new Dimension(700, 900));

            }

        });

        pieceInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("pieceInfo", 1);
                helpWindow.setLocation(1200, 300);
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));
                helpWindow.setMinimumSize(new Dimension(600, 800));
                helpWindow.setPreferredSize(new Dimension(600, 800));
                helpWindow.setMaximumSize(new Dimension(700, 900));
            }

        });

    }

    /**
     * Shows a confirmation dialog box asking if the player would like to save the game
     * before leaving it, and returns the user's selection.
     * @return the user-selected option, one of JOptionPane.YES_OPTION, JOptionPane.NO_OPTION,
     *  or JOptionPane.CANCEL_OPTION
     */
    private int confirmSave(){
        return JOptionPane.showConfirmDialog(this, "Do you want to save before leaving this game?", "Save?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * 
     * @return saveController returns controller to save the PGN
     */
    public ChessGameSaveController getSaveController() {
        return saveController;
    }
    /**
     * 
     * @return loadController returns the controller to load the PGN
     */
    public ChessGameLoadController getLoadController() {
        return loadController;
    }

    /**
     * Returns save game menu item.
     * @return save game menu item.
     */
    public JMenuItem returnSaveGameMenuItem(){
        return saveGameMenuItem;
    }

    /**
     * return load game menu item.
     * @return load game menu item. 
     */
    public JMenuItem returnLoadGameMenuItem(){
        return loadGameMenuItem;
    }

 
}
