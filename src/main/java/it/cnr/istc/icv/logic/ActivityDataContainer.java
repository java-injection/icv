/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.logic;

import java.util.List;

/**
 *
 * @author Luca
 */
public interface ActivityDataContainer extends ContainerDataInterface {
    
    public List<ActivityDataInterface> getActivityData();
}
