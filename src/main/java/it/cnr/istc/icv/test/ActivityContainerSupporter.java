/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test;

import it.cnr.istc.icv.logic.ActivityDataContainer;
import it.cnr.istc.icv.logic.ActivityDataInterface;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luca
 */
public class ActivityContainerSupporter implements ActivityDataContainer{

    private String name;
    private List<ActivityDataInterface> activities = new ArrayList<ActivityDataInterface>();
    private int order = -1;

    public ActivityContainerSupporter(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public List<ActivityDataInterface> getActivityData() {
        return this.activities;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
    
    
}
