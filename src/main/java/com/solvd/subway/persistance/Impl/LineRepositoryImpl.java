package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.exception.InsertDataException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.LineRepository;

import java.sql.*;

public class LineRepositoryImpl implements LineRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Line line, Subway subway) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into lines(subway_id, title) values (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, subway.getId());
            preparedStatement.setString(2, line.getTitle());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                line.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertDataException("Unable to insert data into lines", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
