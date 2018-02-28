package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.List;
import java.util.Map;

public class Stock {
    private ObservableList<Product> goods;
    private ObservableMap<Product, Long> goodsInStock;
    private ObservableList<GoodsMove> goodsMoves;


    private Stock() {
        this.goods = FXCollections.observableArrayList();
        this.goodsInStock = FXCollections.observableHashMap();
        this.goodsMoves = FXCollections.observableArrayList();
    }

    public Stock(List<Map<String, String>> products){
        this();

        for(Map<String, String> i: products){
            System.out.println(i);
            Product ii = new Product(Long.parseLong(i.get("id")), i.get("name"), Float.parseFloat(i.get("basePrice")));

            this.goods.add(ii);
            this.goodsInStock.put(ii, 1000L);
        }
    }

    public void sellGood(Product product, long count){
        assert(count > 0);
        Long goodsInStock = this.goodsInStock.get(product);
        assert(goodsInStock != null);
        assert(goodsInStock < count);
        this.goodsInStock.put(product, goodsInStock - count);

        this.goodsMoves.add(new GoodsMove(product, count, GoodsMove.Action.SALE));
    }

    public void buyGood(Product product, long count){
        assert(count > 0);
        Long value = this.goodsInStock.get(product);
        assert(value != null);
        this.goodsInStock.put(product, value + count);

        this.goodsMoves.add(new GoodsMove(product, count, GoodsMove.Action.BUY));
    }

    public ObservableList<Product> getGoods() {
        return goods;
    }

    public ObservableMap<Product, Long> getGoodsInStock() {
        return goodsInStock;
    }

    public ObservableList<GoodsMove> getGoodsMoves() {
        return goodsMoves;
    }
}