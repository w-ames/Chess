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
    private JMenuItem loadGameMenuItem = new JMenuItem("Load Game");
    private JMenuItem saveGameMenuItem = new JMenuItem("Save Game");
    private JMenuItem exitMenuItem = new JMenuItem("Quit");

    private JMenuItem turnOnOffBoardHighlight = new JMenuItem("Board Highlight (on/off)");
    private JMenuItem turnOnOffNotifications = new JMenuItem("Notifications (on/off)");
    private JMenuItem turnOnOffMoveHints = new JMenuItem("Move Hints (on/off)");
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
    JButton playerOptionsSubmit = new JButton("Submit");
    int player1ComboBoxChoice;
    int player2ComboBoxChoice;

    /**
     * Creates the main application frame for Java Chess.
     * 
     * @author Gregory Cal
     */
    public ApplicationFrame() {
        super(WINDOW_TITLE);
        this.setMinimumSize(new Dimension(1250, 850));
        this.setBackground(new Color(16, 46, 60));
        add(contentPanel);

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

        saveController = new ChessGameSaveController(null);
        loadController = new ChessGameLoadController(null, this, gameSetUp);
        gameSetUp.addLoadListener();
        addMenuBar();
        addActionListenersToMenuBar();

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
            boolean moveHintSwitch, boolean undoRedoSwitch, String player1Name, String player2Name) {

        this.width = (int) this.getBounds().getWidth();
        this.height = (int) this.getBounds().getHeight();

        chessPanel.setPlayerNames(player1Name, player2Name);
        chessPanel.initialize(chessGame);
        chessPanel.updateContainerDimensions(width, height);
        makePiecesResizeable();
        chessPanel.setMoveHintSwitch(moveHintSwitch);
        chessPanel.setNotificationsSwitch(notificationsSwitch);
        chessPanel.setboardHighlightSwitch(highlightMoveSwitch);
        chessPanel.setundoRedoSwitch(undoRedoSwitch);
        chessPanel.checkTutorialSelections();

    }

    /**
     * Builds and adds the menu bar to the application frame.
     */
    public void addMenuBar() {

        file.add(newGameMenuItem);
        file.add(loadGameMenuItem);
        file.add(saveGameMenuItem);
        file.add(exitMenuItem);

        options.add(turnOnOffBoardHighlight);
        options.add(turnOnOffNotifications);
        options.add(turnOnOffMoveHints);
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

        newGameMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                layout.show(contentPanel, "menu");

            }

        });

        loadGameMenuItem.addActionListener(getLoadController());
        // loadGameMenuItem.addActionListener(new ActionListener() {

        // @Override
        // public void actionPerformed(ActionEvent e) {

        // // Add code for when "Load Game" is selected from menu bar

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
                System.exit(0);
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

                // Add code for when "Move Hint On/Off" is selected from menu bar

                if (chessPanel != null) {
                    chessPanel.moveHintOnOff();
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

                player1ComboBox.addItem("Human");
                player1ComboBox.addItem("A.I. (easy)");
                player1ComboBox.addItem("A.I. (medium)");
                player1ComboBox.addItem("A.I. (hard)");
                gb.gridy = 3;
                wrapper.add(player1ComboBox, gb);

                JLabel spacer2 = new JLabel(" ");
                gb.gridy = 4;
                wrapper.add(spacer2, gb);

                JLabel black = new JLabel(" Set black");
                gb.gridy = 5;
                wrapper.add(black, gb);

                player2ComboBox.addItem("Human");
                player2ComboBox.addItem("A.I. (easy)");
                player2ComboBox.addItem("A.I. (medium)");
                player2ComboBox.addItem("A.I. (hard)");
                gb.gridy = 6;
                wrapper.add(player2ComboBox, gb);

                JLabel spacer3 = new JLabel(" ");
                gb.gridy = 7;
                wrapper.add(spacer3, gb);

                gb.gridy = 8;
                wrapper.add(playerOptionsSubmit, gb);

                Object[] options = new Object[] {};

                JOptionPane playerOptionsOptionPane = new JOptionPane("", JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.PLAIN_MESSAGE, null, options, null);

                playerOptionsOptionPane.add(wrapper);

                JDialog diag = new JDialog();
                diag.getContentPane().add(playerOptionsOptionPane);
                diag.pack();
                diag.setLocation(500, 300);
                diag.setVisible(true);

                playerOptionsSubmit.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // Sets player1ComboBoxChoice & player2ComboBoxChoice class members to the int
                        // of the user's choice from the combo box.
                        // 0 = Human, 1 = A.I (easy), 2 = A.I. (medium), 3 = A.I (hard)
                        player1ComboBoxChoice = player1ComboBox.getSelectedIndex();
                        player2ComboBoxChoice = player2ComboBox.getSelectedIndex();

                        diag.setVisible(false);
                    }

                });

            }
        });

        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("about", 3);
                helpWindow.setLocation(1200, 300);
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));
            }

        });

        appHelp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("about", 2);
                helpWindow.setLocation(1200, 300);
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));
            }

        });

        chessRules.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("chessRules", 0);
                helpWindow.setLocation(1200, 300);
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));

            }

        });

        pieceInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("pieceInfo", 1);
                helpWindow.setLocation(1200, 300);
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));
            }

        });

    }

    public ChessGameSaveController getSaveController() {
        return saveController;
    }

    public ChessGameLoadController getLoadController() {
        return loadController;
    }
}
