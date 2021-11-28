package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Privilege;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.exception.InsertDataException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.SubwayPrivilegeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubwayPrivilegeRepositoryImpl implements SubwayPrivilegeRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Privilege privilege, Subway subway) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into subway_privileges(privilege_id, subway_id) values (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setLong(1, privilege.getId());
            preparedStatement.setLong(2, subway.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new InsertDataException("Unable to insert data into subway_privileges", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
