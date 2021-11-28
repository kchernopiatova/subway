package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Privilege;
import com.solvd.subway.domain.exception.InsertDataException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.PrivilegeRepository;

import java.sql.*;

public class PrivilegeRepositoryImpl implements PrivilegeRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Privilege privilege) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into privileges(category, discount) values (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, privilege.getCategory());
            preparedStatement.setInt(2, privilege.getDiscount());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                privilege.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertDataException("Unable to insert data into privileges", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
