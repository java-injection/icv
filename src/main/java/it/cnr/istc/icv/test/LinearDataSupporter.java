/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import it.cnr.istc.icv.logic.LinearDataContainerInterface;
import it.cnr.istc.icv.logic.TimeDataInterface;
import java.awt.Color;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Luca
 */
public class LinearDataSupporter<T extends TimeDataInterface> implements LinearDataContainerInterface{

    private String name;
    private Map<String, List<T>> dataMap = new HashMap<String, List<T>>();
    private Map<String, Color> dataColor = new HashMap<String,Color>();
    private Map<String, Boolean> dotsMap = new HashMap<String,Boolean>();
    private boolean discret = false;
    private int minValueToShow = 0;
    private int maxValueToShow = 200;
    private double maximumThreshold = 180;
    private double minimumThreshold = 60;
    private boolean maxThresholdPaintable = false;
    private boolean minThresholdPaintable = false;
    private int order = -1;
    private boolean dotVisible = true;
    
    
    
    public LinearDataSupporter() {
    }
    
    public void clear(){
        this.dataMap.clear();
    }
    
    public void setColorToSubChart(String subChart, Color color){
            this.dataColor.put(subChart, color);
    }
    
    public Color getColorBySubChartName(String subChart){
        return this.dataColor.get(subChart);
    }
    
    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean isMaxThresholdPaintable() {
        return maxThresholdPaintable;
    }

    public void setMaxThresholdPaintable(boolean maxThresholdPaintable) {
        this.maxThresholdPaintable = maxThresholdPaintable;
    }

    @Override
    public boolean isMinThresholdPaintable() {
        return minThresholdPaintable;
    }

    public void setMinThresholdPaintable(boolean minThresholdPaintable) {
        this.minThresholdPaintable = minThresholdPaintable;
    }

    @Override
    public void setDotVisible(boolean visible) {
        this.dotVisible = visible;
    }

    @Override
    public boolean isDotVisible() {
        return this.dotVisible;
    }
    
    public LinearDataSupporter(String name) {
        this.name = name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getMaximumThreshold() {
        return maximumThreshold;
    }

    @Override
    public double getMinimumThreshold() {
        return minimumThreshold;
    }

    @Override
    public void setDiscret(boolean discret) {
        this.discret = discret;
    }

    @Override
    public int getMinValueToShow() {
        return minValueToShow;
    }

    public void setMinValueToShow(int minValueToShow) {
        this.minValueToShow = minValueToShow;
    }

    @Override
    public int getMaxValueToShow() {
        return maxValueToShow;
    }

    public void setMaxValueToShow(int maxValueToShow) {
        this.maxValueToShow = maxValueToShow;
    }

    @Override
    public boolean isDiscret() {
        return discret;
    }
    
    
    
    public void addData(T t){
        
        if(!this.dataMap.containsKey(t.getSubName())){
            this.dataMap.put(t.getSubName(), new ArrayList<T>());
        }
        this.dataMap.get(t.getSubName()).add(t);
    }
    
    @Override
    public Map<String, List<T>> getValuesMap() {
        return dataMap;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setMaximumThreshold(double maximumThreshold) {
        this.maximumThreshold = maximumThreshold;
    }

    public void setMinimumThreshold(double minimumThreshold) {
        this.minimumThreshold = minimumThreshold;
    }

    @Override
    public synchronized void clearData() {
//        dataMap.clear();
        Set<String> keySet = dataMap.keySet();
        for (String string : keySet) {
            ((Collection)dataMap.get(string)).clear();
        }
        dataMap.clear();
//        dataColor.clear();
    }

    @Override
    public void setSubChartWithDots(String subChart, boolean dotsVisible) {
        this.dotsMap.put(subChart, dotsVisible);
    }

    @Override
    public boolean isChartWithDots(String subChart) {
       if(this.dotsMap.containsKey(subChart)){
           return this.dotsMap.get(subChart);
       }
       return false;
    }

    
}
