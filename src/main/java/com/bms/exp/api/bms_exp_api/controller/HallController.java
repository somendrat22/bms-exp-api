package com.bms.exp.api.bms_exp_api.controller;

import com.bms.exp.api.bms_exp_api.models.Hall;
import com.bms.exp.api.bms_exp_api.requestbody.CreateHallRequestBody;
import com.bms.exp.api.bms_exp_api.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exp/hall")
public class HallController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/create")
    public ResponseEntity createHall(@RequestBody CreateHallRequestBody hallRB){
        // service
        try{
            Hall hall = theaterService.createHall(hallRB);
            return new ResponseEntity(hall, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
