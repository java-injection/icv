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
public class ICVBooleanStateCondition extends ICVCondition {
    
    private boolean desiredState;
    private String positiveConditionName;
    private String negativeConditionName;

    public ICVBooleanStateCondition() {
        super("asd", null);
    }

    public ICVBooleanStateCondition(boolean desiredState, String positiveConditionName, String negativeConditionName) {
        super("ciao", null);
        this.desiredState = desiredState;
        this.positiveConditionName = positiveConditionName;
        this.negativeConditionName = negativeConditionName;
    }

    public boolean isDesiredState() {
        return desiredState;
    }

    public void setDesiredState(boolean desiredState) {
        this.desiredState = desiredState;
    }

    public String getPositiveConditionName() {
        return positiveConditionName;
    }

    public void setPositiveConditionName(String positiveConditionName) {
        this.positiveConditionName = positiveConditionName;
    }

    public String getNegativeConditionName() {
        return negativeConditionName;
    }

    public void setNegativeConditionName(String negativeConditionName) {
        this.negativeConditionName = negativeConditionName;
    }


    
}
