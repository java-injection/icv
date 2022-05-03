/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test;

import java.util.Date;
import it.cnr.istc.icv.logic.ActivityDataExtendedInterface;
import it.cnr.istc.icv.logic.ActivityDataInterface;
import it.cnr.istc.icv.logic.ActivityDataPriority;

/**
 *
 * @author Luca
 */
public class ActivityDataSupporter implements ActivityDataExtendedInterface{
    
    private String subname;
    private Date start;
    private Date end;
    private ActivityDataPriority priority;

    public ActivityDataSupporter() {
    }

    public ActivityDataSupporter(String subname, Date start, Date end) {
        this.subname = subname;
        this.start = start;
        this.end = end;
        priority = new ActivityDataPriority();
    }

    public ActivityDataSupporter(String subname, Date start, Date end, ActivityDataPriority priority) {
        this.subname = subname;
        this.start = start;
        this.end = end;
        this.priority = priority;
    }
    
   

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public Date getStart() {
        return this.start;
    }

    @Override
    public Date getEnd() {
        return this.end;
    }

    public void setPriority(ActivityDataPriority priority) {
        this.priority = priority;
    }

    @Override
    public ActivityDataPriority getDataPriority() {
        return this.priority;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }
    
    

    @Override
    public String getSubname() {
        return this.subname;
    }
    
}
