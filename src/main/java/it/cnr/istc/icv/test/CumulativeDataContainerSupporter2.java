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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Luca
 */
public class CumulativeDataContainerSupporter2 implements CumulativeDataContainer {

    private long tokenLenght = 60000;
    private LinearDataSupporter<TimeValueSupporterClass> cumulativeTimeline = null;
    private int max = 1;
    private String name;
    private boolean needExtraction = false;
    private int order = -1;
    private int verystartValue = 0;
    private Map<String, LinearDataSupporter<TimeValueSupporterClass>> lines = new HashMap<String, LinearDataSupporter<TimeValueSupporterClass>>();
    private Map<String, Double> antiDuplicateMap = new HashMap<String,Double>();
    private boolean startRangeFixed = false;
    private Long startRange = null;

    public CumulativeDataContainerSupporter2(String name) {
        this.name = name;
    }

    @Override
    public void setTokenLenght(long lenght) {
        this.tokenLenght = lenght;
    }

//    public void setVerystartValue(int verystartValue) {
//        this.verystartValue = verystartValue;
//    }

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

    public void cumulateWithSingleData(TimeBooleanDataInterface data, String databar) {
        if (!lines.containsKey(databar)) {
//            System.out.println("adding new databar");
            lines.put(databar, new LinearDataSupporter<TimeValueSupporterClass>(databar));
        }

        int value = data.isTrue() ? 1 : -1;
//        if (this.antiDuplicateMap.containsKey(databar)) {
//            if (this.antiDuplicateMap.get(databar) == value) {
//                System.out.println("duplicate spotted by [ICV.cumulativeDataContainer]");
//                return;
//            }
//        }
//        else{
//            if(value == -1){
////                this.antiDuplicateMap.put(databar, -1d);
//                System.out.println("FAKEE");
//                return;
//            }
//        }
        TimeValueSupporterClass ds1 = new TimeValueSupporterClass(value, databar, data.getTimeStamp());
        lines.get(databar).addData(ds1);
        needExtraction = true;

    }

    @Override
    public void addRowToCumulate(BooleanDataContainer rowToComulate) {
        List<TimeBooleanDataInterface> values = rowToComulate.getValues();
        for (TimeBooleanDataInterface timeBooleanDataInterface : values) {
            this.cumulateWithSingleData(timeBooleanDataInterface, rowToComulate.getName());
        }
    }

    public int getVerystartValue() {
        return verystartValue;
    }
    
    

    @Override
    public LinearDataContainerInterface<TimeValueSupporterClass> extractData() {
        
        if(cumulativeTimeline == null){
//            System.out.println("creating cumulative");
            cumulativeTimeline = new LinearDataSupporter<TimeValueSupporterClass>(name + "extracted");
            needExtraction = true;
        }
        
        if(!needExtraction){
            return cumulativeTimeline;
        }
//        System.out.println("RECALCULING");
//        JOptionPane.showMessageDialog(null, "RECALCULING");
        verystartValue =0;
        Set<String> keySet = lines.keySet();
        for (String databar : keySet) {
//            System.out.println("databar = "+databar);
            List<TimeValueSupporterClass> timeline = lines.get(databar).getValuesMap().get(databar);
            Collections.sort(timeline);
            this.antiDuplicateMap.put(databar, timeline.get(timeline.size()-1).getValue());
//            System.out.println("------------------------------timeline 0 = "+timeline.get(0).getValue());
            if(timeline.get(0).getValue() == -1){
//                System.out.println("------------------------------ALLORA ??");
                verystartValue++;
            }
            if(timeline.get(0).getValue() == 1 && timeline.get(0).getTimeStamp().before(new Date(this.startRange))){
//                System.out.println("vecchio uno ");
//                verystartValue++;
            }
            
        }
//        System.out.println("------------------------------ very start value = "+verystartValue);
        this.cumulativeTimeline.clear();
        List<TimeValueSupporterClass> flatTimeline = new ArrayList<TimeValueSupporterClass>();
        for (String databar : keySet) {
            List<TimeValueSupporterClass> timeline = lines.get(databar).getValuesMap().get(databar);
            flatTimeline.addAll(timeline);
        }
        if (startRange != null) {
//            System.out.println("VERY- > "+verystartValue);
//            for (int i = 0; i < verystartValue; i++) {
                flatTimeline.add(new TimeValueSupporterClass(verystartValue, this.getDefaultSubchart(), new Date(startRange)));
//            }
        }
        Collections.sort(flatTimeline);
        Double value = null;
        for (TimeValueSupporterClass flatValue : flatTimeline) {
//            System.out.println("\tflat value : "+flatValue.getValue());
            if(value == null){
                value = flatValue.getValue();
            }else{
                value+=flatValue.getValue();
            }
            this.cumulativeTimeline.addData(new TimeValueSupporterClass(value, this.getDefaultSubchart(), flatValue.getTimeStamp()));
        }
        
        System.out.println("// Summary");
        Set<String> keySet1 = this.cumulativeTimeline.getValuesMap().keySet();
        System.out.println("keyset size: "+keySet1.size());
        System.out.println("keyset: "+keySet.toString());
        for (String kkkkk : keySet1) {
            System.out.println("kk .> "+kkkkk);
            List<TimeValueSupporterClass> line = this.cumulativeTimeline.getValuesMap().get(kkkkk);
            for (TimeValueSupporterClass timeValueSupporterClass : line) {
                System.out.println("data: "+timeValueSupporterClass.getValue()+" at "+timeValueSupporterClass.getTimeStamp());
            }
        }
        needExtraction = false;
        System.out.println("VERY START VALUE = "+verystartValue);
        System.out.println(this.cumulativeTimeline.getValuesMap());
        
        System.out.println("// checking anti duplication map");
        Set<String> keySet2 = this.antiDuplicateMap.keySet();
        for (String string : keySet2) {
            System.out.println("last databara value: "+string + ":  "+this.antiDuplicateMap.get(string));
        }
        
        
        return this.cumulativeTimeline;
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public void setStartRange(long startRange) {
        this.startRange = startRange;
//        if(!startRangeFixed){
//            System.out.println(" - _ >_ >_ >_ > Very : "+this.verystartValue);
//            for (int i = 0; i < this.verystartValue; i++) {
//                cumulateWithSingleData(new BooleanDataSupporter(new Date(startRange), true), "extra");
//            }
//            startRangeFixed = true;
//        }
        
    }

}
