/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.logic;

import java.awt.Color;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Luca
 * @param <T>
 */
public interface LinearDataContainerInterface<T extends TimeDataInterface> extends ContainerDataInterface {

    public Map<String, List<T>> getValuesMap();
    
    public boolean isDiscret();
    
    public void setDiscret(boolean discret);
    
    public int getMinValueToShow();
    
    public int getMaxValueToShow();
    
    public double getMaximumThreshold();
    public double getMinimumThreshold();
    
    public boolean isMaxThresholdPaintable();
    public boolean isMinThresholdPaintable();
    
    public void setMinValueToShow(int minValueToShow);
    public void setMaxValueToShow(int maxValueToShow);
    
    public void setDotVisible(boolean visible);
    public boolean isDotVisible();
    
    public void clearData();
    
    public void setColorToSubChart(String subChart, Color color);
    
    public Color getColorBySubChartName(String subChart);
    
    public void setSubChartWithDots(String subChart, boolean dotsVisible);
    
    public boolean isChartWithDots(String subChart);
    
}
