package com.solvd.subway.persistance;

import com.solvd.subway.domain.Privilege;
import com.solvd.subway.domain.Subway;

public interface SubwayPrivilegeRepository {
    void create (Privilege privilege, Subway subway);
}
