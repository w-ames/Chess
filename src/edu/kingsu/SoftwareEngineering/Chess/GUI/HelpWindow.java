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
        JPanel textArea = new JPanel();
        textArea.setBackground(new Color(232, 232, 232));
        textArea.setOpaque(true);
        textArea.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(224, 224, 224)));
        silverPanel.add(textArea, gb);

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
        JLabel appHelpText = new JLabel("<html>" + "App Help Text Area" + "</html>");

        textArea.add(appHelpText, gb);

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
        JLabel aboutText = new JLabel("<html>" + "About Text Area" + "</html>");

        textArea.add(aboutText, gb);

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
