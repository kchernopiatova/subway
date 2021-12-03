package com.solvd.subway.persistance;

import com.solvd.subway.domain.PaymentOption;
import com.solvd.subway.domain.Subway;

public interface SubwayPaymentOptionRepository {
    void create(PaymentOption paymentOption, Subway subway);
}
