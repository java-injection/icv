/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.engine.tdelogic.pst;

/**
 *
 * @author Luca
 */
public class ICVGenericStateVariable extends ICVStateVariable {

    private Object[] possiblevalues = new Object[2];

    public ICVGenericStateVariable(String name) {
        super(name);
    }

    public void setPossiblevalues(Object[] possiblevalues) {
        this.possiblevalues = possiblevalues;
    }

    @Override
    public Object[] getPossibleValues() {
        return possiblevalues;
    }

}
