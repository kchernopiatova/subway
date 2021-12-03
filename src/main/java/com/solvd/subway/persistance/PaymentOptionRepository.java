package com.solvd.subway.persistance;

import com.solvd.subway.domain.PaymentOption;

public interface PaymentOptionRepository {
    void create(PaymentOption paymentOption);
}
