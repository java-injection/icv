/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.logic;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Luca
 */
public interface StatisticDataContainerInterface<T extends TimeDataInterface> extends ContainerDataInterface {
    
    public Map<String, List<T>> getValuesMap();
   
    
}
