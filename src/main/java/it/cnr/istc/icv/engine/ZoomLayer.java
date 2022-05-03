/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.engine;

import static it.cnr.istc.icv.engine.ActivityPanel.X_AXIS_LABEL_GAB;
import it.cnr.istc.icv.engine.logic.MixedPanelInterface;
import java.awt.AWTEvent;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import static java.lang.String.format;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
//import javax.swing.plaf.LayerUI;

/**
 *
 * @author Luca
 */
public class ZoomLayer extends MyLayer<JPanel> {

    protected boolean mActive;
    protected int mX, mY;
    protected MixedPanelInterface panel;
    Graphics2D g2Temp;

    public ZoomLayer(MixedPanelInterface panel) {
        this.panel = panel;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        MyJLayer jlayer = (MyJLayer) c;
        jlayer.setLayerEventMask(
                AWTEvent.MOUSE_EVENT_MASK
                | AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }

    @Override
    public void uninstallUI(JComponent c) {
        MyJLayer jlayer = (MyJLayer) c;
        jlayer.setLayerEventMask(0);
        super.uninstallUI(c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2Temp = g2;
        // Paint the view.
        super.paint(g2, c);
//            mActive = this.panel.isZoomEnable();
        if (mActive) {
//                 Create a radial gradient, transparent in the middle.
//                java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(mX, mY);
//                float radius = 72;
//                float[] dist = {0.0f, 1.0f};
//                Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.0f), Color.BLACK};
//                RadialGradientPaint p =
//                        new RadialGradientPaint(center, radius, dist, colors);
//                g2.setPaint(p);
//                g2.setComposite(AlphaComposite.getInstance(
//                        AlphaComposite.SRC_OVER, .6f));
//                g2.fillRect(0, 0, c.getWidth(), c.getHeight());

            Graphics2D g1 = (Graphics2D) g.create();
            Rectangle2D zoom = new Rectangle2D.Double(panel.getStartSelection(), panel.getsY(), Math.abs(panel.getEndSelection() - panel.getStartSelection()), panel.gethY());
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    0.3f));
            g2.setPaint(Color.BLUE);
            g2.fill(zoom);

//                 g2.dispose();
        }
        g2.dispose();
    }

    @Override
    protected void processMouseEvent(MouseEvent e, MyJLayer l) {
        if (!this.panel.isZoomEnable()) {
            return;
        }
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            mActive = true;
            if (this.panel instanceof MixedDataPanel) {
                ((MixedDataPanel) panel).setRepaintable(false);
            }
        }
        if (e.getID() == MouseEvent.MOUSE_RELEASED) {
            mActive = false;
            if (this.panel instanceof MixedDataPanel) {
                ((MixedDataPanel) panel).setRepaintable(true);
            }
        }
        if (e.getID() == MouseEvent.MOUSE_ENTERED) {
            mActive = false;
            if (this.panel instanceof MixedDataPanel) {
//                ((MixedDataPanel) panel).setRepaintable(false);
            }
        }
        if (e.getID() == MouseEvent.MOUSE_EXITED) {
            mActive = false;
            if (this.panel instanceof MixedDataPanel) {
                ((MixedDataPanel) panel).setRepaintable(true);
            }
        }
        l.repaint();
    } 

    @Override
    protected void processMouseMotionEvent(MouseEvent e, MyJLayer l) {
        Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l);
        mX = p.x;
        mY = p.y;
        l.repaint();
    }
}
