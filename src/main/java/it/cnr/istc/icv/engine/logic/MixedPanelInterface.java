/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.engine.logic;

import it.cnr.istc.icv.engine.listeners.CoordinateListener;
import it.cnr.istc.icv.engine.listeners.ZoomListener;
import it.cnr.istc.icv.logic.ContainerDataInterface;
import java.awt.Point;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Luca
 */
public interface MixedPanelInterface {

    public boolean isShowDate();
    
    public int getStartSelection();

    public int getEndSelection();

    public void setEndSelection(int endSelection);

    public int gethY();

    public int getsY();

    public int getsX();
    
    public int geteX();
    
    public boolean isZoomEnable();
    
    public void addCoordinateListener(CoordinateListener listener);
    
     public Date getStartDataSelection();

    public Date getEndDataSelection();
    
    public void repaint();
    
    public Date getDateByPoint(Point point);
    
    public void manageZoom();
    
    public void resetZoom();
    
    public void addZoomListener(ZoomListener listener);
    
    public HashMap<String, ContainerDataInterface> getDataMap();
    
    public String getDatabarFromY(int y);

}
