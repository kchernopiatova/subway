package com.solvd.subway.persistance;

import com.solvd.subway.domain.Subway;

import java.util.List;

public interface SubwayRepository {
    void create(Subway subway);

    void update(String city, Long id);

    void delete();

    List<Subway> findEmployees();
}
