package com.solvd.subway.persistence;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Subway;
import org.apache.ibatis.annotations.Param;

public interface LineRepository {
    void create(@Param("line") Line line, @Param("subway") Subway subway);
}
