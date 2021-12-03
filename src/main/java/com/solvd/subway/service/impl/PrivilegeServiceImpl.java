package com.solvd.subway.service.impl;

import com.solvd.subway.domain.Privilege;
import com.solvd.subway.domain.Subway;

import com.solvd.subway.persistence.Impl.PrivilegeRepositoryImpl;
import com.solvd.subway.persistence.Impl.SubwayPrivilegeRepositoryImpl;
import com.solvd.subway.persistence.PrivilegeRepository;
import com.solvd.subway.persistence.SubwayPrivilegeRepository;
import com.solvd.subway.service.PrivilegeService;

public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository privilegeRepository = new PrivilegeRepositoryImpl();
    private final SubwayPrivilegeRepository subwayPrivilegeRepository = new SubwayPrivilegeRepositoryImpl();

    @Override
    public Privilege create(Privilege privilege, Subway subway) {
        privilege.setId(null);
        privilegeRepository.create(privilege);
        if (subway.getPrivileges() != null) {
            subway.getPrivileges()
                    .forEach(pr -> subwayPrivilegeRepository.create(privilege, subway));
        }
        return privilege;
    }
}
