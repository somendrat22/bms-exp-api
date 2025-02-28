package com.bms.exp.api.bms_exp_api.models;

import java.util.UUID;

public class Hall {
    UUID id;
    String hallName;
    Theatre theatre;
    int totalRows;
    int totalCols;


    public Hall(UUID id, String hallName, Theatre theatre, int totalRows, int totalCols) {
        this.id = id;
        this.hallName = hallName;
        this.theatre = theatre;
        this.totalRows = totalRows;
        this.totalCols = totalCols;
    }

    public Hall() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalCols() {
        return totalCols;
    }

    public void setTotalCols(int totalCols) {
        this.totalCols = totalCols;
    }
}
