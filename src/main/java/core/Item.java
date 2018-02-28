package core;

// TODO: 28.02.2018 delete this class
public class Item {
    private Long id;
    private String name;
    private Float basePrice;

    public Item() {
    }

    public Item(Long id, String name, Float basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
