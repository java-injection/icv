/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.engine;

import it.cnr.istc.icv.engine.listeners.CoordinateListener;
import it.cnr.istc.icv.engine.listeners.PopupMenuTriggerListener;
import it.cnr.istc.icv.engine.listeners.ZoomListener;
import it.cnr.istc.icv.engine.logic.MixedPanelInterface;
import it.cnr.istc.icv.engine.tdelogic.Event;
import it.cnr.istc.icv.engine.tdelogic.pst.ICVBooleanStateCondition;
import it.cnr.istc.icv.engine.tdelogic.pst.ICVCondition;
import it.cnr.istc.icv.exceptions.TypeDataMismatchException;
import it.cnr.istc.icv.logic.ActivityDataContainer;
import it.cnr.istc.icv.logic.ActivityDataExtendedInterface;
import it.cnr.istc.icv.logic.ActivityDataInterface;
import it.cnr.istc.icv.logic.ActivityDataPriority;
import it.cnr.istc.icv.logic.BooleanDataContainer;
import it.cnr.istc.icv.logic.ContainerDataInterface;
import it.cnr.istc.icv.logic.CumulativeDataContainer;
import it.cnr.istc.icv.logic.ICVAnnotation;
import it.cnr.istc.icv.logic.ICVAnnotationLink;
import it.cnr.istc.icv.logic.ICVArrow;
import it.cnr.istc.icv.logic.ICVMappableAnnotation;
import it.cnr.istc.icv.logic.ImpulsiveBooleanDataContainer;
import it.cnr.istc.icv.logic.LinearDataContainerInterface;
import it.cnr.istc.icv.logic.MultiStateContainerInterface;
import it.cnr.istc.icv.logic.TimeBooleanDataInterface;
import it.cnr.istc.icv.logic.TimeDataInterface;
import it.cnr.istc.icv.logic.TimeValueDataInterface;
import it.cnr.istc.icv.test.StateDataSupporter;
import it.cnr.istc.icv.test.TimeValueSupporterClass;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import static java.awt.event.InputEvent.BUTTON1_DOWN_MASK;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 *
 * @author Luca
 */
public class MixedDataPanel extends javax.swing.JPanel implements MixedPanelInterface {

    public int TOP_MARGIN = 40;
    public int LEFT_MARGIN = 30;
    public int RIGHT_MARGIN = 40;
    public int BOTTOM_MARGIN = 5;
    public int LABEL_LENGHT = 0;
    public float X_AXIS_LABEL_GAB = 5;
    public int X_AXIS_DAY_HOUR_VERTICAL_GAP = 5;
    private int sX = 0;
    private int sY = 0;
    private int wX = 0;
    private int hY = 0;
    private int eX = 0;
    private int eY = 0;
    private int maxCharLabel = 30;
    private volatile int startSelection = -1;
    private int endSelection = -1;
    private long startRange = 0;
    private long endRange = 0;
    private int lastX = -1;
    private int lastY = -1;
    private long backup_startRange = 0;
    private long backup_endRange = 0;
    private long beforeZoom_startRange = -1;
    private long beforeZoom_endRange = -1;
    private boolean op = false;
    private int minimumIncrement = 50;
    private Color backgroundChartColor = Color.WHITE;
    private Color linesColor = new Color(180, 180, 180);
    private Color xAxisDataColor = Color.BLACK;
    private Color yAxisDataColor = Color.BLACK;
    private Color alternateColor = new Color(221, 238, 246);
    protected HashMap<String, ContainerDataInterface> dataMap = new HashMap<String, ContainerDataInterface>();
    protected HashMap<String, Class<? extends ContainerDataInterface>> typeDataMap = new HashMap<String, Class<? extends ContainerDataInterface>>();
    protected HashMap<String, List<ICVAnnotation>> annotationMap = new HashMap<String, List<ICVAnnotation>>();
    protected HashMap<String, Integer> dataCoordinateMap = new HashMap<String, Integer>();
    protected HashMap<String, Boolean> dataDataVisibilityMap = new HashMap<String, Boolean>(); //take car of data visibility but the line will be visible 
    protected HashMap<String, Boolean> dataVisibilityMap = new HashMap<String, Boolean>(); //take car of data visibility but the line will be visible 
    protected HashMap<String, Boolean> annotationVisibilityMap = new HashMap<String, Boolean>();
    private final static String dataMeasure = "88/88/8888";
    private final static String hourMeasure = "(88:88)";
    private int dataMeasureLenght = 0;
    private int hourMeasureLenght = 0;
    private float bandWidhtPercentage = 0.3f; //grandezza della barra booleana rispetto alla databar 
    private boolean zoomEnable = true;
    private boolean dragged = false;
    private ZoomLayerUI zoomLayer = new ZoomLayerUI();
    public boolean repaintable = true;
    private BufferedImage bufferedImg = null;
    private BufferedImage headImg = null;
    private volatile boolean needBackup = false;
    private Color separatorLineColor = Color.BLACK;
    //          y coord , label
    private Map<Float, String> yValuesLinearMap = new HashMap<Float, String>();
    private PaintSupplier supplyColor = new PaintSupplier();
    private Map<String, Paint> subnamesColorMap = new HashMap<String, Paint>();
    // name of linear Data Bar
    private Map<String, Boolean> dotPaintMap = new HashMap<String, Boolean>();
    private boolean separatorVisible = true;
    private Color standarFalseColor = Color.RED;
    private Color standarTrueColor = Color.GREEN;
    private Map<String, Map<String, Color>> legendMap = new HashMap<String, Map<String, Color>>();
    private CIELabHelper cIELabHelper = new CIELabHelper();
    private Dimension backupPreferredSize = null;
    private int startNightHour = 22;
    private int endNightHour = 9;
    private boolean nightVisible = true;
    private List<CoordinateListener> coordinateListeners = new ArrayList<CoordinateListener>();
    private boolean handScrollEnbaled = false;
    private boolean containsZoomedChart = false;
    private List<ZoomListener> zoomListeners = new ArrayList<ZoomListener>();
    private boolean nowLineVisible = false;
    private boolean needToPaintOverHead = false;
    private int startVisibleY = 0;
    private List<PopupMenuTriggerListener> popupMenuTriggerListeners = new ArrayList<PopupMenuTriggerListener>();
    private List<ContainerDataInterface> orderedDatabars = new ArrayList<ContainerDataInterface>();
    private Map<String, List<AnnotationArea>> areasListMap = new HashMap<String, List<AnnotationArea>>();
    private List<ICVArrow> arrows = new ArrayList<ICVArrow>();
    private boolean arrowsVisible = true;
    private Map<Object, Point> idAnnotationCenterPointMap = new HashMap<Object, Point>();
    private List<ICVAnnotationLink> links = new ArrayList<ICVAnnotationLink>();
    private List<String> dividerUnderDatabarToHide = new ArrayList<String>();
    private boolean arrowHeaderMode = false;
    private Event mainEvent = null;
    private boolean areaPopupActive = false;
    private boolean annotationDraggable = false;
    private ICVAnnotation annotationToDrag = null;
    private boolean annotationShiftEnable = true;
    private long annoDragTempTime = 0;
//     private static final String distanceTemplate = "999d 99h 99m 99ss";
    private long minimum_red = Long.MIN_VALUE;
    private long maximum_red = Long.MAX_VALUE;
    private String NOW = "now";
    private boolean avoidRepaint = false;
    private float bWidht = 1;
    private int currentVisibleBarCount = 1;
    private List<Date> extraRectangles = new ArrayList<Date>();
    private Color extraRectangleColor = Color.RED;
    private boolean showDate = true;
    private long floatableNow = Long.MIN_VALUE;

    /**
     * Creates new form ActivityPanel
     */
    public MixedDataPanel() {
        initComponents();
        backupPreferredSize = this.getPreferredSize();
        this.addAncestorListener(new AncestorListener() {

            @Override
            public void ancestorAdded(AncestorEvent event) {

            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
//                MixedDataPanel.this.needToPaintOverHead = true;
                Rectangle visibleRect = getVisibleRect();
                startVisibleY = visibleRect.y;
//                generateHeadImg();
                MixedDataPanel.this.setRepaintable(true);
                MixedDataPanel.this.repaint();
            }
        });
//        LayerUI<JComponent> zoomLayerUI = new ZoomLayerUI();
//        JPanel pp = new JPanel();
//        pp.setBounds(this.getBounds());
//        JLayer<JComponent> jLayer = new JLayer<>(pp, zoomLayerUI);
//
//        this.add(jLayer);

    }

    public synchronized void setFloatableNow(long now) {
        this.floatableNow = now;
    }

    public void normalNow() {
        this.floatableNow = Long.MIN_VALUE;
    }

    public void setShowDate(boolean show) {
        this.showDate = show;
    }

    @Override
    public boolean isShowDate() {
        return this.showDate;
    }

    public synchronized void clearLinearDataBar(String databar) {
        ContainerDataInterface get = this.dataMap.get(databar);
        if (get instanceof LinearDataContainerInterface) {
            ((LinearDataContainerInterface) get).clearData();
        }
    }

    public void setExtraRectangleColor(Color extraRectangleColor) {
        this.extraRectangleColor = extraRectangleColor;
    }

    public void surroundDataWithRectangle(Date timepoint) {
        System.out.println("start data sel: " + new Date(startRange));
        System.out.println("end data sel: " + new Date(endRange));
        if (timepoint.getTime() >= startRange && timepoint.getTime() <= endRange) {
            this.extraRectangles.add(timepoint);
            System.out.println(" valid point");
        } else {
            System.out.println("invalid time point");
        }
    }

    public void clearExtraRectangle() {
        this.extraRectangles.clear();
    }

    public void setNOW(String NOW) {
        this.NOW = NOW;
    }

    public void setAvoidRepaint(boolean avoidRepaint) {
        this.avoidRepaint = avoidRepaint;
    }

    public boolean isAvoidRepaint() {
        return avoidRepaint;
    }

    public void addDividerToHide(String databar) {
        this.dividerUnderDatabarToHide.add(databar);
    }

    public void removeDividerToHide(String databar) {
        this.dividerUnderDatabarToHide.remove(databar);
    }

    public boolean isAnnotationDraggable() {
        return annotationDraggable;
    }

    public void setAnnotationDraggable(boolean annotationDraggable) {
        this.annotationDraggable = annotationDraggable;
    }

    public boolean isAnnotationShiftEnable() {
        return annotationShiftEnable;
    }

    public void setAnnotationShiftEnable(boolean annotationShiftEnable) {
        this.annotationShiftEnable = annotationShiftEnable;
    }

    public void linkAnnotations(ICVMappableAnnotation ann1, ICVMappableAnnotation ann2) {
        ICVAnnotationLink link = new ICVAnnotationLink(ann1, ann2);
        this.links.add(link);
        ann2.setParent(ann1);
        ann1.addChildren(ann2);
    }

//    public void unlinkAnnotations(ICVMappableAnnotation ann1, ICVMappableAnnotation ann2) {
//        for (ICVAnnotationLink link : links) {
//            if (link.getStartingAnnotation() == (ann1) && link.getEndingAnnotation() == (ann2)) {
//                System.out.println("link to remove has been found");
//                this.links.remove(link);
////                ann2.setParent(null);
////                ann1.removeChildren(ann2);
//                
//                List<AnnotationArea> whereToSearch = this.areasListMap.get(ann1.getActivity());
//                AnnotationArea ann1ToRemove = null;
//                for (AnnotationArea annotationArea : whereToSearch) {
//                    if (annotationArea.getAnnotation() instanceof ICVMappableAnnotation) {
//                        if (((ICVMappableAnnotation) annotationArea.getAnnotation()).getId().equals(ann1.getId())) {
//                            ann1ToRemove = annotationArea;
//                            break;
//                        }
//                    }
//                }
//                if (ann1ToRemove != null) {
//                    this.areasListMap.get(ann1.getActivity()).remove(ann1ToRemove);
//                    annotationMap.get(ann1.getActivity()).remove(ann1);
//                }
//                
//                List<AnnotationArea> whereToSearch2 = this.areasListMap.get(ann2.getActivity());
//                AnnotationArea ann2ToRemove = null;
//                for (AnnotationArea annotationArea : whereToSearch2) {
//                    if(annotationArea.getAnnotation() instanceof ICVMappableAnnotation){
//                        if(((ICVMappableAnnotation)annotationArea.getAnnotation()).getId().equals(ann2.getId())){
//                            ann2ToRemove = annotationArea;
//                        }
//                    }
//                }
//                if (ann2ToRemove != null) {
//                    this.areasListMap.get(ann2.getActivity()).remove(ann2ToRemove);
//                    annotationMap.get(ann2.getActivity()).remove(ann2);
//                }
//                System.out.println("area deleted");
//                break;
//            }
//        }
//    }
    public void unlinkOnCascade(ICVMappableAnnotation ann) {
        List<ICVMappableAnnotation> children = ann.getChildren();
        for (ICVMappableAnnotation child : children) {
            unlinkOnCascade(child);
        }
        ann.getChildren().clear();
        ICVAnnotationLink linkToRemove = null;
        for (ICVAnnotationLink link : links) {
            if (link.getStartingAnnotation() == (ann.getParent()) && link.getEndingAnnotation() == (ann)) {
                System.out.println("last link to remove has been found");
                linkToRemove = link;
            }
        }
        if (linkToRemove != null) {
            this.links.remove(linkToRemove);
        }
        List<AnnotationArea> whereToSearch2 = this.areasListMap.get(ann.getActivity());
        AnnotationArea ann2ToRemove = null;
        for (AnnotationArea annotationArea : whereToSearch2) {
            if (annotationArea.getAnnotation() instanceof ICVMappableAnnotation) {
                if (((ICVMappableAnnotation) annotationArea.getAnnotation()).getId().equals(ann.getId())) {
                    ann2ToRemove = annotationArea;
                }
            }
        }
        if (ann2ToRemove != null) {
            this.areasListMap.get(ann.getActivity()).remove(ann2ToRemove);
            annotationMap.get(ann.getActivity()).remove(ann);
        }
    }

    public void linkAnnotations(ICVMappableAnnotation ann1, ICVMappableAnnotation ann2, Color arrowColor) {
        ICVAnnotationLink link = new ICVAnnotationLink(ann1, ann2);
        link.setArrowColor(arrowColor);
        this.links.add(link);
        ann2.setParent(ann1);
        ann1.addChildren(ann2);
    }

    public boolean isAreaPopupActive() {
        return areaPopupActive;
    }

    public void setAreaPopupActive(boolean areaPopupActive) {
        this.areaPopupActive = areaPopupActive;
    }

    public void setMainEvent(Event mainEvent) {
        this.mainEvent = mainEvent;
    }

    public Event getMainEvent() {
        return mainEvent;
    }

    public boolean isArrowsVisible() {
        return arrowsVisible;
    }

    public void setArrowsVisible(boolean arrowsVisible) {
        this.arrowsVisible = arrowsVisible;
    }

    public void addICVArrow(ICVArrow arrow) {
        this.arrows.add(arrow);
    }

    public int getDataMeasureLenght() {
        return dataMeasureLenght;
    }

    public int getStartVisibleY() {
        return startVisibleY;
    }

    public void addPopupMenuTriggerListener(PopupMenuTriggerListener listener) {
        this.popupMenuTriggerListeners.add(listener);
    }

    public void removePopupMenuTriggerListener(PopupMenuTriggerListener listener) {
        this.popupMenuTriggerListeners.remove(listener);
    }

    public void addZoomListener(ZoomListener listener) {
        this.zoomListeners.add(listener);
    }

    public void removeZoomListener(ZoomListener listener) {
        this.zoomListeners.remove(listener);
    }

    public void setNowLineVisible(boolean nowLineVisible) {
        this.nowLineVisible = nowLineVisible;
    }

    public boolean isNowLineVisible() {
        return nowLineVisible;
    }

    public List<ContainerDataInterface> getOrderedDatabars() {
        return orderedDatabars;
    }

    public void setOrderedDatabars(List<ContainerDataInterface> orderedDatabars) {
        this.orderedDatabars = orderedDatabars;
    }

    public MixedDataPanel duplicate() {
        MixedDataPanel duplicate = new MixedDataPanel();
        duplicate.setStartRange(this.getStartRange());
        duplicate.setEndRange(this.getEndRange());
        duplicate.setBackground(this.getBackground());
        duplicate.setBackgroundChartColor(this.getBackgroundChartColor());
        duplicate.setBandWidhtPercentage(this.getBandWidhtPercentage());
        duplicate.setZoomEnable(this.isZoomEnable());
        duplicate.setNightVisible(this.isNightVisible());
        duplicate.setDataMap((HashMap<String, ContainerDataInterface>) this.dataMap.clone());
        duplicate.setTypeDataMap((HashMap<String, Class<? extends ContainerDataInterface>>) this.typeDataMap.clone());
        duplicate.setAnnotationMap((HashMap<String, List<ICVAnnotation>>) this.annotationMap.clone());
        duplicate.setDataCoordinateMap((HashMap<String, Integer>) this.dataCoordinateMap.clone());
        duplicate.setDataDataVisibilityMap((HashMap<String, Boolean>) this.dataDataVisibilityMap.clone());
        duplicate.setDataVisibilityMap((HashMap<String, Boolean>) this.dataVisibilityMap.clone());
        duplicate.setAnnotationVisibilityMap((HashMap<String, Boolean>) this.annotationVisibilityMap.clone());
        duplicate.setOrderedDatabars(orderedDatabars);
        return duplicate;
    }

    protected HashMap<String, Boolean> getDataVisibilityMap() {
        return dataVisibilityMap;
    }

    protected void setDataVisibilityMap(HashMap<String, Boolean> dataVisibilityMap) {
        this.dataVisibilityMap = dataVisibilityMap;
    }

    protected void setAnnotationVisibilityMap(HashMap<String, Boolean> annotationVisibilityMap) {
        this.annotationVisibilityMap = annotationVisibilityMap;
    }

    protected HashMap<String, Boolean> getAnnotationVisibilityMap() {
        return annotationVisibilityMap;
    }

    protected HashMap<String, Boolean> getDataDataVisibilityMap() {
        return dataDataVisibilityMap;
    }

    protected void setDataDataVisibilityMap(HashMap<String, Boolean> dataDataVisibilityMap) {
        this.dataDataVisibilityMap = dataDataVisibilityMap;
    }

    protected HashMap<String, Integer> getDataCoordinateMap() {
        return dataCoordinateMap;
    }

    protected void setDataCoordinateMap(HashMap<String, Integer> dataCoordinateMap) {
        this.dataCoordinateMap = dataCoordinateMap;
    }

    protected HashMap<String, List<ICVAnnotation>> getAnnotationMap() {
        return annotationMap;
    }

    protected void setAnnotationMap(HashMap<String, List<ICVAnnotation>> annotationMap) {
        this.annotationMap = annotationMap;
    }

    public HashMap<String, ContainerDataInterface> getDataMap() {
        return dataMap;
    }

    protected void setDataMap(HashMap<String, ContainerDataInterface> dataMap) {
        this.dataMap = dataMap;
    }

    protected HashMap<String, Class<? extends ContainerDataInterface>> getTypeDataMap() {
        return typeDataMap;
    }

    protected void setTypeDataMap(HashMap<String, Class<? extends ContainerDataInterface>> typeDataMap) {
        this.typeDataMap = typeDataMap;
    }

    public boolean isHandScrollEnbaled() {
        return handScrollEnbaled;
    }

    public void setHandScrollEnbaled(boolean handScrollEnbaled) {
        this.handScrollEnbaled = handScrollEnbaled;
        if (handScrollEnbaled) {
            this.zoomEnable = false;
        }
    }

    public int getMaxCharLabel() {
        return maxCharLabel;
    }

    public void setMaxCharLabel(int maxCharLabel) {
        this.maxCharLabel = maxCharLabel;
    }

    public void addCoordinateListener(CoordinateListener listener) {
        this.coordinateListeners.add(listener);
    }

    public void removeCoordinateListener(CoordinateListener listener) {
        this.coordinateListeners.remove(listener);
    }

    public boolean isNightVisible() {
        return nightVisible;
    }

    public void setNightVisible(boolean nightVisible) {
        this.nightVisible = nightVisible;
    }

    public void setStartNightHour(int startNightHour) {
        this.startNightHour = startNightHour;
    }

    public int getStartNightHour() {
        return startNightHour;
    }

    public int getEndNightHour() {
        return endNightHour;
    }

    public void setEndNightHour(int endNightHour) {
        this.endNightHour = endNightHour;
    }

    public int getMinimumIncrement() {
        return minimumIncrement;
    }

    public void setMinimumIncrement(int minimumIncrement) {
        this.minimumIncrement = minimumIncrement;
    }

    public int geteX() {
        return eX;
    }

    public int geteY() {
        return eY;
    }

    public long getBeforeZoom_endRange() {
        return beforeZoom_endRange;
    }

    public long getBeforeZoom_startRange() {
        return beforeZoom_startRange;
    }

    public void clear() {
        clearExtraRectangle();
        dataMap.clear();
        typeDataMap.clear();
        annotationMap.clear();
        dataCoordinateMap.clear();
        legendMap.clear();
        orderedDatabars.clear();
        TOP_MARGIN = 40;
        sX = 0;
        sY = 0;
        wX = 0;
        hY = 0;
        eX = 0;
        eY = 0;
        LABEL_LENGHT = 0;
        bufferedImg = null;
        resetZoom();
        this.setPreferredSize(this.getMinimumSize());
    }

    public Color getStandarFalseColor() {
        return standarFalseColor;
    }

    public void setStandarFalseColor(Color standarFalseColor) {
        this.standarFalseColor = standarFalseColor;
    }

    public Color getStandarTrueColor() {
        return standarTrueColor;
    }

    public void setStandarTrueColor(Color standarTrueColor) {
        this.standarTrueColor = standarTrueColor;
    }

    public boolean isSeparatorVisible() {
        return separatorVisible;
    }

    public void setSeparatorVisible(boolean separatorVisible) {
        this.separatorVisible = separatorVisible;
    }

    public Color getSeparatorLineColor() {
        return separatorLineColor;
    }

    public void setSeparatorLineColor(Color separatorLineColor) {
        this.separatorLineColor = separatorLineColor;
    }

    public Date getDateByPoint(Point point) {
        int x = point.x;
        int y = point.y;
        if (!isInChartArea(x, y)) {
            return null;
        } else {
            if (showDate) {
                long dd = ((endRange - startRange) / (eX - sX)) * (x - sX) + startRange;
                return new Date(dd);
            } else {
                float mid = (((float) (endRange)) - ((float) (startRange))) / ((float) (eX - sX));
                float mid2 = mid * ((float) (x - sX));
                return new Date((long) mid2 + startRange);
            }

        }
    }

    public Date getDateByX(int x) {
        if (showDate) {
            long dd = ((endRange - startRange) / (eX - sX)) * (x - sX) + startRange;
//            System.out.println("dd = " + dd);
            return new Date(dd);
        } else {
            float mid = (((float) (endRange)) - ((float) (startRange))) / ((float) (eX - sX));
            float mid2 = mid * ((float) (x - sX));
//            System.out.println("mid2 = "+mid2);
            return new Date((long) mid2 + startRange);
        }

    }

    public int getXByDate(Date date) {
        long data_range = endRange - startRange;
        int resultDataX = 0;
        try {
            resultDataX = (int) (((date.getTime() - startRange) * wX) / data_range);
            if (resultDataX < 0) {
                resultDataX = 0;
            }
        } catch (Exception ex) {
            return resultDataX;
        }
        return resultDataX;
    }

    private boolean similarTo(Color c1, Color c2) {
        double[] c1LAB = this.cIELabHelper.RGBtoLAB(c1.getRed(), c1.getGreen(), c1.getBlue());
        double[] c2LAB = this.cIELabHelper.RGBtoLAB(c2.getRed(), c2.getGreen(), c2.getBlue());
//        System.out.println("c1 : ["+c1LAB[0]+","+c1LAB[1]+","+c1LAB[2]+"]");
//        System.out.println("c1 : ["+c2LAB[0]+","+c2LAB[1]+","+c2LAB[2]+"]");
        double distance = Math.sqrt((c1LAB[0] - c2LAB[0]) * (c1LAB[0] - c2LAB[0]) + (c1LAB[1] - c2LAB[1]) * (c1LAB[1] - c2LAB[1]) + (c1LAB[2] - c2LAB[2]) * (c1LAB[2] - c2LAB[2]));
//        System.out.println("color disance : "+distance);
        if (distance < 30) {
            return true;
        } else {
            return false;
        }
    }

    public void setRepaintable(boolean repaintable) {
        this.repaintable = repaintable;
        if (repaintable) {
//            bufferedImg = null;
//            System.out.println("BUFFERED IS NULL NOW !");
        } else {
            bufferedImg = null;
//            needBackup = true;
////            if (bufferedImg == null) {
//            needBackup = true;
//            System.out.println("START REPAINT");
//            repaint();
//            System.out.println("FINISH REPAINT");
////            }
        }
    }

    public boolean isRepaintable() {
        return repaintable;
    }

    public ZoomLayerUI getZoomLayer() {
        return zoomLayer;
    }

    public void setArrowHeaderMode(boolean arrowHeaderMode) {
        this.arrowHeaderMode = arrowHeaderMode;
    }

    public boolean isArrowHeaderMode() {
        return arrowHeaderMode;
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

    /**
     * Delete all annotation of the speciefed databar, if the databar does not
     * exist, nothing is done
     *
     * @param bar name of databar
     */
    public void clearAnnotationsOnBar(String bar) {
        if (this.annotationMap.containsKey(bar)) {
            this.annotationMap.get(bar).clear();
        }
    }

    /**
     * show/hide the data labeled with a "dataKey"
     *
     * @param dataKey key that represent a data bar
     * @param visible true = show data false = hide data
     *
     */
    public void setDataVisible(String dataKey, boolean visible) {
        this.dataDataVisibilityMap.put(dataKey, visible);
    }

    /**
     *
     * @param dataKey key that represent a data bar
     * @return true if the data-content in a data bar is visible, false
     * otherwise
     */
    public boolean isDataVisible(String dataKey) {
        return this.dataDataVisibilityMap.get(dataKey);
    }

    /**
     * set an entire data bar visible or not
     *
     * @param dataKey key that represent a data bar
     * @param visible
     */
    public void setDataBarVisible(String dataKey, boolean visible) {
        this.dataVisibilityMap.put(dataKey, visible);
        manageLabels();
    }

    /* 
     * @param dataKey
     * key that represent a data bar
     * @return 
     * true if the data bar is visible, false otherwise
     */
    public boolean isDataBarVisible(String dataKey) {
        if (dataKey == null) {
            return false;
        }
        return this.dataVisibilityMap.get(dataKey);
    }

    public boolean isZoomEnable() {
        return zoomEnable;
    }

    public void setZoomEnable(boolean zoomEnable) {
        this.zoomEnable = zoomEnable;
        if (zoomEnable) {
            this.handScrollEnbaled = false;
        }
    }

    public Color getAlternateColor() {
        return alternateColor;
    }

    public void setAlternateColor(Color alternateColor) {
        this.alternateColor = alternateColor;
    }

    public int getEndSelection() {
        return endSelection;
    }

    public void setEndSelection(int endSelection) {
        this.endSelection = endSelection;
    }

    public int gethY() {
        return hY;
    }

    public int getsY() {
        return sY;
    }

    public int getsX() {
        return sX;
    }

    public int getStartSelection() {
        return startSelection;
    }

    public void setStartSelection(int startSelection) {
        this.startSelection = startSelection;
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
//        this.beforeZoom_startRange = startRange;
    }

    public long getEndRange() {
        return endRange;
    }

    public void setEndRange(long endRange) {
        this.endRange = endRange;
        this.backup_endRange = endRange;
//        this.beforeZoom_endRange = endRange;
    }

    public Color getLinesColor() {
        return linesColor;
    }

    public void setLinesColor(Color linesColor) {
        this.linesColor = linesColor;
    }

    public void addDataBar(ContainerDataInterface container) {
        this.orderedDatabars.add(container);
        Collections.sort(orderedDatabars, new Comparator<ContainerDataInterface>() {

            @Override
            public int compare(ContainerDataInterface o1, ContainerDataInterface o2) {
                return Integer.compare(o1.getOrder(), o2.getOrder());
            }
        });

        this.dataMap.put(container.getName(), container);
        this.dataDataVisibilityMap.put(container.getName(), true);
        this.dataVisibilityMap.put(container.getName(), true);
        this.annotationVisibilityMap.put(container.getName(), true);
        this.typeDataMap.put(container.getName(), container.getClass());
        if (ImpulsiveBooleanDataContainer.class.isInstance(container)) {
            String labelByBooleanValue = ((ImpulsiveBooleanDataContainer) container).getLabelByBooleanValue(container.getName(), true);
            if (labelByBooleanValue != null) {
                Font f = new Font("SansSerif", Font.PLAIN, 12);
                FontMetrics fm = this.getFontMetrics(f);
                int s_width = fm.stringWidth(labelByBooleanValue);
                if (LABEL_LENGHT < s_width) {
                    LABEL_LENGHT = s_width + 15;
                }
            }
            String labelByBooleanValue2 = ((ImpulsiveBooleanDataContainer) container).getLabelByBooleanValue(container.getName(), false);
            if (labelByBooleanValue2 != null) {
                Font f = new Font("SansSerif", Font.PLAIN, 12);
                FontMetrics fm = this.getFontMetrics(f);
                int s_width = fm.stringWidth(labelByBooleanValue2);
                if (LABEL_LENGHT < s_width) {
                    LABEL_LENGHT = s_width + 15;
                }
            }
        }
        if (BooleanDataContainer.class.isInstance(container)) {
            String labelByBooleanValue = ((BooleanDataContainer) container).getLabelByBooleanValue(container.getName(), true);
            if (labelByBooleanValue != null) {
                Font f = new Font("SansSerif", Font.PLAIN, 12);
                FontMetrics fm = this.getFontMetrics(f);
                int s_width = fm.stringWidth(labelByBooleanValue);
                if (LABEL_LENGHT < s_width) {
                    LABEL_LENGHT = s_width + 15;
                }
            }
            String labelByBooleanValue2 = ((BooleanDataContainer) container).getLabelByBooleanValue(container.getName(), false);
            if (labelByBooleanValue2 != null) {
                Font f = new Font("SansSerif", Font.PLAIN, 12);
                FontMetrics fm = this.getFontMetrics(f);
                int s_width = fm.stringWidth(labelByBooleanValue2);
                if (LABEL_LENGHT < s_width) {
                    LABEL_LENGHT = s_width + 15;
                }
            }
        }
//        if (CumulativeDataContainer.class.isInstance(container)) {
//            String labelByBooleanValue = ((BooleanDataContainer)container).getLabelByBooleanValue(container.getName(), true);
//            if(labelByBooleanValue != null) {
//                Font f = new Font("SansSerif", Font.PLAIN, 12);
//                FontMetrics fm = this.getFontMetrics(f);
//                int s_width = fm.stringWidth(labelByBooleanValue);
//                if (LABEL_LENGHT < s_width) {
//                    LABEL_LENGHT = s_width + 15;
//                }
//            }
//            String labelByBooleanValue2 = ((BooleanDataContainer)container).getLabelByBooleanValue(container.getName(), false);
//            if(labelByBooleanValue2 != null) {
//                Font f = new Font("SansSerif", Font.PLAIN, 12);
//                FontMetrics fm = this.getFontMetrics(f);
//                int s_width = fm.stringWidth(labelByBooleanValue2);
//                if (LABEL_LENGHT < s_width) {
//                    LABEL_LENGHT = s_width +15;
//                }
//            }
//        }
        manageLabels();
    }

    public void addBooleanData(String dataBar, TimeBooleanDataInterface data, boolean repaint) throws TypeDataMismatchException {
        String canonicalName = typeDataMap.get(dataBar).getCanonicalName();
//        System.out.println("what expected: " + canonicalName);
        String canonicalName1 = data.getClass().getCanonicalName();
//        System.out.println("what is input " + canonicalName1);
//        if(!typeDataMap.get(dataBar).isInstance(data)){
////        if(!typeDataMap.get(data.getName()).getCanonicalName().equals(data.getClass().getCanonicalName())){
//            throw new TypeDataMismatchException();
//        }
        ContainerDataInterface container = this.dataMap.get(dataBar);
        if (container != null) {
//            System.out.println("ADDING DATA");
            if (container instanceof BooleanDataContainer) {
                ((List<TimeBooleanDataInterface>) ((BooleanDataContainer) container).getValues()).add(data);
            }
            if (container instanceof ImpulsiveBooleanDataContainer) {
                ((List<TimeDataInterface>) ((ImpulsiveBooleanDataContainer) container).getValues()).add(data);
            }
        }
        if (repaint) {
            repaint();
        }
    }

    public void addLinearData(String dataBar, TimeDataInterface data, boolean repaint) throws TypeDataMismatchException {
        String canonicalName = typeDataMap.get(dataBar).getCanonicalName();
//        System.out.println("what expected: "+canonicalName);
        String canonicalName1 = data.getClass().getCanonicalName();
//        System.out.println("what is input "+canonicalName1);
//        if(!typeDataMap.get(dataBar).isInstance(data)){
////        if(!typeDataMap.get(data.getName()).getCanonicalName().equals(data.getClass().getCanonicalName())){
//            throw new TypeDataMismatchException();
//        }
        ContainerDataInterface container = this.dataMap.get(dataBar);
        if (container != null) {
//            System.out.println("ADDING DATA");
            if (container instanceof LinearDataContainerInterface) {
                if (((LinearDataContainerInterface) container).getValuesMap().get(data.getSubName()) == null) {
                    ((LinearDataContainerInterface) container).getValuesMap().put(data.getSubName(), Collections.synchronizedCollection(new ArrayList<TimeDataInterface>()));

                }
                Map<String, List> valuesMap = ((LinearDataContainerInterface) container).getValuesMap();
                String values = "values: ";
//                for (Object object : valuesMap.keySet()) {
////                    System.out.println("key: " + object);
//                    Collection get = valuesMap.get(object);
//                    for (Object object1 : get) {
////                        System.out.println("V : " + object1);
//                    }
//                }

                ((Collection<TimeDataInterface>) ((LinearDataContainerInterface) container).getValuesMap().get(data.getSubName())).add(data);
//                System.out.println("class of data: "+data.getClass().getCanonicalName());
//                int xByDate = this.getXByDate(data.getTimeStamp());
//                int keysCount = currentVisibleBarCount;
//                int increment = (hY / keysCount) < this.minimumIncrement ? this.minimumIncrement : (hY / keysCount);     
//                AnnotationArea area = new AnnotationArea(new Rectangle(xByDate-20, this.dataCoordinateMap.get(dataBar)-increment/2,40 , increment*2), new ICVAnnotation(dataBar, data.getTimeStamp().getTime(), "ciao stronzo", null, separatorVisible));
//                if(!this.areasListMap.containsKey(dataBar)){
//                    this.areasListMap.put(dataBar, new ArrayList<AnnotationArea>());
//                }
//                this.areasListMap.get(dataBar).add(area);
//                this.addICVAnnotation(new ICVMappableAnnotation(new Date().getTime(), dataBar, data.getTimeStamp().getTime(), data.getTimeStamp().toString()+" value: ", null,40,ICVAnnotation.DescriptionAlignment.CENTER,false));
            }
        }
        if (repaint) {
            repaint();
        }
    }

    public void addActivityData(ActivityDataInterface data, boolean repaint) throws TypeDataMismatchException {
        String canonicalName = typeDataMap.get(data.getSubname()).getCanonicalName();
//        System.out.println("what expected: "+canonicalName);
        String canonicalName1 = data.getClass().getCanonicalName();
//        System.out.println("what is input "+canonicalName1);
//        if(!typeDataMap.get(data.getSubname()).isInstance(data)){
////        if(!typeDataMap.get(data.getName()).getCanonicalName().equals(data.getClass().getCanonicalName())){
//            throw new TypeDataMismatchException();
//        }
        ContainerDataInterface container = this.dataMap.get(data.getSubname());
        if (container != null && container instanceof ActivityDataContainer) {
//            System.out.println("ADDING DATA");
            ((ActivityDataContainer) this.dataMap.get(data.getSubname())).getActivityData().add(data);
        }
        if (repaint) {
            repaint();
        }
    }

    public boolean isContainsZoomedChart() {
        return containsZoomedChart;
    }

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
        for (ZoomListener zoomListener : zoomListeners) {
            zoomListener.zoomResetted();
        }
        repaint();
    }

    public int getVisibleDataBarCount() {
        int c = 0;
        for (String s : this.dataVisibilityMap.keySet()) {
            if (this.dataVisibilityMap.get(s)) {
                c++;
            }
        }
        return c;
    }

    private void manageLabels() {
//        this.setPreferredSize(backupPreferredSize);
//        System.out.println("backup = " + backupPreferredSize.toString());
        Set<String> keySet = this.dataMap.keySet();
        for (String activity : keySet) {
            String x = new String(activity);
            if (activity.length() > maxCharLabel) {
                x = x.substring(0, maxCharLabel);
            }
            Font f = new Font("SansSerif", Font.BOLD, 14);
            FontMetrics fm = this.getFontMetrics(f);
            int s_width = fm.stringWidth(x);
            if (LABEL_LENGHT < s_width) {
                LABEL_LENGHT = s_width;
            }
        }
        this.setMinimumSize(new Dimension((int) this.getPreferredSize().getWidth(), this.minimumIncrement * getVisibleDataBarCount() + TOP_MARGIN + BOTTOM_MARGIN));
        this.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth(), this.minimumIncrement * getVisibleDataBarCount() + TOP_MARGIN + BOTTOM_MARGIN));
        this.updateUI();

    }

    public void setBackgroundChartColor(Color backgroundChartColor) {
        this.backgroundChartColor = backgroundChartColor;
    }

    public Color getBackgroundChartColor() {
        return this.backgroundChartColor;
    }

    private void clearMaps() {
        this.yValuesLinearMap.clear();
    }

    public int getHourMeasureLenght() {
        return hourMeasureLenght;
    }

    public int getwX() {
        return wX;
    }

    @Override
    public synchronized void paint(Graphics g) {
//        System.out.println("repaintable ? "+isRepaintable());
        if (dataMap.isEmpty() || isAvoidRepaint()) {
            super.paint(g);
            return;
        }
        if (!isRepaintable()) {
            if (!isRepaintable() && bufferedImg == null) {
//                System.out.println("NEED BACKUP");
                needBackup = true;
            }
            if (bufferedImg != null && !needBackup) {
                g.drawImage(bufferedImg, 0, 0, this.getWidth(), this.getHeight(), null);
//                System.out.println("Avoiding repaint");
                return;
            } else {
//                System.out.println("buffered is null");
            }
        }
        super.paint(g);
        clearMaps();
//        System.out.println("REPAINTING");
        double width = this.getBounds().getWidth();
        double height = this.getBounds().getHeight();
//        System.out.println("widht = " + width);
//        System.out.println(" = " + height);

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
//            Set<String> MAIN_ORDERED_KEY_SET = this.dataMap.keySet();
            LinkedList<String> MAIN_ORDERED_KEY_SET = new LinkedList<String>();
            for (ContainerDataInterface containerDataInterface : orderedDatabars) {
                String dataBar = containerDataInterface.getName();
//                System.out.println("activity blabla : "+dataBar);
                if (isDataBarVisible(dataBar)) {
//                    System.out.println("removing .. "+dataBar);
                    MAIN_ORDERED_KEY_SET.add(dataBar);
                }
            }
//            MAIN_ORDERED_KEY_SET = keySet2;
            int keysCount = MAIN_ORDERED_KEY_SET.size();
            currentVisibleBarCount = keysCount;
            int start_y = -1;

            int increment = hY;
            try {
                increment = (hY / keysCount) < this.minimumIncrement ? this.minimumIncrement : (hY / keysCount);
            } catch (Exception ex) {

            }

//            this.setBounds(0, 0, this.getWidth(), 50*keysCount + TOP_MARGIN + BOTTOM_MARGIN);
            boolean col = false;

//            for (ContainerDataInterface containerDataInterface : orderedDatabars) {
            for (String dataBar : MAIN_ORDERED_KEY_SET) {
//                String dataBar = containerDataInterface.getName();
                Font ff = new Font("SansSerif", Font.BOLD, 14);
                FontMetrics fm2 = g.getFontMetrics(ff);
                g.setFont(ff);
                String x = new String(dataBar);
                if (dataBar.length() > maxCharLabel) {
                    x = x.substring(0, maxCharLabel);
                }
                int s_width = fm2.stringWidth(x);
                if (LABEL_LENGHT < s_width) {
                    LABEL_LENGHT = s_width;
                }
                if (sX < s_width) {
                    sX = LABEL_LENGHT + LEFT_MARGIN;
                    eX = sX + wX;
                }
                int act_x = sX - 10 - s_width;
                if (start_y == -1) {
                    start_y = sY + (hY / (keysCount * 2));
                }
                this.dataCoordinateMap.put(dataBar, start_y);
                g1.setPaint(yAxisDataColor);
//                g.drawString(dataBar, act_x, start_y);
                if (col) {
                    Rectangle2D rect1 = new Rectangle2D.Double(LEFT_MARGIN + LABEL_LENGHT, start_y - increment / 2, width - (RIGHT_MARGIN * 2) - LABEL_LENGHT, increment);
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
            Date dateS = new Date(startRange);
            Date dateE = new Date(endRange);
            if (!this.arrowHeaderMode) {

                Font ff = new Font("SansSerif", Font.PLAIN, 10);
                g1.setPaint(xAxisDataColor);
                g.setFont(ff);
                //START RANGE

                if (showDate) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    g.drawString(format.format(dateS), labelStartX, sY - (fm.getHeight() * 2));
                    SimpleDateFormat h_format = new SimpleDateFormat("(HH:mm)");
                    g.drawString(h_format.format(dateS), sX - (hourMeasureLenght / 2), sY - (fm.getHeight()));
                    g1.setPaint(linesColor);
                    g.drawLine(lineStart, sY - (fm.getHeight() / 2) - 1, lineStart, sY);

                    //END RANGE
                    g1.setPaint(xAxisDataColor);
                    g.drawString(format.format(dateE), eX - (dataMeasureLenght / 2), sY - (fm.getHeight() * 2));
                    g1.setPaint(linesColor);
                    g.drawLine(eX, sY - (fm.getHeight() / 2) - 1, eX, sY);
                    g1.setPaint(xAxisDataColor);
                    g.drawString(h_format.format(dateE), eX - (hourMeasureLenght / 2), sY - (fm.getHeight()));

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
                        g1.setPaint(xAxisDataColor);
                        g.drawString(ddd, labelStartX, sY - fm.getHeight() * 2);
                        g1.setPaint(linesColor);
                        g.drawLine(lineStart, sY - (fm.getHeight() / 2) - 1, lineStart, sY);
                        g1.setPaint(xAxisDataColor);
                        g.drawString(h_format.format(date), hStart, sY - (fm.getHeight()));
                        g1.setPaint(linesColor);
                        g.drawLine(lineStart, sY, lineStart, hY + TOP_MARGIN);

                    }
                    g.drawLine(eX, sY, eX, hY + TOP_MARGIN); //ultima
                    g.drawLine(sX, sY, sX, hY + TOP_MARGIN); //prima
                } else {
                    dataMeasureLenght = fm.stringWidth("" + dateE.getTime());
//                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    g.drawString("" + dateS.getTime(), labelStartX, sY - (fm.getHeight() * 2));
//                    SimpleDateFormat h_format = new SimpleDateFormat("(HH:mm)");
//                    g.drawString(""+dateS, sX - (hourMeasureLenght / 2), sY - (fm.getHeight()));
                    g1.setPaint(linesColor);
                    g.drawLine(lineStart, sY - (fm.getHeight() / 2) - 1, lineStart, sY);

                    //END RANGE
                    g1.setPaint(xAxisDataColor);
                    g.drawString("" + dateE.getTime(), eX - (dataMeasureLenght / 2), sY - (fm.getHeight() * 2));
                    g1.setPaint(linesColor);
                    g.drawLine(eX, sY - (fm.getHeight() / 2) - 1, eX, sY);
                    g1.setPaint(xAxisDataColor);
//                    g.drawString(""+dateE, eX - (hourMeasureLenght / 2), sY - (fm.getHeight()));

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
//                        String ddd = "-";
//                        if (date == null) {
////                        System.out.println("MIXED icv duplication issue: init failed, generated emergency date");
//                            date = new Date(1492, 9, 12);
//                        }
//                        ddd = format.format(date);
                        g1.setPaint(xAxisDataColor);
                        g.drawString("" + date.getTime(), labelStartX, sY - fm.getHeight() * 2);
                        g1.setPaint(linesColor);
                        g.drawLine(lineStart, sY - (fm.getHeight() / 2) - 1, lineStart, sY);
                        g1.setPaint(xAxisDataColor);
//                        g.drawString(h_format.format(date), hStart, sY - (fm.getHeight()));
                        g1.setPaint(linesColor);
                        g.drawLine(lineStart, sY, lineStart, hY + TOP_MARGIN);

                    }
                    g.drawLine(eX, sY, eX, hY + TOP_MARGIN); //ultima
                    g.drawLine(sX, sY, sX, hY + TOP_MARGIN); //prima
                }
            } else {

                g1.setPaint(Color.BLACK);
                Stroke drawingStrokes = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
                g1.setStroke(drawingStrokes);
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g1.drawLine(sX / 2, sY / 2, eX + sX / 2, sY / 2);
                Polygon arrowTriangle = new Polygon();
                arrowTriangle.addPoint(eX + sX / 2 + 3, sY / 2 + 1);
                arrowTriangle.addPoint(eX + sX / 2 - 20 + 3, sY / 2 - 10 + 1);
                arrowTriangle.addPoint(eX + sX / 2 - 20 + 3, sY / 2 + 10 + 1);
                g1.fill(arrowTriangle);

            }

            //<editor-fold defaultstate="collapsed" desc="NIGHTS">
            //********************** N I G H T S *******************************
            if (nightVisible) {
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(dateS);
                gc.add(Calendar.DAY_OF_MONTH, -1);
                gc.set(Calendar.HOUR_OF_DAY, this.startNightHour);
                while (!gc.getTime().after(dateE)) {
                    Graphics2D g2 = (Graphics2D) g.create();
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
            //</editor-fold>
            try {
                bWidht = (hY / (keysCount * 2)) * bandWidhtPercentage;
            } catch (Exception e) {
                bWidht = hY / 2;
            }

//            for (ContainerDataInterface CDI : orderedDatabars) {
            for (String dataBar : MAIN_ORDERED_KEY_SET) {
//                String dataBar = CDI.getName();
                if (!isDataBarVisible(dataBar)) {
                    continue;
                }
                if (isDataVisible(dataBar)) {
                    ContainerDataInterface container = this.dataMap.get(dataBar);
                    //<editor-fold defaultstate="collapsed" desc="ACTIVITY">
                    if (ActivityDataContainer.class.isInstance(container)) {
                        for (ActivityDataInterface data : ((ActivityDataContainer) container).getActivityData()) {
                            //                        if (typeDataMap.get(dataBar).getCanonicalName().equals(ActivityDataInterface.class.getCanonicalName())) {
                            //                            aosdj
                            //************************************************************************************
                            //                  ACTIVITY
                            //************************************************************************************

                            int yData = this.dataCoordinateMap.get(dataBar);

                            int startDataX = (int) (((((ActivityDataInterface) data).getStart().getTime() - startRange) * wX) / data_range);
                            if (startDataX < 0) {
                                startDataX = -10;
                            }
                            if (startDataX + sX > eX) {
                                break;
                            }

                            int endDataX = (int) (((((ActivityDataInterface) data).getEnd().getTime() - startRange) * wX) / data_range);
                            if (sX + endDataX > eX) {
                                endDataX = eX - sX;
                            }

                            RoundRectangle2D.Float rRect = new RoundRectangle2D.Float(sX + startDataX, yData - bWidht, endDataX - startDataX, bWidht * 2, 10, 10);
                            g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            if (data instanceof ActivityDataExtendedInterface) {
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
                            g1.fill(rRect);

                        }
                    }
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="LINEAR DATA">
                    //<editor-fold defaultstate="collapsed" desc="NOT DISCRETE">
                    if (LinearDataContainerInterface.class.isInstance(container) && !((LinearDataContainerInterface) container).isDiscret()) {

//                        for (ActivityDataInterface data : ((LinearDataContainerInterface) container).) {
//                        System.out.println(dataBar + " is Linear Data Interface !!");
                        //************************************************************************************
                        //                  LINEAR DATA
                        //************************************************************************************
                        Map<String, List<TimeDataInterface>> valuesMap = ((LinearDataContainerInterface) container).getValuesMap();
                        Set<String> keySet1 = valuesMap.keySet();
                        double localMax = Double.MIN_VALUE;
                        double localMin = Double.MAX_VALUE;
                        double dt_range = 0;
                        HashMap<String, Color> hashMap = new HashMap<String, Color>();
                        for (String subName : keySet1) {
                            ArrayList<TimeDataInterface> dtiData = new ArrayList(valuesMap.get(subName));

                            if (((LinearDataContainerInterface) container).getColorBySubChartName(subName) != null) {
                                hashMap.put(subName, ((LinearDataContainerInterface) container).getColorBySubChartName(subName));
//                                System.out.println("COLORONE TROVATO");
                                subnamesColorMap.put(subName,hashMap.get(subName));
                            } else {
//                                System.out.println("NON HO TROVATO ..SBRUGNOOO");
                                Color colorino = (Color) this.supplyColor.getNextPaint();
                                hashMap.put(subName, colorino);
                                subnamesColorMap.put(subName,colorino);
                            }
                            this.legendMap.put(dataBar, hashMap);

                            Iterator<TimeDataInterface> iteratorDtiData = dtiData.iterator();
                            while (iteratorDtiData.hasNext()) {
                                TimeDataInterface t = iteratorDtiData.next();
                                double value = ((TimeValueDataInterface) t).getValue();
                                //                                    System.out.println("value -> " + value);
                                if (value > localMax) {
                                    if (localMin > localMax) {
                                        localMin = localMax == Double.MIN_VALUE ? value : localMax;
                                    }
                                    localMax = value;
                                }
                                if (value < localMin) {
                                    localMin = value;
                                }
                            }

                        }
                        if (localMin == localMax && localMax != Double.MIN_VALUE && localMin != Double.MAX_VALUE) {
                            localMin -= Math.abs(localMin * 0.2);
                            localMax += Math.abs(localMax * 0.2);
                        }
                        ((LinearDataContainerInterface) container).setMinValueToShow((int) localMin);
                        ((LinearDataContainerInterface) container).setMaxValueToShow((int) localMax);

                        dt_range = localMax - localMin;
                        double upScart = dt_range * 0.1;
                        double downScart = dt_range * (keySet1.size() == 1 ? 0.1 : 0.3);
                        localMax += upScart;
                        localMin -= downScart;
                        dt_range = localMax - localMin;
                        //                            System.out.println("                                  local Max : " + localMax);
                        //                            System.out.println("                                  local Min : " + localMin);

                        //drawing horizontal lines
                        Font linearLegendFont = new Font("SansSerif", Font.PLAIN, 10);
                        FontMetrics llffm = g.getFontMetrics(linearLegendFont);
                        ((Graphics2D) g).setPaint(Color.GRAY);
                        int llffmHeight = llffm.getHeight();
                        Stroke drawingStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3f, 0f, 3f}, 0);
                        ((Graphics2D) g).setStroke(drawingStroke);
                        float highY = dataCoordinateMap.get(dataBar) - increment / 2 + llffmHeight;
                        float mediumY = dataCoordinateMap.get(dataBar);
                        float lowY = dataCoordinateMap.get(dataBar) + increment / 2 - llffmHeight;
                        Line2D.Float lineHigh = new Line2D.Float(sX, highY, sX + wX, highY);
                        Line2D.Float lineMedium = new Line2D.Float(sX, mediumY, wX + sX, mediumY);
                        Line2D.Float lineLow = new Line2D.Float(sX, lowY, wX + sX, lowY);
                        ((Graphics2D) g).draw(lineHigh);
                        ((Graphics2D) g).draw(lineMedium);
                        ((Graphics2D) g).draw(lineLow);
                        //                            g.drawLine(sX, dataCoordinateMap.get(dataBar)+(int)bWidht, wX, dataCoordinateMap.get(dataBar)+(int)bWidht);
                        float miniSize = mediumY - highY;
                        while (miniSize > llffmHeight * 3) {
                            miniSize /= 2;
                        }
                        float sss = highY;
                        //                            System.out.println("dt_range = "+dt_range);
                        //                            System.out.println("increment = "+increment);
                        //                            System.out.println("y di local Max : "+dataCoordinateMap.get(dataBar));
                        float yMax = dataCoordinateMap.get(dataBar) - increment / 2;
                        //                            System.out.println("sss = "+sss);
                        double scartino = (dt_range * (sss - yMax)) / increment;
                        //                            System.out.println("primo scartin  = " + scartino);
                        //                            System.out.println("primo valore -> "+ (localMax-scartino));

                        double value = localMax - scartino;
                        value = (Math.floor(value * 1000)) / 1000;
                        this.yValuesLinearMap.put(sss + llffmHeight / 4, "" + value);
                        for (;;) {
                            sss += miniSize;
                            if (sss > lowY) {
                                break;
                            }
                            scartino = (dt_range * (sss - yMax)) / increment;
                            value = localMax - scartino;
                            value = (Math.floor(value * 1000)) / 1000;
                            this.yValuesLinearMap.put(sss + llffmHeight / 4, ("" + value));
                            Line2D.Float line = new Line2D.Float(sX, sss, wX + sX, sss);
                            g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            ((Graphics2D) g).draw(line);

                        }

                        //                            Rectangle2D rect1 = new Rectangle2D.Double(sX, yMax+increment-llffmHeight*2, wX, llffmHeight*2);
                        //                            g1.setPaint(Color.GREEN);
                        //                            g1.fill(rect1);
                        g.setFont(linearLegendFont);

                        //----------------------
                        supplyColor = new PaintSupplier();
//s

                        for (String subName : keySet1) {
                            //                                System.out.println("sub name to paint : " + subName);
                            Collection<TimeDataInterface> dtiData = valuesMap.get(subName);
                            dtiData = Collections.synchronizedCollection(dtiData);
                            List<Point> points = new ArrayList<Point>();
                            points = Collections.synchronizedList(points);
                            //                                Map<String,Point> minMaxOutMap = new HashMap<>();
                            //                                minMaxOutMap.put("lastMin", null);
                            //                                minMaxOutMap.put("lastMax", null);
                            //                                localMin+=dt_range*0.2;
                            //                                localMax-=dt_range*0.2;
//                            if (!this.subnamesColorMap.containsKey(subName)) {
//                                Paint nextPaint = supplyColor.getNextPaint();
//                                similarTo(alternateColor, (Color) nextPaint);
//                                subnamesColorMap.put(subName, nextPaint);
//                            }
                            Paint nextPaint = subnamesColorMap.get(subName);
                            Iterator<TimeDataInterface> iteratorDtiData = dtiData.iterator();
                            while (iteratorDtiData.hasNext()) {
                                TimeDataInterface t = iteratorDtiData.next();
                                if (t instanceof TimeValueDataInterface) {
                                    // TIME VALUE DATA INTERFACE !

                                    //                                        System.out.println("data time stamp : " + t.getTimeStamp().toString());
                                    long time = t.getTimeStamp().getTime();
                                    if (data_range == 0) {
                                        continue;
                                    }
                                    int startDataX = (int) (((time - startRange) * wX) / data_range);

                                    int yData = this.dataCoordinateMap.get(dataBar);
                                    double value2 = ((TimeValueDataInterface) t).getValue();
                                    //                                        value : dt_rage = x : height - > x = (value*heingt) / dt_range
                                    double yyy = dt_range == 0 ? yData : ((value2 - localMin) * increment) / dt_range;
//                                    s

                                    g1.setPaint(nextPaint);
                                    int xxxx = startDataX + sX - 4;
                                    int yyyy = yData - 4 + increment / 2 - (int) (yyy);
                                    points.add(new Point(xxxx, yyyy));
                                    boolean chartWithDots = ((LinearDataContainerInterface) container).isChartWithDots(subName);
//                                    System.out.println(subName+" vuole i pallini ? "+chartWithDots);
                                    if (((LinearDataContainerInterface) container).isDotVisible() && chartWithDots) {
                                        Ellipse2D.Double circle = new Ellipse2D.Double(xxxx, yyyy, 8, 8);
                                        g1.fill(circle);
                                    }

                                }

                            }

                            //                                System.out.println("size of data ->" + dtiData.size());
                            //                                System.out.println("size of points -> " + points.size());
                            for (int i = 0; i < points.size(); i++) {
                                //                                    if (dtiData.get(i) instanceof TimeValueDataInterface) {
                                //                                        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                //A TRATTINI
                                //                                        float[] dash1 = {2f, 0f, 2f};
                                //                                        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                                //                                                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
                                //                                        g1.setStroke(bs1);

                                if (i == points.size() - 1) {
                                    break;
                                }

                                RenderingHints rh = new RenderingHints(
                                        RenderingHints.KEY_ANTIALIASING,
                                        RenderingHints.VALUE_ANTIALIAS_ON);

                                rh.put(RenderingHints.KEY_RENDERING,
                                        RenderingHints.VALUE_RENDER_QUALITY);

                                g1.setRenderingHints(rh);

                                BasicStroke bs1 = new BasicStroke(2, BasicStroke.CAP_BUTT,
                                        BasicStroke.JOIN_BEVEL);
                                g1.setStroke(bs1);
//                                g1.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//                                Setting the RenderingHint KEY_STROKE_COTROL to VALUE_STROKE_PURE 
                                g1.drawLine(points.get(i).x + 4, points.get(i).y + 4, points.get(i + 1).x + 4, points.get(i + 1).y + 4);
                                //                                    }

                            }
                        }
                        if (((LinearDataContainerInterface) container).isMaxThresholdPaintable()) {
                            int maximumThreshold = (int) ((LinearDataContainerInterface) container).getMaximumThreshold();
                            int yData = this.dataCoordinateMap.get(dataBar);
                            double yyy = dt_range == 0 ? yData : ((maximumThreshold - localMin) * increment) / dt_range;

                            BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_BUTT,
                                    BasicStroke.JOIN_BEVEL);
                            g1.setStroke(bs1);
                            g1.setPaint(Color.RED);
                            int yyyy = yData + increment / 2 - (int) (yyy);
                            Line2D.Float line = new Line2D.Float(sX, (float) yyyy, wX + sX, (float) yyyy);
                            g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            g1.draw(line);
                            Font maxFont = new Font("SansSerif", Font.BOLD, 12);
                            llffm = g.getFontMetrics(maxFont);
                            g.setFont(maxFont);
                            g1.drawString("max = " + maximumThreshold, sX + 10, yyyy - 5);
                        }
                        if (((LinearDataContainerInterface) container).isMinThresholdPaintable()) {
                            int minimumThreshold = (int) ((LinearDataContainerInterface) container).getMinimumThreshold();
                            int yData = this.dataCoordinateMap.get(dataBar);
                            double yyy = dt_range == 0 ? yData : ((minimumThreshold - localMin) * increment) / dt_range;

                            BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_BUTT,
                                    BasicStroke.JOIN_BEVEL);
                            g1.setStroke(bs1);
                            g1.setPaint(Color.GREEN);
                            int yyyy = yData + increment / 2 - (int) (yyy);
                            Line2D.Float line = new Line2D.Float(sX, (float) yyyy, wX + sX, (float) yyyy);
                            ((Graphics2D) g).draw(line);
                            Font minfFont = new Font("SansSerif", Font.BOLD, 12);
                            llffm = g.getFontMetrics(minfFont);
                            g.setFont(minfFont);
                            g1.drawString("min = " + minimumThreshold, sX + 10, yyyy - 5);
                        }

                    }
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="DISCRETE">
                    if (LinearDataContainerInterface.class.isInstance(container) && ((LinearDataContainerInterface) container).isDiscret()) {
//                        System.out.println(" DISCRETE !!!");
//                        for (ActivityDataInterface data : ((LinearDataContainerInterface) container).) {

//                        System.out.println(dataBar + " is Linear Data Interface !!");
                        //************************************************************************************
                        //                  LINEAR DATA
                        //************************************************************************************
                        Map<String, List<TimeDataInterface>> valuesMap = ((LinearDataContainerInterface) container).getValuesMap();
                        Set<String> keySet1 = valuesMap.keySet();
                        double localMax = ((LinearDataContainerInterface) container).getMaxValueToShow();
                        double localMin = ((LinearDataContainerInterface) container).getMinValueToShow();
                        double dt_range = 0;
                        HashMap<String, Color> hashMap = new HashMap<String, Color>();
                        for (String subName : keySet1) {
                            //                                System.out.println("sub name to paint : " + subName);
//                            List<TimeDataInterface> dtiData = valuesMap.get(subName);
                            ((LinearDataContainerInterface) container).setColorToSubChart(subName, (Color) this.supplyColor.getNextPaint());
                            hashMap.put(subName, (Color) ((LinearDataContainerInterface) container).getColorBySubChartName(subName));
                            this.legendMap.put(dataBar, hashMap);

                        }

//                        dt_range = localMax - localMin;
//                        double upScart = dt_range * 0.1;
//                        double downScart = dt_range * (keySet1.size() == 1 ? 0.1 : 0.3);
//                        localMax += upScart;
//                        localMin -= downScart;
                        dt_range = localMax - localMin;
                        //                            System.out.println("                                  local Max : " + localMax);
                        //                            System.out.println("                                  local Min : " + localMin);

                        //drawing horizontal lines
                        Font linearLegendFont = new Font("SansSerif", Font.PLAIN, 10);
                        FontMetrics llffm = g.getFontMetrics(linearLegendFont);
                        ((Graphics2D) g).setPaint(Color.GRAY);
                        int llffmHeight = llffm.getHeight();
                        Stroke drawingStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3f, 0f, 3f}, 0);
                        ((Graphics2D) g).setStroke(drawingStroke);
                        float highY = dataCoordinateMap.get(dataBar) - increment / 2 + llffmHeight;
                        float mediumY = dataCoordinateMap.get(dataBar);
                        float lowY = dataCoordinateMap.get(dataBar) + increment / 2 - llffmHeight;
                        Line2D.Float lineHigh = new Line2D.Float(sX, highY, sX + wX, highY);
                        Line2D.Float lineMedium = new Line2D.Float(sX, mediumY, wX + sX, mediumY);
                        Line2D.Float lineLow = new Line2D.Float(sX, lowY, wX + sX, lowY);
                        ((Graphics2D) g).draw(lineHigh);
                        ((Graphics2D) g).draw(lineMedium);
                        ((Graphics2D) g).draw(lineLow);
                        //                            g.drawLine(sX, dataCoordinateMap.get(dataBar)+(int)bWidht, wX, dataCoordinateMap.get(dataBar)+(int)bWidht);
                        float miniSize = mediumY - highY;
                        while (miniSize > llffmHeight * 3) {
                            miniSize /= 2;
                        }
                        float sss = highY;
                        //                            System.out.println("dt_range = "+dt_range);
                        //                            System.out.println("increment = "+increment);
                        //                            System.out.println("y di local Max : "+dataCoordinateMap.get(dataBar));
                        float yMax = dataCoordinateMap.get(dataBar) - increment / 2;
                        //                            System.out.println("sss = "+sss);
                        double scartino = (dt_range * (sss - yMax)) / increment;
                        //                            System.out.println("primo scartin  = " + scartino);
                        //                            System.out.println("primo valore -> "+ (localMax-scartino));

                        int value = (int) (localMax - scartino);
                        value = (int) (Math.floor(value * 1000)) / 1000;
                        this.yValuesLinearMap.put(sss + llffmHeight / 4, "" + value);
                        for (;;) {
                            sss += miniSize;
                            if (sss > lowY) {
                                break;
                            }
                            scartino = (dt_range * (sss - yMax)) / increment;
                            value = (int) (localMax - scartino);
                            value = (int) (Math.floor(value * 1000)) / 1000;
                            this.yValuesLinearMap.put(sss + llffmHeight / 4, ("" + value));
                            Line2D.Float line = new Line2D.Float(sX, sss, wX + sX, sss);
                            g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            ((Graphics2D) g).draw(line);

                        }

                        //                            Rectangle2D rect1 = new Rectangle2D.Double(sX, yMax+increment-llffmHeight*2, wX, llffmHeight*2);
                        //                            g1.setPaint(Color.GREEN);
                        //                            g1.fill(rect1);
                        g.setFont(linearLegendFont);

                        //----------------------
                        supplyColor = new PaintSupplier();

                        for (String subName : keySet1) {
                            //                                System.out.println("sub name to paint : " + subName);
                            List<TimeDataInterface> dtiData = valuesMap.get(subName);
                            List<Point> points = new ArrayList<Point>();
                            //                                Map<String,Point> minMaxOutMap = new HashMap<>();
                            //                                minMaxOutMap.put("lastMin", null);
                            //                                minMaxOutMap.put("lastMax", null);
                            //                                localMin+=dt_range*0.2;
                            //                                localMax-=dt_range*0.2;
                            Paint nextPaint = ((LinearDataContainerInterface) container).getColorBySubChartName(subName);
                            if (nextPaint == null) {
                                nextPaint = supplyColor.getNextPaint();
                                similarTo(alternateColor, (Color) nextPaint);
                            }
//                            if (!this.subnamesColorMap.containsKey(subName)) {
//                                Paint nextPaint = supplyColor.getNextPaint();
//                                similarTo(alternateColor, (Color) nextPaint);
                            subnamesColorMap.put(subName, nextPaint);
//                            }
//                            Paint nextPaint = subnamesColorMap.get(subName);

                            boolean chartWithDots = ((LinearDataContainerInterface) container).isChartWithDots(subName);
                            System.out.println(subName + " ---> " + chartWithDots);
                            synchronized (dtiData) {
                                for (TimeDataInterface t : dtiData) {
                                    if (t instanceof TimeValueDataInterface) {
                                        // TIME VALUE DATA INTERFACE !

                                        //                                        System.out.println("data time stamp : " + t.getTimeStamp().toString());
                                        long time = t.getTimeStamp().getTime();
                                        int startDataX = (int) (data_range == 0 ? startRange : (int) (((time - startRange) * wX) / data_range));

                                        int yData = this.dataCoordinateMap.get(dataBar);
                                        double value2 = ((TimeValueDataInterface) t).getValue();
                                        //                                        value : dt_rage = x : height - > x = (value*heingt) / dt_range
                                        double yyy = dt_range == 0 ? yData : ((value2 - localMin) * increment) / dt_range;

                                        g1.setPaint(nextPaint);
                                        int xxxx = startDataX + sX - 4;
                                        int yyyy = yData - 4 + increment / 2 - (int) (yyy);
                                        points.add(new Point(xxxx, yyyy));
                                        if (((LinearDataContainerInterface) container).isDotVisible() && chartWithDots) {

                                            Ellipse2D.Double circle = new Ellipse2D.Double(xxxx, yyyy, 8, 8);
                                            g1.fill(circle);
                                        }
//                                               System.out.println("class of data: "+data.getClass().getCanonicalName());
//                                    int xByDate = this.getXByDate(data.getTimeStamp());
//                                    int keysCount = currentVisibleBarCount;
//                                    int increment = (hY / keysCount) < this.minimumIncrement ? this.minimumIncrement : (hY / keysCount);
                                        if (showDate) {
                                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy @ HH:mm");
                                            String dateString = format.format(t.getTimeStamp());
                                            AnnotationArea area = new AnnotationArea(new Rectangle(xxxx - 5, yyyy - 5, 10, 10), new ICVAnnotation(dataBar, t.getTimeStamp().getTime(), dateString + " value: " + value2, null, separatorVisible));
                                            if (!this.areasListMap.containsKey(dataBar)) {
                                                this.areasListMap.put(dataBar, new ArrayList<AnnotationArea>());
                                            }
                                            this.areasListMap.get(dataBar).add(area);
                                        } else {
                                            AnnotationArea area = new AnnotationArea(new Rectangle(xxxx - 5, yyyy - 5, 10, 10),
                                                    new ICVAnnotation(
                                                            dataBar,
                                                            t.getTimeStamp().getTime(),
                                                            t.getTimeStamp().getTime() + ", " + value2,
                                                            null,
                                                            separatorVisible));
                                            if (!this.areasListMap.containsKey(dataBar)) {
                                                this.areasListMap.put(dataBar, new ArrayList<AnnotationArea>());
                                            }
                                            this.areasListMap.get(dataBar).add(area);
                                        }

                                    }

                                }
                            }
                            //                                System.out.println("size of data ->" + dtiData.size());
                            //                                System.out.println("size of points -> " + points.size());
                            for (int i = 0; i < points.size(); i++) {
                                //                                    if (dtiData.get(i) instanceof TimeValueDataInterface) {
                                //                                        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                //A TRATTINI
                                //                                        float[] dash1 = {2f, 0f, 2f};
                                //                                        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                                //                                                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
                                //                                        g1.setStroke(bs1);

                                if (i == points.size() - 1) {
                                    break;
                                }

                                RenderingHints rh = new RenderingHints(
                                        RenderingHints.KEY_ANTIALIASING,
                                        RenderingHints.VALUE_ANTIALIAS_ON);

                                rh.put(RenderingHints.KEY_RENDERING,
                                        RenderingHints.VALUE_RENDER_QUALITY);

                                g1.setRenderingHints(rh);

                                BasicStroke bs1 = new BasicStroke(2, BasicStroke.CAP_BUTT,
                                        BasicStroke.JOIN_BEVEL);
                                g1.setStroke(bs1);
//                                g1.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                                g1.drawLine(points.get(i).x + 4, points.get(i).y + 4, points.get(i + 1).x + 4, points.get(i + 1).y + 4);
                                //                                    }

                            }
                        }
                        //paint max threshold
                        if (((LinearDataContainerInterface) container).isMaxThresholdPaintable()) {
                            int maximumThreshold = (int) ((LinearDataContainerInterface) container).getMaximumThreshold();
                            int yData = this.dataCoordinateMap.get(dataBar);
                            double yyy = dt_range == 0 ? yData : ((maximumThreshold - localMin) * increment) / dt_range;

                            BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_BUTT,
                                    BasicStroke.JOIN_BEVEL);
                            g1.setStroke(bs1);
                            g1.setPaint(Color.RED);
                            int yyyy = yData + increment / 2 - (int) (yyy);
                            Line2D.Float line = new Line2D.Float(sX, (float) yyyy, wX + sX, (float) yyyy);
                            ((Graphics2D) g).draw(line);
                            Font maxFont = new Font("SansSerif", Font.BOLD, 12);
                            llffm = g.getFontMetrics(maxFont);
                            g.setFont(maxFont);
//                            int maxH = llffm.getHeight();
//                            int maxW = llffm.stringWidth("max = "+maximumThreshold);

                            g1.drawString("max = " + maximumThreshold, sX + 10, yyyy - 5);
                        }
                        //paint min threshold
                        if (((LinearDataContainerInterface) container).isMinThresholdPaintable()) {
                            int minimumThreshold = (int) ((LinearDataContainerInterface) container).getMinimumThreshold();
                            int yData = this.dataCoordinateMap.get(dataBar);
                            double yyy = dt_range == 0 ? yData : ((minimumThreshold - localMin) * increment) / dt_range;

                            BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_BUTT,
                                    BasicStroke.JOIN_BEVEL);
                            g1.setStroke(bs1);
                            g1.setPaint(new Color(137, 0, 0));
                            int yyyy = yData + increment / 2 - (int) (yyy);
                            Line2D.Float line = new Line2D.Float(sX, (float) yyyy, wX + sX, (float) yyyy);
                            ((Graphics2D) g).draw(line);
                            Font minFont = new Font("SansSerif", Font.BOLD, 12);
                            llffm = g.getFontMetrics(minFont);
                            g.setFont(minFont);
                            g1.drawString("min = " + minimumThreshold, sX + 10, yyyy - 5);
                        }

                    }
                    //</editor-fold>
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="BOOLEAN DATA">
                    if (BooleanDataContainer.class.isInstance(container)) {
                        Map map = new HashMap();
                        if (((BooleanDataContainer) container).getLabelByBooleanValue(dataBar, true) != null) {
                            map.put(((BooleanDataContainer) container).getLabelByBooleanValue(dataBar, true), standarTrueColor);
                        }
                        if (((BooleanDataContainer) container).getLabelByBooleanValue(dataBar, false) != null) {
                            map.put(((BooleanDataContainer) container).getLabelByBooleanValue(dataBar, false), standarFalseColor);
                        }
                        this.legendMap.put(dataBar, map);
//                        System.out.println("SPOTTED : " + dataBar);
                        int yData = this.dataCoordinateMap.get(dataBar);
                        RoundRectangle2D.Float rRect = new RoundRectangle2D.Float(sX, yData - bWidht, wX, bWidht * 2, 0, 0);
                        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g1.setPaint(standarFalseColor);
                        g1.fill(rRect);
                        List<TimeBooleanDataInterface> values = ((BooleanDataContainer) container).getValues();
                        for (int i = 0; i < values.size(); i++) {

                            float startDataX = (((values.get(i).getTimeStamp().getTime() - startRange) * wX) / data_range);
                            if (startDataX < 0) {
                                startDataX = -10;
                            }
//                            if (startDataX + sX > eX && i!=0) {
//                                break;
//                            }

                            if (i == 0 && !values.get(i).isTrue()) {
                                rRect = new RoundRectangle2D.Float(sX, yData - bWidht, startDataX, bWidht * 2, 0, 0);
                                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                g1.setPaint(this.standarTrueColor);
                                g1.fill(rRect);

                            }
                            if (i == values.size() - 1 && values.get(i).isTrue()) {
                                rRect = new RoundRectangle2D.Float(sX + startDataX, yData - bWidht, eX - sX - startDataX, bWidht * 2, 0, 0);
                                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                g1.setPaint(this.standarTrueColor);
                                g1.fill(rRect);
                                break;

                            }
                            if (i == values.size() - 1) {
                                break;
                            }

                            int endDataX = (int) (((values.get(i + 1).getTimeStamp().getTime() - startRange) * wX) / data_range);
                            if (sX + endDataX > eX) {
                                endDataX = eX - sX;
                            }

                            rRect = new RoundRectangle2D.Float(sX + startDataX, yData - bWidht, endDataX - startDataX, bWidht * 2, 0, 0);
                            g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            g1.setPaint(values.get(i).isTrue() ? this.standarTrueColor : this.standarFalseColor);
                            g1.fill(rRect);

                        }

                    }
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="STATE DATA">
                    if (MultiStateContainerInterface.class.isInstance(container)) {
                        Set<String> possibleStates = ((MultiStateContainerInterface) container).getPossibleStates();
                        Map map = new HashMap();
                        for (String state : possibleStates) {
                            map.put(state, ((MultiStateContainerInterface) container).getColorByState(state));
                        }
                        this.legendMap.put(dataBar, map);
//                        System.out.println("SPOTTED : " + dataBar);
                        int yData = this.dataCoordinateMap.get(dataBar);
                        //BASE RECTANGLE
                        RoundRectangle2D.Float base = new RoundRectangle2D.Float(sX, yData - bWidht, wX, bWidht * 2, 0, 0);
                        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g1.setPaint(((MultiStateContainerInterface) container).getDefault());
                        g1.fill(base);
                        List<StateDataSupporter> values = ((MultiStateContainerInterface) container).getValues();
                        for (int i = 0; i < values.size(); i++) {

                            float startDataX = (((values.get(i).getTimeStamp().getTime() - startRange) * wX) / data_range);
                            if (startDataX < 0) {
                                startDataX = -10;
                            }
                            if (startDataX + sX > eX) {
                                break;
                            }

//                            if (i == 0) {
//                                RoundRectangle2D.Float rRect = new RoundRectangle2D.Float(sX, yData - bWidht, startDataX, bWidht * 2, 0, 0);
//                                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                                g1.setPaint(((MultiStateContainerInterface)container).getColorByState(values.get(i).getState()));
//                                g1.fill(rRect);
//
//                            }
                            if (i == values.size() - 1) {
                                RoundRectangle2D.Float rRect = new RoundRectangle2D.Float(sX + startDataX, yData - bWidht, eX - sX - startDataX, bWidht * 2, 0, 0);
                                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                g1.setPaint(((MultiStateContainerInterface) container).getColorByState(values.get(i).getState()));
                                g1.fill(rRect);
                                break;

                            }
                            if (i == values.size() - 1) {
                                break;
                            }

                            int endDataX = (int) (((values.get(i + 1).getTimeStamp().getTime() - startRange) * wX) / data_range);
                            if (sX + endDataX > eX) {
                                endDataX = eX - sX;
                            }

                            RoundRectangle2D.Float rRect = new RoundRectangle2D.Float(sX + startDataX, yData - bWidht, endDataX - startDataX, bWidht * 2, 0, 0);
                            g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            g1.setPaint(((MultiStateContainerInterface) container).getColorByState(values.get(i).getState()));
                            g1.fill(rRect);

                        }

                    }
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="IMPULSIVE BOOLEAN DATA">
                    if (ImpulsiveBooleanDataContainer.class.isInstance(container)) {
                        long idleTime = ((ImpulsiveBooleanDataContainer) container).getIdleTime();
                        Map map = new HashMap();
                        if (((ImpulsiveBooleanDataContainer) container).getLabelByBooleanValue(dataBar, true) != null) {
                            map.put(((ImpulsiveBooleanDataContainer) container).getLabelByBooleanValue(dataBar, true), standarTrueColor);
                        }
                        if (((ImpulsiveBooleanDataContainer) container).getLabelByBooleanValue(dataBar, false) != null) {
                            map.put(((ImpulsiveBooleanDataContainer) container).getLabelByBooleanValue(dataBar, false), standarFalseColor);
                        }
                        this.legendMap.put(dataBar, map);
//                        System.out.println("SPOTTED : " + dataBar);
                        int yData = this.dataCoordinateMap.get(dataBar);
                        RoundRectangle2D.Float rRect = new RoundRectangle2D.Float(sX, yData - bWidht, wX, bWidht * 2, 0, 0);
                        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g1.setPaint(standarFalseColor);
                        g1.fill(rRect);
                        List<TimeDataInterface> values = ((ImpulsiveBooleanDataContainer) container).getValues();
                        for (int i = 0; i < values.size(); i++) {

                            float startDataX = (((values.get(i).getTimeStamp().getTime() - startRange) * wX) / data_range);
                            if (startDataX < 0) {
                                startDataX = -10;
                            }
                            if (startDataX + sX > eX) {
                                break;
                            }

//                            if (i == 0 && !values.get(i).isTrue()) {
//                                rRect = new RoundRectangle2D.Float(sX, yData - bWidht, startDataX, bWidht * 2, 0, 0);
//                                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                                g1.setPaint(this.standarTrueColor);
//                                g1.fill(rRect);
//
//                            }
//                            if (i == values.size() - 1 && values.get(i).isTrue()) {
//                                rRect = new RoundRectangle2D.Float(sX + startDataX, yData - bWidht, eX - sX - startDataX, bWidht * 2, 0, 0);
//                                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                                g1.setPaint(this.standarTrueColor);
//                                g1.fill(rRect);
//                                break;
//
//                            }
//                            if (i == values.size() - 1) {
//                                break;
//                            }
                            int endDataX = (int) (((values.get(i).getTimeStamp().getTime() + idleTime - startRange) * wX) / data_range);
                            if (sX + endDataX > eX) {
                                endDataX = eX - sX;
                            }
                            if (endDataX == startDataX) {
                                rRect = new RoundRectangle2D.Float(sX + startDataX, yData - bWidht, 1, bWidht * 2, 0, 0);
                                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                g1.setPaint(this.standarTrueColor); //era giallo 
                                g1.fill(rRect);
                            } else {

                                rRect = new RoundRectangle2D.Float(sX + startDataX, yData - bWidht, endDataX - startDataX, bWidht * 2, 0, 0);
                                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                g1.setPaint(this.standarTrueColor);
                                g1.fill(rRect);
                            }

                        }

                    }
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CUMULATIVE DATA">
                    if (CumulativeDataContainer.class.isInstance(container)) {
                        ((CumulativeDataContainer) container).setStartRange(startRange);
                        int max = ((CumulativeDataContainer) container).getMax();
                        int augment = 125 / max;
                        int yData = this.dataCoordinateMap.get(dataBar);
                        RoundRectangle2D.Float rRect = new RoundRectangle2D.Float(sX, yData - bWidht, wX, bWidht * 2, 0, 0);
                        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g1.setPaint(standarFalseColor);
                        g1.fill(rRect);
                        LinearDataContainerInterface<TimeValueSupporterClass> extractData = ((CumulativeDataContainer) container).extractData();
                        if (extractData == null) {
                            continue;
                        }
                        List<TimeValueSupporterClass> values = extractData.getValuesMap().get(((CumulativeDataContainer) container).getDefaultSubchart());

//                        values.add(0,new TimeValueSupporterClass(((CumulativeDataContainer) container).getVerystartValue(), ((CumulativeDataContainer) container).getDefaultSubchart(), new Date(this.startRange)));
                        for (int i = 0; i < values.size(); i++) {

                            float startDataX = (((values.get(i).getTimeStamp().getTime() - startRange) * wX) / data_range);
                            if (startDataX < 0) {
                                startDataX = -10;
                            }
                            if (startDataX + sX > eX) {
                                break;
                            }
                            int endDataX = 0;
                            if (i == values.size() - 1) {

                                if (values.get(i).getValue() > 0) {
                                    endDataX = eX - sX;
                                } else {
                                    endDataX = (int) (((values.get(i).getTimeStamp().getTime() - startRange) * wX) / data_range);
                                    if (sX + endDataX > eX) {
                                        endDataX = eX - sX;
                                    }
                                }
                            } else {
                                endDataX = (int) (((values.get(i + 1).getTimeStamp().getTime() - startRange) * wX) / data_range);
                                if (sX + endDataX > eX) {
                                    endDataX = eX - sX;
                                }
                            }

                            rRect = new RoundRectangle2D.Float(sX + startDataX, yData - bWidht, endDataX - startDataX, bWidht * 2, 0, 0);
                            g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            if (values.get(i).getValue() == 0) {
//                                System.out.println("non c'è nessuno");
                                g1.setPaint(this.standarFalseColor);
                            } else {
//                                System.out.println("c'è: "+values.get(i).getValue());
                                int cc = (int) (augment * values.get(i).getValue());
//                                System.out.println("cc = "+cc);
//                                System.out.println("max diviso 3 = "+(max/3));
//                                if(values.get(i).getValue() <= max/3){
//                                    System.out.println("GIALO");
//                                    g1.setPaint(new Color(255,125+cc,0));
//                                }else{
                                try {
                                    g1.setPaint(new Color(0, 125 + cc, 0));
                                } catch (Exception ex) {
                                    System.out.println("bad color value: " + 125 + cc);
                                    g1.setPaint(Color.BLACK);
                                }
//                                }
                            }

                            g1.fill(rRect);

                        }
                    }
                    //</editor-fold>
                }
            }

//            for (ContainerDataInterface containerDataInterface : orderedDatabars) {
            for (String databar : MAIN_ORDERED_KEY_SET) {
//                String databar = containerDataInterface.getName();
                if (!isAnnotationVisible(databar)) {
                    continue;
                }
                List<ICVAnnotation> annotations = this.annotationMap.get(databar);
                if (annotations != null) {
                    if (!areasListMap.containsKey(databar)) {
                        areasListMap.put(databar, new ArrayList<AnnotationArea>());
                    } else {
                        areasListMap.get(databar).clear();
                    }
                    int yData = this.dataCoordinateMap.get(databar);
                    for (ICVAnnotation ann : annotations) {
                        int x = sX + (int) (((ann.getWhen() - startRange) * wX) / data_range);
                        int disc = (int) bWidht > 24 ? 24 : (int) bWidht;
//                        JLabel label = new JLabel(new ImageIcon(ann.getImage()));
//                        this.add(label);
//                        label.setBounds(x - disc, yData - disc, (int) disc * 2, (int) disc * 2);
                        g1.drawImage(ann.getImage(), x - disc, yData - disc, (int) disc * 2, (int) disc * 2, null);
                        areasListMap.get(databar).add(new AnnotationArea(new Rectangle(x - disc, yData - disc, (int) disc * 2, (int) disc * 2), ann));

                        if (ann instanceof ICVMappableAnnotation) {
                            this.idAnnotationCenterPointMap.put(((ICVMappableAnnotation) ann).getId(), new Point(x, yData));
//                            if (!((ICVMappableAnnotation) ann).getConditions().isEmpty()) {
//                                List<ICVCondition> conditions = ((ICVMappableAnnotation) ann).getConditions();
//                                int startingY = yData - disc;
//                                for (ICVCondition condition : conditions) {
//                                    if (condition instanceof ICVBooleanStateCondition) {
//                                        boolean desiderateState = ((ICVBooleanStateCondition) condition).isDesiredState();
//                                        String conditionName = desiderateState ? ((ICVBooleanStateCondition) condition).getPositiveConditionName() : ((ICVBooleanStateCondition) condition).getNegativeConditionName();
//                                        g1.setRenderingHint(
//                                                RenderingHints.KEY_TEXT_ANTIALIASING,
//                                                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//
//                                        g1.setRenderingHint(
//                                                RenderingHints.KEY_TEXT_ANTIALIASING,
//                                                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
//                                        Font ff2 = new Font("Tahoma", Font.BOLD, 9);
//                                        FontMetrics fm3 = g.getFontMetrics(ff2);
//                                        g1.setPaint(desiderateState ? new Color(91,140,15) : standarFalseColor);
//                                        g1.setFont(ff2);
//                                        int s_width = fm3.stringWidth("& " + conditionName);
//                                        g1.drawString("& " + conditionName, x - s_width / 2, startingY);
//                                        startingY -= (fm3.getHeight() + 4);
//
//                                    }
//
//                                }
//                            }
                        }

                        if (ann.getDescription() != null && !ann.getDescription().isEmpty() && ann.isDescriptionVisible()) {
                            Font ff2 = new Font("SansSerif", Font.BOLD, 10);
                            FontMetrics fm3 = g.getFontMetrics(ff2);
                            g1.setPaint(Color.BLACK);
                            g.setFont(ff2);
                            int s_width = fm3.stringWidth(ann.getDescription());
                            g1.drawString(ann.getDescription(), x - s_width / 2, yData + disc + 10);
                        }

                    }
                }

                //TO CONTINUE
            }

            Rectangle2D rect1 = new Rectangle2D.Double(0, sY, sX, sY + hY);
            g1.setPaint(backgroundChartColor);
            g1.fill(rect1);

            Rectangle2D rect2 = new Rectangle2D.Double(eX + 1, sY, RIGHT_MARGIN * 2, sY + hY);
            g1.setPaint(backgroundChartColor);
            g1.fill(rect2);

            g1.setPaint(yAxisDataColor);
            for (Float yl : this.yValuesLinearMap.keySet()) {
//                System.out.println("drawing : "+this.yValuesLinearMap.get(yl));
//                System.out.println("y1 -> "+yl);
                ((Graphics2D) g).drawString(this.yValuesLinearMap.get(yl), eX + 3, yl);
            }
//                                        ((Graphics2D) g).drawString("hi", eX+3, highY+llffmHeight/2);
//                            ((Graphics2D) g).drawString("50", eX+3, mediumY+llffmHeight/2);
//                            ((Graphics2D) g).drawString("50", eX+3, lowY+llffmHeight/2);

            int asdr = 0;
            Stroke drawingStroke = new BasicStroke(1.8f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
            ((Graphics2D) g).setStroke(drawingStroke);
            int minimumCharStart = Integer.MAX_VALUE;
            int grandChar = 0;
//            for (ContainerDataInterface containerDataInterface : orderedDatabars) {
            for (String dataBar : MAIN_ORDERED_KEY_SET) {
//                String dataBar = containerDataInterface.getName();
                String x = new String(dataBar);
                if (dataBar.length() > maxCharLabel) {
                    x = x.substring(0, maxCharLabel);
                }
                Font ff3 = new Font("SansSerif", Font.BOLD, 14);
                FontMetrics fm2 = g.getFontMetrics(ff3);
                grandChar = fm2.getHeight();
                g.setFont(ff3);
                int s_width = fm2.stringWidth(x);
//                if (LABEL_LENGHT < s_width) {
//                    LABEL_LENGHT = s_width;
//                }
//                if (sX < s_width) {
//                    sX = LABEL_LENGHT + LEFT_MARGIN;
//                    eX = sX + wX;
//                }
//                int act_x = sX - 10 - s_width;
//                if (start_y == -1) {
//                    start_y = sY + (hY / (keysCount * 2));
//                }
//                this.dataCoordinateMap.put(dataBar, start_y);
                g1.setPaint(yAxisDataColor);
                int act_x = sX - 10 - s_width;
                if (act_x < minimumCharStart) {
                    minimumCharStart = act_x;
                }
                g.drawString(x, act_x, this.dataCoordinateMap.get(dataBar) - (int) increment / 2 + grandChar);
            }

            for (String dataBar : MAIN_ORDERED_KEY_SET) {
//            for (ContainerDataInterface containerDataInterface : orderedDatabars) {
//                String dataBar = containerDataInterface.getName();
                if (!dividerUnderDatabarToHide.contains(dataBar)) {
                    if (asdr != MAIN_ORDERED_KEY_SET.size() - 1 && separatorVisible) {
                        g1.setPaint(separatorLineColor);
                        g.drawLine(minimumCharStart, this.dataCoordinateMap.get(dataBar) + increment / 2, sX + wX + LEFT_MARGIN / 2, this.dataCoordinateMap.get(dataBar) + increment / 2);
                    }
                }
                if (this.legendMap.containsKey(dataBar)) {
                    Map<String, Color> legend = this.legendMap.get(dataBar);
                    float legY = this.dataCoordinateMap.get(dataBar) - (int) increment / 2 + grandChar;
                    float consumableTotalYSpace = increment - grandChar;
                    float consumableLocalYSpace = consumableTotalYSpace / legend.size();
                    for (String label : legend.keySet()) {
                        float space = consumableLocalYSpace * 0.40f < 20 ? consumableLocalYSpace * 0.40f : 20;
                        float side = consumableLocalYSpace * 0.35f < 20 ? consumableLocalYSpace * 0.35f : 20;
                        Rectangle2D.Float lRect = new Rectangle2D.Float(sX - 10 - space, legY + space, side, side);
                        g1.setPaint(legend.get(label));
                        g1.fill(lRect);
                        legY += space + side;
                        g1.setPaint(xAxisDataColor);
                        //settare il font
                        Font ff3 = new Font("SansSerif", Font.PLAIN, (int) (consumableLocalYSpace * 0.35f) > 12 ? 12 : (int) (consumableLocalYSpace * 0.35f));
                        FontMetrics fm2 = g.getFontMetrics(ff3);
                        grandChar = fm2.getHeight();
                        g.setFont(ff3);
                        g.drawString(label, (int) (sX - 10 - space - fm2.stringWidth(label) - 5), (int) (legY - grandChar / 4));
                    }
                }

                start_y += increment;
                asdr++;
//                col = !col;
            }

            //arrows  ----------------------------------------->
            if (arrowsVisible) {

                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                for (ICVAnnotationLink iCVAnnotationLink : links) {
                    ICVMappableAnnotation startingAnnotation = iCVAnnotationLink.getStartingAnnotation();
                    ICVMappableAnnotation endingAnnotation = iCVAnnotationLink.getEndingAnnotation();
                    if (idAnnotationCenterPointMap.containsKey(startingAnnotation.getId()) && idAnnotationCenterPointMap.containsKey(endingAnnotation.getId())) {
                        //dot
                        Point startArrowPoint = idAnnotationCenterPointMap.get(startingAnnotation.getId());
                        Point endArrowPoint = idAnnotationCenterPointMap.get(endingAnnotation.getId());
                        long s1 = startingAnnotation.getWhen();
                        long s2 = endingAnnotation.getWhen();
//                        if (s1 != null && s2 != null) {
                        long diff = s2 - s1;
                        long seconds = diff / 1000 % 60;
                        long min = diff / (60 * 1000) % 60;
                        long hours = diff / (60 * 60 * 1000) % 24;
                        long d = diff / (24 * 60 * 60 * 1000);

//                        }
                        g1.setPaint(iCVAnnotationLink.getArrowColor());
                        Ellipse2D.Double circle = new Ellipse2D.Double(startArrowPoint.x - 3, startArrowPoint.y - 3, 6, 6);
                        g1.fill(circle);
                        //line
                        double w = Math.abs(startArrowPoint.x - endArrowPoint.x);
                        double h = Math.abs(startArrowPoint.y - endArrowPoint.y);
                        double x = Math.min(startArrowPoint.x, endArrowPoint.x);
                        double y = Math.min(startArrowPoint.y, endArrowPoint.y);
                        Rectangle2D.Double bounds = new Rectangle2D.Double(x, y, w, h);
                        Path2D.Double path = new Path2D.Double();
                        double len = Math.sqrt((startArrowPoint.x - endArrowPoint.x) * (startArrowPoint.x - endArrowPoint.x) + (startArrowPoint.y - endArrowPoint.y) * (startArrowPoint.y - endArrowPoint.y));

                        path.moveTo(0, 0);
                        path.lineTo(len - 10, 0);
                        path.setWindingRule(Path2D.WIND_NON_ZERO);
                        Path2D.Double line = path;
                        Path2D.Double head;

                        path = new Path2D.Double();
                        path.moveTo(len - 10, 0);
                        path.lineTo(len - 10, -3);
                        path.lineTo(len, 0);
                        path.lineTo(len - 10, +3);
                        path.closePath();
                        head = path;

                        AffineTransform at = AffineTransform.getRotateInstance(endArrowPoint.x - startArrowPoint.x, endArrowPoint.y - startArrowPoint.y, startArrowPoint.x, startArrowPoint.y);
                        at.concatenate(AffineTransform.getTranslateInstance(startArrowPoint.x, startArrowPoint.y));
                        line.transform(at);
                        head.transform(at);
                        g1.setPaint(iCVAnnotationLink.getArrowColor());
                        g1.draw(line);
                        g1.fill(head);

                        Font font = new Font("Verdana", Font.BOLD, 10);
                        g1.setFont(font);
                        FontMetrics cfm = g1.getFontMetrics(font);
                        String ansns = null;
                        if (endingAnnotation.getTriggerText() != null) {
                            ansns = "for Answer: " + endingAnnotation.getTriggerText();
                        }
                        String distance = d + "d " + hours + "h " + min + "m " + seconds + " ss";
                        int distanceTextLenght = cfm.stringWidth(distance);
                        if (endingAnnotation.getTriggerText() != null) {
                            distanceTextLenght = cfm.stringWidth(ansns) > cfm.stringWidth(distance) ? cfm.stringWidth(ansns) : cfm.stringWidth(distance);
                        }
                        int beautyDistance = 0;
                        if (startArrowPoint.y == endArrowPoint.y) {
                            beautyDistance = distanceTextLenght / 2;
                        }
                        RoundRectangle2D.Float r2r = new RoundRectangle2D.Float(
                                startArrowPoint.x + ((endArrowPoint.x - startArrowPoint.x) / 2) - 3 - beautyDistance,
                                ansns != null ? startArrowPoint.y + ((endArrowPoint.y - startArrowPoint.y) / 2) - cfm.getHeight() * 2 - 6 : startArrowPoint.y + ((endArrowPoint.y - startArrowPoint.y) / 2) - cfm.getHeight() - 6,
                                distanceTextLenght + 6,
                                ansns != null ? cfm.getHeight() * 2 : cfm.getHeight(),
                                6,
                                6
                        );
                        g1.setPaint(new Color(255, 255, 255, 200));
                        g1.fill(r2r);
                        g1.setPaint(iCVAnnotationLink.getArrowColor());

                        if (ansns != null) {
                            g1.drawString(ansns, startArrowPoint.x + ((endArrowPoint.x - startArrowPoint.x) / 2) - beautyDistance, startArrowPoint.y + ((endArrowPoint.y - startArrowPoint.y) / 2) - 25);
                        }
                        g1.drawString(distance, startArrowPoint.x + ((endArrowPoint.x - startArrowPoint.x) / 2) - beautyDistance, startArrowPoint.y + ((endArrowPoint.y - startArrowPoint.y) / 2) - 10);

                    }

                }

                for (ICVArrow iCVArrow : arrows) {
                    //draw start dot
                    Ellipse2D.Double circle = new Ellipse2D.Double(iCVArrow.getStart().x - 2, iCVArrow.getStart().y - 2, 4, 4);
                    g1.fill(circle);
                    //draw line
                    //draw oriented triangle
                }
            }

//            for (ContainerDataInterface containerDataInterface : orderedDatabars) {
//                String databar = containerDataInterface.getName();
            for (String databar : MAIN_ORDERED_KEY_SET) {
                if (!isAnnotationVisible(databar)) {
                    continue;
                }
                int yData = this.dataCoordinateMap.get(databar);

//                if (!areasListMap.containsKey(databar)) {
//                        areasListMap.put(databar, new ArrayList<AnnotationArea>());
//                    } else {
//                        areasListMap.get(databar).clear();
//                    }
                List<ICVAnnotation> annotations = this.annotationMap.get(databar);
                if (annotations == null) {
                    continue;
                }
                for (ICVAnnotation ann : annotations) {

                    if (ann instanceof ICVMappableAnnotation) {
                        int x = sX + (int) (((ann.getWhen() - startRange) * wX) / data_range);
                        int disc = (int) bWidht > 24 ? 24 : (int) bWidht;
//                        this.idAnnotationCenterPointMap.put(((ICVMappableAnnotation) ann).getId(), new Point(x, yData));
                        Font ff2 = new Font("Tahoma", Font.BOLD, 9);
                        FontMetrics fm3 = g.getFontMetrics(ff2);
                        if (!((ICVMappableAnnotation) ann).getConditions().isEmpty()) {
                            List<ICVCondition> conditions = ((ICVMappableAnnotation) ann).getConditions();
                            int max = 0;
                            for (ICVCondition condition : conditions) {
                                if (condition instanceof ICVBooleanStateCondition) {
                                    boolean desiderateState = ((ICVBooleanStateCondition) condition).isDesiredState();
                                    String conditionName = desiderateState ? ((ICVBooleanStateCondition) condition).getPositiveConditionName() : ((ICVBooleanStateCondition) condition).getNegativeConditionName();
                                    int s_width = fm3.stringWidth("& " + conditionName);
                                    if (s_width > max) {
                                        max = s_width;
                                    }
                                }
                            }
                            RoundRectangle2D.Float r2r = new RoundRectangle2D.Float(
                                    x - max / 2,
                                    yData - disc - (conditions.size() * fm3.getHeight()),
                                    max,
                                    (conditions.size() * fm3.getHeight()),
                                    6,
                                    6
                            );
                            g1.setPaint(new Color(240, 248, 0, 200));
                            g1.fill(r2r);

                            int startingY = yData - disc;

                            for (ICVCondition condition : conditions) {
                                if (condition instanceof ICVBooleanStateCondition) {
                                    boolean desiderateState = ((ICVBooleanStateCondition) condition).isDesiredState();
                                    String conditionName = desiderateState ? ((ICVBooleanStateCondition) condition).getPositiveConditionName() : ((ICVBooleanStateCondition) condition).getNegativeConditionName();

//                                    g1.setPaint(iCVAnnotationLink.getArrowColor());
                                    g1.setRenderingHint(
                                            RenderingHints.KEY_TEXT_ANTIALIASING,
                                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                                    g1.setRenderingHint(
                                            RenderingHints.KEY_TEXT_ANTIALIASING,
                                            RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

                                    g1.setPaint(desiderateState ? new Color(91, 140, 15) : standarFalseColor);
                                    g1.setFont(ff2);
                                    int s_width = fm3.stringWidth("& " + conditionName);
//                                    if(s_width > max){
//                                        max = s_width;
//                                    }
                                    g1.drawString("& " + conditionName, x - s_width / 2, startingY);
                                    startingY -= (fm3.getHeight());

                                }

                            }
                        }
                    }
                }
            }

            //start ZOOM
//            if (zoomEnable && startSelection != -1 && endSelection != -1) {
//
//                Rectangle2D zoom = new Rectangle2D.Double(startSelection, sY, Math.abs(endSelection - startSelection), hY);
//                g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
//                        0.3f));
//                g1.setPaint(Color.BLUE);
//                g1.fill(zoom);
//
//            }
//             bufferedImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
//        Graphics2D cg = bImg.createGraphics();
//        this.paintAll(cg);
//             System.out.println("need backup : "+needBackup);
            if (bufferedImg == null && needBackup) {
                bufferedImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                this.printAll(bufferedImg.getGraphics());
//                System.out.println("created backup");
                needBackup = false;
            }
            if (minimum_red != Long.MIN_VALUE) {
                int xByDate = getXByDate(new Date(minimum_red));
                Rectangle2D.Float minRED = new Rectangle2D.Float(sX, sY, xByDate, hY);
                g1.setPaint(new Color(255, 0, 0, 200));
                g1.fill(minRED);
            }
            if (maximum_red != Long.MAX_VALUE) {
                int xByDate = getXByDate(new Date(maximum_red));
                Rectangle2D.Float minRED = new Rectangle2D.Float(sX + xByDate, sY, eX - (xByDate + sX), hY);
                g1.setPaint(new Color(255, 0, 0, 200));
                g1.fill(minRED);
            }

            if (nowLineVisible) {
                Date now;
                if (floatableNow != Long.MIN_VALUE) {
                    now = new Date(this.floatableNow);
                } else {
                    now = new Date();
                }

                int xNow = getXByDate(now);
//                (int) (((time - startRange) * wX) / data_range);
                BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL);
                g1.setStroke(bs1);
                g1.setPaint(Color.BLACK);
                Line2D.Float line = new Line2D.Float((float) sX + xNow, (float) sY, (float) sX + xNow, (float) eY);
                g1.draw(line);
                Font nowFont = new Font("Lucida Console", Font.PLAIN, 12);
                FontMetrics fontMetrics = g1.getFontMetrics(nowFont);
                g1.setRenderingHint(
                        RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
                int stringWidth = fontMetrics.stringWidth(NOW);
                g1.setFont(nowFont);

                RoundRectangle2D.Float rr = new RoundRectangle2D.Float((float) sX + xNow - stringWidth / 2 - 4, (float) sY + 1, (float) stringWidth * 1.5f, fontMetrics.getHeight() + 4, 5, 5);
                g1.setPaint(Color.BLACK);
                g1.fill(rr);
                g1.setPaint(Color.WHITE);
                g1.drawString(NOW, (float) sX + xNow - stringWidth / 2, (float) sY + fontMetrics.getHeight());

            }

            for (Date timepoint : extraRectangles) {
                int middleX = this.getXByDate(timepoint);
                Rectangle ret = new Rectangle(middleX - 15 + sX, sY + 10, 30, hY - 20);
                RoundRectangle2D.Float r2r = new RoundRectangle2D.Float(
                        ret.x,
                        ret.y,
                        ret.width,
                        ret.height,
                        6,
                        6
                );
                Stroke recStroke = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
                g1.setStroke(recStroke);
                g1.setPaint(extraRectangleColor);
                g1.draw(r2r);
                g1.setPaint(new Color(0f, 0f, 1f, 0.2f));
                g1.fill(r2r);
            }

            Rectangle visibleRect = getVisibleRect();
            startVisibleY = visibleRect.y;

        } catch (ArithmeticException exc) {
            System.out.println("division by zero detected and ignored :) [double click]");
            exc.printStackTrace();
            resetZoom();
        }
    }

//    private void generateHeadImg() {
//        headImg = new BufferedImage(this.getWidth(), sY, BufferedImage.TYPE_INT_RGB);
//        this.printAll(headImg.getGraphics());
//    }
    public BufferedImage getHeadImg() {
        return headImg;
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
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void manageZoom() {
        if (!isZoomEnable()) {
            return;
        }
//        System.out.println("Start range = " + new Date(startRange));
        if (dataMap.isEmpty()) {
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
        for (ZoomListener zoomListener : zoomListeners) {
            zoomListener.zoomSelected();
        }

    }

    public String getDatabarFromY(int y) {
        int[] initPoints = new int[dataCoordinateMap.size()];

        Set<String> keySet = dataCoordinateMap.keySet();
        Map<Integer, String> reverseMap = new HashMap<Integer, String>();
        int i = 0;
        for (String string : keySet) {
            reverseMap.put(dataCoordinateMap.get(string), string);
            initPoints[i] = this.dataCoordinateMap.get(string);
            i++;
        }
        Arrays.sort(initPoints);
        int increment = 0;
        try {
            increment = (hY / dataCoordinateMap.size()) < this.minimumIncrement ? this.minimumIncrement : (hY / dataCoordinateMap.size());
        } catch (ArithmeticException ex) {
//            System.out.println("[ICV] divisio by zero");
            return null;
        }

        if (y < initPoints[0] - increment / 2 && y > initPoints[dataCoordinateMap.size() - 1] + increment / 2) {
            System.out.println("[icv] y value is external to interval");
            return null;
        } else {
            for (int j = 0; j < dataCoordinateMap.size(); j++) {
                if (j == dataCoordinateMap.size() - 1) {
                    if (y <= initPoints[j] + increment / 2) {
                        String databar = reverseMap.get(initPoints[j]);
                        return databar;
                    }
                } else if (y >= initPoints[j] - increment / 2 && y <= initPoints[j + 1] - increment / 2) {
                    String databar = reverseMap.get(initPoints[j]);
                    return databar;
                }
            }
        }
        return null;
    }

    public Date getStartDataSelection() {
        long data_range = endRange - startRange;
        return new Date(startRange + ((startSelection - sX) * data_range) / wX);
    }

    public Date getEndDataSelection() {
        long data_range = endRange - startRange;
        return new Date(startRange + ((endSelection - sX) * data_range) / wX);
    }

    private void formMousePressed(MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        boolean inChartArea = isInChartArea(evt.getX(), evt.getY());
        if (inChartArea) {
            startSelection = evt.getX();
            endSelection = evt.getX();
            if (annotationDraggable && evt.getButton() == MouseEvent.BUTTON1) {
                String databarFromY = getDatabarFromY(evt.getY());
                if (areasListMap.containsKey(databarFromY)) {
                    List<AnnotationArea> areas = areasListMap.get(databarFromY);
                    for (AnnotationArea area : areas) {
                        if (isInThisArea(evt.getX(), evt.getY(), area.getArea())) {
                            System.out.println("Annotation: " + area.getAnnotation().getDescription());
                            if (area.getAnnotation() instanceof ICVMappableAnnotation) {
                                if (((ICVMappableAnnotation) area.getAnnotation()).getParent() == null) {
                                    continue;
                                }
                            }
                            annotationToDrag = area.getAnnotation();
                            annoDragTempTime = area.getAnnotation().getWhen();
                            break;
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        boolean inChartArea = isInChartArea(evt.getX(), evt.getY());
//        System.out.println("DRAGGED: " + inChartArea);
        dragged = true;
        if (annotationToDrag != null) {
            if (eX - evt.getPoint().x < 30) {
                endRange += ((endRange - startRange) / 200);
                startRange -= (endRange - startRange) / 5000;
            }
        }
        if (inChartArea) {

            if (handScrollEnbaled && !zoomEnable && containsZoomedChart) {
                if (lastX == -1) {
                    lastX = evt.getX();
                }
//                if(lastY == -1){
//                    lastY = evt.getY();
//                }

                if (evt.getX() >= endSelection) {
//                System.out.println("                                            verso destra");
                    endSelection = evt.getX();
                    op = false;
                    //calculate time difference
                    Date startPivotDate = getDateByX(lastX);
                    Date enddateByPoint = getDateByX(evt.getX());
                    long delta = enddateByPoint.getTime() - startPivotDate.getTime();

//                     System.out.println("delta = "+delta);
                    startRange = (this.getStartRange() - delta);
                    endRange = (this.getEndRange() - delta);
                    this.repaint();
//                    return;

                } else {

                    if (evt.getX() < endSelection && evt.getX() > startSelection) {
                        if (op) {
//                             System.out.println("                                torno indietro sinistra verso destra");
                            startSelection = evt.getX();
//                             Date startPivotDate = getDateByX(startSelection);
//                             Date enddateByPoint = getDateByX(endSelection);
                            Date startPivotDate = getDateByX(lastX);
                            Date enddateByPoint = getDateByX(evt.getX());
                            long delta = enddateByPoint.getTime() - startPivotDate.getTime();
//                             delta = (endRange - startRange) /100;
//                             System.out.println("delta = "+delta);
                            startRange = (this.getStartRange() - delta);
                            endRange = (this.getEndRange() - delta);
                            this.repaint();
//                             return;
                        } else {
//                             System.out.println("                                torno indietro destra-sinistra");
                            endSelection = evt.getX();
                            op = false;
                            Date startPivotDate = getDateByX(evt.getX());
                            Date enddateByPoint = getDateByX(lastX);
                            long delta = enddateByPoint.getTime() - startPivotDate.getTime();
//                             delta = (endRange - startRange) /100;
//                             System.out.println("delta = "+delta);
                            startRange = (this.getStartRange() + delta);
                            endRange = (this.getEndRange() + delta);
                            this.repaint();
//                             return;
                        }
                    } else if (evt.getX() < startSelection) {
//                         System.out.println("                                        oltre la partenza");
                        startSelection = evt.getX();
                        op = true;
                        Date startPivotDate = getDateByX(evt.getX());
                        Date enddateByPoint = getDateByX(lastX);
                        long delta = enddateByPoint.getTime() - startPivotDate.getTime();
//                             delta = (endRange - startRange) /100;
//                         System.out.println("delta = " + delta);
                        startRange = (this.getStartRange() + delta);
                        endRange = (this.getEndRange() + delta);
                        this.repaint();
//                             return;
                    }
                }
                lastX = evt.getX();
            }

            if (zoomEnable && !handScrollEnbaled) {
//            System.out.println("x = " + evt.getX());
//            System.out.println("start selection = " + startSelection);
//            System.out.println("end selection = " + endSelection);
                if (evt.getX() >= endSelection) {
//                System.out.println("                                            verso destra");
                    endSelection = evt.getX();
                    op = false;
                } else {

                    if (evt.getX() < endSelection && evt.getX() > startSelection) {
                        if (op) {
//                            System.out.println("                                torno indietro sinistra verso destra");
                            startSelection = evt.getX();
                        } else {
//                            System.out.println("                                torno indietro destra-sinistra");
                            endSelection = evt.getX();
                            op = false;
                        }
                    } else if (evt.getX() < startSelection) {
//                        System.out.println("                                        oltre la partenza");
                        startSelection = evt.getX();
                        op = true;
                    }
                }
            }

        }
        if (annotationToDrag != null) {
//                System.out.println("da draggare qualcosa");
            Point p = evt.getPoint();
            if (evt.getPoint().x > eX) {
                p.x = eX;
            }
            if (evt.getPoint().x < sX) {
                p.x = sX;
            }
            Date dateByPoint = getDateByPoint(p);
            if (annotationToDrag instanceof ICVMappableAnnotation) {
                ICVMappableAnnotation parent = ((ICVMappableAnnotation) annotationToDrag).getParent();
                if (parent != null) {
                    if (dateByPoint.getTime() >= parent.getWhen() && dateByPoint.getTime() <= maximum_red) {
                        annotationToDrag.setWhen(dateByPoint.getTime());
                        int onmask = CTRL_DOWN_MASK | BUTTON1_DOWN_MASK;
//                            int offmask = CTRL_DOWN_MASK;
                        if ((evt.getModifiersEx() & (onmask)) == onmask) {
                            annotationShiftEnable = false;
                            minimum_red = parent.getWhen();
                            List<ICVMappableAnnotation> children = ((ICVMappableAnnotation) annotationToDrag).getChildren();
                            maximum_red = Long.MAX_VALUE;
                            for (ICVMappableAnnotation iCVMappableAnnotation : children) {
                                if (iCVMappableAnnotation.getWhen() <= maximum_red) {
                                    maximum_red = iCVMappableAnnotation.getWhen();
                                }
                            }

                        } else {
                            minimum_red = Long.MIN_VALUE;
                            maximum_red = Long.MAX_VALUE;
                            annotationShiftEnable = true;
                        }
                        if (annotationShiftEnable) {
                            long delta = annotationToDrag.getWhen() - annoDragTempTime;
                            shiftChildrenInTime((ICVMappableAnnotation) annotationToDrag, delta);
                            annoDragTempTime = annotationToDrag.getWhen();
                        }
                    } else {

//                            System.out.println("non puoi andare indietro nel tempo");
                    }
                }
            }

        }
    }//GEN-LAST:event_formMouseDragged

    private void shiftChildrenInTime(ICVMappableAnnotation baseAnnotation, long howMuch) {
        List<ICVMappableAnnotation> children = baseAnnotation.getChildren();
        if (children.isEmpty()) {
            return;
        }
//        baseAnnotation.setWhen(baseAnnotation.getWhen()+howMuch);
        for (ICVMappableAnnotation child : children) {
            if (child.getWhen() + howMuch > endRange) {
                endRange = child.getWhen() + howMuch + ((endRange - startRange) / 100);
                startRange -= (endRange - startRange) / 5000;
            }
            child.setWhen(child.getWhen() + howMuch);
            shiftChildrenInTime(child, howMuch);
        }
    }
    private void formMouseReleased(MouseEvent evt) {//GEN-FIRST:event_formMouseReleased

        if (annotationToDrag != null) {
            annotationToDrag = null;

        }
        minimum_red = Long.MIN_VALUE;
        maximum_red = Long.MAX_VALUE;

        if (evt.isPopupTrigger()) {
            if (!isInChartArea(evt.getX(), evt.getY())) {
                return;
            }
//            if(areaPopupActive){
//                
//            }
            String databarFromY = getDatabarFromY(evt.getY());
            if (areasListMap.containsKey(databarFromY)) {
                List<AnnotationArea> areas = areasListMap.get(databarFromY);
                for (AnnotationArea area : areas) {
                    if (isInThisArea(evt.getX(), evt.getY(), area.getArea())) {
                        for (PopupMenuTriggerListener popupMenuTriggerListener : popupMenuTriggerListeners) {
                            popupMenuTriggerListener.rightClickAreaTriggered(getDatabarFromY(evt.getY()), area.getAnnotation(), getDateByPoint(evt.getPoint()), evt.getLocationOnScreen().x, evt.getLocationOnScreen().y);
                        }
                        return;
                    }
                }
            }

            for (PopupMenuTriggerListener popupMenuTriggerListener : popupMenuTriggerListeners) {
                popupMenuTriggerListener.rightClickTriggered(getDatabarFromY(evt.getY()), getDateByPoint(evt.getPoint()), evt.getLocationOnScreen().x, evt.getLocationOnScreen().y);
            }
        } else {

            boolean inChartArea = isInChartArea(evt.getX(), evt.getY());
            getDateByPoint(evt.getPoint());
            if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
                if (isZoomEnable()) {
                    resetZoom();
                    return;
                }
            }
            if (handScrollEnbaled) {
                startSelection = -1;
                endSelection = -1;
                lastX = -1;
                dragged = false;
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
        }
    }//GEN-LAST:event_formMouseReleased

    private void formComponentResized(ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentResized

    private Object getValueByY(int y) {
        String databarFromY = getDatabarFromY(y);
        ContainerDataInterface container = dataMap.get(databarFromY);
        Object value = "no data";

        if (LinearDataContainerInterface.class
                .isInstance(container)) {
            int maxValueToShow = ((LinearDataContainerInterface) container).getMaxValueToShow();
            int minValueToShow = ((LinearDataContainerInterface) container).getMinValueToShow();

            try {
                int keysCount = currentVisibleBarCount;
                int increment = (hY / keysCount) < this.minimumIncrement ? this.minimumIncrement : (hY / keysCount);
                int yData = this.dataCoordinateMap.get(databarFromY);
                int dt_range = maxValueToShow - minValueToShow;
                int localMin = minValueToShow;
                //                                        value : dt_rage = x : height - > x = (value*heingt) / dt_range
                //                                        value : dt_rage = x : height - > value = (x*dt_rage)/height
//                int y = yData + increment / 2 - (int) (yyy);
                int yyy = -y + yData + increment / 2;
                double value2 = (yyy * dt_range) / increment;
                return value2 + localMin;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return value;
        } else {
            return null;
        }
    }


    private void formMouseMoved(MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        if (isInChartArea(evt.getX(), evt.getY())) {
            for (CoordinateListener coordinateListener : coordinateListeners) {
                coordinateListener.currentDate(this.getDateByPoint(evt.getPoint()));
                coordinateListener.currentValue(this.getValueByY(evt.getY()));
            }
            String databarFromY = getDatabarFromY(evt.getY());
            if (areasListMap.containsKey(databarFromY)) {
                List<AnnotationArea> areas = areasListMap.get(databarFromY);
                for (AnnotationArea area : areas) {
                    if (isInThisArea(evt.getX(), evt.getY(), area.getArea())) {
                        if (area.getAnnotation().getDescription().isEmpty()) {
//                            System.out.println("NIENTE");
                        } else {
                            for (CoordinateListener coordinateListener : coordinateListeners) {
                                coordinateListener.showTooltip(area.getAnnotation().getDescription(), evt.getPoint().x, evt.getPoint().y);
                            }
//                            System.out.println(area.getAnnotation().getDescription());
                        }
                    }
                }
            }

        } else {
            for (CoordinateListener coordinateListener : coordinateListeners) {
                coordinateListener.currentDate(null);
            }
        }
//        this.repaint();
    }//GEN-LAST:event_formMouseMoved

    public boolean isInThisArea(int x, int y, Rectangle rect) {
        return x >= rect.x && x <= (rect.x + rect.width) && y >= rect.y && y <= (rect.y + rect.height) ? true : false;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
//    class ZoomLayer2 extends LayerUI<JPanel> {
//
//        private boolean mActive;
//        private int mX, mY;
//
//        @Override
//        public void installUI(JComponent c) {
//            super.installUI(c);
//            JLayer jlayer = (JLayer) c;
//            jlayer.setLayerEventMask(
//                    AWTEvent.MOUSE_EVENT_MASK
//                    | AWTEvent.MOUSE_MOTION_EVENT_MASK);
//        }
//
//        @Override
//        public void uninstallUI(JComponent c) {
//            JLayer jlayer = (JLayer) c;
//            jlayer.setLayerEventMask(0);
//            super.uninstallUI(c);
//        }
//
//        @Override
//        public void paint(Graphics g, JComponent c) {
//            Graphics2D g2 = (Graphics2D) g.create();
//
//            // Paint the view.
//            super.paint(g2, c);
//
//            if (mActive) {
//                // Create a radial gradient, transparent in the middle.
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
//            }
//
//            g2.dispose();
//        }
//
//        @Override
//        protected void processMouseEvent(MouseEvent e, JLayer l) {
//            if (e.getID() == MouseEvent.MOUSE_ENTERED) {
//                mActive = true;
//            }
//            if (e.getID() == MouseEvent.MOUSE_EXITED) {
//                mActive = false;
//            }
//            l.repaint();
//        }
//
//        @Override
//        protected void processMouseMotionEvent(MouseEvent e, JLayer l) {
//            Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l);
//            mX = p.x;
//            mY = p.y;
//            l.repaint();
//        }
//    }
    public class AnnotationArea {

        private Rectangle area;
        private ICVAnnotation annotation;

        public AnnotationArea(Rectangle area, ICVAnnotation annotation) {
            this.area = area;
            this.annotation = annotation;
        }

        public Rectangle getArea() {
            return area;
        }

        public void setArea(Rectangle area) {
            this.area = area;
        }

        public ICVAnnotation getAnnotation() {
            return annotation;
        }

        public void setAnnotation(ICVAnnotation annotation) {
            this.annotation = annotation;
        }

    }

    public class ZoomLayerUI extends MyLayer<JComponent> {

        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);

//            if (zoomEnable && startSelection != -1 && endSelection != -1) {
//
//                Graphics2D g1 = (Graphics2D) g.create();
//                Rectangle2D zoom = new Rectangle2D.Double(startSelection, sY, Math.abs(endSelection - startSelection), hY);
//                g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
//                        0.3f));
//                g1.setPaint(Color.BLUE);
//                g1.fill(zoom);
//                g1.dispose();
//
//            }
            Graphics2D g2 = (Graphics2D) g.create();
            int w = c.getWidth();
            int h = c.getHeight();
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, .5f));
            g2.setPaint(new GradientPaint(0, 0, Color.yellow, 0, h, Color.red));
            g2.fillRect(0, 0, w, h);

//            g2.dispose();
        }
    }

}
