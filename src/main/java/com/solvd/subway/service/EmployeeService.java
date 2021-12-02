package com.solvd.subway.service;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Employee;

public interface EmployeeService {
    Employee create(Employee employee, Department department);
}
