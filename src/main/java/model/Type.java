package model;

import javafx.beans.property.*;

public class Type {
    private final StringProperty name;
    private final FloatProperty basePrice;
    private Long allSentence;
    private Long allDemand;

    public Type(){
        this("", 1.0f);
    }

    public Type(String name, Float basePrice) {
        this.name = new SimpleStringProperty(name);
        this.basePrice = new SimpleFloatProperty(basePrice);
        this.allSentence = 0L;
        this.allDemand = 0L;
    }

    public void decDemand(Long count){
        if (allDemand < count){
            allDemand -= count;
        }
    }

    public void incDemand(Long count){
        allDemand += count;
    }
    public void decSentence(Long count){
        if (allSentence < count){
            allSentence -= count;
        }
    }

    public void incSentence(Long count){
        allSentence += count;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public float getBasePrice() {
        return basePrice.get();
    }

    public FloatProperty basePriceProperty() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice.set(basePrice);
    }

    public Long getAllSentence() {
        return allSentence;
    }

    public Long getAllDemand() {
        return allDemand;
    }
}
