/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.timeselector;

/**
 *
 * @author Luca
 */
public enum TimeLevel {
    
    MONTH3("null", "month"),
    MONTH("month3", "week"),
    WEEK("month","day"),
    DAY("week","hour"),
    HOUR("day","null");
    
    TimeLevel(String next, String prev){
        this.next = fromString(next);
        this.prev = fromString(prev);
    }
    
    TimeLevel fromString(String name){
        if(name.equals("month3")){
            return TimeLevel.MONTH3;
        }else if(name.equals("week")){
            return TimeLevel.WEEK;
        }else if(name.equals("day")){
            return TimeLevel.DAY;
        }else if(name.equals("hour")){
            return TimeLevel.HOUR;
        }else if(name.equals("month")){
            return TimeLevel.MONTH;
        }
        return null;
    }
   
    private TimeLevel next;
    private TimeLevel prev;
    
    public TimeLevel next(){
        return next;
    }
    
    public TimeLevel prev(){
        return prev;
    }
    
    
}
