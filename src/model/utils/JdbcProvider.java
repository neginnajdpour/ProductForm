package model.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcProvider {

    private Connection connection;
    private BasicDataSource basicDataSource;

    public Connection getConnection() throws SQLException {

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/mft");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("Negin@09143148516");
        basicDataSource.setInitialSize(5);
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(10);

        return basicDataSource.getConnection();

    }
}
