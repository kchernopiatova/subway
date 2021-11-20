package com.solvd.subway;

public class LineTransfer {

    private Long id;
    private Station from_station;
    private Station to_station;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Station getFrom_station() {
        return from_station;
    }

    public void setFrom_station(Station from_station) {
        this.from_station = from_station;
    }

    public Station getTo_station() {
        return to_station;
    }

    public void setTo_station(Station to_station) {
        this.to_station = to_station;
    }
}
