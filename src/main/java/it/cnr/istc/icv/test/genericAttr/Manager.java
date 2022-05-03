/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test.genericAttr;

import it.cnr.istc.icv.test.TesterExtraFrame;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class Manager {

    private Persona persona;

    public Manager() {
        try {

            
            Annotation annotation = this.getClass().getAnnotation(AttrType.class);
            Class<?> clazz = ((AttrType) annotation).entityClass();
            persona = (Persona) clazz.getConstructor(String.class, String.class).newInstance("Gino","Informatica");
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(TesterExtraFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(TesterExtraFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TesterExtraFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TesterExtraFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TesterExtraFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TesterExtraFrame.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException nu){
            System.out.println("SAFE LINE");
            persona = new Persona("Perseo");
        }
        System.out.println("persona.class = " + persona.getClass().getCanonicalName());
        if (persona instanceof Studente) {
            String ll = ((Studente) persona).getLaurea();
            System.out.println("Laurea = " + ll);
        }

    }

    public Persona getPersona() {
        return persona;
    }
    
    
    
    public static void main(String args[]){
        
        Manager2 manager = new Manager2();
    }
}
