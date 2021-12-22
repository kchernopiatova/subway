package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Subway;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.SubwayRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SubwayMybatisImpl implements SubwayRepository {
    @Override
    public void create(Subway subway) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayRepository subwayRepository = session.getMapper(SubwayRepository.class);
            subwayRepository.create(subway);
        }
    }


    @Override
    public void update(String city, Long id) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayRepository subwayRepository = session.getMapper(SubwayRepository.class);
            subwayRepository.update(city, id);
        }
    }

    @Override
    public void delete(Subway subway) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayRepository subwayRepository = session.getMapper(SubwayRepository.class);
            subwayRepository.delete(subway);
        }
    }

    @Override
    public List<Subway> findEmployees() {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayRepository subwayRepository = session.getMapper(SubwayRepository.class);
            List<Subway> subways = subwayRepository.findEmployees();
            return subways;
        }
    }
}
