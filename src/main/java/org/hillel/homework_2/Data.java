package org.hillel.homework_2;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Data {
    private final Properties properties = new Properties();

    public Data() throws IOException {
        load("application.properties");
    }

    private void load(final String fileName) throws IOException {
        if (!StringUtils.hasText(fileName)) throw new IllegalArgumentException("fileName must be set");
        try (InputStream is = Data.class.getClassLoader().getResourceAsStream(fileName)){
            properties.load(is);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("spring.datasource.url"),
                properties.getProperty("spring.datasource.username"),
                properties.getProperty("spring.datasource.password"));
    }

    public void close(Connection con) throws SQLException {
        if (con != null){
            con.close();
        }
    }
}