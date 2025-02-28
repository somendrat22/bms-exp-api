package com.bms.exp.api.bms_exp_api.models;

import com.bms.exp.api.bms_exp_api.requestbody.CreateUserRequestBody;

import java.util.List;

public class BillModel {
    List<BookedSeats> seats;
    int totalAmount;

    CreateUserRequestBody user;

    public CreateUserRequestBody getUser() {
        return user;
    }

    public void setUser(CreateUserRequestBody user) {
        this.user = user;
    }

    public BillModel(List<BookedSeats> seats, int totalAmount, CreateUserRequestBody user) {
        this.seats = seats;
        this.totalAmount = totalAmount;
        this.user = user;
    }

    public BillModel(List<BookedSeats> seats, int totalAmount) {
        this.seats = seats;
        this.totalAmount = totalAmount;
    }

    public BillModel() {
    }

    public List<BookedSeats> getSeats() {
        return seats;
    }

    public void setSeats(List<BookedSeats> seats) {
        this.seats = seats;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
