package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Privilege;
import com.solvd.subway.domain.exception.ProcessingException;
import com.solvd.subway.persistence.ConnectionPool;
import com.solvd.subway.persistence.PrivilegeRepository;

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
            throw new ProcessingException("Unable to insert data into privileges", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
