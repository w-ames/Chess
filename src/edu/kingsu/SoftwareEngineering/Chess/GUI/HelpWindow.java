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
import java.awt.image.BufferedImage;
import javax.swing.JTabbedPane;
import edu.kingsu.SoftwareEngineering.Chess.Model.*;

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
        JLabel chessRulesText = new JLabel("<html>" + "Chess Rules Text Area" + "</html>");

        textArea.add(chessRulesText, gb);

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
        JLabel licenseText = new JLabel("<html>" + "<span> Copyright 2021 Java Chess Team</span>" + "<br>" + "<br>"
                + "<p>Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:</p>"
                + "<br>"
                + "<p>1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.</p>"
                + "<br>"
                + "<p>2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.</p>"
                + "<br"
                + "<p>3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.</p>"
                + "<br"
                + "<p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.</p>"
                + "</html>");

        textArea.add(licenseText, gb);

    }

    private void addPanelsToTabbedPane() {

        tabbedPane.add("Chess Rules", chessRules);
        tabbedPane.add("Piece Information", pieceInfo);
        tabbedPane.add("Application Help", appHelp);
        tabbedPane.add("About", aboutApp);
        tabbedPane.add("License", license);
    }
}
