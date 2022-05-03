/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.timeselector.smart;

import java.util.Date;

/**
 *
 * @author Luca
 */
public interface SmartIntervalListener {
    
    public void currentSquare(Date start, Date end);
    
}
