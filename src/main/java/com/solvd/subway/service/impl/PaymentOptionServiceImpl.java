package com.solvd.subway.service.impl;

import com.solvd.subway.domain.PaymentOption;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.persistence.Impl.PaymentOptionMybatisImpl;
import com.solvd.subway.persistence.Impl.SubwayPaymentOptionMybatisImpl;
import com.solvd.subway.persistence.PaymentOptionRepository;
import com.solvd.subway.persistence.SubwayPaymentOptionRepository;
import com.solvd.subway.service.PaymentOptionService;

public class PaymentOptionServiceImpl implements PaymentOptionService {

    private final PaymentOptionRepository paymentOptionRepository = new PaymentOptionMybatisImpl();
    private final SubwayPaymentOptionRepository subwayPaymentOptionRepository = new SubwayPaymentOptionMybatisImpl();

    @Override
    public PaymentOption create(PaymentOption paymentOption, Subway subway) {
        paymentOption.setId(null);
        paymentOptionRepository.create(paymentOption);
        if (subway.getPaymentOptions() != null) {
            subway.getPaymentOptions()
                    .forEach(po -> subwayPaymentOptionRepository.create(paymentOption, subway));
        }
        return paymentOption;
    }
}
