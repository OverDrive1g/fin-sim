package model;

import javafx.beans.property.*;

public class Product {
    private final LongProperty id;
    private final StringProperty name;
    private final FloatProperty basePrice;

    public Product() {
        this(0, null, 0.0f);
    }

    public Product(long id, String name, float basePrice) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.basePrice = new SimpleFloatProperty(basePrice);
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

    @Override
    public String toString() {
        return name.getValue();
    }
}
