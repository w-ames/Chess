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

    private JMenuItem about = new JMenuItem("About");
    private JMenuItem appHelp = new JMenuItem("Application Help");
    private JMenuItem chessRules = new JMenuItem("Game Rules");
    private JMenuItem pieceInfo = new JMenuItem("Piece Information"); 

    public ApplicationFrame() {
        super(WINDOW_TITLE);
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

    public void test() {
        // layout.show(contentPanel,"gamesetup");
        System.out.println("TEST");
    }

    public void show(String card) {
        layout.show(contentPanel, card);
    }

    public void initializeChessPanel(ChessGame chessGame) {
        chessPanel.initialize(chessGame);
        chessPanel.makePiecesResizable();
    }

    public void addMenuBar(){ 

        help.add(about);
        help.add(chessRules);
        help.add(pieceInfo);

        menuBar.add(file);
        menuBar.add(options);
        menuBar.add(help);
        this.setJMenuBar(menuBar);
    }

    // Add action listeners to the menu bar items. 
    public void addActionListenersToMenuBar(){

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
    }

    
   
}
