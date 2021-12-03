package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.PaymentOption;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.PaymentOptionRepository;
import org.apache.ibatis.session.SqlSession;

public class PaymentOptionMybatisImpl implements PaymentOptionRepository {
    @Override
    public void create(PaymentOption paymentOption) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PaymentOptionRepository paymentOptionRepository = session.getMapper(PaymentOptionRepository.class);
            paymentOptionRepository.create(paymentOption);
        }
    }
}
