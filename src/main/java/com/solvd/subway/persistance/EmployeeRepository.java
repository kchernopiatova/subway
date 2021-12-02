package com.solvd.subway.persistance;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Employee;

public interface EmployeeRepository {
    void create(Employee employee, Department department);
}
