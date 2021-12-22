package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Privilege;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.PrivilegeRepository;
import org.apache.ibatis.session.SqlSession;

public class PrivilegeMybatisImpl implements PrivilegeRepository {
    @Override
    public void create(Privilege privilege) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PrivilegeRepository privilegeRepository = session.getMapper(PrivilegeRepository.class);
            privilegeRepository.create(privilege);
        }
    }
}
