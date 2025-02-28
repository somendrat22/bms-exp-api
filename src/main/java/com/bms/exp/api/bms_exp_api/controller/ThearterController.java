package com.bms.exp.api.bms_exp_api.controller;

import com.bms.exp.api.bms_exp_api.exceptions.UnAuthorized;
import com.bms.exp.api.bms_exp_api.exceptions.UserNotFound;
import com.bms.exp.api.bms_exp_api.models.Theatre;
import com.bms.exp.api.bms_exp_api.requestbody.CreateTheaterRequestBody;
import com.bms.exp.api.bms_exp_api.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/theater")
public class ThearterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/create")
    public ResponseEntity createTheater(@RequestBody CreateTheaterRequestBody createTheaterRequestBody,
                                        @RequestParam String userEmail){

        // Theater Service
        try{
            Theatre theatre = theaterService.createTheater(createTheaterRequestBody, userEmail);
            return new ResponseEntity(theatre, HttpStatus.CREATED);
        }catch (UnAuthorized e){
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }catch (UserNotFound e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
