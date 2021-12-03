package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Station;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.StationRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StationMybatisImpl implements StationRepository {
    @Override
    public void create(Station station, Line line) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            StationRepository stationRepository = session.getMapper(StationRepository.class);
            stationRepository.create(station, line);
        }
    }

    @Override
    public List<Station> getByNumber(Integer number) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            StationRepository stationRepository = session.getMapper(StationRepository.class);
            List<Station> stations = stationRepository.getByNumber(number);
            return stations;
        }
    }
}
