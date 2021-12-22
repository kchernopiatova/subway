package com.solvd.subway.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static String DRIVER;
    public static String URL;
    public static String USER;
    public static String PASSWORD;

    public Config() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")){

            Properties property = new Properties();
            property.load(fis);

            DRIVER = property.getProperty("driver");
            URL = property.getProperty("url");
            USER = property.getProperty("username");
            PASSWORD = property.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }
}