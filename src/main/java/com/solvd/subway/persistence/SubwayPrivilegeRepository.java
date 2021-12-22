package com.solvd.subway.persistence;

import com.solvd.subway.domain.Privilege;
import com.solvd.subway.domain.Subway;
import org.apache.ibatis.annotations.Param;

public interface SubwayPrivilegeRepository {
    void create(@Param("privilege") Privilege privilege, @Param("subway") Subway subway);
}
