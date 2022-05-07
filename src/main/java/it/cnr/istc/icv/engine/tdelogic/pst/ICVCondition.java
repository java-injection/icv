/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.engine.tdelogic.pst;


import it.cnr.istc.icv.engine.tdelogic.pst.exceptions.PSTInvalidRelationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luca
 */
public class ICVCondition implements ConditionArgument{
    
    private String name;
    private Relation [] possibleRelations = new Relation[]{Relation.and, Relation.or};
    private ConditionArgument conditionArgument;
    private List<ICVConditionValue> values = new ArrayList<ICVConditionValue>();
//    private Relation relation;
//    private Object conditionValue;
//    private List<ICVCondition> subConditions = new ArrayList<ICVCondition>();
//    private Relation subConditionRelation;

    public ICVCondition(String name,ConditionArgument conditionArgument) {
        this.conditionArgument = conditionArgument;
//        this.relation = relation;
//        this.conditionValue = conditionValue;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    

    public ConditionArgument getConditionArgument() {
        return conditionArgument;
    }

    public void setConditionArgument(ConditionArgument conditionArgument) {
        this.conditionArgument = conditionArgument;
    }

    public void addConditionValue(ICVConditionValue value) throws PSTInvalidRelationException {
        for (Relation relation : this.conditionArgument.possibleRelations()) {
            System.out.println(" relation: "+relation.getLabel());
            if(relation.equals(value.getRelation())){
                System.out.println("trovato");
                this.values.add(value);
                return;
            }
        }
        throw new PSTInvalidRelationException(this.name, value.getRelation());

    }

    
    
    @Override
    public Relation[] possibleRelations() {
        return possibleRelations;
    }

    @Override
    public String toString() {
        if(!(this.conditionArgument instanceof ICVCondition)){
            String sub = "";
            for (ICVConditionValue value : values) {
                if(value.getValue() instanceof ICVCondition){
                    sub+="("+value.toString()+")";
                }
                sub+=" "+value.getRelation().getLabel()+value.getValue().toString();
            }
            return conditionArgument.getName() + sub;
        }else{
            return "boh";
        }
            
            
    }
    
    
    
    
    
}
