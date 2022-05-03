/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.logic;

import it.cnr.istc.icv.logic.ActivityDataPriority;
import it.cnr.istc.icv.logic.ActivityDataInterface;

/**
 *
 * @author Luca
 */
public interface ActivityDataExtendedInterface extends ActivityDataInterface{
    
    public ActivityDataPriority getDataPriority();
    
}
