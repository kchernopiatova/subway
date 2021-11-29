package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.exception.*;
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
            throw new InsertDataException("Cannot insert subway to a DataBase", e);
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
            throw new UpdateDataException("Cannot update subways", e);
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
            throw new DeleteDataException("Cannot delete from subways", e);
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
                "left join employees e on d.id = e.department_id left join addresses a on e.id = a.employee_id";

        Connection connection = CONNECTION_POOL.getConnection();
        List<Subway> subways = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            ResultSet rs = preparedStatement.executeQuery();
            subways = subwayMapping(rs);
        } catch (SQLException e) {
            throw new SelectDataException("Unable select data", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return subways;
    }

    public List<Subway> subwayMapping(ResultSet rs) {
        List<Subway> subways = new ArrayList<>();
        try {
            while (rs.next()) {
                Subway subway = new Subway();
                subway.setId(rs.getLong("subway_id"));
                subway.setCity(rs.getString("city"));
                List<Department> departments = DepartmentRepositoryImpl.departmentMapping(rs, subway.getId());
                subway.setDepartments(departments);
                subways.add(subway);
            }
        } catch (SQLException e) {
            throw new MappingException("Unable to map subway", e);
        }
        return subways;
    }
}
