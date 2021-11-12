package edu.kingsu.SoftwareEngineering.Chess.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.*;
import java.awt.Dimension;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

public class ApplicationFrame extends JFrame {

    private static final String WINDOW_TITLE = "Chess Game";
    private static final Dimension WINDOW_SIZE = new Dimension(1200, 900);

    private JPanel contentPanel;
    private CardLayout layout;
    private MainMenu mainMenu;
    private GameSetUp gameSetUp;
    private ChessPanel chessPanel;
    private JMenuBar menuBar = new JMenuBar();

    private JMenu file = new JMenu("File");
    private JMenu options = new JMenu("Options");
    private JMenu help = new JMenu("Help");

    private JMenuItem newGameMenuItem = new JMenuItem("New Game");
    private JMenuItem loadGameMenuItem = new JMenuItem("Load Game");
    private JMenuItem exitMenuItem = new JMenuItem("Quit");

    private JMenuItem turnOnOffBoardHighlight = new JMenuItem("Board Highlight (on/off)");
    private JMenuItem turnOnOffNotifications = new JMenuItem("Notifications (on/off)");
    private JMenuItem turnOnOffMoveHints = new JMenuItem("Move Hints (on/off)");

    private JMenuItem about = new JMenuItem("About");
    private JMenuItem appHelp = new JMenuItem("Application Help");
    private JMenuItem chessRules = new JMenuItem("Game Rules");
    private JMenuItem pieceInfo = new JMenuItem("Piece Information");

    private int width;
    private int height;

    /**
     * Creates the main application frame for Java Chess.
     * 
     */
    public ApplicationFrame() {
        super(WINDOW_TITLE);
        this.setMinimumSize(new Dimension(1280, 1024));
        contentPanel = new JPanel();
        add(contentPanel);
        layout = new CardLayout();
        mainMenu = new MainMenu(this);
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
     * @param chessGame The chessGame to be represented by this chess panel.
     */
    public void initializeChessPanel(ChessGame chessGame) {

        this.width = (int) this.getBounds().getWidth();
        this.height = (int) this.getBounds().getHeight();

        chessPanel.initialize(chessGame);
        chessPanel.updateContainerDimensions(width, height);
        makePiecesResizeable();
    }

    /**
     * Builds and adds the menu bar to the application frame.
     */
    public void addMenuBar() {

        file.add(newGameMenuItem);
        file.add(loadGameMenuItem);
        file.add(exitMenuItem);

        options.add(turnOnOffBoardHighlight);
        options.add(turnOnOffNotifications);
        options.add(turnOnOffMoveHints);

        help.add(about);
        help.add(appHelp);
        help.add(chessRules);
        help.add(pieceInfo);

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
     * Adds action listeners to the menu bar.
     */
    public void addActionListenersToMenuBar() {

        chessRules.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("chessRules");
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));
            }

        });

        pieceInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("pieceInfo");
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));
            }

        });

        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpWindow helpWindow = new HelpWindow("about");
                helpWindow.setVisible(true);
                helpWindow.setSize(new Dimension(600, 800));
            }

        });

        exitMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
    }
}
