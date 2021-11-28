package com.solvd.subway;

import com.solvd.subway.persistance.Impl.SubwayRepositoryImpl;

public class MainClass {

    public static void main(String[] args) {
        SubwayRepositoryImpl subwayRepository = new SubwayRepositoryImpl();
        subwayRepository.selectEmployees();
    }
}
