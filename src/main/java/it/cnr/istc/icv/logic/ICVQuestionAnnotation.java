/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.logic;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Luca
 */
public class ICVQuestionAnnotation extends ICVMappableAnnotation{
    
    public final static String DEFAULT_YES = "Yes";
    public final static String DEFAULT_NO = "No";
    private List<String> possibleAnswers = new ArrayList<String>();
   private Map<String,List<ICVAnswerAnnotation>> answerMap = new HashMap<String, List<ICVAnswerAnnotation>>();

    public ICVQuestionAnnotation(Object id, String activity, long when, Image img, boolean descriptionVisible) {
        super(id, activity, when, img, descriptionVisible);
    }

    public ICVQuestionAnnotation(Object id, String activity, long when, String descr, Image img, boolean descriptionVisible) {
        super(id, activity, when, descr, img, descriptionVisible);
    }

    public ICVQuestionAnnotation(Object id, String activity, long when, String descr, Image img, DescriptionAlignment alignment, boolean descriptionVisible) {
        super(id, activity, when, descr, img, alignment, descriptionVisible);
    }

    public ICVQuestionAnnotation(Object id, String activity, long when, String descr, Image img, float size, DescriptionAlignment alignment, boolean descriptionVisible) {
        super(id, activity, when, descr, img, size, alignment, descriptionVisible);
    }

    public ICVQuestionAnnotation(Object id, String activity, long when, String descr, boolean descriptionVisible) {
        super(id, activity, when, descr, descriptionVisible);
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
        for (String ans : possibleAnswers) {
            this.answerMap.put(ans, new ArrayList<ICVAnswerAnnotation>());
        }
    }
    
    public void addPossibleAnswer(String ans){
        this.possibleAnswers.add(ans);
        this.answerMap.put(ans, new ArrayList<ICVAnswerAnnotation>());
    }
    
    public void addICVAnswerForTrigger(String trigger, ICVAnswerAnnotation ans) throws Exception{
        if(possibleAnswers.contains(trigger)){
        this.answerMap.get(trigger).add(ans);
        }else{
            throw new Exception("Invalid trigger");
        }
    }
    
    
}
