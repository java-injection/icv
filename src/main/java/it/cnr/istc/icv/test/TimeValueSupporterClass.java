/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test;

import java.util.Date;
import it.cnr.istc.icv.logic.TimeValueDataInterface;

/**
 *
 * @author Luca
 */
public class TimeValueSupporterClass implements TimeValueDataInterface, Comparable<TimeValueSupporterClass>{

    private double value;
    private String subName;
    private Date timeStamp;

    public TimeValueSupporterClass() {
    }

    public TimeValueSupporterClass(double value, String subName, Date timeStamp) {
        this.value = value;
        this.subName = subName;
        this.timeStamp = timeStamp;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
 
    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getSubName() {
        return this.subName;
    }

    @Override
    public Date getTimeStamp() {
        return this.timeStamp;
    }

    @Override
    public int compareTo(TimeValueSupporterClass o) {
        return -o.getTimeStamp().compareTo(this.timeStamp);
    }
    
    
}
