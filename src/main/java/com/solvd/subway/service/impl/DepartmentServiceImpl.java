package com.solvd.subway.service.impl;

import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Employee;
import com.solvd.subway.domain.Subway;

import com.solvd.subway.persistence.DepartmentRepository;
import com.solvd.subway.persistence.Impl.DepartmentRepositoryImpl;
import com.solvd.subway.service.DepartmentService;
import com.solvd.subway.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public Department create(Department department, Subway subway) {
        department.setId(null);
        departmentRepository.create(department, subway);
        if (department.getEmployees() != null) {
            List<Employee> employees = department.getEmployees().stream()
                    .map(employee -> employeeService.create(employee, department))
                    .collect(Collectors.toList());
            department.setEmployees(employees);
        }
        return department;
    }
}
