/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.engine.tdelogic.pst;

import java.util.List;

/**
 *
 * @author Luca
 */
public abstract class ICVStateVariable implements ConditionArgument{
    
    private String name;
    private Relation[] relations = new Relation[]{Relation.equal};

    public ICVStateVariable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public abstract Object [] getPossibleValues();

    @Override
    public Relation[] possibleRelations() {
        return relations;
    }
    
    
    
}
