package com.bms.exp.api.bms_exp_api.models;

import java.util.UUID;

public class Show implements Comparable<Show>{
    UUID id;
    int showPrice;
    String movieName;
    int showStartTime;
    int showEndTime;
    Hall hall;

    public Show(UUID id, int showPrice, String movieName, int showStartTime, int showEndTime, Hall hall) {
        this.id = id;
        this.showPrice = showPrice;
        this.movieName = movieName;
        this.showStartTime = showStartTime;
        this.showEndTime = showEndTime;
        this.hall = hall;
    }

    public Show() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(int showPrice) {
        this.showPrice = showPrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(int showStartTime) {
        this.showStartTime = showStartTime;
    }

    public int getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(int showEndTime) {
        this.showEndTime = showEndTime;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public int compareTo(Show obj){
        return this.showStartTime - obj.showStartTime;
    }

}
