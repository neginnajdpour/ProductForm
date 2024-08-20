package model.test;

import model.da.ProductDa;
import model.entity.Brand;
import model.entity.Product;
import model.utils.JdbcProvider;

import java.sql.Connection;
import java.sql.SQLException;

public class testProduct {
    public static void main(String[] args) throws SQLException {

        JdbcProvider jdbcProvider = new JdbcProvider();
        System.out.println("Connecting to database...");
        Connection connection = jdbcProvider.getConnection();

        Product product = Product
                .builder()
                .id(1)
                .name("bb")
                .brand(Brand.Asus)
                .price(Double.parseDouble("1000"))
                .count(1)
                .build();

            ProductDa productDa = new ProductDa();
            productDa.editProduct(product);

    }
}
