/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.test;

import it.cnr.istc.icv.logic.BooleanDataContainer;
import it.cnr.istc.icv.logic.CumulativeDataContainer;
import it.cnr.istc.icv.logic.LinearDataContainerInterface;
import it.cnr.istc.icv.logic.TimeBooleanDataInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luca
 */
public class CumulativeDataContainerSupporter implements CumulativeDataContainer {

    private long tokenLenght = 60000;
    private LinearDataSupporter<TimeValueSupporterClass> cumulativeTimeline = null;
    private int max = 1;
    private String name;
    private boolean extracted = false;
    private int order = -1;
    private int verystartValue = 0;

    public CumulativeDataContainerSupporter(String name) {
        this.name = name;
    }

    @Override
    public void setTokenLenght(long lenght) {
        this.tokenLenght = lenght;
    }

    public long getTokenLenght() {
        return tokenLenght;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDefaultSubchart() {
        return "Connessioni";
    }
    
    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
    public void cumulateWithSingleData(TimeBooleanDataInterface data) {
        int value = data.isTrue() ? 1 : 0;
        TimeValueSupporterClass ds1 = new TimeValueSupporterClass(value, "Connessioni", data.getTimeStamp());
        this.cumulativeTimeline.addData(ds1);
        Collections.sort(this.cumulativeTimeline.getValuesMap().get("Connessioni"));
    }

    @Override
    public void addRowToCumulate(BooleanDataContainer rowToComulate) {
        extracted = false;
        System.out.println("PARSING: " + rowToComulate.getName());
        List<TimeBooleanDataInterface> newDataValues = rowToComulate.getValues();
        if (this.cumulativeTimeline == null) {
            this.cumulativeTimeline = new LinearDataSupporter("Timeline");
            boolean first = true;
            for (TimeBooleanDataInterface timeBooleanDataInterface : newDataValues) {
                if(first){
                    if(!timeBooleanDataInterface.isTrue()){
                        verystartValue++;
                    }
                    first = false;
                }
                int value = timeBooleanDataInterface.isTrue() ? 1 : 0;
                TimeValueSupporterClass ds1 = new TimeValueSupporterClass(value, "Connessioni", timeBooleanDataInterface.getTimeStamp());
                this.cumulativeTimeline.addData(ds1);

            }
            Collections.sort(this.cumulativeTimeline.getValuesMap().get("Connessioni"));
        } else {
            boolean first = true;
            for (TimeBooleanDataInterface timeBooleanDataInterface : newDataValues) {
                if(first){
                    if(!timeBooleanDataInterface.isTrue()){
                        verystartValue++;
                    }
                    first = false;
                }
                int value = timeBooleanDataInterface.isTrue() ? 1 : 0;
                TimeValueSupporterClass ds1 = new TimeValueSupporterClass(value, "Connessioni", timeBooleanDataInterface.getTimeStamp());
                this.cumulativeTimeline.addData(ds1);

            }
            Collections.sort(this.cumulativeTimeline.getValuesMap().get("Connessioni"));
        }
    }

    public int getVerystartValue() {
        return verystartValue;
    }
    
    

    @Override
    public LinearDataContainerInterface<TimeValueSupporterClass> extractData() {
        if(this.cumulativeTimeline == null){
            return null;
        }
        if(this.cumulativeTimeline.getValuesMap().isEmpty()){
            return null;
        }
        if (!extracted) {
            int startConnections = 0;
            try {
                for (TimeValueSupporterClass object : this.cumulativeTimeline.getValuesMap().get("Connessioni")) {
                    startConnections += (object.getValue() == 1 ? 1 : -1) ;
    
//                    if(startConnections ==-1){
//                        startConnections = 0;
//                    }
                    object.setValue(startConnections+ verystartValue);
                }

                for (TimeValueSupporterClass object : this.cumulativeTimeline.getValuesMap().get("Connessioni")) {
                    System.out.println("time: " + object.getTimeStamp() + " -> " + object.getValue());
                }
            } catch (Exception ex) {
                System.out.println("no data in cumulative chart");
            }
        }
        extracted = true;
        return this.cumulativeTimeline;
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public void setStartRange(long startRange) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
