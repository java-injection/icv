/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.logic;

//import it.cnr.istc.giraffplus.pers.api.Condition;
//import it.cnr.istc.giraffplus.pers.api.Condition.NumericCondition;
//import it.cnr.istc.giraffplus.pers.api.UserProfile;
import it.cnr.istc.icv.engine.tdelogic.pst.ICVCondition;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luca
 */
public class ICVMappableAnnotation extends ICVAnnotation implements ICVAnswerAnnotation{
    
    private Object id;
    private ICVMappableAnnotation parent;
    private List<ICVMappableAnnotation> children = new ArrayList<ICVMappableAnnotation>();
    private String triggerText;
    private List<ICVCondition> conditions = new ArrayList<ICVCondition>();
//    private Condition condition;

    public ICVMappableAnnotation(Object id, String activity, long when, Image img, boolean descriptionVisible) {
        super(activity, when, img, descriptionVisible);
        
        this.id = id;
    }

    public ICVMappableAnnotation(Object id, String activity, long when, String descr, Image img, boolean descriptionVisible) {
        super(activity, when, descr, img, descriptionVisible);
        this.id = id;
    }

    public ICVMappableAnnotation(Object id, String activity, long when, String descr, Image img, DescriptionAlignment alignment, boolean descriptionVisible) {
        super(activity, when, descr, img, alignment, descriptionVisible);
        this.id = id;
    }

    public ICVMappableAnnotation(Object id, String activity, long when, String descr, Image img, float size, DescriptionAlignment alignment, boolean descriptionVisible) {
        super(activity, when, descr, img, size, alignment, descriptionVisible);
        this.id = id;
    }

    public ICVMappableAnnotation(Object id, String activity, long when, String descr, boolean descriptionVisible) {
        super(activity, when, descr, descriptionVisible);
        this.id = id;
    }

    public List<ICVCondition> getConditions() {
        return conditions;
    }
    
    public void addCondition(ICVCondition condition){
        this.conditions.add(condition);
    }
    
    

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public void setParent(ICVMappableAnnotation parent) {
        this.parent = parent;
    }
    
    public void addChildren(ICVMappableAnnotation child){
        this.children.add(child);
    }
    
    public void removeChildren(ICVMappableAnnotation child){
        this.children.remove(child);
    }

    public ICVMappableAnnotation getParent() {
        return parent;
    }

    public List<ICVMappableAnnotation> getChildren() {
        return children;
    }

    @Override
    public ICVQuestionAnnotation getParentQuestion() {
        if (parent instanceof ICVQuestionAnnotation) {
            return (ICVQuestionAnnotation) parent;
        } else {
            System.out.println("Warning, this is not an Answer");
            return null;
        }
    }

    @Override
    public void setParentQuestion(ICVQuestionAnnotation question) {
        setParent(question);
    }

    @Override
    public String getTriggerText() {
        return triggerText;
    }

    @Override
    public void setTriggerText(String text) {
        this.triggerText = text;
    }

//    public void setCondition(Condition condition) {
//        this.condition = condition;
//    }
//
//    public Condition getCondition() {
//        return condition;
//    }
    
}
