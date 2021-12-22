package com.solvd.subway.persistence;

import com.solvd.subway.domain.PaymentOption;

public interface PaymentOptionRepository {
    void create(PaymentOption paymentOption);
}
