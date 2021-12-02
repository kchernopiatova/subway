package com.solvd.subway.service;

import com.solvd.subway.domain.Privilege;
import com.solvd.subway.domain.Subway;

public interface PrivilegeService {
    Privilege create(Privilege privilege, Subway subway);
}
