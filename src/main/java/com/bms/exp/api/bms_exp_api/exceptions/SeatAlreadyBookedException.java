package com.bms.exp.api.bms_exp_api.exceptions;

public class SeatAlreadyBookedException extends RuntimeException{
    public SeatAlreadyBookedException(String message){
        super(message);
    }
}
