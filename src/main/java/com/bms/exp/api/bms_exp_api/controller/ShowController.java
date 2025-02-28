package com.bms.exp.api.bms_exp_api.controller;

import com.bms.exp.api.bms_exp_api.models.Show;
import com.bms.exp.api.bms_exp_api.requestbody.CreateShowRB;
import com.bms.exp.api.bms_exp_api.responsebody.SeatPlan;
import com.bms.exp.api.bms_exp_api.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/exp/show")
public class ShowController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/create")
    public Show createShow(@RequestBody CreateShowRB show){
        return theaterService.createShow(show);
    }

    @GetMapping("/seatstatus/{showId}")
    public List<SeatPlan> getSeatStatusByShow(@PathVariable UUID showId){
        return theaterService.getSeatStatusByShowId(showId);
    }

}
