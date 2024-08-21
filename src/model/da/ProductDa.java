package model.da;

import model.entity.Brand;
import model.entity.Product;
import model.utils.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDa {

    private JdbcProvider jdbcProvider = new JdbcProvider();

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public ProductDa() throws SQLException {
        connection = jdbcProvider.getConnection();
    }



    public void saveProduct(Product product) throws SQLException {

        preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT(NAME,BRAND,COUNT,PRICE) VALUES (?,?,?,?)");
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2,product.getBrand().name());
        preparedStatement.setInt(3,product.getCount());
        preparedStatement.setDouble(4,product.getPrice());
        preparedStatement.executeUpdate();

    }

    public void editProduct(Product product) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE PRODUCT SET NAME=?,BRAND=?,COUNT=?,PRICE=? WHERE ID=?");
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2,product.getBrand().name());
        preparedStatement.setInt(3,product.getCount());
        preparedStatement.setDouble(4,product.getPrice());
        preparedStatement.setInt(5,product.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteProduct(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM PRODUCT WHERE ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCT");
        rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("PRODUCT.ID"));
            product.setName(rs.getString("PRODUCT.NAME"));
            product.setBrand(Brand.valueOf(rs.getString("PRODUCT.BRAND")));
            product.setCount(rs.getInt("PRODUCT.COUNT"));
            product.setPrice(rs.getDouble("PRODUCT.PRICE"));

            products.add(product);
        }
        return products;
    }

}
