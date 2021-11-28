package com.solvd.subway.persistance;

import com.solvd.subway.domain.Address;
import com.solvd.subway.domain.Employee;

public interface AddressRepository {

    void create(Address address, Employee employee);
}
