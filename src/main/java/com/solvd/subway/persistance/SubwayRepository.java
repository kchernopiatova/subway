package com.solvd.subway.persistance;

import com.solvd.subway.domain.Subway;

public interface SubwayRepository {
    void create(Subway subway);

    void update();

    void delete();
}
