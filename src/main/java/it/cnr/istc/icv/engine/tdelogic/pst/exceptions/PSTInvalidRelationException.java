/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.engine.tdelogic.pst.exceptions;

import it.cnr.istc.icv.engine.tdelogic.pst.Relation;

/**
 *
 * @author Luca
 */
public class PSTInvalidRelationException extends Exception{

    public PSTInvalidRelationException(String conditionArgument, Relation relation) {
        super("Relation: "+relation.getLabel()+" is not applicable on Condition argument: "+conditionArgument);
    }
    
    
    
}
