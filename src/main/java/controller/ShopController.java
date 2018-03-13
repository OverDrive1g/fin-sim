package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;
import model.GoodsMove;
import model.Product;
import model.Stock;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@ViewController(value = "/view/shopView.fxml", title = "Магазин")
public class ShopController {

    @FXML
    private TableView<Product> goodsTable;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Float> priceColumn;

    @FXML
    private TableColumn<Product, Long> countColumn;

    @FXML
    private TableColumn<Product, Float> basePriceColumn;

    @FXML
    private ListView<GoodsMove> goodsMovementList;

    @FXML
    private ComboBox<Product> productMenu;

    @FXML
    private RadioButton rbBuy;

    @FXML
    private RadioButton rbSell;

    @FXML
    @ActionTrigger("accept")
    private Button acceptBtn;

    @FXML
    private TextField quantityOfProduct;

    private Stock stock;

    public ShopController() {
        StringBuilder json = new StringBuilder();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("items.json").getFile());

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                json.append(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        List<Product.RawProduct> items = gson.fromJson(json.toString(), new TypeToken<List<Product.RawProduct>>(){}.getType());
        stock = new Stock(items);
    }

    @PostConstruct
    private void init() {
        goodsTable.setItems(stock.getGoods());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        countColumn.setCellValueFactory(cellData -> cellData.getValue().countProperty().asObject());
        basePriceColumn.setCellValueFactory(cellData -> cellData.getValue().getType().basePriceProperty().asObject());

        goodsMovementList.setItems(stock.getGoodsMoves());

        acceptBtn.setDisable(true);

        productMenu.setItems(stock.getGoods());

        quantityOfProduct.setTextFormatter(new TextFormatter<>(new NumberStringConverter(Locale.ROOT)));
        quantityOfProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() != 0){
                acceptBtn.setDisable(false);
            } else {
                acceptBtn.setDisable(true);
            }
        });
    }

    @ActionMethod("accept")
    public void acceptClick(){
        Product selectedProduct = productMenu.getSelectionModel().getSelectedItem();

        long count = parceLongFromInput(quantityOfProduct.getText());

        if(selectedProduct == null){
            System.err.println("Ошибка, выберите продукт!");
            return;
        }
        if(count < 0 ){
            System.err.println("Ошибка, кол-во товара не может быть отрицательным!");
            return;
        }

        if (rbBuy.isSelected()) {
            stock.buyGood(selectedProduct, count);
        } else {
            stock.sellGood(selectedProduct, count);
        }

        quantityOfProduct.setText("");
        acceptBtn.setDisable(true);
    }

    private Long parceLongFromInput(String input){
        String rowStr = input.replace( ",", "");
        return Long.parseLong(rowStr);
    }

    @FXML
    private void initialize() {
    }
}
