package com.solvd.subway;

public class LineTransfer {

    private Long id;
    private String firstStation;
    private Line firstLine;
    private Line destinationLine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(String firstStation) {
        this.firstStation = firstStation;
    }

    public Line getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(Line firstLine) {
        this.firstLine = firstLine;
    }

    public Line getDestinationLine() {
        return destinationLine;
    }

    public void setDestinationLine(Line destinationLine) {
        this.destinationLine = destinationLine;
    }
}
