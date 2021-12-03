package com.solvd.subway.persistence;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Subway;
import org.apache.ibatis.annotations.Param;

public interface DepartmentRepository {
    void create(@Param("department") Department department, @Param("subway") Subway subway);

    void update(@Param("title") String title, @Param("id") Long id);

    void delete(Department department);
}
