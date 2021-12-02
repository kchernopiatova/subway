package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Employee;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.exception.ProcessingException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.DepartmentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            throw new ProcessingException("Unable to insert data to departments", e);
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
            long id = 3L;

            preparedStatement.setString(1, title);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessingException("Cannot update departments", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete() {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "Delete from departments where title = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            String title = "Control";

            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessingException("Cannot delete from departments", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static List<Department> departmentMapping(ResultSet rs) {
        List<Department> departments = new ArrayList<>();
        try {
            Long id = rs.getLong("department_id");
            Department department = checkIfPresent(id, departments);
            department.setTitle(rs.getString("department_title"));

            List<Employee> employees = EmployeeRepositoryImpl.employeeMapping(rs);
            department.setEmployees(employees);
        } catch (SQLException e) {
            throw new ProcessingException("Mapping exception", e);
        }
        return departments;
    }

    private static Department checkIfPresent(Long id, List<Department> departments) {
        Department result = null;
        for (Department department : departments) {
            if (department.getId().equals(id)) {
                result = department;
            }
        }
        if (result == null) {
            Department newDepartment = new Department();
            newDepartment.setId(id);
            departments.add(newDepartment);
            result = newDepartment;
        }
        return result;
    }
}
