package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Station;
import com.solvd.subway.domain.exception.ProcessingException;
import com.solvd.subway.persistence.ConnectionPool;
import com.solvd.subway.persistence.StationRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationRepositoryImpl implements StationRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Station station, Line line) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into Stations(line_id, number, title) values (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, line.getId());
            preparedStatement.setInt(2, station.getNumber());
            preparedStatement.setString(3, station.getTitle());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                station.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Unable to insert data into stations", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public List<Station> getByNumber(Integer number) {
        String query = "Select s.id as station_id, s.number as station_number, s.title as station_title " +
                "from stations s where number = ?";
        Connection connection = CONNECTION_POOL.getConnection();
        List<Station> stations;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, number);
            ResultSet rs = preparedStatement.executeQuery();
            stations = stationMapping(rs);
        } catch (SQLException e) {
            throw new ProcessingException("Unable to get data from Stations", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return stations;
    }

    public List<Station> stationMapping(ResultSet rs) {
        List<Station> stations = new ArrayList<>();
        try {
            while (rs.next()) {
                Station station = new Station();
                station.setId(rs.getLong("station_id"));
                station.setTitle(rs.getString("station_title"));
                station.setNumber(rs.getInt("station_number"));
                stations.add(station);
            }
        } catch (SQLException e) {
            throw new ProcessingException("Mapping exception", e);
        }
        return stations;
    }
}
