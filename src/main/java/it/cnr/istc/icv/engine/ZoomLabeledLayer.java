/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.engine;

import it.cnr.istc.icv.engine.listeners.CoordinateListener;
import it.cnr.istc.icv.engine.logic.MixedPanelInterface;
import it.cnr.istc.icv.logic.ContainerDataInterface;
import it.cnr.istc.icv.logic.LinearDataContainerInterface;
import it.cnr.istc.icv.test.LinearDataSupporter;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComponent;

/**
 *
 * @author Luca
 */
public class ZoomLabeledLayer extends ZoomLayer implements CoordinateListener {

//        private boolean mActive;
//        private int mX, mY;
//        private MixedDataPanel panel;
    public Date dateToShow = null;
    private final static String dataMeasure = "88/88/8888 hh:mm:ss";
    private int xMessage;
    private int yMessage;
    private String message;
    private Object currentValue = " - ";
    private String baseValueWord = "value";
    private String fromTranslated = "from";
    private String toTranslated = "to";
    private boolean forcedFromTo = false;
    private String xLabel="x";
    private String yLabel="y";

    public ZoomLabeledLayer(MixedPanelInterface panel) {
        super(panel);
        panel.addCoordinateListener(this);

    }

    public void setxLabel(String xLabel) {
        this.xLabel = xLabel;
    }

    public void setyLabel(String yLabel) {
        this.yLabel = yLabel;
    }
    
    

    public void setBaseValueWord(String baseValueWord) {
        this.baseValueWord = baseValueWord;
    }

    public void setFromTranslated(String fromTranslated) {
        this.fromTranslated = fromTranslated;
    }

    public void setToTranslated(String toTranslated) {
        this.toTranslated = toTranslated;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();
        super.paint(g2, c);

        if (dateToShow != null) {

            Font f = new Font("Lucida Console", Font.BOLD, 10);
            FontMetrics fm = g.getFontMetrics(f);
            ((Graphics2D) g).setPaint(Color.BLACK);
            g.setFont(f);
            int k = 0;
            if (mActive || forcedFromTo) {
                SimpleDateFormat format = new SimpleDateFormat("'" + fromTranslated + ":' dd/MM/yyyy HH:mm:ss");
                SimpleDateFormat format2 = new SimpleDateFormat("'  " + toTranslated + ":' dd/MM/yyyy HH:mm:ss");
                int stringWidth = fm.stringWidth("'" + fromTranslated + ":' dd/MM/yyyy HH:mm:ss");
                k = mX + 15 + stringWidth + 10 > panel.geteX() ? -mX - 15 + this.panel.geteX() - stringWidth - 10 : 0;
//                k = mX < panel.getsX() ? -mX-15+this.panel.getsX(): 0;
                if (k == 0 && mX + 15 < panel.getsX()) {
                    k = -mX - 15 + panel.getsX();
                }
                Rectangle2D label = new Rectangle2D.Double(mX + 15 + k, mY + 15, stringWidth + 10, fm.getHeight() * 2 + 8);

                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                        0.8f));
                g2.setPaint(Color.WHITE);
                g2.fill(label);
                g2.setPaint(Color.BLACK);
                if (panel.isShowDate()) {
                    g.drawString(format.format(panel.getStartDataSelection()), mX + 20 + k, mY + 20 + fm.getHeight() / 2);
                    g.drawString(format2.format(panel.getEndDataSelection()), mX + 20 + k, mY + 30 + fm.getHeight());
                } else {
                    g.drawString("from: " + panel.getStartDataSelection().getTime()+ " "+xLabel, mX + 20 + k, mY + 20 + fm.getHeight() / 2 );
                    g.drawString("to:   " + panel.getEndDataSelection().getTime()+" "+xLabel, mX + 20 + k, mY + 30 + fm.getHeight() );
                }

            } else {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                SimpleDateFormat format2 = new SimpleDateFormat(""+currentValue);
                int stringWidth = fm.stringWidth(dataMeasure);
//                 if (!panel.isShowDate() ) {
//                     currentValue = ((Date)currentValue).getTime();
//                 }
                int stringWidth2 = fm.stringWidth("" + baseValueWord + " :" + currentValue);
                int wMax = stringWidth > stringWidth2 ? stringWidth : stringWidth2;

                k = mX + 15 + wMax + 10 > panel.geteX() ? -mX - 15 + this.panel.geteX() - wMax - 10 : 0;

                Rectangle2D label = null;
                if (panel.isShowDate()) {
                    label = new Rectangle2D.Double(mX + 15 + k, mY + 15, wMax + 10, fm.getHeight() * 2 + 3);
                } else {
                    String databarFromY = panel.getDatabarFromY(mY);
//                    System.out.println("databar-> " + databarFromY);
                    ContainerDataInterface databar = panel.getDataMap().get(databarFromY);
                    if (databar instanceof LinearDataContainerInterface) {
                        int maxValueToShow = ((LinearDataContainerInterface) databar).getMaxValueToShow();
//                        System.out.println("max-> "+maxValueToShow);
                        int wwMax = fm.stringWidth(xLabel+": "+ panel.getEndDataSelection().getTime()) > fm.stringWidth(yLabel+": "+ maxValueToShow+".00") ? fm.stringWidth(xLabel+": " + panel.getEndDataSelection().getTime()) : fm.stringWidth(yLabel+": " + maxValueToShow+".00");
                        label = new Rectangle2D.Double(mX + 15 + k, mY + 15, wwMax + 15, fm.getHeight() * 2 + 3);
                    }else{
                        label = new Rectangle2D.Double(mX + 15 + k, mY + 15, wMax + 10, fm.getHeight() * 2 + 3);
                    }

                }
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                        0.8f));
                g2.setPaint(Color.WHITE);
                g2.fill(label);
                g2.setPaint(Color.BLACK);
                if (panel.isShowDate()) {
                    g.drawString(format.format(dateToShow), mX + 20 + k, mY + 20 + fm.getHeight() / 2);
                } else {
                    g.drawString(xLabel+": " + dateToShow.getTime(), mX + 20 + k, mY + 20 + fm.getHeight() / 2);
                    g.drawString(yLabel+": " + currentValue, mX + 20 + k, mY + 20 + fm.getHeight() + 4);
                }

                try {
//                g.drawString(""+((int)(Float.parseFloat(""+currentValue))), mX + 20 +k, mY + 20 + fm.getHeight()+3);
                    if (baseValueWord != null && currentValue != null) {
                        g.drawString("sss" + baseValueWord + " :" + currentValue, mX + 20 + k, mY + 20 + fm.getHeight() + 4);
                    } else {
                        if (panel.isShowDate()) {
                            g.drawString(" -", mX + 20 + k, mY + 20 + fm.getHeight() + 4);
                        }
                    }
                } catch (Exception ex) {
                    g.drawString(" -", mX + 20 + k, mY + 20 + fm.getHeight() + 4);
                }
            }

        }

        if (message != null) {
            Font font = new Font("Verdana", Font.PLAIN, 14);
            g2.setFont(font);
            FontMetrics fm = g2.getFontMetrics(font);
            int stringWidth = fm.stringWidth(message);
            int k = xMessage + 15 + stringWidth + 10 > panel.geteX() ? -xMessage - 15 + this.panel.geteX() - stringWidth - 10 : 0;

            Rectangle2D label = new Rectangle2D.Double(xMessage + 15 + k, yMessage - 30, stringWidth + 10, fm.getHeight() + 3);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    0.8f));
            g2.setPaint(Color.BLUE);
            g2.fill(label);
            g2.setPaint(Color.WHITE);

            g2.drawString(message, xMessage + 20 + k, yMessage - 25 + fm.getHeight() / 2);

            message = null;
        }

        g2.dispose();
    }

    @Override
    public void currentDate(Date date) {
        this.dateToShow = date;
        this.panel.repaint();
    }

    @Override
    public void currentValue(Object value) {
        this.currentValue = value;
        this.panel.repaint();
    }

    @Override
    public void showTooltip(String message, int x, int y) {
        this.message = message;
        this.xMessage = x;
        this.yMessage = y;
    }

    @Override
    public void forceFromTo(boolean force) {
        this.forcedFromTo = force;
    }
}
