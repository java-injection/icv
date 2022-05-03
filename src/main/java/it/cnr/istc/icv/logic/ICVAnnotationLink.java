/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.logic;

import java.awt.Color;

/**
 *
 * @author Luca
 */
  public class ICVAnnotationLink {
    
    private ICVMappableAnnotation startingAnnotation;
    private ICVMappableAnnotation endingAnnotation;
    private Color arrowColor = Color.BLUE;

    public ICVAnnotationLink() {
    }

    public ICVAnnotationLink(ICVMappableAnnotation startingAnnotation, ICVMappableAnnotation endingAnnotation) {
        this.startingAnnotation = startingAnnotation;
        this.endingAnnotation = endingAnnotation;
    }

    public ICVMappableAnnotation getStartingAnnotation() {
        return startingAnnotation;
    }

    public void setStartingAnnotation(ICVMappableAnnotation startingAnnotation) {
        this.startingAnnotation = startingAnnotation;
    }

    public ICVMappableAnnotation getEndingAnnotation() {
        return endingAnnotation;
    }

    public void setEndingAnnotation(ICVMappableAnnotation endingAnnotation) {
        this.endingAnnotation = endingAnnotation;
    }

    public Color getArrowColor() {
        return arrowColor;
    }

    public void setArrowColor(Color arrowColor) {
        this.arrowColor = arrowColor;
    }

    
    
    
}
