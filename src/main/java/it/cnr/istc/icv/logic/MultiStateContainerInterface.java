/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.logic;

import it.cnr.istc.icv.test.StateDataSupporter;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Luca
 */
public interface  MultiStateContainerInterface extends ContainerDataInterface{
    
    public void mapValue(String state, Color color);
    
    public Color getColorByState(String state);
    
    public List<StateDataSupporter> getValues();
    
    public void addStateData(StateDataSupporter data);
    
    public Set<String> getPossibleStates();
    
    public Color getDefault();
}
