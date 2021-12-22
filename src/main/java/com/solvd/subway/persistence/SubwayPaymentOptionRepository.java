package com.solvd.subway.persistence;

import com.solvd.subway.domain.PaymentOption;
import com.solvd.subway.domain.Subway;
import org.apache.ibatis.annotations.Param;

public interface SubwayPaymentOptionRepository {
    void create(@Param("paymentOption") PaymentOption paymentOption, @Param("subway") Subway subway);
}
