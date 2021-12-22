package com.solvd.subway.persistence;

import com.solvd.subway.domain.Subway;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubwayRepository {
    void create(Subway subway);

    void update(@Param("city") String city, @Param("id") Long id);

    void delete(Subway subway);

    List<Subway> findEmployees();
}
