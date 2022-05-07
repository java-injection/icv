/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.extra.layers;


import it.cnr.istc.extra.layers.InfoPanelLayer;
import it.cnr.istc.icv.engine.MyJLayer;
import it.cnr.istc.icv.engine.MyLayer;
import it.cnr.istc.icv.extra.BlinkableGlassPane;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Luca
 */
public class TestInfoPanelLayer extends javax.swing.JFrame {

    private MyLayer layer;
    /**
     * Creates new form TestClockLayer
     */
    public TestInfoPanelLayer() {
        initComponents();

        layer = new BlinkableGlassPane(jPanel_test);
            JPanel containerP = new JPanel();
            containerP.setLayout(new GridLayout(0, 1));
            containerP.add(jPanel_test);
            
            MyJLayer trup = new MyJLayer<JComponent>(jPanel_test, layer);
            containerP.add(trup);
            
            
            MyLayer<JComponent> clockLayer = new InfoPanelLayer();
//            ((InfoPanelLayer)clockLayer).setExtraSurfacePanel(new NewJPanel());
            JPanel containerP2 = new JPanel();
            containerP2.setLayout(new GridLayout(0, 1));
            containerP2.add(containerP);
            
            MyJLayer trup2 = new MyJLayer<JComponent>(containerP, clockLayer);
            containerP2.add(trup2);
            
            
            this.jScrollPane1.setViewportView(containerP2);
            
            
        
//        MyLayer<JComponent> layerUI = new ClockLayer();
//        JPanel containerP = new JPanel();
//        containerP.setLayout(new GridLayout(0, 1));
//        containerP.add(jPanel_test);
//
//        MyJLayer<JComponent> jlayer = new MyJLayer<JComponent>(jPanel_test, layerUI);
//        containerP.add(jlayer);
//        this.jScrollPane1.setViewportView(containerP);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_test = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        jPanel_test.setBackground(new java.awt.Color(204, 255, 204));
        jPanel_test.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel_testMouseReleased(evt);
            }
        });

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jCheckBox1.setText("jCheckBox1");

        javax.swing.GroupLayout jPanel_testLayout = new javax.swing.GroupLayout(jPanel_test);
        jPanel_test.setLayout(jPanel_testLayout);
        jPanel_testLayout.setHorizontalGroup(
            jPanel_testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_testLayout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jButton1)
                .addGap(76, 76, 76)
                .addComponent(jButton2)
                .addContainerGap(108, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_testLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addGap(221, 221, 221))
        );
        jPanel_testLayout.setVerticalGroup(
            jPanel_testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_testLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addGap(71, 71, 71))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_testMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_testMouseReleased
//         System.out.println("clicked");
//
//        double newX = (double)evt.getX() / (double)this.jScrollPane1.getWidth();
//        double newY = (double)evt.getY() / (double)this.jScrollPane1.getHeight();
//        System.out.println("%%%%%%%%%%%%%%%% punto x : "+evt.getX());
//        System.out.println("%%%%%%%%%%%% lungh "+this.jScrollPane1.getWidth());
//        System.out.println(" %%%%%%%%%%%%%%%%%  newX = "+newX);
//            ((BlinkableGlassPane) this.layer).startBlinking(((ImageIcon)SensorTester.balug24), newX, newY);

    }//GEN-LAST:event_jPanel_testMouseReleased

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
            java.util.logging.Logger.getLogger(TestInfoPanelLayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestInfoPanelLayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestInfoPanelLayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestInfoPanelLayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestInfoPanelLayer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_test;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
