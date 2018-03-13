package model;

import javafx.beans.property.*;

import java.util.List;

public class Product {
    private final LongProperty id;
    private final StringProperty name;
    private final FloatProperty price;
    private final LongProperty sentence; //предложение всегда статично, можноизменять руками(через интерфейс)
    private final LongProperty count; // кол-во на складе
    private final Type type;

    public Product() {
        this(0L, null, 0.0f, 0L, 0L, null);
    }

    public Product(long id, String name, float basePrice, long sentence, long count, Type type) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleFloatProperty(basePrice);
        this.sentence = new SimpleLongProperty(sentence);
        this.count = new SimpleLongProperty(count);
        this.type = type;
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

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name.getValue();
    }


    public class RawProduct {
        private String name;
        private Float basePrice;
        private List<Item> items = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Float getBasePrice() {
            return basePrice;
        }

        public void setBasePrice(Float basePrice) {
            this.basePrice = basePrice;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }

    public class Item {
        private Integer id;
        private String specificity;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSpecificity() {
            return specificity;
        }

        public void setSpecificity(String specificity) {
            this.specificity = specificity;
        }
    }
}
