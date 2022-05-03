/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.logic;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Luca
 */
public interface ImpulsiveBooleanDataContainer extends ContainerDataInterface{
    
    public void mapValues(String subName, Map<Boolean,String> map);
    
    public String getLabelByBooleanValue(String subname, boolean value);
    
    public List<TimeDataInterface> getValues();
    
    public long getIdleTime();
    
}
