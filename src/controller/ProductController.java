package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.SneakyThrows;
import model.da.ProductDa;
import model.entity.Brand;
import model.entity.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private TextField  idTxt, nameTxt,countTxt,priceTxt;

    @FXML
    private ComboBox brandCmb;

    @FXML
    private Button saveBtn, editBtn, deleteBtn;

    @FXML
    private TableView<Product> productTbl;

    @FXML
    private TableColumn<Product, String> nameCol, brandCol;

    @FXML
    private TableColumn<Product, Integer> countCol;

    @FXML
    private TableColumn<Product, Double> priceCol;


    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (Brand brand : Brand.values()) {
            brandCmb.getItems().add(brand);
        }
        resetForm();

        saveBtn.setOnAction(event -> {


            try {
                Brand brand = (Brand) brandCmb.getSelectionModel().getSelectedItem();
                ProductDa productDa = new ProductDa();
                Product product = Product
                        .builder()
                        .name(nameTxt.getText())
                        .brand(Brand.valueOf(brand.name()))
                        .price(Double.parseDouble(priceTxt.getText()))
                        .count(Integer.parseInt(countTxt.getText()))
                        .build();
                productDa.saveProduct(product);
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Product Saved");
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Product Saved");
                alert.showAndWait();
                resetForm();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        editBtn.setOnAction(event -> {

            try {
                Brand brand = (Brand) brandCmb.getSelectionModel().getSelectedItem();
                ProductDa productDa = new ProductDa();
                Product product = Product
                        .builder()
                        .id(Integer.parseInt(idTxt.getText()))
                        .name(nameTxt.getText())
                        .brand(Brand.valueOf(brand.name()))
                        .price(Double.parseDouble(priceTxt.getText()))
                        .count(Integer.parseInt(countTxt.getText()))
                        .build();
                productDa.editProduct(product);
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Product Edited");
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Product Edited");
                alert.showAndWait();
                resetForm();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

        deleteBtn.setOnAction(event -> {

            try {

                ProductDa productDa = new ProductDa();
                productDa.deleteProduct(Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Product Deleted");
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Product Deleted");
                alert.showAndWait();
                resetForm();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

        productTbl.setOnMouseReleased(event -> {
            Product person = productTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(person.getId()));
            nameTxt.setText(person.getName());
            countTxt.setText(String.valueOf(person.getCount()));
            priceTxt.setText(String.valueOf(person.getPrice()));
            brandCmb.getSelectionModel().select(person.getBrand());
        });


    }

    public void resetForm() throws SQLException {
        idTxt.clear();
        nameTxt.clear();
        countTxt.clear();
        priceTxt.clear();

        try {
            ProductDa productDa = new ProductDa();
            refreshTbl(productDa.getAllProducts());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Find Persons Error\n" + e.getMessage());
            alert.show();
        }
    }

    public void refreshTbl(List<Product> productList){

        ObservableList<Product> products = FXCollections.observableList(productList);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTbl.setItems(products);
    }
}
