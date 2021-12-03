package com.solvd.subway.persistence.Impl;

import com.solvd.subway.domain.Address;
import com.solvd.subway.domain.Employee;
import com.solvd.subway.persistence.AddressRepository;
import com.solvd.subway.persistence.MybatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class AddressMybatisImpl implements AddressRepository {
    @Override
    public void create(Address address, Employee employee) {
        try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AddressRepository addressRepository = session.getMapper(AddressRepository.class);
            addressRepository.create(address, employee);
        }
    }
}
