package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.List;

public class Stock {
    private ObservableList<Product> goods;
    private ObservableList<GoodsMove> goodsMoves;

    private Stock() {
        this.goods = FXCollections.observableArrayList();
        this.goodsMoves = FXCollections.observableArrayList();
    }

    public Stock(List<Product.RawProduct> products) {
        this();

        for (Product.RawProduct rp : products) {
            Type type = new Type(rp.getName(), rp.getBasePrice());
            for (Product.Item i : rp.getItems()) {
                Product product = new Product(i.getId(), String.format("%s [%s]", rp.getName(), i.getSpecificity()), rp.getBasePrice(), 1000L,
                        1000L, type);

                type.incSentence(1000L);

                this.goods.add(product);
                this.goodsMoves.add(new GoodsMove(product, 1000L, GoodsMove.Action.BUY));
            }
        }
    }

    private void recalculationPrice(Type type) {
        if (type != null) {
            FilteredList<Product> filteredProducts = goods.filtered(product ->
                    product.getType().getName().equals(type.getName()));

            FilteredList<GoodsMove> fgm = goodsMoves.filtered(goodsMove ->
                    goodsMove.getAction() == GoodsMove.Action.SALE &&
                            goodsMove.getProduct().getType().getName().equals(type.getName()));

            for (Product p :filteredProducts) {

                long demand = 0;
                FilteredList<GoodsMove> fg = fgm.filtered(goodsMove -> goodsMove.getProduct().getName().equals(p.getName()));
                for (GoodsMove g : fg) {
                    demand += g.getCount();
                }

                float basePrice = p.getType().getBasePrice();
                if (p.getCount() == 0 || demand == 0) {
                    p.setPrice(basePrice);
                    continue;
                }

                p.setPrice(basePrice * ((float) demand / (float) p.getType().getAllDemand()) / ((float) p.getSentence() / (float) p.getType().getAllSentence()));

            }
        }
    }

    public void sellGood(Product product, long count) {
        if (count > product.getCount()) {
            System.err.println("Ошибка, кол-во не может быть больше чем на \"складе\"");
            return;
        }
        product.setCount(product.getCount() - count);
        product.getType().decSentence(count);
        product.getType().incDemand(count);
        this.goodsMoves.add(new GoodsMove(product, count, GoodsMove.Action.SALE));

        this.recalculationPrice(product.getType());
    }

    public void buyGood(Product product, long count) {
        product.setCount(product.getCount() + count);
        product.getType().incSentence(count);
        this.goodsMoves.add(new GoodsMove(product, count, GoodsMove.Action.BUY));
        this.recalculationPrice(product.getType());
    }

    public ObservableList<Product> getGoods() {
        return goods;
    }

    public ObservableList<GoodsMove> getGoodsMoves() {
        return goodsMoves;
    }
}