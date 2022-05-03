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
public class ICVResource implements ConditionArgument{
    
    private String name;
    private double min;
    private double max;
    private final Relation [] relations = new Relation[5];

    public ICVResource(String name, double min, double max) {
        this.name = name;
        this.min = min;
        this.max = max;
        relations[0] = Relation.equal;
        relations[1] = Relation.greaterThan;
        relations[2] = Relation.greaterEqualThan;
        relations[3] = Relation.lessThan;
        relations[4] = Relation.lessEqualThan;
    }

    public ICVResource() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public Relation[] possibleRelations() {
        return relations; 
    }
    
    
    
}
