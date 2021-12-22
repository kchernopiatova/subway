package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Address;
import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Employee;
import com.solvd.subway.domain.exception.ProcessingException;
import com.solvd.subway.persistence.ConnectionPool;
import com.solvd.subway.persistence.EmployeeRepository;

import java.sql.*;
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
            throw new ProcessingException("Cannot insert data into employees", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static List<Employee> employeeMapping(ResultSet rs) {
        List<Employee> employees = new ArrayList<>();
        try {
            long id = rs.getLong("employee_id");
            if (id != 0) {
                Employee employee = checkIfPresent(id, employees);
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setDob(rs.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate());
                employee.setPosition(rs.getString("position"));

                Address address = AddressRepositoryImpl.addressMapping(rs);
                employee.setAddress(address);
            }
        } catch (SQLException e) {
            throw new ProcessingException("Mapping exception", e);
        }
        return employees;
    }

    private static Employee checkIfPresent(Long id, List<Employee> employees) {
        Employee result = null;
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                result = employee;
            }
        }
        if (result == null) {
            Employee newEmployee = new Employee();
            newEmployee.setId(id);
            employees.add(newEmployee);
            result = newEmployee;
        }
        return result;
    }
}
