/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.logic;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Luca
 */
public class ICVArrow {
    
    private Color arrowColor;
    private int size;
    private Point start;
    private Point end;

    public ICVArrow() {
    }

    public ICVArrow(Color arrowColor, int size, Point start, Point end) {
        this.arrowColor = arrowColor;
        this.size = size;
        this.start = start;
        this.end = end;
    }

    public Color getArrowColor() {
        return arrowColor;
    }

    public void setArrowColor(Color arrowColor) {
        this.arrowColor = arrowColor;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
    
    
    
}
