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
import java.awt.Font;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class CustomButton extends JButton implements MouseListener {

    public CustomButton(String buttonText) {
        this.setOpaque(true);
        this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(64, 64, 64)));
        JLabel text = new JLabel(buttonText);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setForeground(new Color(242, 242, 242));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbForButton = new GridBagConstraints();
        gbForButton.fill = GridBagConstraints.CENTER;
        this.add(text, gbForButton);
        addMouseListener(this);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, new Color(64, 64, 64), 0, h, new Color(152, 152, 152));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(96, 96, 96)));
    }

    public void mouseExited(MouseEvent e) {
        this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(64, 64, 64)));
    }

    public void mousePressed(MouseEvent e) {
        this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(152, 152, 152)));
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("CLICK");
        this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(64, 64, 64)));
    }
}