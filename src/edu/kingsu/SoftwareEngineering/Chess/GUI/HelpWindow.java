package edu.kingsu.SoftwareEngineering.Chess.GUI;

import java.awt.Color;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.JTabbedPane;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;
import javax.swing.JEditorPane;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JScrollPane;
import java.io.File;

/**
 * Creates seperate window frame for help and about dialog for java chess game.
 * 
 * @author Chelsie Bajic
 * @since 10/2021
 */
public class HelpWindow extends JFrame {

    JTabbedPane tabbedPane = new JTabbedPane();

    // The four help menu tabs
    JPanel chessRules = new JPanel();
    JPanel pieceInfo = new JPanel();
    JPanel appHelp = new JPanel();
    JPanel aboutApp = new JPanel();
    JPanel license = new JPanel();
    /**
     * Default HelpWindow constructor with no parameters
     */
    public HelpWindow() {
    }
    /**
     * Open the HelpWindow from a specific button
     * @param orgin  Which button was used to open the dialog
     */
    public HelpWindow(String orgin) {

        buildChessRulesPanel();
        buildPieceInfoPanel();
        buildAppHelpPanel();
        buildAboutAppPanel();
        buildLicensePanel();
        addPanelsToTabbedPane();

        this.add(tabbedPane);

    }

    /**
     * Opens the HelpWindow from a button to a specific tab
     * @param orgin Which button was used to open the dialog
     * @param index Specifies which tab to open
     */
    public HelpWindow(String orgin, int index) {

        buildChessRulesPanel();
        buildPieceInfoPanel();
        buildAppHelpPanel();
        buildAboutAppPanel();
        buildLicensePanel();
        addPanelsToTabbedPane();

        tabbedPane.setSelectedIndex(index);

        this.add(tabbedPane);

    }

    /**
     * For opening to a specific piece info tab.
     * 
     * @param orgin         From a specific button
     * @param mainTabIndex  Which main tab to open
     * @param pieceTabIndex Which piece tab to open
     */

    public HelpWindow(String orgin, int mainTabIndex, int pieceTabIndex) {

        buildChessRulesPanel();
        buildPieceInfoPanelAndChooseOpeningTab(pieceTabIndex);
        buildAppHelpPanel();
        buildAboutAppPanel();
        buildLicensePanel();
        addPanelsToTabbedPane();

        tabbedPane.setSelectedIndex(mainTabIndex);

        this.add(tabbedPane);

    }
    /**
     * Set the background for each tab
     */
    private void setBackgroundColors() {
        // Set background color for each tab.

        MainLayer pieceInfoBackground = new MainLayer();
        pieceInfo.setLayout(new GridLayout(0, 1));
        pieceInfo.add(pieceInfoBackground);
        pieceInfo.setOpaque(true);

        MainLayer appHelpBackground = new MainLayer();
        appHelp.setLayout(new GridLayout(0, 1));
        appHelp.add(appHelpBackground);

        MainLayer aboutAppBackground = new MainLayer();
        aboutApp.setLayout(new GridLayout(0, 1));
        aboutApp.add(aboutAppBackground);
    }
    /**
     * A MainLayer for chessRulePanel
     */
    private void buildChessRulesPanel() {

        MainLayer gameRulesBackground = new MainLayer();
        chessRules.setLayout(new GridLayout(0, 1));
        chessRules.add(gameRulesBackground);
        chessRules.setOpaque(true);

        ButtonContainer silverPanel = new ButtonContainer();
        gameRulesBackground.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1;
        gb.weighty = 1;
        gb.insets = new Insets(20, 20, 20, 20);
        gameRulesBackground.add(silverPanel, gb);

        silverPanel.setLayout(new GridBagLayout());
        JPanel textArea = new JPanel();
        textArea.setBackground(new Color(232, 232, 232));
        textArea.setOpaque(true);
        textArea.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(224, 224, 224)));
        silverPanel.add(textArea, gb);

        textArea.setLayout(new GridBagLayout());
        JEditorPane chessRulesHelpMenu = new JEditorPane();
        try {
            URL chessRulesURL = null;
            chessRulesURL = HelpWindow.class.getClassLoader().getResource("chess_rules.html");
            chessRulesHelpMenu.setPage(chessRulesURL);
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        chessRulesHelpMenu.setEditable(false);
        chessRulesHelpMenu.setOpaque(false);

        JScrollPane editorScrollPane = new JScrollPane(chessRulesHelpMenu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        editorScrollPane.setOpaque(false);
        textArea.add(editorScrollPane, gb);
    }
    /**
     * A MainLayer for pieceInfo Panel
     */
    private void buildPieceInfoPanel() {
        MainLayer pieceInfoBackground = new MainLayer();
        pieceInfo.setLayout(new GridLayout(0, 1));
        pieceInfo.add(pieceInfoBackground);
        pieceInfo.setOpaque(true);

        ButtonContainer silverPanel = new ButtonContainer();
        pieceInfoBackground.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1;
        gb.weighty = 1;
        gb.insets = new Insets(20, 20, 20, 20);
        pieceInfoBackground.add(silverPanel, gb);

        silverPanel.setLayout(new GridBagLayout());
        JTabbedPane piecePanel = new JTabbedPane();
        piecePanel.setBackground(new Color(232, 232, 232));
        piecePanel.setOpaque(true);
        piecePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(224, 224, 224)));
        silverPanel.add(piecePanel, gb);

        // Pawn Information.
        JEditorPane pawnInfo = new JEditorPane();
        try {
            URL pawnInfoURL = null;
            pawnInfoURL = HelpWindow.class.getClassLoader().getResource("pawn_info.html");
            pawnInfo.setPage(pawnInfoURL);
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        pawnInfo.setEditable(false);
        pawnInfo.setOpaque(false);

        JScrollPane pawnEditorScrollPane = new JScrollPane(pawnInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pawnEditorScrollPane.setOpaque(false);

        piecePanel.add("Pawn", pawnEditorScrollPane);

        // Rook information
        JEditorPane rookInfo = new JEditorPane();
        try {
            URL rookInfoURL = null;
            rookInfoURL = HelpWindow.class.getClassLoader().getResource("rook_info.html");
            rookInfo.setPage(rookInfoURL); 
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        rookInfo.setEditable(false);
        rookInfo.setOpaque(false);

        JScrollPane rookEditorScrollPane = new JScrollPane(rookInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rookEditorScrollPane.setOpaque(false);

        piecePanel.add("Rook", rookEditorScrollPane);

        // Bishop information
        JEditorPane bishopInfo = new JEditorPane();
        try {
            URL bishopInfoURL = null;
            bishopInfoURL = HelpWindow.class.getClassLoader().getResource("bishop_info.html");
            bishopInfo.setPage(bishopInfoURL); 
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        bishopInfo.setEditable(false);
        bishopInfo.setOpaque(false);

        JScrollPane bishopEditorScrollPane = new JScrollPane(bishopInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bishopEditorScrollPane.setOpaque(false);

        piecePanel.add("Bishop", bishopEditorScrollPane);

        // Knight information
        JEditorPane knightInfo = new JEditorPane();
        try {
            URL knightInfoURL = null;
            knightInfoURL = HelpWindow.class.getClassLoader().getResource("knight_info.html");
            knightInfo.setPage(knightInfoURL); 
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        knightInfo.setEditable(false);
        knightInfo.setOpaque(false);

        JScrollPane knightEditorScrollPane = new JScrollPane(knightInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        knightEditorScrollPane.setOpaque(false);

        piecePanel.add("Knight", knightEditorScrollPane);

        // Queen information
        JEditorPane queenInfo = new JEditorPane();
        try {
            URL queenInfoURL = null;
            queenInfoURL = HelpWindow.class.getClassLoader().getResource("queen_info.html");
            queenInfo.setPage(queenInfoURL); 
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        queenInfo.setEditable(false);
        queenInfo.setOpaque(false);

        JScrollPane queenEditorScrollPane = new JScrollPane(queenInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        queenEditorScrollPane.setOpaque(false);

        piecePanel.add("Queen", queenEditorScrollPane);

        // King information
        JEditorPane kingInfo = new JEditorPane();
        try {
            URL kingInfoURL = null;
            kingInfoURL = HelpWindow.class.getClassLoader().getResource("king_info.html");
            kingInfo.setPage(kingInfoURL);
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        kingInfo.setEditable(false);
        kingInfo.setOpaque(false);

        JScrollPane kingEditorScrollPane = new JScrollPane(kingInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        kingEditorScrollPane.setOpaque(false);

        piecePanel.add("King", kingEditorScrollPane);

    }
    /**
     * A MainLayer that holds the description for all the pieces
     * @param tabIndex Specifies which tab to open
     */
    private void buildPieceInfoPanelAndChooseOpeningTab(int tabIndex) {
        MainLayer pieceInfoBackground = new MainLayer();
        pieceInfo.setLayout(new GridLayout(0, 1));
        pieceInfo.add(pieceInfoBackground);
        pieceInfo.setOpaque(true);

        ButtonContainer silverPanel = new ButtonContainer();
        pieceInfoBackground.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1;
        gb.weighty = 1;
        gb.insets = new Insets(20, 20, 20, 20);
        pieceInfoBackground.add(silverPanel, gb);

        silverPanel.setLayout(new GridBagLayout());
        JTabbedPane piecePanel = new JTabbedPane();
        piecePanel.setBackground(new Color(232, 232, 232));
        piecePanel.setOpaque(true);
        piecePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(224, 224, 224)));
        silverPanel.add(piecePanel, gb);

        // Pawn Information.
        JEditorPane pawnInfo = new JEditorPane();
        try {
            URL pawnInfoURL = null;
            pawnInfoURL = HelpWindow.class.getClassLoader().getResource("pawn_info.html");
            pawnInfo.setPage(pawnInfoURL); 
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        pawnInfo.setEditable(false);
        pawnInfo.setOpaque(false);

        JScrollPane pawnEditorScrollPane = new JScrollPane(pawnInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pawnEditorScrollPane.setOpaque(false);

        piecePanel.add("Pawn", pawnEditorScrollPane);

        // Rook information
        JEditorPane rookInfo = new JEditorPane();
        try {
            URL rookInfoURL = null;
            rookInfoURL = HelpWindow.class.getClassLoader().getResource("rook_info.html");
            rookInfo.setPage(rookInfoURL); 
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        rookInfo.setEditable(false);
        rookInfo.setOpaque(false);

        JScrollPane rookEditorScrollPane = new JScrollPane(rookInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rookEditorScrollPane.setOpaque(false);

        piecePanel.add("Rook", rookEditorScrollPane);

        // Bishop information
        JEditorPane bishopInfo = new JEditorPane();
        try {
            URL bishopInfoURL = null;
            bishopInfoURL = HelpWindow.class.getClassLoader().getResource("bishop_info.html");
            bishopInfo.setPage(bishopInfoURL); 
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        bishopInfo.setEditable(false);
        bishopInfo.setOpaque(false);

        JScrollPane bishopEditorScrollPane = new JScrollPane(bishopInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bishopEditorScrollPane.setOpaque(false);

        piecePanel.add("Bishop", bishopEditorScrollPane);

        // Knight information
        JEditorPane knightInfo = new JEditorPane();
        try {
            URL knightInfoURL = null;
            knightInfoURL = HelpWindow.class.getClassLoader().getResource("knight_info.html");
            knightInfo.setPage(knightInfoURL);
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        knightInfo.setEditable(false);
        knightInfo.setOpaque(false);

        JScrollPane knightEditorScrollPane = new JScrollPane(knightInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        knightEditorScrollPane.setOpaque(false);

        piecePanel.add("Knight", knightEditorScrollPane);

        // Queen information
        JEditorPane queenInfo = new JEditorPane();
        try {
            URL queenURL = null;
            queenURL = HelpWindow.class.getClassLoader().getResource("queen_info.html");
            queenInfo.setPage(queenURL);
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        queenInfo.setEditable(false);
        queenInfo.setOpaque(false);

        JScrollPane queenEditorScrollPane = new JScrollPane(queenInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        queenEditorScrollPane.setOpaque(false);

        piecePanel.add("Queen", queenEditorScrollPane);

        // King information
        JEditorPane kingInfo = new JEditorPane();
       
        try {
            URL kingURL = null;
            kingURL = HelpWindow.class.getClassLoader().getResource("king_info.html");
            kingInfo.setPage(kingURL);
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        kingInfo.setEditable(false);
        kingInfo.setOpaque(false);

        JScrollPane kingEditorScrollPane = new JScrollPane(kingInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        kingEditorScrollPane.setOpaque(false);

        piecePanel.add("King", kingEditorScrollPane);

        piecePanel.setSelectedIndex(tabIndex);

    }
    /**
     * A MainLayer for the appHelp Panel
     */
    private void buildAppHelpPanel() {
        MainLayer appHelpBackground = new MainLayer();
        appHelp.setLayout(new GridLayout(0, 1));
        appHelp.add(appHelpBackground);
        appHelp.setOpaque(true);

        ButtonContainer silverPanel = new ButtonContainer();
        appHelpBackground.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1;
        gb.weighty = 1;
        gb.insets = new Insets(20, 20, 20, 20);
        appHelpBackground.add(silverPanel, gb);

        silverPanel.setLayout(new GridBagLayout());
        JPanel textArea = new JPanel();
        textArea.setBackground(new Color(232, 232, 232));
        textArea.setOpaque(true);
        textArea.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(224, 224, 224)));
        silverPanel.add(textArea, gb);

        textArea.setLayout(new GridBagLayout());
        JEditorPane appHelpPane = new JEditorPane();
        try {
            URL appHelpURL = null;
            appHelpURL = HelpWindow.class.getClassLoader().getResource("application_help.html");
            appHelpPane.setPage(appHelpURL);
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        appHelpPane.setEditable(false);
        appHelpPane.setOpaque(false);

        JScrollPane editorScrollPane = new JScrollPane(appHelpPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        editorScrollPane.setOpaque(false);
        textArea.add(editorScrollPane, gb);

        textArea.add(editorScrollPane, gb);

    }
    /**
     * A MainLayer for about panel
     */
    private void buildAboutAppPanel() {
        MainLayer aboutAppBackground = new MainLayer();
        aboutApp.setLayout(new GridLayout(0, 1));
        aboutApp.add(aboutAppBackground);
        aboutApp.setOpaque(true);

        ButtonContainer silverPanel = new ButtonContainer();
        aboutAppBackground.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1;
        gb.weighty = 1;
        gb.insets = new Insets(20, 20, 20, 20);
        aboutAppBackground.add(silverPanel, gb);

        silverPanel.setLayout(new GridBagLayout());
        JPanel textArea = new JPanel();
        textArea.setBackground(new Color(232, 232, 232));
        textArea.setOpaque(true);
        textArea.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(224, 224, 224)));
        silverPanel.add(textArea, gb);

        textArea.setLayout(new GridBagLayout());
        JEditorPane aboutPane = new JEditorPane();
        try {
            URL appHelpURL = null;
            appHelpURL = HelpWindow.class.getClassLoader().getResource("about.html");
            aboutPane.setPage(appHelpURL);
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        aboutPane.setEditable(false);
        aboutPane.setOpaque(false);

        JScrollPane editorScrollPane = new JScrollPane(aboutPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        editorScrollPane.setOpaque(false);
        textArea.add(editorScrollPane, gb);

    }
    /**
     * A MainLayer for license panel
     */
    private void buildLicensePanel() {
        MainLayer licenseBackground = new MainLayer();
        license.setLayout(new GridLayout(0, 1));
        license.add(licenseBackground);
        license.setOpaque(true);

        ButtonContainer silverPanel = new ButtonContainer();
        licenseBackground.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1;
        gb.weighty = 1;
        gb.insets = new Insets(20, 20, 20, 20);
        licenseBackground.add(silverPanel, gb);

        silverPanel.setLayout(new GridBagLayout());
        JPanel textArea = new JPanel();
        textArea.setBackground(new Color(232, 232, 232));
        textArea.setOpaque(true);
        textArea.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(224, 224, 224)));
        silverPanel.add(textArea, gb);

        textArea.setLayout(new GridBagLayout());
        JEditorPane licensePane = new JEditorPane();
       
        try {
            URL licenseURL = null;
            licenseURL = HelpWindow.class.getClassLoader().getResource("license.html");
            licensePane.setPage(licenseURL);
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        licensePane.setEditable(false);
        licensePane.setOpaque(false);

        JScrollPane editorScrollPane = new JScrollPane(licensePane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        editorScrollPane.setOpaque(false);
        textArea.add(editorScrollPane, gb);

    }
    /**
     * Add the tabbed panel
     */
    private void addPanelsToTabbedPane() {

        tabbedPane.add("Chess Rules", chessRules);
        tabbedPane.add("Piece Information", pieceInfo);
        tabbedPane.add("Application Help", appHelp);
        tabbedPane.add("About", aboutApp);
        tabbedPane.add("License", license);
    }
}
