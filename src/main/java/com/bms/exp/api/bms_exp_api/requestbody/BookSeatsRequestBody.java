package com.bms.exp.api.bms_exp_api.requestbody;

import java.util.List;

import com.bms.exp.api.bms_exp_api.models.BookedSeats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class BookSeatsRequestBody {



    List<BookedSeats> seats;

    public BookSeatsRequestBody(List<BookedSeats> seats) {
        this.seats = seats;
    }

    public BookSeatsRequestBody() {
    }

    public List<BookedSeats> getSeats() {
        return seats;
    }

    public void setSeats(List<BookedSeats> seats) {
        this.seats = seats;
    }
}
