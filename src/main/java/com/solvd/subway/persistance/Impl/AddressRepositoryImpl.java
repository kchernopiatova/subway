package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Address;
import com.solvd.subway.domain.Employee;
import com.solvd.subway.domain.exception.InsertDataException;
import com.solvd.subway.persistance.AddressRepository;
import com.solvd.subway.persistance.ConnectionPool;

import java.sql.*;

public class AddressRepositoryImpl implements AddressRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public void create(Address address, Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into addresses(employee_id, city, street, house) values (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setInt(4, address.getHouseNumber());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                address.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertDataException("Unable to insert data into addresses", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
