package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.Train;
import com.solvd.subway.domain.exception.ProcessingException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.TrainRepository;

import java.sql.*;

public class TrainRepositoryImpl implements TrainRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public void create(Train train, Subway subway) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into trains(subway_id, model, speed, creation_date) values (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, subway.getId());
            preparedStatement.setString(2, train.getModel());
            preparedStatement.setInt(3, train.getSpeed());
            preparedStatement.setDate(4, Date.valueOf(train.getCreationDate()));

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                train.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Cannot insert train to a DataBase", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update() {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "Update trains set model = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            long id = 3L;
            String model = "NewModel";

            preparedStatement.setString(1, model);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessingException("Cannot update train data", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Train train) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "Delete from trains where train_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            Long id = train.getId();
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessingException("Cannot delete train data", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}