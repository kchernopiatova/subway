package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.exception.DeleteDataException;
import com.solvd.subway.domain.exception.InsertDataException;
import com.solvd.subway.domain.exception.UpdateDataException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.SubwayRepository;

import java.sql.*;

public class SubwayRepositoryImpl implements SubwayRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Subway subway) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into subways(city) values (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, subway.getCity());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                subway.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertDataException("Cannot insert subway to a DataBase", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update() {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "Update subways set city = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            String city = "Pinsk";
            Long id = 5L;

            preparedStatement.setString(1, city);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new UpdateDataException("Cannot update subways", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete() {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "Delete from subways where city = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            String city = "Brest";

            preparedStatement.setString(1, city);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteDataException("Cannot delete from subways", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
