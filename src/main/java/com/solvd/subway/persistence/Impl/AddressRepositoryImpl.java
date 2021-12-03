package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Address;
import com.solvd.subway.domain.Employee;
import com.solvd.subway.domain.exception.ProcessingException;
import com.solvd.subway.persistence.AddressRepository;
import com.solvd.subway.persistence.ConnectionPool;

import java.sql.*;

public class AddressRepositoryImpl implements AddressRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public void create(Address address, Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into addresses(employee_id, city, street, house_number) values (?, ?, ?, ?)";
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
            throw new ProcessingException("Unable to insert data into addresses", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static Address addressMapping(ResultSet rs) {
        Address address = new Address();
        try {
            long id = rs.getLong("address_id");
            if (id != 0) {
                address.setCity(rs.getString("employee_city"));
                address.setStreet(rs.getString("employee_street"));
                address.setHouseNumber(rs.getInt("employee_house_number"));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Mapping exception", e);
        }
        return address;
    }
}
