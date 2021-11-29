package com.solvd.subway.service;

import com.solvd.subway.domain.Address;
import com.solvd.subway.domain.Employee;

public interface AddressService {

    Address create(Address address, Employee employee);
}
