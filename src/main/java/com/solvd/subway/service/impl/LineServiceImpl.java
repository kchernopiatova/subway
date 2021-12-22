package com.solvd.subway.service.impl;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Station;
import com.solvd.subway.domain.Subway;

import com.solvd.subway.persistence.Impl.LineMybatisImpl;
import com.solvd.subway.persistence.LineRepository;
import com.solvd.subway.service.LineService;
import com.solvd.subway.service.StationService;

import java.util.List;
import java.util.stream.Collectors;

public class LineServiceImpl implements LineService {

    private final LineRepository lineRepository = new LineMybatisImpl();
    private final StationService stationService = new StationServiceImpl();

    @Override
    public Line create(Line line, Subway subway) {
        line.setId(null);
        lineRepository.create(line, subway);
        if (line.getStations() != null) {
            List<Station> stations = line.getStations().stream()
                    .map(station -> stationService.create(station, line))
                    .collect(Collectors.toList());
            line.setStations(stations);
        }
        return line;
    }
}
