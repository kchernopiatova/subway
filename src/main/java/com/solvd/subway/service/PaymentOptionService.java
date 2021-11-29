package com.solvd.subway.service;

import com.solvd.subway.domain.PaymentOption;
import com.solvd.subway.domain.Subway;

public interface PaymentOptionService {
    PaymentOption create(PaymentOption paymentOption, Subway subway);
}
