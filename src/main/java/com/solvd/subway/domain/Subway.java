package com.solvd.subway.domain;

import java.util.List;

public class Subway {

    private Long id;
    private String city;
    private List<PaymentOption> paymentOptions;
    private List<Train> trains;
    private List<Department> departments;
    private List<Line> lines;
    private List<Privilege> privileges;

    @Override
    public String toString() {
        return "Subway{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", paymentOptions=" + paymentOptions +
                ", trains=" + trains +
                ", departments=" + departments +
                ", lines=" + lines +
                ", privileges=" + privileges +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<PaymentOption> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(List<PaymentOption> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
