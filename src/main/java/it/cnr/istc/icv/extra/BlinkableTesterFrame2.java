/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.extra;

import it.cnr.istc.extra.test.NewJPanel;
import it.cnr.istc.extra.test.SensorTester;
import it.cnr.istc.icv.engine.MyJLayer;
import it.cnr.istc.icv.engine.MyLayer;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Luca
 */
public class BlinkableTesterFrame2 extends javax.swing.JFrame {

    /**
     * Creates new form BlinkableTesterFrame
     */
    private MyLayer<JComponent> layerUI = null;

    public BlinkableTesterFrame2() {
        initComponents();

        

        NewJPanel newJPanel = new NewJPanel();
        layerUI = new BlinkableGlassPane(newJPanel);
        newJPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {

                double newX = (double) evt.getX() / (double) BlinkableTesterFrame2.this.jScrollPane1.getWidth();
                double newY = (double) evt.getY() / (double) BlinkableTesterFrame2.this.jScrollPane1.getHeight();
                if (BlinkableTesterFrame2.this.jComboBox1.getSelectedIndex() == 1) {
                    ((BlinkableGlassPane) BlinkableTesterFrame2.this.layerUI).startBlinking(((ImageIcon) SensorTester.balug24), newX, newY);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BlinkableTesterFrame2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ((BlinkableGlassPane) BlinkableTesterFrame2.this.layerUI).startBlinking(((ImageIcon) SensorTester.balug24), newX, newY);
                } else if (BlinkableTesterFrame2.this.jComboBox1.getSelectedIndex() == 0) {
                    ((BlinkableGlassPane) BlinkableTesterFrame2.this.layerUI).startBlinking(((ImageIcon) SensorTester.powerOnIcon), newX, newY, 1.1d, 4000, 500);
                } else if (BlinkableTesterFrame2.this.jComboBox1.getSelectedIndex() == 2) {
                    ((BlinkableGlassPane) BlinkableTesterFrame2.this.layerUI).spamBlinkingAround(((ImageIcon) SensorTester.smoke), newX, newY, 40d, 15d, 15000, 1000);
                } else if (BlinkableTesterFrame2.this.jComboBox1.getSelectedIndex() == 3) {
                    ((BlinkableGlassPane) BlinkableTesterFrame2.this.layerUI).fillSpaceAround(((ImageIcon) SensorTester.smoke), 20, newX, newY, 60d, 15d, 15000, 1000);
                }
            }

        });

        
        
        JPanel containerP = new JPanel();
//        containerP.setLayout(new GridLayout(0, 1));
        containerP.setOpaque(false);
        containerP.setBackground(null);
//        JPanel pewe = new JPanel();
        newJPanel.setBounds(0, 0, 300, 300);
        containerP.add(newJPanel);

        MyJLayer<JComponent> jlayer = new MyJLayer<JComponent>(newJPanel, layerUI);
        containerP.add(jlayer);
        this.jScrollPane1.setViewportView(containerP);


        
        
        
        
        
        
        
        
        
        
        
        

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
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("PIR");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NORMAL TICK", "PIR", "ALARM", "RANDOM" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, 0, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(59, 59, 59))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jCheckBox1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
//        System.out.println("clicked");

        double newX = (double) evt.getX() / (double) this.jScrollPane1.getWidth();
        double newY = (double) evt.getY() / (double) this.jScrollPane1.getHeight();
//        System.out.println("%%%%%%%%%%%%%%%% punto x : " + evt.getX());
//        System.out.println("%%%%%%%%%%%% lungh " + this.jScrollPane1.getWidth());
//        System.out.println(" %%%%%%%%%%%%%%%%%  newX = " + newX);
        if (this.jComboBox1.getSelectedIndex() == 1) {
            ((BlinkableGlassPane) this.layerUI).startBlinking(((ImageIcon) SensorTester.balug24), newX, newY);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(BlinkableTesterFrame2.class.getName()).log(Level.SEVERE, null, ex);
            }
            ((BlinkableGlassPane) this.layerUI).startBlinking(((ImageIcon) SensorTester.balug24), newX, newY);
        } else if (this.jComboBox1.getSelectedIndex() == 0) {
            ((BlinkableGlassPane) this.layerUI).startBlinking(((ImageIcon) SensorTester.powerOnIcon), newX, newY, 1.1d, 4000, 500);
        } else if (this.jComboBox1.getSelectedIndex() == 2) {
            ((BlinkableGlassPane) this.layerUI).spamBlinkingAround(((ImageIcon) SensorTester.smoke), newX, newY, 40d, 15d, 15000, 1000);
        } else if (this.jComboBox1.getSelectedIndex() == 3) {
            ((BlinkableGlassPane) this.layerUI).fillSpaceAround(((ImageIcon) SensorTester.smoke), 20, newX, newY, 60d, 15d, 15000, 1000);
        }
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(BlinkableTesterFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        ((BlinkableGlassPane) this.layerUI).startBlinking(Color.yellow, newX, newY, 0, 3000, 500);
//        ((BlinkableGlassPane) this.layerUI).startBlinking(Color.yellow, newX, newY);
    }//GEN-LAST:event_jPanel1MouseReleased

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(BlinkableTesterFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlinkableTesterFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlinkableTesterFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlinkableTesterFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BlinkableTesterFrame2().setVisible(true);
                MathUtil.generateDecelerationValues(15.8d, 30000, 1000);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}