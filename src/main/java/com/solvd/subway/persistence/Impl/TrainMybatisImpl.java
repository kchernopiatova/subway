package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.Train;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.TrainRepository;
import org.apache.ibatis.session.SqlSession;

public class TrainMybatisImpl implements TrainRepository {
    @Override
    public void create(Train train, Subway subway) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            TrainRepository trainRepository = session.getMapper(TrainRepository.class);
            trainRepository.create(train, subway);
        }
    }

    @Override
    public void delete(Train train) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            TrainRepository trainRepository = session.getMapper(TrainRepository.class);
            trainRepository.delete(train);
        }
    }
}
