/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.logic;

/**
 *
 * @author Luca
 */
public interface ICVAnswerAnnotation {
    
    public ICVQuestionAnnotation getParentQuestion();
    public void setParentQuestion(ICVQuestionAnnotation question);
    
    /**
     * Return the possible answer of the question that trigger this Answer
     * @return 
     */
    public String getTriggerText();
    
    public void setTriggerText(String text);
    
}
