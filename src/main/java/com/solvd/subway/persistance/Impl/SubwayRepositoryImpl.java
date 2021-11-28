package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.exception.DeleteDataException;
import com.solvd.subway.domain.exception.InsertDataException;
import com.solvd.subway.domain.exception.SelectDataException;
import com.solvd.subway.domain.exception.UpdateDataException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.SubwayRepository;

import java.sql.*;

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
    public void update() {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "Update subways set city = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            String city = "Pinsk";
            Long id = 5L;

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

    public void selectEmployees() {
        String select = "Select s.id as subway_id, s.city as city, d.id as department_id, d.title as department_title, " +
                "e.id as employee_id, e.first_name as first_name, e.last_name as last_name, e.dob as date_of_birth, " +
                "e.position as position, a.id as address_id, a.city as employee_city, a.street as employee_street, " +
                "a.house_number as employee_house_number from subways s left join departments d on s.id = d.subway_id " +
                "left join employees e on d.id = e.department_id left join addresses a on e.id = a.employee_id";
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long subwayId = rs.getLong(1);
                String city = rs.getString(2);
                Long depId = rs.getLong(3);
                String department = rs.getString(4);
                Long employeeId = rs.getLong(5);
                String firstName = rs.getString(6);
                String lastName = rs.getString(7);
                Date dob = rs.getDate(8);
                String position = rs.getString(9);
                Long addressId = rs.getLong(10);
                String employeeCity = rs.getString(11);
                String employeeStreet = rs.getString(12);
                Integer employeeHouseNumber = rs.getInt(13);

                System.out.println("Subway Id: " + subwayId + ", Subway city: " + city);
                System.out.println("Department Id: " + depId + ", Department: " + department);
                System.out.println("Employee Id: " + employeeId + ", First name: " + firstName + ", Last name: " + lastName + ", Date of birth: " + dob + ", Position: " + position);
                System.out.println("Address id: " + addressId + "; Employee address: " + employeeCity + ", " + employeeStreet + ", " + employeeHouseNumber);
                System.out.println();
            }

        } catch (SQLException e) {
            throw new SelectDataException("Unable select data", e);
        }
        finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
