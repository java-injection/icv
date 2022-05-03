/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.test.rnd;

import it.cnr.istc.icv.exceptions.TypeDataMismatchException;
import it.cnr.istc.icv.logic.ActivityDataPriority;
import it.cnr.istc.icv.logic.LinearDataContainerInterface;
import it.cnr.istc.icv.test.ActivityContainerSupporter;
import it.cnr.istc.icv.test.ActivityDataSupporter;
import it.cnr.istc.icv.test.BooleanDataSupporter;
import it.cnr.istc.icv.test.CumulativeDataContainerSupporter;
import it.cnr.istc.icv.test.CumulativeDataContainerSupporter2;
import it.cnr.istc.icv.test.LinearBooleanDataSupporter;
import it.cnr.istc.icv.test.LinearDataSupporter;
import it.cnr.istc.icv.test.TimeValueSupporterClass;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class TimeNowTesterFrame extends javax.swing.JFrame {

    CumulativeDataContainerSupporter2 cum;
    LinearDataContainerInterface<TimeValueSupporterClass> extractData;
    /**
     * Creates new form NewJFrame
     */
    public TimeNowTesterFrame() {
//        try {
            initComponents();
            this.veryDroppablePanel1.getMixedPanel().setStartRange(new Date().getTime() - 2000000);
            this.veryDroppablePanel1.getMixedPanel().setEndRange(new Date().getTime() + 2000000);
//            this.veryDroppablePanel1.getMixedPanel().setNowLineVisible(true);
//            this.veryDroppablePanel1.getMixedPanel().addDataBar(new ActivityContainerSupporter("P1"));
//            this.veryDroppablePanel1.getMixedPanel().addDataBar(new ActivityContainerSupporter("P2"));
//            this.veryDroppablePanel1.getMixedPanel().addDataBar(new ActivityContainerSupporter("P3"));
//            this.veryDroppablePanel1.getMixedPanel().addDataBar(new ActivityContainerSupporter("P4"));
//            this.veryDroppablePanel1.getMixedPanel().addDataBar(new ActivityContainerSupporter("P5"));
//            this.veryDroppablePanel1.getMixedPanel().addDataBar(new ActivityContainerSupporter("P6"));
//            this.veryDroppablePanel1.getMixedPanel().addDataBar(new ActivityContainerSupporter("P7"));
//            veryDroppablePanel1.getMixedPanel().addActivityData(new ActivityDataSupporter("P1", new Date(new Date().getTime()-2000000+400000), new Date(new Date().getTime()+2000000-400000),new ActivityDataPriority(ActivityDataPriority.Priority.LOW, false)),false);
////            veryDroppablePanel1.getMixedPanel().addActivityData(new ActivityDataSupporter("P6", new Date(113, 9, 5, 10, 15, 0), new Date(113, 9, 5, 10, 30, 0),new ActivityDataPriority(ActivityDataPriority.Priority.HIGH, true)),false);
////            veryDroppablePanel1.getMixedPanel().addActivityData(new ActivityDataSupporter("P6", new Date(113, 9, 5, 10, 33, 0), new Date(113, 9, 5, 11, 5, 0),new ActivityDataPriority(ActivityDataPriority.Priority.LOW, false)),false);
//            
//            this.veryDroppablePanel1.getMixedPanel().setNowLineVisible(true);
//            this.veryDroppablePanel1.getMixedPanel().repaint();
//        } catch (TypeDataMismatchException ex) {
//            Logger.getLogger(TimeNowTesterFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
            
             LinearBooleanDataSupporter bGino = new LinearBooleanDataSupporter("Gino");
//        BooleanDataSupporter bs1 = new BooleanDataSupporter(new Date(113, 9, 5, 10, 0, 0), true);
//        BooleanDataSupporter bs2 = new BooleanDataSupporter(new Date(113, 9, 5, 11, 30, 0), false);
//        bGino.addData(bs1);
//        bGino.addData(bs2);
        Map<Boolean, String> bMap = new HashMap<Boolean, String>();
        bMap.put(true, "Connesso");
        bMap.put(false, "Disconnesso");
        bGino.mapValues("Gino", bMap);
        this.veryDroppablePanel1.getMixedPanel().addDataBar(bGino);
//        mixedDataPanel.repaint();
        
        
       LinearBooleanDataSupporter bGinetto = new LinearBooleanDataSupporter("Ginetto");
//        BooleanDataSupporter bs12 = new BooleanDataSupporter(new Date(113, 9, 5, 10, 30, 0), true);
        BooleanDataSupporter bs22 = new BooleanDataSupporter(new Date(new Date().getTime() - 2000000 + 800000), false);
//        bGinetto.addData(bs12);
        bGinetto.addData(bs22);
        bGinetto.mapValues("Ginetto", bMap);
        this.veryDroppablePanel1.getMixedPanel().addDataBar(bGinetto);

        
        LinearBooleanDataSupporter bGinuzzo = new LinearBooleanDataSupporter("Ginuzzo");
//        BooleanDataSupporter bs123 = new BooleanDataSupporter(new Date(113, 9, 5, 11, 20, 0), true);
        BooleanDataSupporter bs223 = new BooleanDataSupporter(new Date(113, 9, 5, 9, 45, 0), true);
//        bGinuzzo.addData(bs123);
        bGinuzzo.addData(bs223);
        bGinuzzo.mapValues("Ginuzzo", bMap);
        this.veryDroppablePanel1.getMixedPanel().addDataBar(bGinuzzo);
          
        cum = new CumulativeDataContainerSupporter2("Prova");
        cum.setStartRange(new Date().getTime() - 2000000);
        cum.setOrder(1);
        cum.setMax(5);
        cum.addRowToCumulate(bGino);
        cum.addRowToCumulate(bGinetto);
        cum.addRowToCumulate(bGinuzzo);
        extractData = cum.extractData();
        ((LinearDataSupporter)extractData).setOrder(2);
        bGinetto.setOrder(4);
        bGino.setOrder(5);
        bGinuzzo.setOrder(6);
        this.veryDroppablePanel1.getMixedPanel().addDataBar(extractData);
        this.veryDroppablePanel1.getMixedPanel().addDataBar(cum);
        this.veryDroppablePanel1.getMixedPanel().repaint();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        veryDroppablePanel1 = new it.cnr.istc.icv.engine.EmbeddablePanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Gino False");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Gino True");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Ginetto True");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Ginetto False");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Ginuzzo True");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Ginuzzo False");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(veryDroppablePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6)
                        .addComponent(jButton5))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(veryDroppablePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         try {
            BooleanDataSupporter bs223 = new BooleanDataSupporter(new Date(), true);
            cum.cumulateWithSingleData(bs223, "Gino");
            veryDroppablePanel1.getMixedPanel().addBooleanData("Gino", bs223, true);
//            List<TimeValueSupporterClass> extracted = extractData.getValuesMap().get(cum.getDefaultSubchart());
//            extracted.add(new TimeValueSupporterClass(extracted.get(extracted.size()-1).getValue()+1 , cum.getDefaultSubchart(), new Date()));
            this.veryDroppablePanel1.getMixedPanel().repaint();
        } catch (TypeDataMismatchException ex) {
            Logger.getLogger(TimeNowTesterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            BooleanDataSupporter bs223 = new BooleanDataSupporter(new Date(), false);
            cum.cumulateWithSingleData(bs223, "Gino");
            veryDroppablePanel1.getMixedPanel().addBooleanData("Gino", bs223, false);
//            List<TimeValueSupporterClass> extracted = extractData.getValuesMap().get(cum.getDefaultSubchart());
//            extracted.add(new TimeValueSupporterClass(extracted.get(extracted.size()-1).getValue()-1 , cum.getDefaultSubchart(), new Date()));
            this.veryDroppablePanel1.getMixedPanel().repaint();
        } catch (TypeDataMismatchException ex) {
            Logger.getLogger(TimeNowTesterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       try {
            BooleanDataSupporter bs223 = new BooleanDataSupporter(new Date(), true);
            cum.cumulateWithSingleData(bs223, "Ginetto");
            veryDroppablePanel1.getMixedPanel().addBooleanData("Ginetto", bs223, true);
//            List<TimeValueSupporterClass> extracted = extractData.getValuesMap().get(cum.getDefaultSubchart());
//            extracted.add(new TimeValueSupporterClass(extracted.get(extracted.size()-1).getValue()+1 , cum.getDefaultSubchart(), new Date()));
            this.veryDroppablePanel1.getMixedPanel().repaint();
        } catch (TypeDataMismatchException ex) {
            Logger.getLogger(TimeNowTesterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         try {
            BooleanDataSupporter bs223 = new BooleanDataSupporter(new Date(), true);
            cum.cumulateWithSingleData(bs223, "Ginuzzo");
            veryDroppablePanel1.getMixedPanel().addBooleanData("Ginuzzo", bs223, true);
//            List<TimeValueSupporterClass> extracted = extractData.getValuesMap().get(cum.getDefaultSubchart());
//            extracted.add(new TimeValueSupporterClass(extracted.get(extracted.size()-1).getValue()+1 , cum.getDefaultSubchart(), new Date()));
            this.veryDroppablePanel1.getMixedPanel().repaint();
        } catch (TypeDataMismatchException ex) {
            Logger.getLogger(TimeNowTesterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            BooleanDataSupporter bs223 = new BooleanDataSupporter(new Date(), false);
            cum.cumulateWithSingleData(bs223, "Ginetto");
            veryDroppablePanel1.getMixedPanel().addBooleanData("Ginetto", bs223, false);
//            List<TimeValueSupporterClass> extracted = extractData.getValuesMap().get(cum.getDefaultSubchart());
//            extracted.add(new TimeValueSupporterClass(extracted.get(extracted.size()-1).getValue()-1 , cum.getDefaultSubchart(), new Date()));
            this.veryDroppablePanel1.getMixedPanel().repaint();
        } catch (TypeDataMismatchException ex) {
            Logger.getLogger(TimeNowTesterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            BooleanDataSupporter bs223 = new BooleanDataSupporter(new Date(), false);
            cum.cumulateWithSingleData(bs223, "Ginuzzo");
            veryDroppablePanel1.getMixedPanel().addBooleanData("Ginuzzo", bs223, false);
//            List<TimeValueSupporterClass> extracted = extractData.getValuesMap().get(cum.getDefaultSubchart());
//            extracted.add(new TimeValueSupporterClass(extracted.get(extracted.size()-1).getValue()-1 , cum.getDefaultSubchart(), new Date()));
            this.veryDroppablePanel1.getMixedPanel().repaint();
        } catch (TypeDataMismatchException ex) {
            Logger.getLogger(TimeNowTesterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TimeNowTesterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimeNowTesterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimeNowTesterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimeNowTesterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimeNowTesterFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private it.cnr.istc.icv.engine.EmbeddablePanel veryDroppablePanel1;
    // End of variables declaration//GEN-END:variables
}
