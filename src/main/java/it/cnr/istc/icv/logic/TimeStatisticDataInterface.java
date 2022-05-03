/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.logic;

/**
 *
 * @author Luca
 */
public interface TimeStatisticDataInterface extends TimeDataInterface{
    
    public double getVariance();
    
    public void setVariance(double var);
    
    public long getTimeRange();
    
    public void setTimeRange(long range);
    
    public double getValue();
    
    public void setValue(double value);
    
}
