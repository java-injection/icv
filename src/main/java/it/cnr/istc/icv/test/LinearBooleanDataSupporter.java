/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test;

import it.cnr.istc.icv.logic.BooleanDataContainer;
import it.cnr.istc.icv.logic.TimeBooleanDataInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Luca
 */
public class LinearBooleanDataSupporter implements BooleanDataContainer{
    
    private Map<String, Map<Boolean,String>> labelMap = new HashMap<String, Map<Boolean,String>>();
    private String name;
    private List<TimeBooleanDataInterface> values = new ArrayList<TimeBooleanDataInterface>();
    private int order = -1;

    public LinearBooleanDataSupporter(String name) {
        this.name = name;
    }
    
    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
    
    public void addData(TimeBooleanDataInterface t){
        this.values.add(t);
    }
    
    @Override
    public void mapValues(String subName, Map<Boolean,String> map){
        labelMap.put(subName, map);
    }

    @Override
    public String getLabelByBooleanValue(String subname, boolean value) {
//        System.out.println("subname: "+subname);
        if(!this.labelMap.containsKey(subname)){
//            System.out.println("subname is not here");
            return null;
        }
        return this.labelMap.get(subname).containsKey(value) ? this.labelMap.get(subname).get(value) : null;
    }

    @Override
    public List<TimeBooleanDataInterface> getValues() {
        return values;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
}
