package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Address;
import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Employee;
import com.solvd.subway.domain.exception.InsertDataException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.EmployeeRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Employee employee, Department department) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into employees(department_id, first_name, last_name, dob, position) values (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, department.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setDate(4, Date.valueOf(employee.getDob()));
            preparedStatement.setString(5, employee.getPosition());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                employee.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertDataException("Cannot insert data into employees", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static List<Employee> employeeMapping(ResultSet rs, Long departmentId) {
        List<Employee> employees = new ArrayList<>();
        try {
            while (rs.next()) {
                if (rs.getLong("department_id") == departmentId) {
                    Employee employee = new Employee();
                    employee.setId(rs.getLong("department_id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setDob(rs.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate());
                    employee.setPosition(rs.getString("position"));
                    Address address = AddressRepositoryImpl.addressMapping(rs, employee.getId());
                    employee.setAddress(address);
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("exc", e);
        }
        return employees;
    }
}
