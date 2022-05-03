/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.logic;

import java.awt.Color;

/**
 *
 * @author Luca
 */
public final class ActivityDataPriority {

    public static Color LOW_COLOR_START = new Color(0, 122, 0);
    public static Color LOW_COLOR_END = Color.GREEN;
    public static Color MEDIUM_COLOR_START = new Color(0, 0, 122);
    public static Color MEDIUM_COLOR_END = Color.BLUE;
    public static Color HIGH_COLOR_START = new Color(122, 0, 0);
    public static Color HIGH_COLOR_END = Color.RED;
    private Priority priority = Priority.MEDIUM;
    public boolean vertical = true;

    public ActivityDataPriority() {

    }

    
    
    public ActivityDataPriority(Priority pr, boolean vertical) {
        this.setPriority(pr);
        this.setVertical(vertical);
    }
    
    

    public final void setPriority(Priority pr) {
        priority = pr;
    }

    public Color getStartColor() {
        switch (priority) {
            case HIGH:
                return HIGH_COLOR_START;
            case MEDIUM:
                return MEDIUM_COLOR_START;
            case LOW:
                return LOW_COLOR_START;
            default:
                return Color.BLACK;
        }
    }
    
    public Color getEndColor() {
        switch (priority) {
            case HIGH:
                return HIGH_COLOR_END;
            case MEDIUM:
                return MEDIUM_COLOR_END;
            case LOW:
                return LOW_COLOR_END;
            default:
                return Color.BLACK;
        }
    }

    public final void setVertical(boolean v) {
        vertical = v;
    }

    public boolean isVertical() {
        return vertical;
    }
    
    

    public enum Priority {

        LOW, MEDIUM, HIGH
    }
}
