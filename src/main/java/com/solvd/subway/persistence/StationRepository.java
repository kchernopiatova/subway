package com.solvd.subway.persistence;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Station;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StationRepository {
    void create(@Param("station") Station station, @Param("line") Line line);

    List<Station> getByNumber(Integer number);
}
