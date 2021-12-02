package com.solvd.subway.service;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Station;

import java.util.List;

public interface StationService {

    Station create(Station station, Line line);

    List<Station> getByNumber(Integer number);
}
