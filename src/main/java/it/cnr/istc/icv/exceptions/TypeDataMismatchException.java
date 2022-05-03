/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.exceptions;

/**
 *
 * @author Luca
 */
public class TypeDataMismatchException extends Exception{

    public TypeDataMismatchException() {
        super("wrong data type .class");
    }

    public TypeDataMismatchException(String message) {
        super(message);
    }
     
    
    
}
