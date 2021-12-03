package com.solvd.subway.service.impl;

import com.solvd.subway.domain.Address;
import com.solvd.subway.domain.Department;
import com.solvd.subway.domain.Employee;

import com.solvd.subway.persistence.EmployeeRepository;
import com.solvd.subway.persistence.Impl.EmployeeRepositoryImpl;
import com.solvd.subway.service.AddressService;
import com.solvd.subway.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    private final AddressService addressService = new AddressServiceImpl();

    @Override
    public Employee create(Employee employee, Department department) {
        employee.setId(null);
        employeeRepository.create(employee, department);
        if (employee.getAddress() != null) {
            Address address = addressService.create(employee.getAddress(), employee);
            employee.setAddress(address);
        }
        return employee;
    }
}
