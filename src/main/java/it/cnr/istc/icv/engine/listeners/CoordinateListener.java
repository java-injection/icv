/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.engine.listeners;

import java.util.Date;

/**
 *
 * @author Luca
 */
public interface CoordinateListener {
    
    public void currentDate(Date date);
    
    public void currentValue(Object value);
    
    public void showTooltip(String message, int x, int y);
    
    public void forceFromTo(boolean force);
    
}
