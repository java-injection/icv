/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test;

import it.cnr.istc.icv.exceptions.TypeDataMismatchException;
import it.cnr.istc.icv.logic.TimeDataInterface;
import java.awt.Color;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class TesterNoDataPanelFrame extends javax.swing.JFrame {

    /**
     * Creates new form TesterNoDataPanelFrame
     */
    public TesterNoDataPanelFrame() {
        initComponents();
        LinearDataSupporter container = new LinearDataSupporter("MainChart");
        this.veryDroppablePanel1.getMixedPanel().setShowDate(false);
        this.veryDroppablePanel1.getMixedPanel().setStartRange(30);
        this.veryDroppablePanel1.getMixedPanel().setEndRange(100);  
        this.veryDroppablePanel1.setXTooltipLabel("MJoule");
        this.veryDroppablePanel1.setYTooltipLabel("m/s");
        container.setMinValueToShow(10);
        container.setMaxValueToShow(200);
        container.setDiscret(true);
        container.setColorToSubChart("Energia", Color.RED);
        container.setColorToSubChart("Chiromanzia", Color.BLUE);
        this.veryDroppablePanel1.getMixedPanel().addDataBar(container);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        veryDroppablePanel1 = new it.cnr.istc.icv.engine.EmbeddablePanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(veryDroppablePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(7, 7, 7)
                .addComponent(veryDroppablePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Thread t = new Thread(new DataGenerator("Energia", "Chiromanzia"));
        t.start();
        System.out.println("thread partito");
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
            java.util.logging.Logger.getLogger(TesterNoDataPanelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TesterNoDataPanelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TesterNoDataPanelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TesterNoDataPanelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TesterNoDataPanelFrame().setVisible(true);
            }
        });
    }

    class DataGenerator implements Runnable {

        private String subChartname1;
        private String subChartname2;

        public DataGenerator(String subChartname1, String subChartname2) {
            this.subChartname1 = subChartname1;
            this.subChartname2 = subChartname2;
        }

        int[] sMax = {0, 1, 4, 6, 0, 5, 4, 5, 5, 5, 6, 0, 6, 6, 5, 0, 1, 2};
        int[] xValues = {40, 50, 60, 70, 80, 90};
        int[] yValues = {30, 60, 90, 110, 130, 150};

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(2200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TesterNoDataPanelFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                veryDroppablePanel1.getMixedPanel().clearLinearDataBar("MainChart");
                int max1 = sMax[((int) ((Math.random() * 100) % sMax.length))];
                int max2 = sMax[((int) ((Math.random() * 100) % sMax.length))];
                for (int i = 0; i < max1; i++) {
                    int x = (int) (xValues[i] + ((Math.random() > 0.5f ? 1 : -1) * ((int) ((Math.random() * 100) % 10))));
                    int y = (int) (yValues[i] + ((Math.random() > 0.5f ? 1 : -1) * ((int) ((Math.random() * 100) % 20))));
                    TimeValueSupporterClass ds1 = new TimeValueSupporterClass(y, subChartname1, new Date(x));
                    try {
                        veryDroppablePanel1.getMixedPanel().addLinearData("MainChart", ds1, true);
                    } catch (TypeDataMismatchException ex) {
                        ex.printStackTrace();
                    }
                }
                for (int i = 0; i < max2; i++) {
                    int x = (int) (xValues[i] + ((Math.random() > 0.5f ? 1 : -1) * ((int) ((Math.random() * 100) % 10))));
                    int y = (int) (yValues[i] + ((Math.random() > 0.5f ? 1 : -1) * ((int) ((Math.random() * 100) % 20))));
                    TimeValueSupporterClass ds1 = new TimeValueSupporterClass(y, subChartname2, new Date(x));
                    try {
                        veryDroppablePanel1.getMixedPanel().addLinearData("MainChart", ds1, true);
                    } catch (TypeDataMismatchException ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println("new dat");
                veryDroppablePanel1.repaint();

            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private it.cnr.istc.icv.engine.EmbeddablePanel veryDroppablePanel1;
    // End of variables declaration//GEN-END:variables
}
