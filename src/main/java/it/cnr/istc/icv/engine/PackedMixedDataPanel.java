/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.engine;


import it.cnr.istc.icv.exceptions.TypeDataMismatchException;
import it.cnr.istc.icv.extra.MathUtil;
import it.cnr.istc.icv.test.ActivityContainerSupporter;
import it.cnr.istc.icv.test.ActivityDataSupporter;
import it.cnr.istc.icv.test.TesterFrame2;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class PackedMixedDataPanel extends javax.swing.JPanel {

    private final MixedDataPanel mixedDataPanel = new MixedDataPanel();
    boolean salvato = false;
    int dx = 0;
    int dy = 0;
    Point previousPoint = null;

    /**
     * Creates new form PackedMixedDataPanel
     */
    public PackedMixedDataPanel() {
        initComponents();
//        MyLayer<JPanel> layerUI = new ZoomLabeledLayer(mixedDataPanel);
//        JPanel containerP = new JPanel();
//        containerP.setLayout(new GridLayout(0, 1));
//        containerP.add(mixedDataPanel);

        mixedDataPanel.setBackgroundChartColor(Color.WHITE);
        mixedDataPanel.setBackground(Color.WHITE);

        mixedDataPanel.setStartRange(new Date(113, 9, 5, 8, 0, 0).getTime());
        mixedDataPanel.setEndRange(new Date(113, 9, 5, 12, 0, 0).getTime());

//            panel.setyAxisDataColor(Color.yellow);
//            panel.setxAxisDataColor(Color.yellow);
//            panel.set
        //        panel.setDataColor(new Color(0,146,29));
        mixedDataPanel.setZoomEnable(false);
//        MyJLayer<JPanel> jlayer = new MyJLayer<JPanel>(mixedDataPanel, layerUI);
//        containerP.add(jlayer);
//        this.jScrollPane1.setViewportView(containerP);

        this.jScrollPane1.setViewportView(mixedDataPanel);

        mixedDataPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

        });
        
        mixedDataPanel.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                
//                System.out.println("point y: "+e.getPoint().y);
//                System.out.println("MAX: "+jScrollPane1.getVerticalScrollBar().getMaximum());
//                System.out.println("MIN: "+jScrollPane1.getVerticalScrollBar().getMinimum());
                try {
                    if(!isGoingDown(e.getLocationOnScreen())){
                        System.out.println(MathUtil.ANSI_BLUE+"UP");
                        
                        int newX = jScrollPane1.getVerticalScrollBar().getValue()+4;
//                        System.out.println("newX : "+newX);
                        if(newX >= jScrollPane1.getVerticalScrollBar().getMaximum()){
                            System.out.println(MathUtil.ANSI_CYAN+"CORRECTION");
                            return;
//                            newX = jScrollPane1.getVerticalScrollBar().getMaximum();
                        }
                        jScrollPane1.getVerticalScrollBar().setValue(newX);
                    }else{
                        System.out.println(MathUtil.ANSI_RED+"DOWN");
                        int newX = jScrollPane1.getVerticalScrollBar().getValue()-4;
//                        System.out.println("newX : "+newX);
                        if(newX <= jScrollPane1.getVerticalScrollBar().getMinimum()){
                            System.out.println(MathUtil.ANSI_CYAN+"CORRECTION 2");
                            return;
//                            newX = jScrollPane1.getVerticalScrollBar().getMinimum();
                        }
                        jScrollPane1.getVerticalScrollBar().setValue(newX);
                    }
                } catch (Exception ex) {
//                    Logger.getLogger(PackedMixedDataPanel.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(MathUtil.ANSI_GREEN+"EQUAL");
                }
                
                
//                
//                            System.out.println("DRAGGGG");
//                Point myPoint = jScrollPane1.getViewport().getViewPosition();
//                
//                System.out.println("x MyPoint = "+myPoint.x);
//                System.out.println("y MyPoint = "+myPoint.y);
//
//                double new_x = myPoint.getX();
//                double new_y = myPoint.getY();
//
//                dx = e.getX() - dx;
//                dy = e.getY() - dy;
//                new_x += dx;
//                new_y += dy;
//                if (new_x <= 0 || new_y <= 0) {
//                    System.out.println(MathUtil.ANSI_PURPLE+" new x :" + new_x);
//                    System.out.println(MathUtil.ANSI_PURPLE+" new y :" + new_y);
//                    dx = e.getX();
//                dy = e.getY();
//                    return;
//                } else {
//                    System.out.println("new x :" + new_x);
//                    System.out.println("new y :" + new_y);
//                }
//                
//                myPoint.setLocation(new_x, new_y);
////                new_x = 0;
////                new_y = 0;
//                jScrollPane1.getViewport().setViewPosition(myPoint);
////                jScrollPane1.getVerticalScrollBar().setValue(new_y);
//
//                dx = e.getX();
//                dy = e.getY();
                mixedDataPanel.repaint();
            }
            
            
});

        try {
            mixedDataPanel.clear();

            ActivityContainerSupporter acs = new ActivityContainerSupporter("A1");
            ActivityContainerSupporter acs2 = new ActivityContainerSupporter("A2");
            ActivityContainerSupporter acs3 = new ActivityContainerSupporter("A3");
            ActivityContainerSupporter acs4 = new ActivityContainerSupporter("A4");
            ActivityContainerSupporter acs5 = new ActivityContainerSupporter("A5");
            ActivityContainerSupporter acs6 = new ActivityContainerSupporter("A6");
            ActivityContainerSupporter acs7 = new ActivityContainerSupporter("A7");
            ActivityContainerSupporter acs8 = new ActivityContainerSupporter("A8");
            ActivityContainerSupporter acs9 = new ActivityContainerSupporter("A9");
            

//            mixedDataPanel.addDataBar(acs);
//            for (ActivityInstance act_data : activityList) {
//                mixedDataPanel1.addActivityData(new ActivityDataSupporter(I18nTranslator.getInstance().translate(activityName), act_data.start, act_data.end), false);
//            }
//            ActivityContainerSupporter a1 = new ActivityContainerSupporter("A1");
            mixedDataPanel.addDataBar(acs);
            mixedDataPanel.addDataBar(acs2);
            mixedDataPanel.addDataBar(acs3);
            mixedDataPanel.addDataBar(acs4);
            mixedDataPanel.addDataBar(acs5);
            mixedDataPanel.addDataBar(acs6);
            mixedDataPanel.addDataBar(acs7);
            mixedDataPanel.addDataBar(acs8);
            mixedDataPanel.addDataBar(acs9);
            

            ActivityDataSupporter a1Data1 = new ActivityDataSupporter("A1", new Date(113, 9, 5, 10, 0, 0), new Date(113, 9, 5, 10, 20, 0));
            ActivityDataSupporter a1Data2 = new ActivityDataSupporter("A2", new Date(113, 9, 5, 10, 25, 0), new Date(113, 9, 5, 10, 35, 0));
            ActivityDataSupporter a1Data3 = new ActivityDataSupporter("A1", new Date(113, 9, 5, 10, 50, 0), new Date(113, 9, 5, 10, 52, 0));
            ActivityDataSupporter a1Data4 = new ActivityDataSupporter("A1", new Date(113, 9, 5, 11, 0, 0), new Date(113, 9, 5, 11, 20, 0));

            ActivityDataSupporter a1Data5 = new ActivityDataSupporter("A3", new Date(113, 9, 5, 10, 0, 0), new Date(113, 9, 5, 10, 20, 0));
            ActivityDataSupporter a1Data6 = new ActivityDataSupporter("A2", new Date(113, 9, 5, 10, 25, 0), new Date(113, 9, 5, 10, 35, 0));
            ActivityDataSupporter a1Data7 = new ActivityDataSupporter("A4", new Date(113, 9, 5, 10, 50, 0), new Date(113, 9, 5, 10, 52, 0));
            ActivityDataSupporter a1Data8 = new ActivityDataSupporter("A5", new Date(113, 9, 5, 11, 0, 0), new Date(113, 9, 5, 11, 20, 0));

            mixedDataPanel.addActivityData(a1Data1, false);
            mixedDataPanel.addActivityData(a1Data2, false);
            mixedDataPanel.addActivityData(a1Data3, false);
            mixedDataPanel.addActivityData(a1Data4, false);
            mixedDataPanel.addActivityData(a1Data5, false);
            mixedDataPanel.addActivityData(a1Data6, false);
            mixedDataPanel.addActivityData(a1Data7, false);
            mixedDataPanel.addActivityData(a1Data8, false);

            mixedDataPanel.repaint();
        } catch (TypeDataMismatchException ex) {
            Logger.getLogger(TesterFrame2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public MixedDataPanel getMixedDataPanel() {
        return mixedDataPanel;
    }
    
    public boolean isGoingDown(Point p) throws Exception{
        if(this.previousPoint == null){
            this.previousPoint = p;
        }
        if(p.y > this.previousPoint.y){
            this.previousPoint = p;
            return true;
        }
        if(p.y < this.previousPoint.y){
            this.previousPoint = p;
            return false;
        }else{
//            this.previousPoint = p;
            throw new Exception("equal");
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

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseReleased(evt);
            }
        });
        jScrollPane1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseDragged

    }//GEN-LAST:event_jScrollPane1MouseDragged

    private void jScrollPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MousePressed

//        }
    }//GEN-LAST:event_jScrollPane1MousePressed

    private void jScrollPane1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseReleased


    }//GEN-LAST:event_jScrollPane1MouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
