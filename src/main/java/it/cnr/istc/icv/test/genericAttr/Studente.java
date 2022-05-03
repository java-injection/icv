/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test.genericAttr;

/**
 *
 * @author Luca
 */
public class Studente extends Persona{
    
    private String laurea;

    public Studente(String laurea, String nome) {
        super(nome);
        this.laurea = laurea;
    }

    public String getLaurea() {
        return laurea;
    }
    
    
}
