package com.solvd.subway.service;

import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.Train;

public interface TrainService {
    Train create(Train train, Subway subway);
}
