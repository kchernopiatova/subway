package com.solvd.subway.service.impl;

import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.Train;
import com.solvd.subway.persistence.Impl.TrainRepositoryImpl;
import com.solvd.subway.persistence.TrainRepository;
import com.solvd.subway.service.TrainService;

public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository = new TrainRepositoryImpl();

    @Override
    public Train create(Train train, Subway subway) {
        train.setId(null);
        trainRepository.create(train, subway);
        return train;
    }
}
