package com.solvd.subway.persistance;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Subway;

public interface DepartmentRepository {
    void create(Department department, Subway subway);

    void update();

    void delete();
}
