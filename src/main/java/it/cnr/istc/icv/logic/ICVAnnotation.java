/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.logic;

import java.awt.Image;

/**
 *
 * @author Luca
 */
public class ICVAnnotation {

    private String description = "";
    private Image image = null;
    private float size = 1f;
    private DescriptionAlignment alignment = DescriptionAlignment.BOTTOM;
    private long when;
    private String activity;
    private boolean descriptionVisible = true;
    

    public ICVAnnotation() {
    }

    public ICVAnnotation(String activity, long when, String descr, Image img, float size, DescriptionAlignment alignment, boolean descriptionVisible) {
        this.activity = activity;
        this.when = when;
        this.description = descr;
        this.image = img;
        this.size = size;
        this.alignment = alignment;
        this.descriptionVisible = descriptionVisible;
    }

    public ICVAnnotation(String activity, long when, String descr, boolean descriptionVisible) {
        this.description = descr;
        this.activity = activity;
        this.when = when;
        this.descriptionVisible = descriptionVisible;
    }

    public ICVAnnotation(String activity, long when, String descr, Image img, boolean descriptionVisible) {
        this.description = descr;
        this.image = img;
        this.activity = activity;
        this.when = when;
        this.descriptionVisible = descriptionVisible;
    }

    public ICVAnnotation(String activity, long when, Image img, boolean descriptionVisible) {
        this.image = img;
        this.activity = activity;
        this.when = when;
        this.descriptionVisible = descriptionVisible;
    }

    public ICVAnnotation(String activity, long when, String descr, Image img, DescriptionAlignment alignment, boolean descriptionVisible) {
        this.description = descr;
        this.image = img;
        this.alignment = alignment;
        this.activity = activity;
        this.when = when;
        this.descriptionVisible = descriptionVisible;
    }

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public boolean isDescriptionVisible() {
        return descriptionVisible;
    }

    public void setDescriptionVisible(boolean descriptionVisible) {
        this.descriptionVisible = descriptionVisible;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public DescriptionAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(DescriptionAlignment alignment) {
        this.alignment = alignment;
    }

    public enum DescriptionAlignment {

        BOTTOM, CENTER, TOP;
    }
}
