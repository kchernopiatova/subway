package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.exception.ProcessingException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.SubwayRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubwayRepositoryImpl implements SubwayRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Subway subway) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into subways(city) values (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, subway.getCity());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                subway.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Cannot insert subway to a DataBase", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(String city, Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "Update subways set city = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            preparedStatement.setString(1, city);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ProcessingException("Cannot update subways", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete() {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "Delete from subways where city = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            String city = "Brest";

            preparedStatement.setString(1, city);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessingException("Cannot delete from subways", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Subway> findEmployees() {
        String select = "Select s.id as subway_id, s.city as city, d.id as department_id, d.title as department_title, " +
                "e.id as employee_id, e.first_name as first_name, e.last_name as last_name, e.dob as date_of_birth, " +
                "e.position as position, a.id as address_id, a.city as employee_city, a.street as employee_street, " +
                "a.house_number as employee_house_number from subways s left join departments d on s.id = d.subway_id " +
                "left join employees e on d.id = e.department_id left join addresses a on e.id = a.employee_id;";
        List<Subway> subways;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            ResultSet rs = preparedStatement.executeQuery();
            subways = subwayMapping(rs);
        } catch (SQLException e) {
            throw new ProcessingException("Can't get employees", e);
        }
        return subways;
    }

    public static List<Subway> subwayMapping(ResultSet rs) {
        List<Subway> subways = new ArrayList<>();
        List<Department> departments = new ArrayList<>();
        try {
            while (rs.next()) {
                Long id = rs.getLong("subway_id");
                Subway subway = checkIfPresent(id, subways);
                subway.setCity(rs.getString("city"));

                departments.addAll(DepartmentRepositoryImpl.departmentMapping(rs));
                subway.setDepartments(departments);
            }
        } catch (SQLException e) {
            throw new ProcessingException("Mapping exception", e);
        }
        return subways;
    }

    private static Subway checkIfPresent(Long id, List<Subway> subways) {
        Subway result = null;
        for (Subway subway : subways) {
            if (subway.getId().equals(id)) {
                result = subway;
            }
        }
        if (result == null) {
            Subway newSubway = new Subway();
            newSubway.setId(id);
            subways.add(newSubway);
            result = newSubway;
        }
        return result;
    }
}
