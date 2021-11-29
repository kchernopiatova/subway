package com.solvd.subway.service;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Station;

public interface StationService {

    Station create(Station station, Line line);
}
