package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Privilege;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.SubwayPrivilegeRepository;
import org.apache.ibatis.session.SqlSession;

public class SubwayPrivilegeMybatisImpl implements SubwayPrivilegeRepository {
    @Override
    public void create(Privilege privilege, Subway subway) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            SubwayPrivilegeRepository subwayPrivilegeRepository = session.getMapper(SubwayPrivilegeRepository.class);
            subwayPrivilegeRepository.create(privilege, subway);
        }
    }
}
