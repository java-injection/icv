/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.timeselector;

import it.cnr.istc.icv.engine.MyLayer;
import java.beans.Beans;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;

/**
 *
 * @author Luca
 */
public class TimeSelectorContainer extends javax.swing.JPanel implements PropertyChangeListener {

//    public static final String FROM_ID = "FROM";
//    public static final String TO_ID = "TO";
    private volatile String zoomLabeledValueWord;
//    private volatile String startIntervalWord;
//    private volatile String endIntervalWord;
    private MyLayer<JPanel> layerUI;

    /**
     * Creates new form TimeSelectorContainer
     */
    public TimeSelectorContainer() {
        initComponents();

        this.proDateSelector_From.setIdentifier(ProDateSelector.RANGE_TYPE.FROM);
        this.proDateSelector_To.setIdentifier(ProDateSelector.RANGE_TYPE.TO);
        if (!Beans.isDesignTime()) {
            this.proDateSelector_From.addPropertySupportListener(this);
            this.proDateSelector_To.addPropertySupportListener(this);
            this.timeSelector1.addZoomListener(proDateSelector_To);
            this.timeSelector1.addZoomListener(proDateSelector_From);
        }
    }

    public void setZoomLabeledValueWord(String zoomLabeledValueWord) {
        this.zoomLabeledValueWord = zoomLabeledValueWord;
    }

    public String getZoomLabeledValueWord() {
        return zoomLabeledValueWord;
    }

    public void setEndInternalWord(String endWord) {
        this.timeSelector1.setEndIntervalWord(endWord);
    }
    
    public void setStartInternalWord(String startWord) {
        this.timeSelector1.setStartIntervalWord(startWord);
    }

    public void setStartRange(Date startRange) {
        this.proDateSelector_From.setDate(startRange);
        System.out.println("setting start: "+startRange);
        this.timeSelector1.setStartRange(startRange.getTime());
    }

    public void setEndRange(Date endRange) {
        System.out.println("setting end: "+endRange);
        this.proDateSelector_To.setDate(endRange);
        this.timeSelector1.setEndRange(endRange.getTime());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        proDateSelector_From = new it.cnr.istc.icv.timeselector.ProDateSelector();
        proDateSelector_To = new it.cnr.istc.icv.timeselector.ProDateSelector();
        timeSelector1 = new it.cnr.istc.icv.timeselector.ZoomableTimeSelector();

        proDateSelector_From.setBorder(javax.swing.BorderFactory.createTitledBorder("From"));

        proDateSelector_To.setBorder(javax.swing.BorderFactory.createTitledBorder("To"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(proDateSelector_From, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                .addComponent(proDateSelector_To, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(timeSelector1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proDateSelector_From, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proDateSelector_To, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeSelector1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

//    public TimeLevel getCurrentTimeLevel() {
//        return this.timeSelector1.getCurrentTimeLevel();
//    }
    public long getStartRange() {
        return this.timeSelector1.getStartRange();
    }

    public long getEndRange() {
        return this.timeSelector1.getEndRange();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private it.cnr.istc.icv.timeselector.ProDateSelector proDateSelector_From;
    private it.cnr.istc.icv.timeselector.ProDateSelector proDateSelector_To;
    private it.cnr.istc.icv.timeselector.ZoomableTimeSelector timeSelector1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ProDateSelector.DATA_CHANGED)) {
            if (((ProDateSelector.RANGE_TYPE)evt.getOldValue()) == ProDateSelector.RANGE_TYPE.FROM){
                this.timeSelector1.setStartRange(((Calendar) evt.getNewValue()).getTime().getTime());
            } else if (((ProDateSelector.RANGE_TYPE)evt.getOldValue()) == ProDateSelector.RANGE_TYPE.TO) {
                this.timeSelector1.setEndRange(((Calendar) evt.getNewValue()).getTime().getTime());
            }
            this.timeSelector1.repaint();
        }
    }
}
