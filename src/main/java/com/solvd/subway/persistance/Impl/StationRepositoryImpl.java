package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Station;
import com.solvd.subway.domain.exception.InsertDataException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.StationRepository;

import java.sql.*;

public class StationRepositoryImpl implements StationRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Station station, Line line) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into Stations(line_id, number, title) values (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setLong(1, line.getId());
            preparedStatement.setInt(2, station.getNumber());
            preparedStatement.setString(3, station.getTitle());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                station.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertDataException("Unable to insert data into stations", e);
        }
        finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
