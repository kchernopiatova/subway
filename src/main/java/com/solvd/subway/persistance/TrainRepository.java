package com.solvd.subway.persistance;

import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.Train;

public interface TrainRepository {
    void create(Train train, Subway subway);

    void update();

    void delete();
}
