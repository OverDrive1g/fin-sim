package model;

import javafx.beans.property.*;

public class Product {
    private final LongProperty id;
    private final StringProperty name;
    private final FloatProperty basePrice;
    private final FloatProperty price;
    private final LongProperty sentence; //предложение всегда статично, можноизменять руками(через интерфейс)
    private final LongProperty count; // спрос, изменяется только с на след день.

    public Product() {
        this(0L, null, 0.0f, 0L, 0L);
    }

    public Product(long id, String name, float basePrice, long sentence, long count) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.basePrice = new SimpleFloatProperty(basePrice);
        this.price = new SimpleFloatProperty(basePrice);
        this.sentence = new SimpleLongProperty(sentence);
        this.count = new SimpleLongProperty(count);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
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

    public long getCount() {
        return count.get();
    }

    public LongProperty countProperty() {
        return count;
    }

    public void setCount(long count) {
        this.count.set(count);
    }

    public float getPrice() {
        return price.get();
    }

    public FloatProperty priceProperty() {
        return price;
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public long getSentence() {
        return sentence.get();
    }

    public LongProperty sentenceProperty() {
        return sentence;
    }

    public void setSentence(long sentence) {
        this.sentence.set(sentence);
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
