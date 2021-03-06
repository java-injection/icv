/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.timeselector.smart;

import it.cnr.istc.icv.engine.MyJLayer;
import it.cnr.istc.icv.engine.MyLayer;
import it.cnr.istc.icv.engine.ZoomLabeledLayer;
import it.cnr.istc.icv.engine.listeners.ZoomListener;
import it.cnr.istc.icv.logic.ICVAnnotation;
import it.cnr.istc.icv.timeselector.TopTSHeaderLayer;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Luca
 */
public class DroppableSmartTimeSelector extends javax.swing.JPanel {

    private MyLayer<JPanel> layerUI;
    private volatile String zoomLabeledValueWord;
    private volatile String startIntervalWord;
    private volatile String endIntervalWord;

    /**
     * Creates new form DroppableSmartTimeSelector
     */
    public DroppableSmartTimeSelector() {
        initComponents();
        smartTimeSelector1.setBackground(Color.WHITE);
        smartTimeSelector1.setZoomEnable(false);
        layerUI = new ZoomLabeledLayer(smartTimeSelector1);
        ((ZoomLabeledLayer) layerUI).setBaseValueWord(zoomLabeledValueWord);
        ((ZoomLabeledLayer) layerUI).setFromTranslated(startIntervalWord);
        ((ZoomLabeledLayer) layerUI).setToTranslated(endIntervalWord);
        JPanel containerP = new JPanel();
        containerP.setLayout(new GridLayout(0, 1));
        containerP.add(smartTimeSelector1);

        MyJLayer<JPanel> jlayer = new MyJLayer<JPanel>(smartTimeSelector1, layerUI);
        containerP.add(jlayer);

        this.jScrollPane1.setBorder(null);
        this.jScrollPane1.setViewportView(containerP);

        this.setLayout(new GridLayout(0, 1));
        this.add(jScrollPane1);
        this.setOpaque(false);

//        MyLayer<JComponent> top = new TopTSHeaderLayer(smartTimeSelector1, this.jScrollPane1.getVerticalScrollBar());
//        MyJLayer<JComponent> layer2 = new MyJLayer<JComponent>(jScrollPane1, top);
//        this.add(jlayer);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        smartTimeSelector1 = new it.cnr.istc.icv.timeselector.smart.SmartTimeSelector();

        javax.swing.GroupLayout smartTimeSelector1Layout = new javax.swing.GroupLayout(smartTimeSelector1);
        smartTimeSelector1.setLayout(smartTimeSelector1Layout);
        smartTimeSelector1Layout.setHorizontalGroup(
            smartTimeSelector1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        smartTimeSelector1Layout.setVerticalGroup(
            smartTimeSelector1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    public void addICVAnnotation(ICVAnnotation annotation){
        this.smartTimeSelector1.addICVAnnotation(annotation);
        this.smartTimeSelector1.repaint();
    }
    
    public void setStartIntervalWord(String startIntervalWord) {
        this.startIntervalWord = startIntervalWord;
        ((ZoomLabeledLayer) layerUI).setFromTranslated(startIntervalWord);

    }
    
    public int getStartNightHour() {
        return smartTimeSelector1.getStartNightHour(); 
    }

    public void setStartNightHour(int startNightHour) {
        this.smartTimeSelector1.setStartNightHour(startNightHour);
    }

    public void setEndNightHour(int endNightHour) {
        this.smartTimeSelector1.setEndNightHour(endNightHour);
        
    }

    public int getEndNightHour() {
        return this.smartTimeSelector1.getEndNightHour();
    }

    public boolean isNightVisible() {
        return this.smartTimeSelector1.isNightVisible();
    }

    public void setNightVisible(boolean nightVisible) {
        this.smartTimeSelector1.setNightVisible(nightVisible);
    }

    public void setEndIntervalWord(String endIntervalWord) {
        this.endIntervalWord = endIntervalWord;
        ((ZoomLabeledLayer) layerUI).setToTranslated(endIntervalWord);
    }

    public Date getStartDataSelection() {
        return this.smartTimeSelector1.getStartDataSelection();
    }

    public Date getEndDataSelection() {
        return this.smartTimeSelector1.getEndDataSelection();
    }
    
    public void selectAll(){
        this.smartTimeSelector1.selectAll();
    }

    public Date getStartSquare() {
        return this.smartTimeSelector1.getStartSquareDate();
    }

    public Date getEndSquare() {
        return this.smartTimeSelector1.getEndSquareDate();
    }

    public void addSmartIntervalListener(SmartIntervalListener listener) {
        this.smartTimeSelector1.addSmartIntervalListener(listener);
    }

    public void setStartRange(long time) {
        this.smartTimeSelector1.setStartRange(time);
        smartTimeSelector1.setStartRange(time);
        this.smartTimeSelector1.repaint();
    }

    public void setEndRange(long time) {
        this.smartTimeSelector1.setEndRange(time);
        smartTimeSelector1.setEndRange(time);
        this.smartTimeSelector1.repaint();
    }

    public long getStartRange() {
        return this.smartTimeSelector1.getStartRange();
    }

    public long getEndRange() {
        return this.smartTimeSelector1.getEndRange();
    }

    public void addZoomListener(ZoomListener listener) {
        this.smartTimeSelector1.addZoomListener(listener);
    }

    public void initRedZone() {
        this.smartTimeSelector1.initRedZone();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private it.cnr.istc.icv.timeselector.smart.SmartTimeSelector smartTimeSelector1;
    // End of variables declaration//GEN-END:variables
}
