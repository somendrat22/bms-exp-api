package com.bms.exp.api.bms_exp_api.controller;

import com.bms.exp.api.bms_exp_api.models.BillModel;
import com.bms.exp.api.bms_exp_api.requestbody.BookSeatsRequestBody;
import com.bms.exp.api.bms_exp_api.requestbody.CreateUserRequestBody;
import com.bms.exp.api.bms_exp_api.security.JwtTokenHandler;
import com.bms.exp.api.bms_exp_api.service.TheaterService;
import com.bms.exp.api.bms_exp_api.service.UserService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exp/user")
public class CommonUserController {
    UserService userService;

    @Autowired
    TheaterService theaterService;

    @Autowired
    JwtTokenHandler jwtTokenHandler;
    @Autowired
    CommonUserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity registerUser(@RequestBody CreateUserRequestBody createUserRequestBody){
        // service
        try{
            userService.createUser(createUserRequestBody);
            String credentials = createUserRequestBody.getEmail() + ":" + createUserRequestBody.getPassword();
            String token = jwtTokenHandler.generateToken(credentials);
            return new ResponseEntity(token, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/book/seats/{showId}/{userId}")
    public BillModel bookSeats(@PathVariable UUID showId, @PathVariable UUID userId, @RequestBody BookSeatsRequestBody seats){
        // Service layer
        return theaterService.bookSeats(showId, seats, userId);
    }
}
