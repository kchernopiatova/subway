package com.solvd.subway.service.impl;

import com.solvd.subway.domain.Station;
import com.solvd.subway.service.LineTransferService;
import com.solvd.subway.service.StationService;

public class LineTransferServiceImpl implements LineTransferService {

    private final StationService stationService = new StationServiceImpl();

    @Override
    public Station create(Station station) {
        return station;
    }
}
