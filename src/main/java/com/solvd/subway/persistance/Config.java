package com.solvd.subway.persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static String DRIVER;
    public static String URL;
    public static String USER;
    public static String PASSWORD;

    static {
        try {
            FileInputStream fis;
            Properties property = new Properties();

            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);

            DRIVER = property.getProperty("driver");
            URL = property.getProperty("url");
            USER = property.getProperty("username");
            PASSWORD = property.getProperty("password");
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }
}