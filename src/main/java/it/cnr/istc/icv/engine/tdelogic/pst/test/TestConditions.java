/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.engine.tdelogic.pst.test;

import it.cnr.istc.icv.engine.tdelogic.pst.ICVCondition;
import it.cnr.istc.icv.engine.tdelogic.pst.ICVConditionValue;
import it.cnr.istc.icv.engine.tdelogic.pst.ICVResource;
import it.cnr.istc.icv.engine.tdelogic.pst.Relation;
import it.cnr.istc.icv.engine.tdelogic.pst.exceptions.PSTInvalidRelationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class TestConditions {
    
    public static void main(String [] args){
        
        ICVResource batteria = new ICVResource("Battery", 0, 100);
        ICVResource amore = new ICVResource("Amore", 0, 1000);
        
        ICVCondition c = new ICVCondition("A", batteria);
        ICVCondition c2 = new ICVCondition("B", amore);
        ICVCondition c3 = new ICVCondition("B", c);
        try {
            c.addConditionValue(new ICVConditionValue(Relation.greaterThan, 100));
            c2.addConditionValue(new ICVConditionValue(Relation.equal, 10));
            c3.addConditionValue(new ICVConditionValue(Relation.and, c2));
//            c.addConditionValue(new ICVConditionValue(Relation.greaterThan, 100));
        } catch (PSTInvalidRelationException ex) {
            Logger.getLogger(TestConditions.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("batteria: "+c3.toString());
    }
}
