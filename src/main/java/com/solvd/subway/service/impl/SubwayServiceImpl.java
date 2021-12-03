package com.solvd.subway.service.impl;

import com.solvd.subway.domain.*;
import com.solvd.subway.persistence.Impl.SubwayRepositoryImpl;
import com.solvd.subway.persistence.SubwayRepository;
import com.solvd.subway.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class SubwayServiceImpl implements SubwayService {

    private final SubwayRepository subwayRepository = new SubwayRepositoryImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private final TrainService trainService = new TrainServiceImpl();
    private final PaymentOptionService paymentOptionService = new PaymentOptionServiceImpl();
    private final LineService lineService = new LineServiceImpl();
    private final PrivilegeService privilegeService = new PrivilegeServiceImpl();

    @Override
    public Subway create(Subway subway) {
        subway.setId(null);
        subwayRepository.create(subway);
        if (subway.getDepartments() != null) {
            List<Department> departments = subway.getDepartments().stream()
                    .map(department -> departmentService.create(department, subway))
                    .collect(Collectors.toList());
            subway.setDepartments(departments);
        }
        if (subway.getTrains() != null) {
            List<Train> trains = subway.getTrains().stream()
                    .map(train -> trainService.create(train, subway))
                    .collect(Collectors.toList());
            subway.setTrains(trains);
        }
        if (subway.getPaymentOptions() != null) {
            List<PaymentOption> paymentOptions = subway.getPaymentOptions().stream()
                    .map(po -> paymentOptionService.create(po, subway))
                    .collect(Collectors.toList());
            subway.setPaymentOptions(paymentOptions);
        }
        if (subway.getLines() != null) {
            List<Line> lines = subway.getLines().stream()
                    .map(line -> lineService.create(line, subway))
                    .collect(Collectors.toList());
            subway.setLines(lines);
        }
        if (subway.getPrivileges() != null) {
            List<Privilege> privileges = subway.getPrivileges().stream()
                    .map(privilege -> privilegeService.create(privilege, subway))
                    .collect(Collectors.toList());
            subway.setPrivileges(privileges);
        }
        return subway;
    }
}
