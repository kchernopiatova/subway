package com.solvd.subway.persistance;

import com.solvd.subway.domain.exception.CreateConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    private volatile static ConnectionPool instance;

    private volatile static List<Connection> connections;
    private static final Integer NUMBER_OF_CONNECTIONS = 5;

    private ConnectionPool() {
        Config config = new Config();
        if (instance == null) {
            try {
                Class.forName(Config.DRIVER).newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("message", e);
            }
            connections = new ArrayList<>(NUMBER_OF_CONNECTIONS);
            IntStream.range(0, NUMBER_OF_CONNECTIONS)
                    .boxed()
                    .forEach(index -> connections.add(createConnection()));
        }
    }

    private Connection createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(Config.URL, Config.USER, Config.PASSWORD);
        } catch (SQLException e) {
            throw new CreateConnectionException("Couldn't create connection", e);
        }
        return connection;
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        synchronized (this) {
            return connections
                    .remove(connections.size() - 1);
        }
    }

    public void releaseConnection(Connection connection) {
        synchronized (this) {
            connections.add(connection);
        }
    }
}
