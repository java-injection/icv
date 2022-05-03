/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.engine.listeners;

import it.cnr.istc.icv.logic.ICVAnnotation;
import java.util.Date;

/**
 *
 * @author Luca
 */
public interface PopupMenuTriggerListener {
    
    public void rightClickTriggered(String databar, Date timeValue, int pointX, int pointY);
    
    public void rightClickAreaTriggered(String databar, ICVAnnotation annotation, Date timeValue, int pointX, int pointY);
    
}
