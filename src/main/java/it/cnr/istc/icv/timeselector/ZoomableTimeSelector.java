/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.timeselector;

import it.cnr.istc.icv.engine.MyJLayer;
import it.cnr.istc.icv.engine.MyLayer;
import it.cnr.istc.icv.engine.ZoomLabeledLayer;
import it.cnr.istc.icv.engine.listeners.ZoomListener;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Luca
 */
public class ZoomableTimeSelector extends javax.swing.JPanel {

    private volatile String zoomLabeledValueWord;
    private volatile String startIntervalWord;
    private volatile String endIntervalWord;
    private MyLayer<JPanel> layerUI;
//    private TimeSelector panel = new TimeSelector();

    /**
     * Creates new form ZoomableTimeSelector
     */
    public ZoomableTimeSelector() {
        initComponents();
        timeSelector1.setBackground(Color.WHITE);
        timeSelector1.setZoomEnable(true);
        layerUI = new ZoomLabeledLayer(timeSelector1);
        ((ZoomLabeledLayer) layerUI).setBaseValueWord(zoomLabeledValueWord);
        ((ZoomLabeledLayer) layerUI).setFromTranslated(startIntervalWord);
        ((ZoomLabeledLayer) layerUI).setToTranslated(endIntervalWord);
        JPanel containerP = new JPanel();
        containerP.setLayout(new GridLayout(0, 1));
        containerP.add(timeSelector1);

        MyJLayer<JPanel> jlayer = new MyJLayer<JPanel>(timeSelector1, layerUI);
        containerP.add(jlayer);

        this.jScrollPane1.setBorder(null);
        this.jScrollPane1.setViewportView(containerP);

        this.setLayout(new GridLayout(0, 1));
        this.add(jScrollPane1);
        this.setOpaque(false);

        MyLayer<JComponent> top = new TopTSHeaderLayer(timeSelector1, this.jScrollPane1.getVerticalScrollBar());
        MyJLayer<JComponent> layer2 = new MyJLayer<JComponent>(jScrollPane1, top);

        this.add(layer2);

    }

    public void setStartIntervalWord(String startIntervalWord) {
        this.startIntervalWord = startIntervalWord;
        ((ZoomLabeledLayer) layerUI).setFromTranslated(startIntervalWord);

    }

    public void setEndIntervalWord(String endIntervalWord) {
        this.endIntervalWord = endIntervalWord;
        ((ZoomLabeledLayer) layerUI).setToTranslated(endIntervalWord);
    }

    public void setStartRange(long time) {
        this.timeSelector1.setStartRange(time);
        timeSelector1.setStartRange(time);
        this.timeSelector1.repaint();
    }

    public void setEndRange(long time) {
        this.timeSelector1.setEndRange(time);
        timeSelector1.setEndRange(time);
        this.timeSelector1.repaint();
    }

    public long getStartRange() {
        return this.timeSelector1.getStartRange();
    }

    public long getEndRange() {
        return this.timeSelector1.getEndRange();
    }
    
    public void addZoomListener(ZoomListener listener){
        this.timeSelector1.addZoomListener(listener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timeSelector1 = new it.cnr.istc.icv.timeselector.TimeSelector();
        jScrollPane1 = new javax.swing.JScrollPane();

        javax.swing.GroupLayout timeSelector1Layout = new javax.swing.GroupLayout(timeSelector1);
        timeSelector1.setLayout(timeSelector1Layout);
        timeSelector1Layout.setHorizontalGroup(
            timeSelector1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        timeSelector1Layout.setVerticalGroup(
            timeSelector1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private it.cnr.istc.icv.timeselector.TimeSelector timeSelector1;
    // End of variables declaration//GEN-END:variables
}
