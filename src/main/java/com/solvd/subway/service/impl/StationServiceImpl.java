package com.solvd.subway.service.impl;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Station;
import com.solvd.subway.persistance.Impl.StationRepositoryImpl;
import com.solvd.subway.persistance.StationRepository;
import com.solvd.subway.service.StationService;

public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository = new StationRepositoryImpl();

    @Override
    public Station create(Station station, Line line) {
        station.setId(null);
        stationRepository.create(station, line);
        return station;
    }
}
