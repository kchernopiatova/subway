package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Station;
import com.solvd.subway.domain.exception.ProcessingException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.LineTransferRepository;

import java.sql.*;

public class LineTransferRepositoryImpl implements LineTransferRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Station station) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into line_transfers(from_station, to_station) values (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, station.getId());
            preparedStatement.setLong(2, station.getToStationId());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
        } catch (SQLException e) {
            throw new ProcessingException("Unable to insert data into payment_options", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
