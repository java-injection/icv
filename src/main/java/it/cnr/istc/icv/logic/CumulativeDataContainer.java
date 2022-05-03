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
public interface CumulativeDataContainer extends ContainerDataInterface{
    
    public void setTokenLenght(long lenght);
    
    public String getDefaultSubchart();
    
    public long getTokenLenght();
    
    public void addRowToCumulate(BooleanDataContainer rowToComulate);
    
    public LinearDataContainerInterface extractData();
    
    public int getMax();
    
    public int getVerystartValue();
    
    public void setStartRange(long startRange);
    
    
}
