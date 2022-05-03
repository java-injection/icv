/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.extra.test;

import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author Luca
 */
public class SensorTester extends JLabel{
    
    private boolean value = false;
    private int sx;
    private int sy;
    public static final String NEW_DATA = "new_data";
    
    public static final Icon fallen = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/slipper48.png"));
    public static final Icon powerOffIcon = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/powerOff32.png"));
    public static final Icon powerOnIcon = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/powerOn32.png"));
    public static final Icon balug24 = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/balug24.png"));
    public static final Icon flood = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/flood.png"));
    public static final Icon smoke = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/smokeSmall.png"));
    public static final Icon question = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/edit64.png"));
    public static final Icon reminder = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/postitBad32.png"));
    public static final Icon mail = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/mail.png"));
    public static final Icon green = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/green.png"));
    public static final Icon red = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/red.png"));
    public static final Icon yellow = new javax.swing.ImageIcon(SensorTester.class.getResource("/it/cnr/istc/icv/icons/yellow.png"));
    
    private PropertyChangeSupport pps = new PropertyChangeSupport(this);
    
    public SensorTester() {
    }
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener){
        this.pps.addPropertyChangeListener(listener);
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
        if(value){
            this.setIcon(powerOnIcon);
        }else{
            this.setIcon(powerOffIcon);
        }
        pps.firePropertyChange(NEW_DATA, null, value);
        
    }

    public int getSX() {
        return sx;
    }

    public void setSX(int x) {
        this.sx = x;
    }

    public int getSY() {
        return sy;
    }

    public void setSY(int y) {
        this.sy = y;
    }
    
}
