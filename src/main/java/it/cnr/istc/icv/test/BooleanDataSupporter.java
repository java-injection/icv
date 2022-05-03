/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test;

import it.cnr.istc.icv.logic.TimeBooleanDataInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Luca
 */
public class BooleanDataSupporter implements TimeBooleanDataInterface{

   
      
    private Date timeStamp;
    private boolean data;
    private String subName;
 
    @Override
    public boolean isTrue() {
        return data;
    }

    public BooleanDataSupporter(Date timeStamp, boolean data, String subName) {
        this.timeStamp = timeStamp;
        this.data = data;
        this.subName = subName;
    }
 
    public BooleanDataSupporter(Date timeStamp, boolean data) {
        this.timeStamp = timeStamp;
        this.data = data;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public String getSubName() {
        return this.subName;
    }

    @Override
    public Date getTimeStamp() {
        return this.timeStamp;
    }

}
