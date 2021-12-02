package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.PaymentOption;
import com.solvd.subway.domain.exception.ProcessingException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.PaymentOptionRepository;

import java.sql.*;

public class PaymentOptionRepositoryImpl implements PaymentOptionRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(PaymentOption paymentOption) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into payment_options(type, price) values (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, String.valueOf(paymentOption.getType()));
            preparedStatement.setBigDecimal(2, paymentOption.getPrice());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                paymentOption.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Unable to insert data into payment_options", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
