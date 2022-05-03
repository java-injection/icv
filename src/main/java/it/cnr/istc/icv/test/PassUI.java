/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test;

/**
 *
 * @author Luca
 */
import it.cnr.istc.icv.engine.MyJLayer;
import it.cnr.istc.icv.engine.MyLayer;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//import javax.swing.plaf.LayerUI;

public class PassUI extends JFrame {

    public static void main(String[] args) {
        PassUI window = new PassUI();
        window.setVisible(true);
    }

    public PassUI() {
        this.setSize(300, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyLayer<JPanel> layerUI = new PasswordLayerUI();
        MyJLayer<JPanel> jlayer = new MyJLayer<JPanel>(getPanel(), layerUI);
        this.add(jlayer);
    }

    private JPanel getPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(1, 2));
        JLabel quantityLabel = new JLabel("Password");
        gridPanel.add(quantityLabel);
        JPasswordField passwordField = new JPasswordField();
        gridPanel.add(passwordField);
        panel.add(gridPanel, BorderLayout.CENTER);

        return panel;
    }
}

class PasswordLayerUI extends MyLayer<JPanel> {

    private String errorMessage = "Password too short";

    @Override
    public void paint(Graphics g, JComponent c) {
        FontMetrics fontMetrics;
        int height;
        super.paint(g, c);
        Graphics2D g2d = (Graphics2D) g.create();
        int componentWidth = c.getWidth();
        int componentHeight = c.getHeight();
        g2d.setFont(c.getFont());
        fontMetrics = g2d.getFontMetrics(c.getFont());
        height = fontMetrics.getHeight();
        g2d.drawString(errorMessage, componentWidth / 2 + 10, componentHeight / 2
                + height);
        g2d.dispose();
    }

    @Override
    public void installUI(JComponent component) {
        super.installUI(component);
        ((MyJLayer) component).setLayerEventMask(AWTEvent.KEY_EVENT_MASK);
    }

    @Override
    public void uninstallUI(JComponent component) {
        super.uninstallUI(component);
        ((MyJLayer) component).setLayerEventMask(0);
    }

    protected void processKeyEvent(KeyEvent event, MyJLayer layer) {
        JTextField f = (JTextField) event.getSource();
        if (f.getText().length() < 6) {
            errorMessage = "Password too short";
        } else {
            errorMessage = "";
        }
        layer.repaint();
    }
}
