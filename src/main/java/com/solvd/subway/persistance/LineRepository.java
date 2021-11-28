package com.solvd.subway.persistance;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Subway;

public interface LineRepository {
    void create(Line line, Subway subway);
}
