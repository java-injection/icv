/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.engine.listeners;

import java.util.Date;

/**
 *
 * @author Luca
 */
public interface ZoomListener {
    
    public void zoomSelected();
    
    public void zoomResetted();
    
    public void zoomNewInterval(Date startRange, Date endRange);
    
}
