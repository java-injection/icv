/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.engine;

import static it.cnr.istc.icv.engine.ActivityPanel.X_AXIS_LABEL_GAB;
import java.awt.AWTEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JScrollBar;

/**
 *
 * @author Luca
 */
public class TopHeaderLayer extends MyLayer<JComponent> {

    protected boolean mActive;
    protected int mX, mY;
    protected MixedDataPanel panel;
    private boolean headOverRepaint = true;
    private JScrollBar scrollbar;
    private int rightScrollMargin = 10;

    public TopHeaderLayer(MixedDataPanel panel, JScrollBar scrollbar) {
        this.panel = panel;
        this.scrollbar = scrollbar;

    }

    public boolean isHeadOverRepaint() {
        return headOverRepaint;
    }

    public void setHeadOverRepaint(boolean headOverRepaint) {
        this.headOverRepaint = headOverRepaint;
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
        super.paint(g2, c);
        int sw = this.scrollbar.getSize().width;

        Rectangle2D.Float whiteRect = new Rectangle2D.Float(0, 0, (float) c.getWidth() - 1 - sw, (float) panel.getsY()); //sY- (fm.getHeight() / 2) - 1
        g2.setPaint(panel.getBackgroundChartColor());
        g2.draw(whiteRect);
        g2.fill(whiteRect);
        if (!panel.isArrowHeaderMode()) {
            BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL);
            g2.setStroke(bs1);
            int rightMargin = panel.getDataMeasureLenght() / 2;
            int labelStartX = panel.getsX() - rightMargin;
            int lineStart = panel.getsX();

            Font ff = new Font("SansSerif", Font.PLAIN, 10);
            g2.setPaint(panel.getxAxisDataColor());
            g2.setFont(ff);
            Date dateS = new Date(panel.getStartRange());
            Date dateE = new Date(panel.getEndRange());
            Format format = new SimpleDateFormat("dd/MM/yyyy");
            Font f = new Font("SansSerif", Font.PLAIN, 10);
            FontMetrics fm = g.getFontMetrics(f);

            if (panel.isShowDate()) {
//            //START RANGE

                g2.drawString(format.format(dateS), labelStartX, panel.getsY() - (fm.getHeight() * 2));
                Format h_format = new SimpleDateFormat("(HH:mm)");
                g2.drawString(h_format.format(dateS), panel.getsX() - (panel.getHourMeasureLenght() / 2), panel.getsY() - (fm.getHeight()));
                g2.setPaint(panel.getLinesColor());
                g2.drawLine(lineStart, panel.getsY() - (fm.getHeight() / 2) - 1, lineStart, panel.getsY());
//            //END RANGE

                g2.setPaint(panel.getxAxisDataColor());
                g2.drawString(format.format(dateE), panel.geteX() - (panel.getDataMeasureLenght() / 2), panel.getsY() - (fm.getHeight() * 2));
                g2.setPaint(panel.getLinesColor());
                g2.drawLine(panel.geteX(), panel.getsY() - (fm.getHeight() / 2) - 1, panel.geteX(), panel.getsY());
                g2.setPaint(panel.getxAxisDataColor());
                g2.drawString(h_format.format(dateE), panel.geteX() - (panel.getHourMeasureLenght() / 2), panel.getsY() - (fm.getHeight()));
//
//

                float labels = (panel.getwX() - panel.getDataMeasureLenght()) / (panel.getDataMeasureLenght() + 20);
                float gap = (panel.getwX() - panel.getDataMeasureLenght()) % (panel.getDataMeasureLenght() + 20);
                float rGap = gap / (labels + 1);
                X_AXIS_LABEL_GAB = rGap + 20;
                int hStart = panel.getsX() - (panel.getHourMeasureLenght() / 2);
                for (int i = 0; i < labels; i++) {
                    lineStart += panel.getDataMeasureLenght() + X_AXIS_LABEL_GAB;
//            Date date = panel.getDateByPoint(new Point(lineStart, panel.gethY() / 2));
                    Date date = panel.getDateByX(lineStart);
                    labelStartX += (panel.getDataMeasureLenght() + X_AXIS_LABEL_GAB);
                    hStart += panel.getDataMeasureLenght() + X_AXIS_LABEL_GAB;
                    String ddd = "-";
                    if (date == null) {
                        System.out.println("TOP HEADER icv duplication issue: init failed, generated emergency date");
                        date = new Date(1492, 9, 12);
                    }
                    ddd = format.format(date);
                    g2.setPaint(panel.getxAxisDataColor());
                    g2.drawString(ddd, labelStartX, panel.getsY() - fm.getHeight() * 2);
                    g2.setPaint(panel.getLinesColor());
                    g2.drawLine(lineStart, panel.getsY() - (fm.getHeight() / 2) - 1, lineStart, panel.getsY());
                    g2.setPaint(panel.getxAxisDataColor());
                    g2.drawString(h_format.format(date), hStart, panel.getsY() - (fm.getHeight()));
                    g2.setPaint(panel.getLinesColor());
//                g.drawLine(lineStart, sY, lineStart, hY + TOP_MARGIN);
                }
            } else {
                int dataMeasureLenght = fm.stringWidth("" + dateE.getTime());
//                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                g.drawString("" + dateS.getTime(), labelStartX, panel.getsY() - (fm.getHeight() * 2));
//                    SimpleDateFormat h_format = new SimpleDateFormat("(HH:mm)");
//                    g.drawString(""+dateS, sX - (hourMeasureLenght / 2), sY - (fm.getHeight()));
                g2.setPaint(panel.getLinesColor());
                g2.drawLine(lineStart, panel.getsY() - (fm.getHeight() / 2) - 1, lineStart, panel.getsY());

                //END RANGE
                g2.setPaint(panel.getxAxisDataColor());
                g.drawString("" + dateE.getTime(), panel.geteX() - (dataMeasureLenght / 2), panel.getsY() - (fm.getHeight() * 2));
                g2.setPaint(panel.getLinesColor());
                g2.drawLine(panel.geteX(), panel.getsY() - (fm.getHeight() / 2) - 1, panel.geteX(), panel.getsY());
                g2.setPaint(panel.getxAxisDataColor());
//                    g.drawString(""+dateE, eX - (hourMeasureLenght / 2), sY - (fm.getHeight()));

                float labels = (panel.getwX()- dataMeasureLenght) / (dataMeasureLenght + 20);
                float gap = (panel.getwX() - dataMeasureLenght) % (dataMeasureLenght + 20);
                float rGap = gap / (labels + 1);
                X_AXIS_LABEL_GAB = rGap + 20;
//                int hStart = panel.getsX() - (hourMeasureLenght / 2);
                for (int i = 0; i < labels; i++) {
                    lineStart += dataMeasureLenght + X_AXIS_LABEL_GAB;
//                    System.out.println("lineStart = "+lineStart);
                    Date date = panel.getDateByX(lineStart);
//                    System.out.println("time date = "+date.getTime());
                    labelStartX += (dataMeasureLenght + X_AXIS_LABEL_GAB);
//                    hStart += dataMeasureLenght + X_AXIS_LABEL_GAB;
//                        String ddd = "-";
//                        if (date == null) {
////                        System.out.println("MIXED icv duplication issue: init failed, generated emergency date");
//                            date = new Date(1492, 9, 12);
//                        }
//                        ddd = format.format(date);
                    g2.setPaint(panel.getxAxisDataColor());
                    g.drawString("" + date.getTime(), labelStartX, panel.getsY() - fm.getHeight() * 2);
                    g2.setPaint(panel.getLinesColor());
                    g2.drawLine(lineStart, panel.getsY() - (fm.getHeight() / 2) - 1, lineStart, panel.getsY());
                    g2.setPaint(panel.getxAxisDataColor());
//                        g.drawString(h_format.format(date), hStart, sY - (fm.getHeight()));
                    g2.setPaint(panel.getLinesColor());
//                    g.drawLine(lineStart, panel.getsY(), lineStart, panel.gethY() + TOP_MARGIN);

                }
//                g.drawLine(panel.geteX(), panel.getsY(), panel.geteX(), panel.gethY() + TOP_MARGIN); //ultima
//                g.drawLine(panel.getsX(), panel.getsY(), panel.getsX(), panel.gethY() + TOP_MARGIN); //prima
            }

        } else {
            g2.setPaint(Color.BLACK);
            Stroke drawingStrokes = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
            g2.setStroke(drawingStrokes);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawLine(panel.getsX() / 2, panel.getsY() / 2, panel.geteX() + panel.getsX() / 3 + 3 - sw, panel.getsY() / 2);
            Polygon arrowTriangle = new Polygon();
            arrowTriangle.addPoint(panel.geteX() + panel.getsX() / 2 + 3 - sw, panel.getsY() / 2 + 1);
            arrowTriangle.addPoint(panel.geteX() + panel.getsX() / 2 - 20 + 3 - sw, panel.getsY() / 2 - 10 + 1);
            arrowTriangle.addPoint(panel.geteX() + panel.getsX() / 2 - 20 + 3 - sw, panel.getsY() / 2 + 10 + 1);
            g2.fill(arrowTriangle);
        }

        g2.dispose();

    }

}
