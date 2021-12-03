package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Line;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.persistence.LineRepository;
import com.solvd.subway.persistence.MybatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class LineMybatisImpl implements LineRepository {
    @Override
    public void create(Line line, Subway subway) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LineRepository lineRepository = session.getMapper(LineRepository.class);
            lineRepository.create(line, subway);
        }
    }
}
