package model;

public class GoodsMove {
    public enum Action {SALE, BUY}

    private Product product;
    private Long count;
    private Action action;

    public GoodsMove(Product product, Long count, Action action) {
        this.product = product;
        this.count = count;
        this.action = action;
    }

    public Product getProduct() {
        return product;
    }

    public Long getCount() {
        return count;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        return String.format("%s %s * %d", action == Action.BUY? "-":"+", product.getName(), count);
    }
}
