/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test.genericAttr;

/**
 *
 * @author Luca
 */
public class Persona {

    private String nome;

    public Persona(String nome) {
        this.nome = nome;
    }
    
    public Persona(String nome, String cognome) {
        this.nome = nome;
        System.out.println("cognome = "+cognome);
    }

    public String getNome() {
        return nome;
    }
    
    
}
