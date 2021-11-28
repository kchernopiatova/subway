package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.exception.InsertDataException;
import com.solvd.subway.domain.exception.UpdateDataException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.DepartmentRepository;

import java.sql.*;

public class DepartmentRepositoryImpl implements DepartmentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Department department, Subway subway) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into departments(subway_id, title) values (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, subway.getId());
            preparedStatement.setString(2, department.getTitle());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                department.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertDataException("Unable to insert data to departments");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update() {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "Update departments set title = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            String title = "Control";
            Long id = 3L;

            preparedStatement.setString(1, title);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateDataException("Cannot update departments", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public void delete() {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "Delete from departments where title = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            String title = "Control";

            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateDataException("Cannot delete from departments", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
