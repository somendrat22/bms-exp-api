package com.bms.exp.api.bms_exp_api.models;

import java.util.UUID;

public class BookedSeats {

    UUID id;
    int rowNum;
    int colNum;
    Show show;

    public BookedSeats(UUID id, int rowNum, int colNum, Show show) {
        this.id = id;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.show = show;
    }

    public BookedSeats() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
