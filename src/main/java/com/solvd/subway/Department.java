package com.solvd.subway;

public enum Department {

    SECURITY("Security"), ENGINEER("Engineer"), ACCOUNTANT("Accountant"), HR("HR");

    private Long id;
    private String title;

    Department(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
