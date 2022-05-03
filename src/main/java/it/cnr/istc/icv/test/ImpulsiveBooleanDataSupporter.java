/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test;

import it.cnr.istc.icv.logic.BooleanDataContainer;
import it.cnr.istc.icv.logic.ImpulsiveBooleanDataContainer;
import it.cnr.istc.icv.logic.TimeBooleanDataInterface;
import it.cnr.istc.icv.logic.TimeDataInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Luca
 */
public class ImpulsiveBooleanDataSupporter implements ImpulsiveBooleanDataContainer {

    private Map<String, Map<Boolean, String>> labelMap = new HashMap<String, Map<Boolean, String>>();
    private String name;
    private List<TimeDataInterface> values = new ArrayList<TimeDataInterface>();
    private long idle;
    private int order = -1;

    public ImpulsiveBooleanDataSupporter(String name) {
        this.name = name;
    }

    public void addData(TimeBooleanDataInterface t) {
        this.values.add(t);
    }

    @Override
    public void mapValues(String subName, Map<Boolean, String> map) {
        labelMap.put(subName, map);
    }
    
    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String getLabelByBooleanValue(String subname, boolean value) {
        String dd = subname;
        try {
            dd = this.labelMap.get(subname).containsKey(value) ? this.labelMap.get(subname).get(value) : null;
            if(value && dd==null){
                dd="is present";
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
            dd=null;
        }
        return dd;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<TimeDataInterface> getValues() {
        return this.values;
    }

    public void setIdle(long idle) {
        this.idle = idle;
    }

    @Override
    public long getIdleTime() {
        return this.idle;
    }

}
