 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.engine;

import it.cnr.istc.icv.logic.ActivityDataExtendedInterface;
import it.cnr.istc.icv.logic.ActivityDataPriority;
import it.cnr.istc.icv.logic.ICVAnnotation;
import it.cnr.istc.icv.logic.ActivityDataInterface;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.GroupLayout;

/**
 *
 * @author Luca
 */
public class ActivityPanel extends javax.swing.JPanel {

    public static int TOP_MARGIN = 40;
    public static int LEFT_MARGIN = 20;
    public static int RIGHT_MARGIN = 20;
    public static int BOTTOM_MARGIN = 20;
    public static int LABEL_LENGHT = 0;
    public static float X_AXIS_LABEL_GAB = 5;
    public static int X_AXIS_DAY_HOUR_VERTICAL_GAP = 5;
    private transient static int sX = 0;
    private transient static int sY = 0;
    private transient static int wX = 0;
    private transient static int hY = 0;
    private transient static int eX = 0;
    private transient static int eY = 0;
    private transient static int startSelection = -1;
    private transient static int endSelection = -1;
    private long startRange = 0;
    private long endRange = 0;
    private long backup_startRange = 0;
    private long backup_endRange = 0;
    private boolean op = false;
    private Color backgroundChartColor = Color.WHITE;
//    private Color dataColor = Color.GREEN;
    private Color linesColor = new Color(180, 180, 180);
    private Color xAxisDataColor = Color.BLACK;
    private Color yAxisDataColor = Color.BLACK;
    private Color alternateColor = new Color(221, 238, 246);
    private Map<String, List<ActivityDataInterface>> activityDataMap = new HashMap<String, List<ActivityDataInterface>>();
    private Map<String, List<ICVAnnotation>> annotationMap = new HashMap<String, List<ICVAnnotation>>();
    private Map<String, Integer> dataCoordinateMap = new HashMap<String, Integer>();
    private Map<String, Boolean> activityDataVisibilityMap = new HashMap<String, Boolean>(); //take car of data visibility but the line will be visible 
    private Map<String, Boolean> activityVisibilityMap = new HashMap<String, Boolean>(); //take car of data visibility but the line will be visible 
    private Map<String, Boolean> annotationVisibilityMap = new HashMap<String, Boolean>();
    private final static String dataMeasure = "88/88/8888";
    private final static String hourMeasure = "(88:88)";
    private static int dataMeasureLenght = 0;
    private static int hourMeasureLenght = 0;
    private float bandWidhtPercentage = 0.5f;
    private boolean zoomEnable = true;
    private boolean dragged = false;

//    private Graphics2D currentGraphic = null;
    /**
     * Creates new form ActivityPanel
     */
    public ActivityPanel() {
        initComponents();
    }

    public void addICVAnnotation(ICVAnnotation annotation) {

        if (this.annotationMap.containsKey(annotation.getActivity())) {
            this.annotationMap.get(annotation.getActivity()).add(annotation);
        } else {
            this.annotationMap.put(annotation.getActivity(), new ArrayList<ICVAnnotation>());
            this.annotationMap.get(annotation.getActivity()).add(annotation);
        }
    }

    public void setAnnotationVisible(String activity, boolean visible) {
        this.annotationVisibilityMap.put(activity, visible);
    }

    public boolean isAnnotationVisible(String activity) {
        return this.annotationVisibilityMap.get(activity);
    }

    public void setDataLineVisible(String activity, boolean visible) {
        this.activityDataVisibilityMap.put(activity, visible);
    }

    public boolean isDataLineVisible(String activity) {
        return this.activityDataVisibilityMap.get(activity);
    }
    
    public void setActivityVisible(String activity, boolean visible) {
//        this.activityDataVisibilityMap.put(activity, visible);
        this.activityVisibilityMap.put(activity, visible);
    }

    public boolean isActivityVisible(String activity) {
        return this.activityVisibilityMap.get(activity);
    }

    public boolean isZoomEnable() {
        return zoomEnable;
    }

    public void setZoomEnable(boolean zoomEnable) {
        this.zoomEnable = zoomEnable;
    }

    public Color getAlternateColor() {
        return alternateColor;
    }

    public void setAlternateColor(Color alternateColor) {
        this.alternateColor = alternateColor;
    }

    public static int getStartSelection() {
        return startSelection;
    }

    public static void setStartSelection(int startSelection) {
        ActivityPanel.startSelection = startSelection;
    }

    public Color getyAxisDataColor() {
        return yAxisDataColor;
    }

    public void setyAxisDataColor(Color yAxisDataColor) {
        this.yAxisDataColor = yAxisDataColor;
    }

//    public Color getDataColor() {
//        return dataColor;
//    }
//
//    public void setDataColor(Color dataColor) {
//        this.dataColor = dataColor;
//    }
    public Color getxAxisDataColor() {
        return xAxisDataColor;
    }

    public void setxAxisDataColor(Color xAxisDataColor) {
        this.xAxisDataColor = xAxisDataColor;
    }

    public float getBandWidhtPercentage() {
        return bandWidhtPercentage;
    }

    public void setBandWidhtPercentage(float bandWidhtPercentage) {
        this.bandWidhtPercentage = bandWidhtPercentage;
    }

    public long getStartRange() {
        return startRange;
    }

    public void setStartRange(long startRange) {
        this.startRange = startRange;
        this.backup_startRange = startRange;
    }

    public long getEndRange() {
        return endRange;
    }

    public void setEndRange(long endRange) {
        this.endRange = endRange;
        this.backup_endRange = endRange;
    }

    public Color getLinesColor() {
        return linesColor;
    }

    public void setLinesColor(Color linesColor) {
        this.linesColor = linesColor;
    }

    public void addActivity(String activity) {
        this.activityDataMap.put(activity, new ArrayList<ActivityDataInterface>());
        this.activityDataVisibilityMap.put(activity, true);
        this.activityVisibilityMap.put(activity, true);
        this.annotationVisibilityMap.put(activity, true);
        manageLabels();
    }

    public void addActivityData(ActivityDataInterface data, boolean repaint) {
        List<ActivityDataInterface> get = this.activityDataMap.get(data.getSubname());
        if (get != null) {
            this.activityDataMap.get(data.getSubname()).add(data);
        }
        if (repaint) {
            repaint();
        }
    }

    public void resetZoom() {
        this.startRange = backup_startRange;
        this.endRange = backup_endRange;
        repaint();
    }

    private void manageLabels() {
        Set<String> keySet = this.activityDataMap.keySet();
        for (String activity : keySet) {
            Font f = new Font("SansSerif", Font.BOLD, 14);
            FontMetrics fm = this.getFontMetrics(f);
            int s_width = fm.stringWidth(activity);
            if (LABEL_LENGHT < s_width) {
                LABEL_LENGHT = s_width;
            }
        }
    }

    public void setBackgroundChartColor(Color backgroundChartColor) {
        this.backgroundChartColor = backgroundChartColor;
    }

    public Color getBackgroundChartColor() {
        return this.backgroundChartColor;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        double width = this.getBounds().getWidth();
        double height = this.getBounds().getHeight();


        try {
            sX = LEFT_MARGIN + LABEL_LENGHT;
            sY = TOP_MARGIN;
            Font f = new Font("SansSerif", Font.PLAIN, 10);
            FontMetrics fm = g.getFontMetrics(f);
            ((Graphics2D) g).setPaint(Color.BLACK);

            g.setFont(f);
            if (dataMeasureLenght == 0) {
                dataMeasureLenght = fm.stringWidth(dataMeasure);
            }
            if (hourMeasureLenght == 0) {
                hourMeasureLenght = fm.stringWidth(hourMeasure);
            }
            int rightMargin = dataMeasureLenght / 2;
            int labelStartX = sX - rightMargin;
            int lineStart = sX;
            if (RIGHT_MARGIN < rightMargin) {
                RIGHT_MARGIN = rightMargin;
            }



            wX = (int) width - (RIGHT_MARGIN * 2) - LABEL_LENGHT; //larghezza del quadrato bianco
            hY = (int) height - 2 * BOTTOM_MARGIN - TOP_MARGIN; //altezza
            eX = sX + wX;
            eY = sY + hY;

            Rectangle2D rect = new Rectangle2D.Double(sX, sY, wX, hY);
            Graphics2D g1 = (Graphics2D) g;
//        g1.draw(rect);
            g1.setPaint(backgroundChartColor);
            g1.fill(rect);

            //paint labels:
            Set<String> keySet = this.activityDataMap.keySet();
            Set<String> keySet2 = new HashSet<String>();
            for (String activity : keySet) {
//                System.out.println("activity blabla : "+activity);
                if(isActivityVisible(activity)){
//                    System.out.println("removing .. "+activity);
                    keySet2.add(activity);
                }
            }
            keySet = keySet2;
            int keysCount = keySet.size();
            int start_y = -1;

            int increment = (hY / keysCount);
            boolean col = false;
            for (String activity : keySet) {
//            System.out.println("====================== " + activity);
                Font ff = new Font("SansSerif", Font.BOLD, 14);
                FontMetrics fm2 = g.getFontMetrics(ff);
                g.setFont(ff);
                int s_width = fm2.stringWidth(activity);
                if (LABEL_LENGHT < s_width) {
                    LABEL_LENGHT = s_width;
                }
//            System.out.println("LEFT 0 -> " + LEFT_MARGIN);
//            System.out.println("text width 0 = " + s_width);
                if (sX < s_width) {
                    sX = LABEL_LENGHT + LEFT_MARGIN;
                    eX = sX + wX;
                }
//            System.out.println("LEFT -> " + LEFT_MARGIN);
//            System.out.println("text width = " + s_width);
                int act_x = sX - 10 - s_width;
                if (start_y == -1) {
//                System.out.println("height : "+fm.getHeight());
                    start_y = sY + (hY / (keysCount * 2));
                }
                this.dataCoordinateMap.put(activity, start_y);
                g1.setPaint(yAxisDataColor);
                g.drawString(activity, act_x, start_y);
                if (col) {
                    Rectangle2D rect1 = new Rectangle2D.Double(LEFT_MARGIN + LABEL_LENGHT, start_y - increment / 2, width - (RIGHT_MARGIN * 2) - LABEL_LENGHT, increment);
//        g1.draw(rect);
                    g1.setPaint(alternateColor);
                    g1.fill(rect1);
                }


                start_y += increment;
                col = !col;
            }

            //end painting labels



//        //paint data
            long data_range = endRange - startRange;

            // x axis



            Font ff = new Font("SansSerif", Font.PLAIN, 10);
            g1.setPaint(xAxisDataColor);
            g.setFont(ff);
            //START RANGE
            Date dateS = new Date(startRange);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
            g.drawString(format.format(dateS), labelStartX, sY - (fm.getHeight() * 2));
            SimpleDateFormat h_format = new SimpleDateFormat("(hh:mm)");
            g.drawString(h_format.format(dateS), sX - (hourMeasureLenght / 2), sY - (fm.getHeight()));
            g1.setPaint(linesColor);
            g.drawLine(lineStart, sY - (fm.getHeight() / 2) - 1, lineStart, sY);
            //END RANGE
            Date dateE = new Date(endRange);
            g1.setPaint(xAxisDataColor);
            g.drawString(format.format(dateE), eX - (dataMeasureLenght / 2), sY - (fm.getHeight() * 2));
            g1.setPaint(linesColor);
            g.drawLine(eX, sY - (fm.getHeight() / 2) - 1, eX, sY);
            g1.setPaint(xAxisDataColor);
            g.drawString(h_format.format(dateE), eX - (hourMeasureLenght / 2), sY - (fm.getHeight()));


            float labels = (wX - dataMeasureLenght) / (dataMeasureLenght + 20);
//        int labels = wX / dataMeasureLenght;
            float gap = (wX - dataMeasureLenght) % (dataMeasureLenght + 20);
            float rGap = gap / (labels + 1);
//            System.out.println("rGap " + rGap);
            X_AXIS_LABEL_GAB = rGap + 20;
//            System.out.println("intervalli = " + labels);
//            System.out.println("gap = " + gap);
//            System.out.println("x gap " + X_AXIS_LABEL_GAB);
//            System.out.println("data lenght : " + dataMeasureLenght);
//            lineStart += dataMeasureLenght + X_AXIS_LABEL_GAB;
            int hStart = sX - (hourMeasureLenght / 2);
//            System.out.println("=================================");
            for (int i = 0; i < labels; i++) {
                Date date = new Date(startRange);
                labelStartX += (dataMeasureLenght + X_AXIS_LABEL_GAB);
                hStart += dataMeasureLenght + X_AXIS_LABEL_GAB;

//                System.out.println("actual point: " + labelStartX);
//                System.out.println("punto prox : " + (labelStartX + dataMeasureLenght + X_AXIS_LABEL_GAB));
//                System.out.println("ex : " + eX);

                String ddd = format.format(date);
                g1.setPaint(xAxisDataColor);
                g.drawString(ddd, labelStartX, sY - fm.getHeight() * 2);
                g1.setPaint(linesColor);
                g.drawLine(lineStart, sY - (fm.getHeight() / 2) - 1, lineStart, sY);
                long xDate = (lineStart * data_range) / wX;
//            g.drawString(h_format.format(new Date(xDate+startRange)), labelStartX+(hourMeasureLenght/2)-((int)rGap), sY - (fm.getHeight()));
                g1.setPaint(xAxisDataColor);
                g.drawString(h_format.format(new Date(xDate + startRange)), hStart, sY - (fm.getHeight()));
                g1.setPaint(linesColor);
                g.drawLine(lineStart, sY, lineStart, hY + TOP_MARGIN);
                lineStart += dataMeasureLenght + X_AXIS_LABEL_GAB;
//            hStart+= dataMeasureLenght + X_AXIS_LABEL_GAB;

            }
            g.drawLine(lineStart, sY - (fm.getHeight() / 2) - 1, lineStart, sY);
            g.drawLine(lineStart, sY, lineStart, hY + TOP_MARGIN); //penultima
            g.drawLine(eX, sY, eX, hY + TOP_MARGIN); //ultima


            //paint data
            Set<String> activities = this.activityDataMap.keySet();
//        long data_range = endRange - startRange;
//        System.out.println("data  range -> " + data_range);
//        System.out.println("start range : " + startRange + " data: " + new Date(startRange));
//        System.out.println("end   range : " + endRange + " data: " + new Date(endRange));
            float bWidht = (hY / (keysCount * 2)) * bandWidhtPercentage;
            for (String activity : activities) {
                if(!isActivityVisible(activity)){
                    continue;
                }
//                System.out.println("analyzing act : "+activity);
                if (isDataLineVisible(activity)) {
                    for (ActivityDataInterface data : this.activityDataMap.get(activity)) {
//                System.out.println("inserting data: ");
//                System.out.println("activity : " + data.getName());
//                System.out.println("START : " + data.getStart() + "  long-> " + data.getStart().getTime());
//                System.out.println("END : " + data.getEnd() + "  long-> " + data.getEnd().getTime());
//                System.out.println("wx : " + wX);
                        int yData = this.dataCoordinateMap.get(activity);
//                long preSum = (data.getStart().getTime()-startRange)*wX;
//                System.out.println("pre sum = "+preSum);
//                int startDataX =(int) (preSum / data_range);
                        int startDataX = (int) (((data.getStart().getTime() - startRange) * wX) / data_range);
                        if (startDataX < 0) {
                            startDataX = 0;
                        }
                        if (startDataX + sX > eX) {
                            break;
                        }

                        int endDataX = (int) (((data.getEnd().getTime() - startRange) * wX) / data_range);
                        if (sX + endDataX > eX) {
                            endDataX = eX - sX;


                        }
//                if(endDataX < sX){
//                    break;
//                }
//                System.out.println("start Data X : " + startDataX);
                        //                System.out.println("end Data X : "+endDataX);
                        RoundRectangle2D.Float rRect = new RoundRectangle2D.Float(sX + startDataX, yData - bWidht, endDataX - startDataX, bWidht * 2, 10, 25);
//                Rectangle2D rect_data = new Rectangle2D.Double(sX + startDataX, yData - bWidht, endDataX - startDataX, bWidht*2);
                        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        if (data instanceof ActivityDataExtendedInterface) {
//                    System.out.println("DATA NAME : "+data.getName());
                            ActivityDataPriority dataPriority = ((ActivityDataExtendedInterface) data).getDataPriority();
                            if (dataPriority.isVertical()) {
                                GradientPaint gradientPaint = new GradientPaint(
                                        sX + startDataX,
                                        yData,
                                        dataPriority.getStartColor(),
                                        sX + endDataX,
                                        yData,
                                        dataPriority.getEndColor());
                                g1.setPaint(gradientPaint);
                            } else {
                                GradientPaint gradientPaint = new GradientPaint(
                                        (sX + endDataX - startDataX) / 2,
                                        yData - bWidht,
                                        dataPriority.getStartColor(),
                                        (sX + endDataX - startDataX) / 2,
                                        yData,
                                        dataPriority.getEndColor(),
                                        true);
                                g1.setPaint(gradientPaint);
                            }

                        } else {
                            GradientPaint gradientPaint = new GradientPaint(
                                    (sX + endDataX - startDataX) / 2,
                                    yData - bWidht,
                                    ActivityDataPriority.MEDIUM_COLOR_START,
                                    (sX + endDataX - startDataX) / 2,
                                    yData,
                                    ActivityDataPriority.MEDIUM_COLOR_END,
                                    true);
                            g1.setPaint(gradientPaint);
                        }
//                GradientPaint gradientPaint = new GradientPaint(
//                        sX+startDataX,
//                        yData,
//                        Color.RED,
//                        sX+endDataX,
//                        yData,
//                        Color.BLUE
//                );
//                GradientPaint gradientPaint = new GradientPaint(
//                        (sX + endDataX  - startDataX)/2,
//                        yData - bWidht,
//                        new Color(0,122,0),
//                        (sX + endDataX  - startDataX)/2,
//                        yData,
//                        Color.GREEN,
//                        true);
//                g1.setPaint(gradientPaint);
//                g1.setPaint(dataColor);
                        g1.fill(rRect);

                    }
                }

            }

            //end x axis
            
            //sdfsdf
            for (String activity : keySet) {
                if (!isAnnotationVisible(activity)) {
                    continue;
                }
                List<ICVAnnotation> annotations = this.annotationMap.get(activity);
                if (annotations != null) {
                    int yData = this.dataCoordinateMap.get(activity);
                    for (ICVAnnotation ann : annotations) {
                        int x = sX + (int) (((ann.getWhen() - startRange) * wX) / data_range);
                        int disc = (int)bWidht > 24 ? 24 : (int)bWidht;
                        g1.drawImage(ann.getImage(), x - disc, yData - disc, (int) disc * 2, (int) disc * 2, null);
                        if (ann.getDescription() != null && !ann.getDescription().isEmpty()) {
                            Font ff2 = new Font("SansSerif", Font.BOLD, 10);
                            FontMetrics fm3 = g.getFontMetrics(ff2);
                            g1.setPaint(Color.BLACK);
                            g.setFont(ff2);
                            int s_width = fm3.stringWidth(ann.getDescription());
                            g1.drawString(ann.getDescription(), x-s_width/2, yData + disc +10 );
                        }

                    }
                }

                //TO CONTINUE
            }
            //start ZOOM
            if (zoomEnable && startSelection != -1 && endSelection != -1) {

                Rectangle2D zoom = new Rectangle2D.Double(startSelection, sY, Math.abs(endSelection - startSelection), hY);
                g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                        0.3f));
                g1.setPaint(Color.BLUE);
                g1.fill(zoom);

            }
        } catch (ArithmeticException exc) {
            System.out.println("division by zero detected and ignored :) [double click]");
//            resetZoom();
        }

    }

    private boolean isInChartArea(int x, int y) {
//        System.out.println("in chart : x = " + x + ", y = " + y);
//        System.out.println("sx = " + sX);
//        System.out.println("ex = " + eX);
//        System.out.println("eY = " + sY);
//        System.out.println("sY = " + eY);

        return x >= sX && x <= eX && y >= sY && y <= eY ? true : false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void manageZoom() {
        System.out.println("Start range = " + new Date(startRange));
        long data_range = endRange - startRange;
        System.out.println("Start selection = " + new Date(startRange + ((startSelection - sX) * data_range) / wX));
        System.out.println("End selection = " + new Date(startRange + ((endSelection - sX) * data_range) / wX));

        endRange = startRange + ((endSelection - sX) * data_range) / wX;
        startRange = startRange + ((startSelection - sX) * data_range) / wX;


    }

    private void formMousePressed(MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        boolean inChartArea = isInChartArea(evt.getX(), evt.getY());
        if (inChartArea) {
            startSelection = evt.getX();
            endSelection = evt.getX();
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        boolean inChartArea = isInChartArea(evt.getX(), evt.getY());
        dragged = true;
        if (inChartArea) {
            System.out.println("x = " + evt.getX());
            System.out.println("start selection = " + startSelection);
            System.out.println("end selection = " + endSelection);
            if (evt.getX() >= endSelection) {
//                System.out.println("                                            verso destra");
                endSelection = evt.getX();
                op = false;
            } else {

                if (evt.getX() < endSelection && evt.getX() > startSelection) {
                    if (op) {
//                        System.out.println("                                torno indietro sinistra verso destra");
                        startSelection = evt.getX();
                    } else {
//                        System.out.println("                                torno indietro destra-sinistra");
                        endSelection = evt.getX();
                        op = false;
                    }
                } else if (evt.getX() < startSelection) {
//                    System.out.println("                                        oltre la partenza");
                    startSelection = evt.getX();
                    op = true;
                }
            }
//            Graphics2D g1 = (Graphics2D) this.getGraphics();
//            g1.clearRect(startSelection, sY, Math.abs(endSelection - startSelection), hY);
//            g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                    RenderingHints.VALUE_ANTIALIAS_ON);
//
//            System.out.println("rettangolo: x = " + startSelection + ", y = " + sY + " lunghezza: " + (Math.abs(endSelection - startSelection)));
//            Rectangle2D zoom = new Rectangle2D.Double(startSelection, sY, Math.abs(endSelection - startSelection), hY);
//            g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
//                    0.7f));
//            g1.setPaint(Color.CYAN);
//            g1.fill(zoom);

//            repaint();
//            this.paint(this.getGraphics());
        }
    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        boolean inChartArea = isInChartArea(evt.getX(), evt.getY());
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            resetZoom();
            return;
        }
        if (!dragged) {
            return;
        }
        manageZoom();
        startSelection = -1;
        endSelection = -1;
        dragged = false;
        this.repaint();

    }//GEN-LAST:event_formMouseReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
