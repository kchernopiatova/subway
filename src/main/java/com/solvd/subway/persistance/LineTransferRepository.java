package com.solvd.subway.persistance;

import com.solvd.subway.domain.Station;

public interface LineTransferRepository {
    void create(Station station);
}
