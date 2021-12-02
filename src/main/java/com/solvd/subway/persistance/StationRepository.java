package com.solvd.subway.persistance;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Station;

import java.util.List;

public interface StationRepository {
    void create(Station station, Line line);

    List<Station> getByNumber(Integer number);
}
