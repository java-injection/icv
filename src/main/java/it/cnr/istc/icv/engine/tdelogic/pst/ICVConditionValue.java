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
public class ICVConditionValue {
    
    private Relation relation;
    private Object value;

    public ICVConditionValue(Relation relation, Object value) {
        this.relation = relation;
        this.value = value;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
}
