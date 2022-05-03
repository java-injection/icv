/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.test;

import it.cnr.istc.icv.logic.MultiStateContainerInterface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Luca
 */
public class MultiStateContainerSupporter implements MultiStateContainerInterface{
    
    private final Map<String,Color> colorMap = new HashMap<String, Color>();
    private final List<StateDataSupporter> data = new ArrayList<StateDataSupporter>();
    private String name;
    private int order;
    private Color defaultColor = Color.BLACK;

    public MultiStateContainerSupporter(String name) {
        this.name = name;
    }
    
    

    @Override
    public void mapValue(String state, Color color) {
        this.colorMap.put(state, color);
    }

    @Override
    public Color getColorByState(String state) {
        return this.colorMap.get(state);
    }
    
    

    @Override
    public List<StateDataSupporter> getValues() {
        return data;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public void addStateData(StateDataSupporter data) {
        this.data.add(data);
    }

    @Override
    public Set<String> getPossibleStates() {
        return colorMap.keySet();
    }

    @Override
    public Color getDefault() {
        return defaultColor;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }
    
}
