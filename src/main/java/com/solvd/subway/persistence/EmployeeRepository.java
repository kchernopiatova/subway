package com.solvd.subway.persistence;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeRepository {
    void create(@Param("employee") Employee employee, @Param("department") Department department);
}
