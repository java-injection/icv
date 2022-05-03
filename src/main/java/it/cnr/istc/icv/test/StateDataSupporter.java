/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.test;

import it.cnr.istc.icv.logic.TimeStateDataInterface;
import java.util.Date;

/**
 *
 * @author Luca
 */
public class StateDataSupporter implements TimeStateDataInterface{
    
    private String state;
    private String subname;
    private Date timeStamp;

    public StateDataSupporter() {
    }

    public StateDataSupporter(String state, String subname, Date timeStamp) {
        this.state = state;
        this.subname = subname;
        this.timeStamp = timeStamp;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String getState() {
        return this.state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getSubName() {
        return subname;
    }

    @Override
    public Date getTimeStamp() {
        return timeStamp;
    }
    
}
