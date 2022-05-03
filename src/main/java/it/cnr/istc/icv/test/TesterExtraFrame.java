/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test;

import it.cnr.istc.extra.MathUtil;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
//import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
//import javax.swing.plaf.LayerUI;
import it.cnr.istc.icv.logic.ActivityDataPriority;
import it.cnr.istc.icv.engine.MixedDataPanel;
import it.cnr.istc.icv.engine.MyJLayer;
import it.cnr.istc.icv.engine.MyLayer;
import it.cnr.istc.icv.engine.ZoomLabeledLayer;
import it.cnr.istc.icv.engine.listeners.CoordinateListener;
import it.cnr.istc.icv.engine.listeners.PopupMenuTriggerListener;
import it.cnr.istc.icv.exceptions.TypeDataMismatchException;
import it.cnr.istc.icv.logic.ICVAnnotation;
import it.cnr.istc.icv.logic.ICVMappableAnnotation;
import java.awt.AWTEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author Luca
 */
public class TesterExtraFrame extends javax.swing.JFrame implements CoordinateListener, PopupMenuTriggerListener{

//    final ActivityPanel panel = new ActivityPanel();
    final static MixedDataPanel panel = new MixedDataPanel();

    /**
     * Creates new form TesterExtraFrame
     */
    public TesterExtraFrame() {
        
        try {
            initComponents();

            //        final HackedPanel p = new HackedPanel();
            //        setContentPane(p);
            //         final ActivityPanel panel = new ActivityPanel();
            ActivityDataPriority.MEDIUM_COLOR_START = Color.MAGENTA;
            //        ActivityDataPriority.vertical = false;
            panel.addCoordinateListener(this);
            panel.addPopupMenuTriggerListener(this);
            panel.setStartRange(new Date(113, 9, 5, 9, 0, 0).getTime());
            panel.setEndRange(new Date(113, 9, 5, 12, 0, 0).getTime());
            panel.setShowDate(false);
//            panel.setBackgroundChartColor(Color.BLACK);
//            panel.setyAxisDataColor(Color.yellow);
//            panel.setxAxisDataColor(Color.yellow);
            panel.setBackground(Color.WHITE);
//            panel.set
            //        panel.setDataColor(new Color(0,146,29));
            panel.setZoomEnable(true);
            //        panel.addActivity("Sleep");
            //        panel.addActivity("TV");
            //        panel.addActivity("Cooking");
            //        panel.addActivity("Thinking");
            //        panel.addActivity("Testing");
//                    panel.addActivity("Playing");
            //        panel.addActivity("Events");

//            panel.addDataBar("Pressure", LinearDataContainerInterface.class);
//            panel.addDataBar("TV", ActivityDataExtendedInterface.class);
//            panel.addDataBar("Cooking", ActivityDataInterface.class);
//            panel.addDataBar("Thinking", ActivityDataInterface.class);
//            panel.addDataBar("Testing", ActivityDataInterface.class);
//            panel.addDataBar("Playing", ActivityDataContainer.class);
//            panel.addDataBar("Events", ActivityDataInterface.class);
//            panel.addDataBar("MultiSensor", LinearDataContainerInterface.class);
            
//            panel.addDataBar("Door Contact", BooleanDataContainer.class);

            LinearBooleanDataSupporter b1 = new LinearBooleanDataSupporter("Door Contact");
            b1.setOrder(0);
//            BooleanDataSupporter bs1 = new BooleanDataSupporter(new Date(113, 9, 5, 9, 50, 0), true);
//            BooleanDataSupporter bs2 = new BooleanDataSupporter(new Date(113, 9, 5, 11, 0, 0), false);
//            b1.addData(bs1);
//            b1.addData(bs2);
            Map<Boolean, String> bMap = new HashMap<Boolean, String>();
            bMap.put(true, "Aperto");
            bMap.put(false, "Chiuso poco");
            b1.mapValues("Door Contact", bMap);
            panel.addDataBar(b1);
//            panel.addLinearData(b1, false);
//            ActivityContainerSupporter activityContainerSupporter = new ActivityContainerSupporter("Playing");
//            activityContainerSupporter.setOrder(2);
//          panel.addDataBar(activityContainerSupporter);
//
            LinearDataSupporter s = new LinearDataSupporter("Pressure");
            s.setOrder(1);
            s.setDiscret(true);
            s.setMaxValueToShow(250);
            s.setMinValueToShow(0);
            s.setMaxThresholdPaintable(true);
            s.setMaximumThreshold(180);
            s.setMinThresholdPaintable(true);
            s.setMinimumThreshold(60);
            panel.addDataBar(s);
            TimeValueSupporterClass ds1 = new TimeValueSupporterClass(23, "Sistolic", new Date(113, 9, 5, 9, 50, 0));
//            s.addData(ds1);
            TimeValueSupporterClass ds2 = new TimeValueSupporterClass(50, "Sistolic", new Date(113, 9, 5, 10, 50, 0));
//            s.addData(ds2);
            TimeValueSupporterClass ds3 = new TimeValueSupporterClass(44, "Sistolic", new Date(113, 9, 5, 11, 10, 0));
//            s.addData(ds3);
            TimeValueSupporterClass ds4 = new TimeValueSupporterClass(40, "Dia", new Date(113, 9, 5, 9, 50, 0));
//            s.addData(ds4);
            TimeValueSupporterClass ds5 = new TimeValueSupporterClass(70, "Dia", new Date(113, 9, 5, 10, 30, 0));
//            s.addData(ds5);
            TimeValueSupporterClass ds6 = new TimeValueSupporterClass(75, "Dia", new Date(113, 9, 5, 11, 25, 0));
//            s.addData(ds6);
            
            TimeValueSupporterClass ds7 = new TimeValueSupporterClass(140, "Heart Beat", new Date(113, 9, 5, 8, 20, 0));
//            s.addData(ds4);
            TimeValueSupporterClass ds8 = new TimeValueSupporterClass(85, "Heart Beat", new Date(113, 9, 5, 10, 0, 0));
//            s.addData(ds5);
            TimeValueSupporterClass ds9 = new TimeValueSupporterClass(115, "Heart Beat", new Date(113, 9, 5, 11, 0, 0));
//            s.addData(ds6);

            panel.addLinearData("Pressure",ds1, false);
            panel.addLinearData("Pressure",ds2, false);
            panel.addLinearData("Pressure",ds3, false);
            panel.addLinearData("Pressure",ds4, false);
            panel.addLinearData("Pressure",ds5, false);
            panel.addLinearData("Pressure",ds6, false);
            panel.addLinearData("Pressure",ds7, false);
            panel.addLinearData("Pressure",ds8, false);
            panel.addLinearData("Pressure",ds9, false);
            
            
            LinearDataSupporter s2 = new LinearDataSupporter("MultiSensor");
            s2.setOrder(3);
            panel.addDataBar(s2);
            TimeValueSupporterClass dss1 = new TimeValueSupporterClass(44, "Sensor", new Date(113, 9, 5, 7, 50, 0));
            panel.addLinearData("MultiSensor",dss1, false);
            TimeValueSupporterClass dss2 = new TimeValueSupporterClass(56, "Sensor", new Date(113, 9, 5, 8, 10, 0));
            panel.addLinearData("MultiSensor",dss2, false);
            TimeValueSupporterClass dss3 = new TimeValueSupporterClass(111, "Sensor", new Date(113, 9, 5, 8, 25, 0));
            panel.addLinearData("MultiSensor",dss3, false);
            TimeValueSupporterClass dss4 = new TimeValueSupporterClass(100, "Sensor", new Date(113, 9, 5, 8, 26, 0));
            panel.addLinearData("MultiSensor",dss4, false);
            TimeValueSupporterClass dss5 = new TimeValueSupporterClass(78, "Sensor", new Date(113, 9, 5, 9, 0, 0));
            panel.addLinearData("MultiSensor",dss5, false);
            TimeValueSupporterClass dss6 = new TimeValueSupporterClass(98, "Sensor", new Date(113, 9, 5, 9, 22, 0));
            panel.addLinearData("MultiSensor",dss6, false);
            TimeValueSupporterClass dss7 = new TimeValueSupporterClass(143, "Sensor", new Date(113, 9, 5, 9, 26, 0));
            panel.addLinearData("MultiSensor",dss7, false);
            TimeValueSupporterClass dss8 = new TimeValueSupporterClass(103, "Sensor", new Date(113, 9, 5, 9, 50, 0));
            panel.addLinearData("MultiSensor",dss8, false);
            TimeValueSupporterClass dss9 = new TimeValueSupporterClass(104, "Sensor", new Date(113, 9, 5, 10, 45, 0));
            panel.addLinearData("MultiSensor",dss9, false);
            TimeValueSupporterClass dss10 = new TimeValueSupporterClass(67, "Sensor", new Date(113, 9, 5, 11, 26, 0));
            panel.addLinearData("MultiSensor",dss10, false);
            TimeValueSupporterClass dss11 = new TimeValueSupporterClass(44, "Sensor", new Date(113, 9, 5, 12, 26, 0));
            panel.addLinearData("MultiSensor",dss11, false);
//          
            

//            panel.addActivityData(s2, true);

//            panel.addActivityData(new DataTesterClass("TV", new Date(113, 9, 5, 8, 0, 0), new Date(113, 9, 5, 10, 0, 0), new ActivityDataPriority(ActivityDataPriority.Priority.HIGH, false)), false);
//            panel.addActivityData(new DataTesterClass("Thinking", new Date(113, 9, 5, 9, 30, 0), new Date(113, 9, 5, 11, 0, 0)), false);
//            panel.addActivityData(new DataTesterClass("Testing", new Date(113, 9, 5, 11, 30, 0), new Date(113, 9, 5, 12, 30, 0)), false);
//            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 0, 0), new Date(113, 9, 5, 10, 10, 0),new ActivityDataPriority(ActivityDataPriority.Priority.MEDIUM, false)), false);
//            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 15, 0), new Date(113, 9, 5, 10, 30, 0),new ActivityDataPriority(ActivityDataPriority.Priority.MEDIUM, false)), false);
//            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 33, 0), new Date(113, 9, 5, 11, 5, 0),new ActivityDataPriority(ActivityDataPriority.Priority.MEDIUM, false)), false);
            
            ActivityContainerSupporter acs = new ActivityContainerSupporter("Playing");
            acs.setOrder(4);
            panel.addDataBar(acs);
            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 0, 0), new Date(113, 9, 5, 10, 10, 0),new ActivityDataPriority(ActivityDataPriority.Priority.LOW, false)),false);
            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 15, 0), new Date(113, 9, 5, 10, 30, 0),new ActivityDataPriority(ActivityDataPriority.Priority.HIGH, true)),false);
            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 33, 0), new Date(113, 9, 5, 11, 5, 0),new ActivityDataPriority(ActivityDataPriority.Priority.LOW, false)),false);
            //        setContentPane(panel);
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/it/cnr/istc/icv/icons/GiraffWarning48.png"));
            ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/it/cnr/istc/icv/icons/physio48s.png"));
            ActivityContainerSupporter events = new ActivityContainerSupporter("Events");
            events.setOrder(5);
            panel.addDataBar(events);
            ICVMappableAnnotation ann1 = new ICVMappableAnnotation("123","Events", new Date(113, 9, 5, 10, 0, 0).getTime(), imageIcon.getImage(),false);
            panel.addICVAnnotation(ann1);
            ICVMappableAnnotation ann2 = new ICVMappableAnnotation("124","Events2", new Date(113, 9, 5, 9, 15, 0).getTime(), "Fault Detection", imageIcon.getImage(),false);
            panel.addICVAnnotation(ann2);
//            panel.addICVAnnotation(new ICVAnnotation("Events", new Date(113, 9, 5, 8, 40, 0).getTime(), "Pressure Measurement", imageIcon2.getImage()));
            panel.addICVAnnotation(new ICVMappableAnnotation("125","Events", new Date(113, 9, 5, 9, 45, 0).getTime(), "Weight Measurement", imageIcon2.getImage(),false));
//          
            panel.setArrowsVisible(true);
            panel.linkAnnotations(ann2, ann1);
            
//            ActivityContainerSupporter events2 = new ActivityContainerSupporter("Events2");
//            events2.setOrder(6);
//            panel.addDataBar(events2);
//            
//            ActivityContainerSupporter events3 = new ActivityContainerSupporter("Events3");
//            events3.setOrder(7);
//            panel.addDataBar(events3);
            
            ActivityContainerSupporter events4 = new ActivityContainerSupporter("Events4");
            events4.setOrder(8);
            panel.addDataBar(events4);
            panel.addDividerToHide("Events");
            
            MyLayer<JPanel> layerUI = new ZoomLabeledLayer(panel);
            JPanel containerP = new JPanel();
            containerP.setLayout(new GridLayout(0, 1));
            containerP.add(panel);
            
            MyJLayer<JPanel> jlayer = new MyJLayer<JPanel>(panel, layerUI);
            containerP.add(jlayer);
            this.jScrollPane1.setViewportView(containerP);
            
        } 
        catch (TypeDataMismatchException ex) {
            Logger.getLogger(TesterExtraFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new JButton();
        jScrollPane1 = new JScrollPane();
        jButton2 = new JButton();
        jCheckBox1 = new JCheckBox();
        jCheckBox2 = new JCheckBox();
        jCheckBox3 = new JCheckBox();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jToggleButton1 = new JToggleButton();
        jButton5 = new JButton();
        jButton6 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("save");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("reset zoom");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("TV - Data");
        jCheckBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("Playing - Data");
        jCheckBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setSelected(true);
        jCheckBox3.setText("Playing Act");
        jCheckBox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new ImageIcon(getClass().getResource("/it/cnr/istc/icv/icons/GiraffWarning48.png"))); // NOI18N

        jLabel2.setIcon(new ImageIcon(getClass().getResource("/it/cnr/istc/icv/icons/physio48s.png"))); // NOI18N

        jButton3.setText("TRUE");
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("FALSE");
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("HAND");
        jToggleButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Duplicate");
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("show rec");
        jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox2)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jButton2)
                                .addComponent(jCheckBox1)
                                .addComponent(jCheckBox2)
                                .addComponent(jCheckBox3)))
                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jToggleButton1)
                            .addComponent(jButton5)
                            .addComponent(jButton6))))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        save();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.panel.resetZoom();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        this.panel.setDataVisible("TV", jCheckBox1.isSelected());
        this.repaint();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        this.panel.setDataVisible("Playing", jCheckBox2.isSelected());
        this.repaint();
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        this.panel.setDataBarVisible("Playing", jCheckBox3.isSelected());
//        this.panel.setDataBarVisible("Events", jCheckBox3.isSelected());
//        this.panel.setDataBarVisible("Events4", jCheckBox3.isSelected());
//        this.panel.setDataBarVisible("Events2", jCheckBox3.isSelected());
//        this.panel.setDataBarVisible("Events3", jCheckBox3.isSelected());
        this.panel.setDataBarVisible("Pressure", jCheckBox3.isSelected());
        
        this.repaint();
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jButton3ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            this.panel.addBooleanData("Door Contact",new BooleanDataSupporter(new Date(), true), true);
        } catch (TypeDataMismatchException ex) {
            Logger.getLogger(TesterExtraFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 try {
            this.panel.addBooleanData("Door Contact",new BooleanDataSupporter(new Date(), false), true);
        } catch (TypeDataMismatchException ex) {
            Logger.getLogger(TesterExtraFrame.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jToggleButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if(jToggleButton1.isSelected()){
            this.panel.setHandScrollEnbaled(true);
        }else{
            this.panel.setHandScrollEnbaled(false);
            this.panel.setZoomEnable(true);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton5ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JFrame frame = new JFrame();
        MixedDataPanel duplicate = panel.duplicate();
        duplicate.repaint();
        frame.setContentPane(duplicate);
        duplicate.invalidate();
        frame.setLocationRelativeTo(this);
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.validate();
        frame.getContentPane().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        System.out.println("surrouding");
        Date date = new Date(113, 9, 5, 9, 50, 0);
        System.out.println("surrounding : "+date.toString());
        panel.surroundDataWithRectangle(date);
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
            java.util.logging.Logger.getLogger(TesterExtraFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TesterExtraFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TesterExtraFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TesterExtraFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TesterExtraFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JCheckBox jCheckBox1;
    private JCheckBox jCheckBox2;
    private JCheckBox jCheckBox3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

    public void save() {
        BufferedImage bImg = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        panel.paintAll(cg);
        try {
            FileOutputStream fm = new FileOutputStream(new File("./output_image.png"));
            if (ImageIO.write(bImg, "png", fm)) {
                System.out.println("-- saved");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void currentDate(Date date) {
//        System.out.println("NOW IS : "+date.toString());
    }

    @Override
    public void currentValue(Object value) {
//        System.out.println("current value = "+value);
    }

    @Override
    public void rightClickTriggered(String databar, Date timeValue, int x, int y) {
        System.out.println("POPUP");
        System.out.println(MathUtil.ANSI_BLUE+"databar = "+databar);
        System.out.println("time: "+timeValue.toString());
    }

    @Override
    public void showTooltip(String message, int x, int y) {
        
    }

    @Override
    public void rightClickAreaTriggered(String databar, ICVAnnotation annotation, Date timeValue, int pointX, int pointY) {
        System.err.println("not implemented.. ");
    }

    @Override
    public void forceFromTo(boolean force) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class HackedPanel extends JPanel {

        public HackedPanel() {
            super();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Area area = new Area(new Rectangle(0, 0, getWidth(), getHeight()));
            area.subtract(new Area(new RoundRectangle2D.Float(10, 10, getWidth() - 20, getHeight() - 20, 20, 20)));
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLACK);
            g2d.fill(area);
            g2d.dispose();
        }

       
//        
//        @Override
//        public void paint(Graphics g) {
//            super.paint(g);
//            double width = this.getBounds().getWidth();
//            double height = this.getBounds().getHeight();
//            Rectangle2D rect = new Rectangle2D.Double(2, 2, width/10, height/10);
//        Graphics2D g1 = (Graphics2D) g;
//        g1.draw(rect);
//        
//           Rectangle2D rec = new Rectangle2D.Double(50, 50, 50, 50);
//           g.draw3DRect(0, 0, 30, 30, true);
        //To change body of generated methods, choose Tools | Templates.
    }
    
     public class ZoomLayerUI extends MyLayer<JComponent> {

//         MixedDataPanel ppp;
//        public ZoomLayerUI(MixedDataPanel panel) {
//            this.ppp = panel;
//        }
        
        public ZoomLayerUI() {
//            this.ppp = panel;
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

             super.paint(g, c);
             System.out.println("start-selection = " + TesterExtraFrame.panel.getStartSelection());
             if (TesterExtraFrame.panel.isZoomEnable() && TesterExtraFrame.panel.getStartSelection() != -1) {
                 Graphics2D g1 = (Graphics2D) g.create();
                 Rectangle2D zoom = new Rectangle2D.Double(TesterExtraFrame.panel.getStartSelection(), TesterExtraFrame.panel.getsY(), Math.abs(TesterExtraFrame.panel.getEndSelection() - TesterExtraFrame.panel.getStartSelection()), TesterExtraFrame.panel.gethY());
                 g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                         0.3f));
                 g1.setPaint(Color.BLUE);
                 g1.fill(zoom);
                 g1.dispose();

             }
            }
        }
//    }
     
     
     class ZoomLayer2 extends MyLayer<JPanel> {

        private boolean mActive;
        private int mX, mY;
        private JPanel panel;

        public ZoomLayer2(JPanel panel) {
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

            // Paint the view.
            super.paint(g2, c);

            if (mActive) {
                System.out.println("ACTIVE");
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
                 Rectangle2D zoom = new Rectangle2D.Double(TesterExtraFrame.panel.getStartSelection(), TesterExtraFrame.panel.getsY(), Math.abs(TesterExtraFrame.panel.getEndSelection() - TesterExtraFrame.panel.getStartSelection()), TesterExtraFrame.panel.gethY());
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
            if (e.getID() == MouseEvent.MOUSE_PRESSED) {
                mActive = true;
                if(this.panel instanceof MixedDataPanel){
                    System.out.println("not repaint mode on");
                    ((MixedDataPanel)panel).setRepaintable(false);
                }
            }
            if (e.getID() == MouseEvent.MOUSE_RELEASED) {
                mActive = false;
                if(this.panel instanceof MixedDataPanel){
                    ((MixedDataPanel)panel).setRepaintable(true);
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
     
}
