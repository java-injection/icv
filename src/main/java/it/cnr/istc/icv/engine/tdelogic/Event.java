/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.engine.tdelogic;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;

/**
 *
 * @author Luca
 */
public class Event {
    
    private  Object id;
    
    private String name;
    private String description;
    private long delay;
    private boolean periodic;
    private long period;
    private Icon icon;
    private List<Event> consequences = new ArrayList<Event>();
    private Object idParent;
    private Object triggerValue = null; //if null it means there are no trigger value

    public Event() {
    }

    public Event(Object id, String name, String description, long delay, boolean periodic, long period, Icon icon, Object idParent, Object triggerValue) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.delay = delay;
        this.periodic = periodic;
        this.period = period;
        this.icon = icon;
        this.idParent = idParent;
        this.triggerValue = triggerValue;
    }

    public Object getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(Object triggerValue) {
        this.triggerValue = triggerValue;
    }
    
    public final void addConsequence(Event event){
        event.setIdParent(this.getId());
        this.consequences.add(event);
    }

    public Object getId() {
        return id;
    }

    protected void setIdParent(Object idParent) {
        this.idParent = idParent;
    }

    public Object getIdParent() {
        return idParent;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public boolean isPeriodic() {
        return periodic;
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public List<Event> getConsequences() {
        return consequences;
    }

    public void setConsequences(List<Event> consequences) {
        this.consequences = consequences;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
