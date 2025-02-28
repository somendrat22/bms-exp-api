package com.bms.exp.api.bms_exp_api.requestbody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateShowRB {
    UUID userId;
    UUID threaterId;
    UUID hallId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    int ticketPrice;
    String movieName;

    public CreateShowRB(UUID userId, UUID threaterId, UUID hallId, LocalDateTime startTime, LocalDateTime endTime, int ticketPrice, String movieName) {
        this.userId = userId;
        this.threaterId = threaterId;
        this.hallId = hallId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ticketPrice = ticketPrice;
        this.movieName = movieName;
    }

    public CreateShowRB() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getThreaterId() {
        return threaterId;
    }

    public void setThreaterId(UUID threaterId) {
        this.threaterId = threaterId;
    }

    public UUID getHallId() {
        return hallId;
    }

    public void setHallId(UUID hallId) {
        this.hallId = hallId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
