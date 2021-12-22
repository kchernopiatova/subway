package com.solvd.subway.service.impl;

import com.solvd.subway.domain.Address;
import com.solvd.subway.domain.Employee;
import com.solvd.subway.persistence.AddressRepository;
import com.solvd.subway.persistence.Impl.AddressRepositoryImpl;
import com.solvd.subway.service.AddressService;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository = new AddressRepositoryImpl();

    @Override
    public Address create(Address address, Employee employee) {
        address.setId(null);
        addressRepository.create(address, employee);
        return address;
    }
}