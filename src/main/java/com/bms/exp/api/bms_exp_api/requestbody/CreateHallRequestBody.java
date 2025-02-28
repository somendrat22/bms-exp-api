package com.bms.exp.api.bms_exp_api.requestbody;


import java.util.UUID;

public class CreateHallRequestBody {
    UUID userId;
    UUID theaterId;
    String hallName;
    int totalRows;
    int totalCols;

    public CreateHallRequestBody(UUID userId, UUID theaterId, String hallName, int totalRows, int totalCols) {
        this.userId = userId;
        this.theaterId = theaterId;
        this.hallName = hallName;
        this.totalRows = totalRows;
        this.totalCols = totalCols;
    }

    public CreateHallRequestBody() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(UUID theaterId) {
        this.theaterId = theaterId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
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
