/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.engine.tdelogic.pst;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luca
 */
public class ICVBooleanStateVariable extends ICVStateVariable{

    private Object [] possiblevalues = new Object[2];
    public ICVBooleanStateVariable(String name) {
        super(name);
        possiblevalues[0] = Boolean.FALSE;
        possiblevalues[1] = Boolean.TRUE;
    }

    
    @Override
    public Object [] getPossibleValues() {
        return possiblevalues;
    }
    
    
    
}
