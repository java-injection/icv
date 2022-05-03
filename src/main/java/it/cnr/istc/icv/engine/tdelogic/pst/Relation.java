/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.engine.tdelogic.pst;

/**
 *
 * @author Luca
 */
public enum Relation {

    equal("="),
    greaterThan(">"),
    lessThan("<"),
    lessEqualThan("<="),//lessEqualThan("≤"),
    greaterEqualThan(">="),//greaterEqualThan("≥"),
    and("&"),
    or("|");

    Relation(String label) {
        this.label = label;
    }
    
    private String label;

    public String getLabel() {
        return label;
    }
    
    

}
