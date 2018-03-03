package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.List;
import java.util.Map;

public class Stock {
    private ObservableList<Product> goods;
    private ObservableList<GoodsMove> goodsMoves;
    private long allSentence = 0;


    private Stock() {
        this.goods = FXCollections.observableArrayList();
        this.goodsMoves = FXCollections.observableArrayList();
    }

    public Stock(List<Map<String, String>> products) {
        this();

        for (Map<String, String> i : products) {
            Product product = new Product(Long.parseLong(i.get("id")), i.get("name"),
                    Float.parseFloat(i.get("basePrice")), 1000L, 1000L);

            this.allSentence += 1000L;
            this.goods.add(product);
        }
    }

    private void recalculationPrice() {
        for (Product p : goods) {
            FilteredList<GoodsMove> filteredList = goodsMoves.filtered(goodsMove ->
                    goodsMove.getProduct().getName().equals(p.getName()));

            long demand = 0;
            for(GoodsMove gm: filteredList){
                if (gm.getAction() == GoodsMove.Action.SALE)
                    demand += gm.getCount();
            }

            if (p.getCount() == 0 || demand == 0)
                continue;

            p.setPrice(p.getBasePrice() * (getAllDemand() / demand) / (allSentence / p.getSentence()));
        }
    }

    public void sellGood(Product product, long count) {
        if (count > product.getCount()){
            System.err.println("Ошибка, кол-во не может быть больше чем на \"складе\"");
            return;
        }
        product.setCount(product.getCount() - count);
        this.goodsMoves.add(new GoodsMove(product, count, GoodsMove.Action.SALE));

        this.recalculationPrice();
    }

    public void buyGood(Product product, long count) {
        product.setCount(product.getCount() + count);
        this.goodsMoves.add(new GoodsMove(product, count, GoodsMove.Action.BUY));
        this.recalculationPrice();
    }

    public ObservableList<Product> getGoods() {
        return goods;
    }

    public ObservableList<GoodsMove> getGoodsMoves() {
        return goodsMoves;
    }

    private long getAllDemand(){
        long result = 0L;

        for (GoodsMove gm:goodsMoves) {
            result += gm.getAction() == GoodsMove.Action.SALE ? gm.getCount():0L;
        }

        return result;
    }
}