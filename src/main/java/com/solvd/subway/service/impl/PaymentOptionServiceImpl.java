package com.solvd.subway.service.impl;

import com.solvd.subway.domain.PaymentOption;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.persistance.Impl.PaymentOptionRepositoryImpl;
import com.solvd.subway.persistance.Impl.SubwayPaymentOptionRepositoryImpl;
import com.solvd.subway.persistance.PaymentOptionRepository;
import com.solvd.subway.persistance.SubwayPaymentOptionRepository;
import com.solvd.subway.service.PaymentOptionService;

public class PaymentOptionServiceImpl implements PaymentOptionService {

    private final PaymentOptionRepository paymentOptionRepository = new PaymentOptionRepositoryImpl();
    private final SubwayPaymentOptionRepository subwayPaymentOptionRepository = new SubwayPaymentOptionRepositoryImpl();

    @Override
    public PaymentOption create(PaymentOption paymentOption, Subway subway) {
        paymentOption.setId(null);
        paymentOptionRepository.create(paymentOption);
        if (subway.getPaymentOptions() != null) {
            subway.getPaymentOptions().stream()
                    .forEach(po -> subwayPaymentOptionRepository.create(paymentOption, subway));
        }
        return paymentOption;
    }
}
