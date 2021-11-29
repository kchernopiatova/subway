package com.solvd.subway.domain;

import java.util.List;

public class Station {

    private Long id;
    private Integer number;
    private String title;
    private Station toStation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setToStation(Station toStations) {
        this.toStation = toStations;
    }
}
