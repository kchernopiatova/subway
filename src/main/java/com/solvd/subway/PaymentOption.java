package com.solvd.subway;

public class PaymentOption {

    private Long id;
    private enum type {
        TICKET, TRAVEL_CARD, BANK_CARD;
    };
    private Double price;
    private Privilege privilege;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}
