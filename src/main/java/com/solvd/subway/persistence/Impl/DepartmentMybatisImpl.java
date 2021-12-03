package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Subway;
import com.solvd.subway.persistence.DepartmentRepository;
import com.solvd.subway.persistence.MybatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class DepartmentMybatisImpl implements DepartmentRepository {
    @Override
    public void create(Department department, Subway subway) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = session.getMapper(DepartmentRepository.class);
            departmentRepository.create(department, subway);
        }
    }

    @Override
    public void update(String title, Long id) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = session.getMapper(DepartmentRepository.class);
            departmentRepository.update(title, id);
        }
    }

    @Override
    public void delete(Department department) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = session.getMapper(DepartmentRepository.class);
            departmentRepository.delete(department);
        }
    }
}
