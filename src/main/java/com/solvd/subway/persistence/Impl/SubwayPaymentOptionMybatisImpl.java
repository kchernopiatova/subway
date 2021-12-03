package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.PaymentOption;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.SubwayPaymentOptionRepository;
import org.apache.ibatis.session.SqlSession;

public class SubwayPaymentOptionMybatisImpl implements SubwayPaymentOptionRepository {
    @Override
    public void create(PaymentOption paymentOption, Subway subway) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayPaymentOptionRepository subwayPaymentOptionRepository = session.getMapper(SubwayPaymentOptionRepository.class);
            subwayPaymentOptionRepository.create(paymentOption, subway);
        }
    }
}
