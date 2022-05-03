/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.test;

import it.cnr.istc.icv.engine.MixedDataPanel;
import it.cnr.istc.icv.engine.MyJLayer;
import it.cnr.istc.icv.engine.MyLayer;
import it.cnr.istc.icv.engine.ZoomLabeledLayer;
import it.cnr.istc.icv.engine.listeners.CoordinateListener;
import it.cnr.istc.icv.exceptions.TypeDataMismatchException;
import it.cnr.istc.icv.logic.ActivityDataPriority;
import it.cnr.istc.icv.logic.ICVAnnotation;
import static it.cnr.istc.icv.test.TesterExtraFrame.panel;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Luca
 */
public class DroppableTestPanel extends javax.swing.JPanel  {

    final static MixedDataPanel panel = new MixedDataPanel();

    public static MixedDataPanel getPanel() {
        return panel;
    }
    
    
    
    /**
     * Creates new form DroppableTestPanel
     */
    public DroppableTestPanel() {
        initComponents();
        try{
           ActivityDataPriority.MEDIUM_COLOR_START = Color.MAGENTA;
            //        ActivityDataPriority.vertical = false;
//            panel.addCoordinateListener(this);
            panel.setStartRange(new Date(113, 9, 5, 9, 0, 0).getTime());
            panel.setEndRange(new Date(113, 9, 5, 12, 0, 0).getTime());
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
          panel.addDataBar(new ActivityContainerSupporter("Playing"));
//
            LinearDataSupporter s = new LinearDataSupporter("Pressure");
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
            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 0, 0), new Date(113, 9, 5, 10, 10, 0),new ActivityDataPriority(ActivityDataPriority.Priority.MEDIUM, false)), false);
            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 15, 0), new Date(113, 9, 5, 10, 30, 0),new ActivityDataPriority(ActivityDataPriority.Priority.MEDIUM, false)), false);
            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 33, 0), new Date(113, 9, 5, 11, 5, 0),new ActivityDataPriority(ActivityDataPriority.Priority.MEDIUM, false)), false);
            
            ActivityContainerSupporter acs = new ActivityContainerSupporter("Playing");
            panel.addDataBar(acs);
            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 0, 0), new Date(113, 9, 5, 10, 10, 0),new ActivityDataPriority(ActivityDataPriority.Priority.LOW, false)),false);
            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 15, 0), new Date(113, 9, 5, 10, 30, 0),new ActivityDataPriority(ActivityDataPriority.Priority.HIGH, true)),false);
            panel.addActivityData(new ActivityDataSupporter("Playing", new Date(113, 9, 5, 10, 33, 0), new Date(113, 9, 5, 11, 5, 0),new ActivityDataPriority(ActivityDataPriority.Priority.LOW, false)),false);
            //        setContentPane(panel);
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/it/cnr/istc/icv/icons/GiraffWarning48.png"));
            ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/it/cnr/istc/icv/icons/physio48s.png"));
            ActivityContainerSupporter events = new ActivityContainerSupporter("Events");
            panel.addDataBar(events);
            panel.addICVAnnotation(new ICVAnnotation("Events", new Date(113, 9, 5, 10, 0, 0).getTime(), imageIcon.getImage(),false));
            panel.addICVAnnotation(new ICVAnnotation("Events", new Date(113, 9, 5, 9, 15, 0).getTime(), "Fault Detection", imageIcon.getImage(),false));
//            panel.addICVAnnotation(new ICVAnnotation("Events", new Date(113, 9, 5, 8, 40, 0).getTime(), "Pressure Measurement", imageIcon2.getImage()));
            panel.addICVAnnotation(new ICVAnnotation("Events", new Date(113, 9, 5, 8, 45, 0).getTime(), "Weight Measurement", imageIcon2.getImage(),false));
//            
            
//            ActivityContainerSupporter events2 = new ActivityContainerSupporter("Events2");
//            panel.addDataBar(events2);
//            
//            ActivityContainerSupporter events3 = new ActivityContainerSupporter("Events3");
//            panel.addDataBar(events3);
//            
//            ActivityContainerSupporter events4 = new ActivityContainerSupporter("Events4");
//            panel.addDataBar(events4);
            
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

        jScrollPane1 = new javax.swing.JScrollPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
