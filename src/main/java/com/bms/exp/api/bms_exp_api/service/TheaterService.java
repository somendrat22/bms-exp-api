package com.bms.exp.api.bms_exp_api.service;

import com.bms.exp.api.bms_exp_api.enums.UserType;
import com.bms.exp.api.bms_exp_api.exceptions.OverlappingShow;
import com.bms.exp.api.bms_exp_api.exceptions.SeatAlreadyBookedException;
import com.bms.exp.api.bms_exp_api.exceptions.UnAuthorized;
import com.bms.exp.api.bms_exp_api.exceptions.UserNotFound;
import com.bms.exp.api.bms_exp_api.models.*;
import com.bms.exp.api.bms_exp_api.requestbody.*;
import com.bms.exp.api.bms_exp_api.responsebody.SeatPlan;
import com.bms.exp.api.bms_exp_api.util.DBApiUtil;
import com.bms.exp.api.bms_exp_api.util.MailApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class TheaterService {

    @Autowired
    DBApiUtil dbApiUtil;

    @Autowired
    MailApiUtil mailApiUtil;


    public Theatre createTheater(CreateTheaterRequestBody createTheaterRequestBody, String email){
        // Check user e
        // xist with this email or not
        // Check user is a theater owner or  not

        // You need to hit database api to get user by email

        CreateUserRequestBody user  = dbApiUtil.getUserByEmail(email);
        if(user == null){
            throw new UserNotFound(String.format("User with email %s does not exist.", user.getEmail()));
        }

        if(!user.getUserType().equals(UserType.THEATEROWNERS)){
            throw new UnAuthorized(String.format("User with email %s does not have access to create threater", user.getEmail()));
        }

        Theatre theatre = new Theatre();

        theatre.setTheaterLoc(createTheaterRequestBody.getLocation());
        theatre.setTheaterName(createTheaterRequestBody.getTheaterName());
        theatre.setOwner(user);

        Theatre theater = dbApiUtil.createTheater(theatre);

        return theater;

    }


    public Hall createHall(CreateHallRequestBody createHallRequestBody){
        UUID userId = createHallRequestBody.getUserId();
        UUID theaterId = createHallRequestBody.getTheaterId();

        CreateUserRequestBody user  = dbApiUtil.getUserById(userId);
        Theatre theatre = dbApiUtil.getTheaterById(theaterId);

        if(!userId.equals(theatre.getOwner().getId())){
            throw new UnAuthorized("userId does not own this theater");
        }

        Hall hall = new Hall();
        hall.setHallName(createHallRequestBody.getHallName());
        hall.setTheatre(theatre);
        hall.setTotalCols(createHallRequestBody.getTotalCols());
        hall.setTotalRows(createHallRequestBody.getTotalRows());

        Hall hallResp = dbApiUtil.createHall(hall);

        return hallResp;
    }

    public boolean isOverLapping(List<Show> shows, Show currentShow){
        for(Show show : shows){
            if(currentShow.getShowStartTime() < show.getShowEndTime()){
                return true;
            }
        }
        return false;
    }


    public Show createShow(CreateShowRB createShowRB){
        UUID userId = createShowRB.getUserId();
        UUID hallId = createShowRB.getHallId();
        Hall  hall = dbApiUtil.getHallbyId(hallId);
        UUID theaterId = createShowRB.getThreaterId();

        // On the basis of hallId i want all the shows
        List<Show> shows = dbApiUtil.getAllShowsByHallId(hallId);
        Collections.sort(shows);
        Show show = new Show();
        LocalDateTime defaultTime = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0);
        LocalDateTime startTime =  createShowRB.getStartTime();
        LocalDateTime endTime = createShowRB.getEndTime();

        int startTimeInSeconds = (int)Duration.between(defaultTime, startTime ).toSeconds();
        int endTimeInSeconds = (int)Duration.between(defaultTime, endTime ).toSeconds();

        show.setShowStartTime(startTimeInSeconds);
        show.setShowEndTime(endTimeInSeconds);

        boolean res = this.isOverLapping(shows, show);
        if(res){
            throw new OverlappingShow("Can't create show because of overlapping timings");
        }
        show.setHall(hall);
        show.setShowPrice(createShowRB.getTicketPrice());
        show.setMovieName(createShowRB.getMovieName());

        return dbApiUtil.createShow(show);
    }

    public List<SeatPlan> getSeatStatusByShowId(UUID showId){
        Show show = dbApiUtil.getShowById(showId);
        int rows = show.getHall().getTotalRows();
        int cols = show.getHall().getTotalCols();
        List<SeatPlan> seatPlans = new ArrayList<>();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                BookedSeats isBooked = dbApiUtil.getBookedSeats(showId, i, j);
                if(isBooked == null){
                    SeatPlan seatPlan = new SeatPlan(i, j, true);
                    seatPlans.add(seatPlan);
                }else{
                    SeatPlan seatPlan = new SeatPlan(i, j, false);
                    seatPlans.add(seatPlan);
                }
            }
        }
        return seatPlans;
    }



    public BillModel bookSeats(UUID showId, BookSeatsRequestBody seats, UUID userId){

        Show show = dbApiUtil.getShowById(showId);
        CreateUserRequestBody user = dbApiUtil.getUserById(userId);

        List<BookedSeats> seatList = seats.getSeats();

        for(BookedSeats seat : seatList){
            BookedSeats dbSeat = dbApiUtil.getBookedSeats(showId, seat.getRowNum(), seat.getColNum());
            if(dbSeat != null){
                throw new SeatAlreadyBookedException("Seat is already booked");
            }
        }

        // Initiate booking process

        // stripe api -> return
        int totalAmount = seatList.size()*show.getShowPrice();
        List<BookedSeats> seats1 = new ArrayList<>();
        for(BookedSeats seat : seatList){
            BookedSeats dbSeat = new BookedSeats();
            dbSeat.setColNum(seat.getColNum());
            dbSeat.setRowNum(seat.getRowNum());
            dbSeat.setShow(show);
            seats1.add(dbSeat);
            dbApiUtil.createBookedSeats(dbSeat);
        }

        // mail api

        BillModel bill = new BillModel();
        bill.setSeats(seats1);
        bill.setUser(user);
        bill.setTotalAmount(totalAmount);
        mailApiUtil.sendBookingMail(bill);

        return bill;
    }


}
