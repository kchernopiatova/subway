package com.solvd.subway.service;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Subway;

public interface DepartmentService {
    Department create(Department department, Subway subway);
}
