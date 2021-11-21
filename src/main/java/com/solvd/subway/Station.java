package com.solvd.subway;

public class Station {

    private Long id;
    private Integer number;
    private String title;
    private LineTransfer lineTransfer;

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

    public LineTransfer getLineTransfer() {
        return lineTransfer;
    }

    public void setLineTransfer(LineTransfer lineTransfer) {
        this.lineTransfer = lineTransfer;
    }
}
