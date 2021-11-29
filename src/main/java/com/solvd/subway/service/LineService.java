package com.solvd.subway.service;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Subway;

public interface LineService {
    Line create(Line line, Subway subway);
}
