package com.solvd.subway.persistance;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Employee;

public interface EmployeesRepository {
    void create(Employee employee, Department department);
}
