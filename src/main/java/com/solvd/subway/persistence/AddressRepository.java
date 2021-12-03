package com.solvd.subway.persistence;

import com.solvd.subway.domain.Address;
import com.solvd.subway.domain.Employee;
import org.apache.ibatis.annotations.Param;

public interface AddressRepository {

    void create(@Param("address") Address address, @Param("employee") Employee employee);
}
