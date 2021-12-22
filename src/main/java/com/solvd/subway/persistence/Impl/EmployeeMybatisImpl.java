package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Employee;
import com.solvd.subway.persistence.EmployeeRepository;
import com.solvd.subway.persistence.MybatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class EmployeeMybatisImpl implements EmployeeRepository {

    @Override
    public void create(Employee employee, Department department) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = session.getMapper(EmployeeRepository.class);
            employeeRepository.create(employee, department);
        }
    }
}
