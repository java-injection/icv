/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.timeselector.smart;

import it.cnr.istc.icv.engine.listeners.CoordinateListener;
import it.cnr.istc.icv.engine.listeners.ZoomListener;
import it.cnr.istc.icv.engine.logic.MixedPanelInterface;
import it.cnr.istc.icv.logic.ContainerDataInterface;
import it.cnr.istc.icv.logic.ICVAnnotation;
import it.cnr.istc.icv.timeselector.TimeLevel;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Luca
 */
public class SmartTimeSelector extends javax.swing.JPanel implements MixedPanelInterface {

    private long beforeZoom_startRange = -1;
    private long beforeZoom_endRange = -1;
    private boolean containsZoomedChart = false;
    private List<CoordinateListener> coordinateListeners = new ArrayList<CoordinateListener>();
    public int TOP_MARGIN = 40;
    public int LEFT_MARGIN = 30;
    public int RIGHT_MARGIN = 40;
    public int BOTTOM_MARGIN = 5;
    public int LABEL_LENGHT = 0;
    public float X_AXIS_LABEL_GAB = 5;
    public int ARROW_MARGIN = 10;
    public int X_AXIS_DAY_HOUR_VERTICAL_GAP = 5;
    private int sX = 0;
    private int sY = 0;
    private int wX = 0;
    private int hY = 0;
    private int eX = 0;
    private int eY = 0;
    private int maxCharLabel = 30;
    private boolean dragged = false;
    private boolean op = false;
    private boolean duringZoom = false;

    private TimeLevel currentTimeLevel = TimeLevel.DAY;
    private Color backgroundChartColor = Color.WHITE;
    private Color arrowColor = Color.BLACK;

    private long startRange = 0;
    private long endRange = 0;

    private Color linesColor = new Color(180, 180, 180);
    private Color xAxisDataColor = Color.BLACK;
    private Color yAxisDataColor = Color.BLACK;
    private Color alternateColor = new Color(221, 238, 246);
    private boolean arrowHeaderMode = false;
    private boolean zoomEnable = true;

    private int dataMeasureLenght = 0;
    private int hourMeasureLenght = 0;

    private final static String dataMeasure = "88/88/8888";
    private final static String hourMeasure = "(88:88)";

    private volatile int startSelection = -1;
    private int endSelection = -1;
    private volatile List<ZoomListener> zummini = new ArrayList<ZoomListener>();
    public static final String INTERVAL_CHANGED = "interval changed";
    private PropertyChangeSupport pps = new PropertyChangeSupport(this);
    private Date startSquareDate = null;
    private Date endSquareDate = null;

    private boolean resizingRight = false;
    private boolean resizingLeft = false;
    private boolean resizeAll = false;
    private int movePivot = 0;
    private Rectangle2D mainSquare = null;
    private List<SmartIntervalListener> smartIntervalListeners = new ArrayList<SmartIntervalListener>();
    private List<ICVAnnotation> annotations = new ArrayList<ICVAnnotation>();
    private int startNightHour = 22;
    private int endNightHour = 9;
    private boolean nightVisible = true;

    /**
     * Creates new form TimeSelector
     */
    public SmartTimeSelector() {
        initComponents();

    }

    public int getStartNightHour() {
        return startNightHour;
    }

    public void setStartNightHour(int startNightHour) {
        this.startNightHour = startNightHour;
    }

    public void setEndNightHour(int endNightHour) {
        this.endNightHour = endNightHour;
    }

    public int getEndNightHour() {
        return endNightHour;
    }

    public boolean isNightVisible() {
        return nightVisible;
    }

    public void setNightVisible(boolean nightVisible) {
        this.nightVisible = nightVisible;
    }
    
    public void addICVAnnotation(ICVAnnotation ann) {
        this.annotations.add(ann);
    }

    public void clearAnnotations() {
        this.annotations.clear();
    }

    public void addSmartIntervalListener(SmartIntervalListener listener) {
        this.smartIntervalListeners.add(listener);
    }

    public void addPropertySupportListener(PropertyChangeListener p) {
        pps.addPropertyChangeListener(p);
    }

    public void removePropertySupportListener(PropertyChangeListener p) {
        pps.removePropertyChangeListener(p);

    }

    public void setZoomEnable(boolean zoomEnable) {
        this.zoomEnable = zoomEnable;
    }

    public Color getLinesColor() {
        return linesColor;
    }

    public int getsX() {
        return sX;
    }

    public int getsY() {
        return sY;
    }

    public int getwX() {
        return wX;
    }

    public int gethY() {
        return hY;
    }

    public int geteX() {
        return eX;
    }

    public int geteY() {
        return eY;
    }

    public void initRedZone() {
        long data_range = endRange - startRange;
        long startSquare = startRange + (data_range / 4);
//            System.out.println("startSquare = "+startSquare);
        this.startSquareDate = new Date(startSquare);
//            System.out.println("startDate -> "+startSquareDate);
        long endSquare = endRange - (data_range / 4);
        this.endSquareDate = new Date(endSquare);

        System.out.println("init red zone s: " + this.startSquareDate);
        System.out.println("init red zone e: " + this.endSquareDate);

//        Date ssssq = new Date(startSquareDate.getTime() - data_range);
//        startSquareDate = ssssq.before(new Date(startRange)) ? startSquareDate : ssssq;
//        endSquareDate = new Date(endSquareDate.getTime() - data_range);
//        System.out.println("start Selection = " + startSelection);
//        System.out.println("start Selection = " + endSelection);
//        startSelection = getXByDate(ssssq.before(new Date(startRange)) ? startSquareDate : ssssq);
//        endSelection = getXByDate(endSquareDate);
//        System.out.println("start Selection 2 = " + startSelection);
//        System.out.println("start Selection 2 = "+endSelection);
    }

    public Color getxAxisDataColor() {
        return xAxisDataColor;
    }

    public Color getyAxisDataColor() {
        return yAxisDataColor;
    }

    public Color getAlternateColor() {
        return alternateColor;
    }

    public int getDataMeasureLenght() {
        return dataMeasureLenght;
    }

    public int getHourMeasureLenght() {
        return hourMeasureLenght;
    }

    public void setStartRange(long startRange) {
        this.startRange = startRange;
    }

    public void setEndRange(long endRange) {
        this.endRange = endRange;
    }

    public long getStartRange() {
        return startRange;
    }

    public long getEndRange() {
        return endRange;
    }

    public Date getStartSquareDate() {
        return startSquareDate;
    }

    public Date getEndSquareDate() {
        return endSquareDate;
    }

    public boolean isArrowHeaderMode() {
        return arrowHeaderMode;
    }

    public void adjustRange() {
        GregorianCalendar calendar = new GregorianCalendar();
        switch (currentTimeLevel) {
            case DAY:

                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                this.startRange = calendar.getTime().getTime();
                this.endRange = calendar.getTime().getTime() + 1000l * 60l * 60l * 24l;
                break;
            case HOUR:
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                this.startRange = calendar.getTime().getTime();
                this.endRange = calendar.getTime().getTime() + 1000l * 60l * 60l;
                break;
            case MONTH:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                this.startRange = calendar.getTime().getTime();
//                calendar.add(Calendar.MONTH,1);
                System.out.println("day of month: " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                calendar.add(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                this.endRange = calendar.getTime().getTime();

                //+ 1000*60*60 * 24 * calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                break;
            case MONTH3:
                calendar.set(Calendar.DAY_OF_MONTH, 0);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.add(Calendar.MONTH, -3);
                this.startRange = calendar.getTime().getTime();
                calendar.add(Calendar.MONTH, 3);
                this.startRange = calendar.getTime().getTime();
                break;
            case WEEK:
                // "calculate" the start date of the week
                Calendar first = (Calendar) calendar.clone();
                first.add(Calendar.DAY_OF_WEEK,
                        first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));
                // and add six days to the end date
                Calendar last = (Calendar) first.clone();
                last.add(Calendar.DAY_OF_YEAR, 6);
                this.startRange = first.getTime().getTime();
                this.endRange = last.getTime().getTime();
                break;
            default:
                System.out.println("default");
                this.startRange = 0;
                this.endRange = 0;

        }
    }

    public void setBackgroundChartColor(Color backgroundChartColor) {
        this.backgroundChartColor = backgroundChartColor;
    }

    public Color getBackgroundChartColor() {
        return backgroundChartColor;
    }

    public Color getArrowColor() {
        return arrowColor;
    }

    public void setArrowColor(Color arrowColor) {
        this.arrowColor = arrowColor;
    }

    public void nex() {
        this.currentTimeLevel = this.currentTimeLevel.next();
        adjustRange();
    }

    public void prev() {
        this.currentTimeLevel = this.currentTimeLevel.prev();
        adjustRange();
    }

    public TimeLevel getCurrentTimeLevel() {
        return currentTimeLevel;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        double width = this.getBounds().getWidth();
        double height = this.getBounds().getHeight();

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

//        sX = LEFT_MARGIN;
//        sY = TOP_MARGIN;
//        wX = (int) width - (RIGHT_MARGIN + LEFT_MARGIN); //larghezza del quadrato bianco
//        hY = (int) height - BOTTOM_MARGIN - TOP_MARGIN; //altezza
//        eX = sX + wX;
//        eY = sY + hY;
        Rectangle2D rect = new Rectangle2D.Double(sX, sY, wX, hY);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(backgroundChartColor);
        g2.fill(rect);

        g2.setPaint(arrowColor);
        Stroke drawingStrokeG = new BasicStroke(2);
        g2.setStroke(drawingStrokeG);
        g2.drawLine(sX + wX - 15, (int) (height + TOP_MARGIN) / 2 - 5, sX + wX - ARROW_MARGIN, (int) (height + TOP_MARGIN) / 2);
        g2.drawLine(sX + wX - 15, (int) (height + TOP_MARGIN) / 2 + 5, sX + wX - ARROW_MARGIN, (int) (height + TOP_MARGIN) / 2);
        Stroke drawingStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1f, 0f, 3f}, 0);
        g2.setStroke(drawingStroke);
        g2.drawLine(sX, (int) (height + TOP_MARGIN) / 2, sX + wX - ARROW_MARGIN, (int) (height + TOP_MARGIN) / 2);

        long data_range = endRange - startRange;

        // x axis
        Date dateS = new Date(startRange);
        Date dateE = new Date(endRange);

        Stroke drawingStroke2 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2.setStroke(drawingStroke2);

//        int rightMargin = dataMeasureLenght / 2;
//        int labelStartX = sX - rightMargin;
//        int lineStart = sX;
//        if (RIGHT_MARGIN < rightMargin) {
//            RIGHT_MARGIN = rightMargin;
//        }
        Font ff = new Font("SansSerif", Font.PLAIN, 10);
        g2.setPaint(xAxisDataColor);
        g2.setFont(ff);
        //START RANGE

//        Font f = new Font("SansSerif", Font.PLAIN, 12);
//        FontMetrics fm = this.getFontMetrics(f);
//        System.out.println("START DATE : " + dateS);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        g2.drawString(format.format(dateS), labelStartX, sY - (fm.getHeight() * 2));
        SimpleDateFormat h_format = new SimpleDateFormat("(HH:mm)");
        g2.drawString(h_format.format(dateS), sX - (hourMeasureLenght / 2), sY - (fm.getHeight()));
        g2.setPaint(linesColor);
        g2.drawLine(lineStart, sY - (fm.getHeight() / 2) - 1, lineStart, sY);
        //END RANGE

        g2.setPaint(xAxisDataColor);
        g2.drawString(format.format(dateE), eX - (dataMeasureLenght / 2), sY - (fm.getHeight() * 2));
        g2.setPaint(linesColor);
        g2.drawLine(eX, sY - (fm.getHeight() / 2) - 1, eX, sY);
        g2.setPaint(xAxisDataColor);
        g2.drawString(h_format.format(dateE), eX - (hourMeasureLenght / 2), sY - (fm.getHeight()));

        float labels = (wX - dataMeasureLenght) / (dataMeasureLenght + 20);
        float gap = (wX - dataMeasureLenght) % (dataMeasureLenght + 20);
        float rGap = gap / (labels + 1);
        X_AXIS_LABEL_GAB = rGap + 20;
        int hStart = sX - (hourMeasureLenght / 2);
        for (int i = 0; i < labels; i++) {
            lineStart += dataMeasureLenght + X_AXIS_LABEL_GAB;
            Date date = getDateByX(lineStart);
            labelStartX += (dataMeasureLenght + X_AXIS_LABEL_GAB);
            hStart += dataMeasureLenght + X_AXIS_LABEL_GAB;
            String ddd = "-";
            if (date == null) {
//                        System.out.println("MIXED icv duplication issue: init failed, generated emergency date");
                date = new Date(1492, 9, 12);
            }
            ddd = format.format(date);
            g2.setPaint(xAxisDataColor);
            g2.drawString(ddd, labelStartX, sY - fm.getHeight() * 2);
            g2.setPaint(linesColor);
            g2.drawLine(lineStart, sY - (fm.getHeight() / 2) - 1, lineStart, sY);
            g2.setPaint(xAxisDataColor);
            g2.drawString(h_format.format(date), hStart, sY - (fm.getHeight()));
            g2.setPaint(linesColor);
            g2.drawLine(lineStart, sY, lineStart, hY + TOP_MARGIN);

        }
        g2.drawLine(eX, sY, eX, hY + TOP_MARGIN); //ultima
        g2.drawLine(sX, sY, sX, hY + TOP_MARGIN); //prima

        //********************** N I G H T S *******************************
        if (nightVisible) {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(dateS);
            gc.add(Calendar.DAY_OF_MONTH, -1);
            gc.set(Calendar.HOUR_OF_DAY, this.startNightHour);
            while (!gc.getTime().after(dateE)) {
//                Graphics2D g2 = (Graphics2D) g.create();
                int x1_Night = getXByDate(gc.getTime());
                gc.setTime(gc.getTime());
                gc.add(Calendar.DAY_OF_MONTH, 1);
                gc.set(Calendar.HOUR_OF_DAY, this.endNightHour);
                int x2_Night = getXByDate(gc.getTime());
                Rectangle2D zoom = new Rectangle2D.Double(sX + x1_Night, sY, x2_Night - x1_Night, hY);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                        0.2f));
                g2.setPaint(Color.BLACK);
                g2.fill(zoom);
                gc.set(Calendar.HOUR_OF_DAY, this.startNightHour);
            }
        }
            //******************************************************************  

         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                        1f));

        //ANNOTATIONS
        for (ICVAnnotation ann : annotations) {
            int x = sX + (int) (((ann.getWhen() - startRange) * wX) / data_range);
//            int disc = (int) hY > 24 ? 24 : (int) hY;
            
            int disc = (int) ann.getSize() / 2;
            g2.drawImage(ann.getImage(), x - disc, (hY / 2) - disc, (int) disc * 2, (int) disc * 2, null);
        }

        if (startRange != 0 && endRange != 0) {
//            System.out.println("start Range = "+new Date(startRange));
//            long startSquare = startRange + (data_range / 4);
////            System.out.println("startSquare = "+startSquare);
//            this.startSquareDate = new Date(startSquare);
////            System.out.println("startDate -> "+startSquareDate);
//            long endSquare = endRange - (data_range / 4);
//            this.endSquareDate = new Date(endSquare);
//            System.out.println("END SQUARE = "+this.endSquareDate);
            int ddd = getXByDate(this.endSquareDate) - getXByDate(this.startSquareDate);
            mainSquare = new Rectangle2D.Double(sX + getXByDate(this.startSquareDate), sY, ddd, hY);
            g2.setPaint(new Color(1f, 0f, 0f, 0.4f));
            g2.fill(mainSquare);
            for (SmartIntervalListener smartIntervalListener : smartIntervalListeners) {
                smartIntervalListener.currentSquare(getDateByX(sX + getXByDate(this.startSquareDate)), getDateByX(ddd + sX + getXByDate(this.startSquareDate)));
            }
        }

    }

    public Date getDateByX(int x) {
        long dd = ((endRange - startRange) / (eX - sX)) * (x - sX) + startRange;
        return new Date(dd);

    }

    public int getXByDate(Date date) {
        long data_range = endRange - startRange;
        //(int) (((time - startRange) * wX) / data_range);
        int startDataX = 0;
        try {
            startDataX = (int) (((date.getTime() - startRange) * wX) / data_range);
            if (startDataX < 0) {
                startDataX = 0;
            }
        } catch (Exception ex) {
            return startDataX;
        }
//        if (startDataX + sX > eX) {
//            return -1;
//        }
        return startDataX;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

        int ssX = getXByDate(startSquareDate) + sX;
        int eeX = getXByDate(endSquareDate) + sX;

        if ((evt.getX() > ssX - 5 && evt.getX() < ssX + 5)) {
            for (CoordinateListener coordinateListener : coordinateListeners) {
                coordinateListener.forceFromTo(true);
            }
            this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            if (isInChartArea(evt.getX(), evt.getY())) {
                for (CoordinateListener coordinateListener : coordinateListeners) {
                    coordinateListener.currentDate(this.getDateByPoint(evt.getPoint()));
                }
            } else {
                for (CoordinateListener coordinateListener : coordinateListeners) {
                    coordinateListener.currentDate(null);
                }
            }

        } else if ((evt.getX() > eeX - 5 && evt.getX() < eeX + 5)) {
            for (CoordinateListener coordinateListener : coordinateListeners) {
                coordinateListener.forceFromTo(true);
            }
            this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            if (isInChartArea(evt.getX(), evt.getY())) {
                for (CoordinateListener coordinateListener : coordinateListeners) {
                    coordinateListener.currentDate(this.getDateByPoint(evt.getPoint()));
                }
            } else {
                for (CoordinateListener coordinateListener : coordinateListeners) {
                    coordinateListener.currentDate(null);
                }
            }

        } else if ((evt.getX() > ssX + 5 && evt.getX() < eeX - 5)) {
            for (CoordinateListener coordinateListener : coordinateListeners) {
                coordinateListener.forceFromTo(true);
            }

//             Date pivotDate = getDateByX(movePivot);
//            Date mouseDate = getDateByX(evt.getX());
//            long delta = pivotDate.getTime() - mouseDate.getTime();
//            
////            long delta = endSquareDate.getTime() - startSquareDate.getTime();
////                System.out.println("movepivot = "+movePivot);
////            System.out.println("getX - >" + evt.getX());
//////                System.out.println("delta = "+delta);
////            System.out.println("X : " + (getXByDate(startSquareDate) + sX));
////                int newX = getXByDate(startSquareDate)+delta;
////                System.out.println("newX = "+newX);
//            if (delta > 0) {
////                System.out.println("+++++++");
//                Date ssssq = new Date(startSquareDate.getTime() - delta);
//                startSquareDate = ssssq.before(new Date(startRange)) ? startSquareDate : ssssq;
//                endSquareDate = new Date(endSquareDate.getTime() - delta);
            startSelection = getXByDate(startSquareDate) + sX;
            endSelection = getXByDate(endSquareDate) + sX;
//            } else {
////                System.out.println("-------");
//                startSquareDate = new Date(startSquareDate.getTime() - delta);
//                Date eeeed = new Date(endSquareDate.getTime() - delta);
//                endSquareDate = eeeed.after(new Date(endRange)) ? endSquareDate : eeeed;
//                startSelection = getXByDate(startSquareDate);
//                endSelection = getXByDate(endSquareDate);
////                    endSquareDate = new Date(endSquareDate.getTime() - delta);
//            }

            this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            if (isInChartArea(evt.getX(), evt.getY())) {
                for (CoordinateListener coordinateListener : coordinateListeners) {
                    coordinateListener.currentDate(this.getDateByPoint(evt.getPoint()));
                }
            } else {
                for (CoordinateListener coordinateListener : coordinateListeners) {
                    coordinateListener.currentDate(null);
                }
            }
        } else {
            this.setCursor(Cursor.getDefaultCursor());
        }

    }//GEN-LAST:event_formMouseMoved

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        int ssX = getXByDate(startSquareDate) + sX;
        int eeX = getXByDate(endSquareDate) + sX;

        if ((evt.getX() > ssX - 5 && evt.getX() < ssX + 5)) {
            this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            resizingLeft = true;
        } else if ((evt.getX() > eeX - 5 && evt.getX() < eeX + 5)) {
            resizingRight = true;
            this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
        } else {
            if ((evt.getX() > ssX + 5 && evt.getX() < eeX - 5)) {
                this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                resizeAll = true;
                movePivot = evt.getX();
            } else {
                this.setCursor(Cursor.getDefaultCursor());
            }
        }


    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        this.setCursor(Cursor.getDefaultCursor());
        if (evt.isPopupTrigger()) {
            if (!isInChartArea(evt.getX(), evt.getY())) {
//                startSelection = -1;
//                endSelection = -1;
                dragged = false;
                resizingLeft = false;
                resizingRight = false;
                movePivot = 0;
                resizeAll = false;
                for (CoordinateListener coordinateListener : coordinateListeners) {
                    coordinateListener.forceFromTo(true);
                }
                return;
            }
            System.out.println("POPUPPO TRIGGERO");
        } else {
            System.out.println("ELSE");
//            boolean inChartArea = isInChartArea(evt.getX(), evt.getY());
//            getDateByPoint(evt.getPoint());
//            if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
////                System.out.println("ZUMMI");
//                if (isZoomEnable()) {
//                    resetZoom();
//                    return;
//                }
//                return;
//            }
//            System.out.println("non 2 click");

//            if (dragged) {
////                System.out.println("managgio zoom");
//                manageZoom();
//            }
//            startSelection = -1;
//            endSelection = -1;
            dragged = false;
            resizingLeft = false;
            resizingRight = false;
            movePivot = 0;
            resizeAll = false;
            this.repaint();
        }
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
//        boolean inChartArea = isInChartArea(evt.getX(), evt.getY());
//        System.out.println("DRAGGED: " + inChartArea);
//        dragged = true;
        int ssX = getXByDate(startSquareDate) + sX;
        int eeX = getXByDate(endSquareDate) + sX;
        if (resizingLeft) {

            for (CoordinateListener coordinateListener : coordinateListeners) {
                coordinateListener.forceFromTo(true);
            }
            if (evt.getX() < sX || evt.getX() >= eeX) {
//                System.out.println("BLOCK !!!");
                return;
            } else {
                //legal
                startSquareDate = getDateByX(evt.getX());
//                if (isInChartArea(evt.getX(), evt.getY())) {
//                    for (CoordinateListener coordinateListener : coordinateListeners) {
//                        coordinateListener.currentDate(this.getDateByPoint(evt.getPoint()));
//                    }
//                } else {
//                    for (CoordinateListener coordinateListener : coordinateListeners) {
//                        coordinateListener.currentDate(null);
//                    }
//                }
                this.repaint();
            }

        } else if (resizingRight) {
            for (CoordinateListener coordinateListener : coordinateListeners) {
                coordinateListener.forceFromTo(true);
            }
            if (evt.getX() < ssX || evt.getX() >= eX) {
//                System.out.println("BLOCK !!!");
                return;
            } else {
                //legal
                endSquareDate = getDateByX(evt.getX());
                this.repaint();
            }
//            if (isInChartArea(evt.getX(), evt.getY())) {
//                for (CoordinateListener coordinateListener : coordinateListeners) {
//                    coordinateListener.currentDate(this.getDateByPoint(evt.getPoint()));
//                }
//            } else {
//                for (CoordinateListener coordinateListener : coordinateListeners) {
//                    coordinateListener.currentDate(null);
//                }
//            }
        } else if (resizeAll) {

            for (CoordinateListener coordinateListener : coordinateListeners) {
                coordinateListener.forceFromTo(true);
            }
            Date pivotDate = getDateByX(movePivot);
            Date mouseDate = getDateByX(evt.getX());
            long delta = pivotDate.getTime() - mouseDate.getTime();
//                System.out.println("movepivot = "+movePivot);
//            System.out.println("getX - >" + evt.getX());
////                System.out.println("delta = "+delta);
//            System.out.println("X : " + (getXByDate(startSquareDate) + sX));
//                int newX = getXByDate(startSquareDate)+delta;
//                System.out.println("newX = "+newX);
            if (delta > 0) {
//                System.out.println("+++++++");
                Date ssssq = new Date(startSquareDate.getTime() - delta);
                startSquareDate = ssssq.before(new Date(startRange)) ? startSquareDate : ssssq;
                endSquareDate = new Date(endSquareDate.getTime() - delta);
                startSelection = getXByDate(startSquareDate);
                endSelection = getXByDate(endSquareDate);
            } else {
//                System.out.println("-------");
                startSquareDate = new Date(startSquareDate.getTime() - delta);
                Date eeeed = new Date(endSquareDate.getTime() - delta);
                endSquareDate = eeeed.after(new Date(endRange)) ? endSquareDate : eeeed;
                startSelection = getXByDate(startSquareDate);
                endSelection = getXByDate(endSquareDate);
//                    endSquareDate = new Date(endSquareDate.getTime() - delta);
            }

            movePivot = evt.getX();
//                endSquareDate = getDateByX(evt.getX()+delta);

            this.repaint();

        }
        startSelection = getXByDate(startSquareDate) + sX;
        endSelection = getXByDate(endSquareDate) + sX;

        if (isInChartArea(evt.getX(), evt.getY())) {
            for (CoordinateListener coordinateListener : coordinateListeners) {
                coordinateListener.currentDate(startSquareDate);
            }
        } else {
            for (CoordinateListener coordinateListener : coordinateListeners) {
                coordinateListener.currentDate(null);
            }
        }

//        if (inChartArea) {
//            if (zoomEnable) {
////            System.out.println("x = " + evt.getX());
////            System.out.println("start selection = " + startSelection);
////            System.out.println("end selection = " + endSelection);
//                if (evt.getX() >= endSelection) {
////                System.out.println("                                            verso destra");
//                    endSelection = evt.getX();
//                    op = false;
//                } else {
//
//                    if (evt.getX() < endSelection && evt.getX() > startSelection) {
//                        if (op) {
////                            System.out.println("                                torno indietro sinistra verso destra");
//                            startSelection = evt.getX();
//                        } else {
////                            System.out.println("                                torno indietro destra-sinistra");
//                            endSelection = evt.getX();
//                            op = false;
//                        }
//                    } else if (evt.getX() < startSelection) {
////                        System.out.println("                                        oltre la partenza");
//                        startSelection = evt.getX();
//                        op = true;
//                    }
//                }
//            }
//
//        }
    }//GEN-LAST:event_formMouseDragged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public int getStartSelection() {
        return this.startSelection;
    }

    @Override
    public int getEndSelection() {
        return this.endSelection;
    }

    @Override
    public void setEndSelection(int endSelection) {
        this.endSelection = endSelection;
    }

    @Override
    public boolean isZoomEnable() {
        return zoomEnable;
    }

    @Override
    public void addCoordinateListener(CoordinateListener listener) {
        this.coordinateListeners.add(listener);
    }

    @Override
    public Date getStartDataSelection() {
        long data_range = endRange - startRange;
        return new Date(startRange + ((startSelection - sX) * data_range) / wX);
    }

    @Override
    public Date getEndDataSelection() {
        long data_range = endRange - startRange;
        return new Date(startRange + ((endSelection - sX) * data_range) / wX);
    }

    public boolean isInThisArea(int x, int y, Rectangle rect) {
        return x >= rect.x && x <= (rect.x + rect.width) && y >= rect.y && y <= (rect.y + rect.height) ? true : false;
    }

    private boolean isInChartArea(int x, int y) {
        return x >= sX && x <= eX && y >= sY && y <= eY ? true : false;
    }

    private boolean isInSquareArea(int x, int y) {
        int ssX = getXByDate(startSquareDate) + sX;
        int eeX = getXByDate(endSquareDate) + sX;
        return x >= ssX && x <= eeX && y >= sY && y <= eY ? true : false;
    }

    @Override
    public void resetZoom() {
        if (beforeZoom_endRange == -1 && beforeZoom_startRange == -1) {
            repaint();
            return;
        }
        this.startRange = beforeZoom_startRange;
        this.endRange = beforeZoom_endRange;
        this.beforeZoom_endRange = -1;
        this.beforeZoom_startRange = -1;
        this.containsZoomedChart = false;
        for (ZoomListener zoomListener : zummini) {
            zoomListener.zoomResetted();
            zoomListener.zoomNewInterval(new Date(this.startRange), new Date(this.endRange));
        }
        repaint();
    }

    @Override
    public Date getDateByPoint(Point point) {
        int x = point.x;
        int y = point.y;
        if (!isInChartArea(x, y)) {
            return null;
        } else {
            long dd = ((endRange - startRange) / (eX - sX)) * (x - sX) + startRange;
            return new Date(dd);
        }
    }

    @Override
    public void manageZoom() {
        System.out.println("almost start zooming: " + zummini.size());
        System.out.println("managing zoom");
        if (!isZoomEnable()) {
            System.out.println("RETURN !");
            return;
        }
        long data_range = endRange - startRange;
//        System.out.println("Start selection = " + new Date(startRange + ((startSelection - sX) * data_range) / wX));
//        System.out.println("End selection = " + new Date(startRange + ((endSelection - sX) * data_range) / wX));

        if (this.beforeZoom_endRange == -1 && this.beforeZoom_startRange == -1) {
            this.beforeZoom_endRange = endRange;
            this.beforeZoom_startRange = startRange;
        }
        endRange = startRange + ((endSelection - sX) * data_range) / wX;

        startRange = startRange + ((startSelection - sX) * data_range) / wX;
        this.containsZoomedChart = true;
        System.out.println("almost end zooming: " + zummini.size());
        for (ZoomListener zoomListener : zummini) {
            zoomListener.zoomSelected();
            System.out.println("SPAMMING");
            zoomListener.zoomNewInterval(new Date(startRange), new Date(endRange));
        }

    }

    @Override
    public void addZoomListener(ZoomListener listener) {
        System.out.println("ADDING ZOOM LISTENER!! " + listener.getClass().getCanonicalName());
        this.zummini.add(listener);
        System.out.println("this zoom listener size _> " + this.zummini.size());
    }

    public void selectAll() {
        this.startSquareDate = new Date(this.startRange);
        this.endSquareDate = new Date(this.endRange);
        this.repaint();
    }

    @Override
    public boolean isShowDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, ContainerDataInterface> getDataMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDatabarFromY(int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
