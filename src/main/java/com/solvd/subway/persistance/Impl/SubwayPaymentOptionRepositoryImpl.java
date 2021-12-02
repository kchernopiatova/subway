package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.PaymentOption;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.exception.ProcessingException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.SubwayPaymentOptionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubwayPaymentOptionRepositoryImpl implements SubwayPaymentOptionRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(PaymentOption paymentOption, Subway subway) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into subway_payment_options(payment_option_id, subway_id) values (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setLong(1, paymentOption.getId());
            preparedStatement.setLong(2, subway.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessingException("Unable to insert data into subway_payment_options", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
