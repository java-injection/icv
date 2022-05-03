/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.logic;

import java.awt.Image;
import java.util.List;

/**
 *
 * @author Luca
 */
public class ICVYesNotQuestionAnnotation extends ICVQuestionAnnotation{

    public ICVYesNotQuestionAnnotation(Object id, String activity, long when, Image img, boolean descriptionVisible) {
        super(id, activity, when, img, descriptionVisible);
        super.addPossibleAnswer(ICVQuestionAnnotation.DEFAULT_YES);
        super.addPossibleAnswer(ICVQuestionAnnotation.DEFAULT_NO);
    }

    public ICVYesNotQuestionAnnotation(Object id, String activity, long when, String descr, Image img, boolean descriptionVisible) {
        super(id, activity, when, descr, img, descriptionVisible);
        super.addPossibleAnswer(ICVQuestionAnnotation.DEFAULT_YES);
        super.addPossibleAnswer(ICVQuestionAnnotation.DEFAULT_NO);
    }

    public ICVYesNotQuestionAnnotation(Object id, String activity, long when, String descr, Image img, DescriptionAlignment alignment, boolean descriptionVisible) {
        super(id, activity, when, descr, img, alignment, descriptionVisible);
        super.addPossibleAnswer(ICVQuestionAnnotation.DEFAULT_YES);
        super.addPossibleAnswer(ICVQuestionAnnotation.DEFAULT_NO);
    }

    public ICVYesNotQuestionAnnotation(Object id, String activity, long when, String descr, Image img, float size, DescriptionAlignment alignment, boolean descriptionVisible) {
        super(id, activity, when, descr, img, size, alignment, descriptionVisible);
        super.addPossibleAnswer(ICVQuestionAnnotation.DEFAULT_YES);
        super.addPossibleAnswer(ICVQuestionAnnotation.DEFAULT_NO);
    }

    public ICVYesNotQuestionAnnotation(Object id, String activity, long when, String descr, boolean descriptionVisible) {
        super(id, activity, when, descr, descriptionVisible);
        super.addPossibleAnswer(ICVQuestionAnnotation.DEFAULT_YES);
        super.addPossibleAnswer(ICVQuestionAnnotation.DEFAULT_NO);
    }

    @Override
    public void setPossibleAnswers(List<String> possibleAnswers) {
        throw  new UnsupportedOperationException("It's not possible to add different answer to YES-NOT question");
    }

    @Override
    public void addPossibleAnswer(String ans) {
        throw  new UnsupportedOperationException("It's not possible to add different answer to YES-NOT question");
    }
    
    
    
    
    
    
}
