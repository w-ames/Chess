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

    public HelpWindow() {
    }

    public HelpWindow(String orgin) {

        buildChessRulesPanel();
        buildPieceInfoPanel();
        buildAppHelpPanel();
        buildAboutAppPanel();
        buildLicensePanel();
        addPanelsToTabbedPane();

        this.add(tabbedPane);

    }

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
        File file = new File("src/assets/chess_rules.html");
        try {
            chessRulesHelpMenu.setPage(file.toURI().toURL());
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
        File file = new File("src/assets/pawn_info.html");
        try {
            pawnInfo.setPage(file.toURI().toURL());
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
        File file2 = new File("src/assets/rook_info.html");
        try {
            rookInfo.setPage(file2.toURI().toURL());
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
        File file3 = new File("src/assets/bishop_info.html");
        try {
            bishopInfo.setPage(file3.toURI().toURL());
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
        File file4 = new File("src/assets/knight_info.html");
        try {
            knightInfo.setPage(file4.toURI().toURL());
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
        File file5 = new File("src/assets/queen_info.html");
        try {
            queenInfo.setPage(file5.toURI().toURL());
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
        File file6 = new File("src/assets/king_info.html");
        try {
            kingInfo.setPage(file6.toURI().toURL());
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
        JEditorPane licensePane = new JEditorPane();
        File file = new File("src/assets/application_help.html");
        try {
            licensePane.setPage(file.toURI().toURL());
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

        textArea.add(editorScrollPane, gb);

    }

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
        File file = new File("src/assets/about.html");
        try {
            aboutPane.setPage(file.toURI().toURL());
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
        File file = new File("src/assets/license.html");
        try {
            licensePane.setPage(file.toURI().toURL());
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

    private void addPanelsToTabbedPane() {

        tabbedPane.add("Chess Rules", chessRules);
        tabbedPane.add("Piece Information", pieceInfo);
        tabbedPane.add("Application Help", appHelp);
        tabbedPane.add("About", aboutApp);
        tabbedPane.add("License", license);
    }
}
