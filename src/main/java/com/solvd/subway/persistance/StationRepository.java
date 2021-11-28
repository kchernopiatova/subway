package com.solvd.subway.persistance;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Station;

public interface StationRepository {
    void create(Station station, Line line);
}
