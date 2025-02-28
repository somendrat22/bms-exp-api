package com.bms.exp.api.bms_exp_api.util;

import com.bms.exp.api.bms_exp_api.models.BookedSeats;
import com.bms.exp.api.bms_exp_api.models.Hall;
import com.bms.exp.api.bms_exp_api.models.Show;
import com.bms.exp.api.bms_exp_api.models.Theatre;
import com.bms.exp.api.bms_exp_api.requestbody.CreateUserRequestBody;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class DBApiUtil extends ApiUtilImpl {

    @Value("${bms.db.api.url}")
    String dbApiUrl;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ModelMapper mapper;


    public void createUser(CreateUserRequestBody createUserRequestBody){
        String endpoint = "/user/create";
        makePostCall(createUserRequestBody, endpoint, dbApiUrl, new HashMap<>());
    }

    public CreateUserRequestBody getUserByEmail(String email){
        String endPoint = "/user/email/" + email;
        Object resp = makeGetCall(endPoint, dbApiUrl, new HashMap<>());
        CreateUserRequestBody user = mapper.map(resp, CreateUserRequestBody.class);
        return user;
    }

    public Theatre createTheater(Theatre theatre){
       Object resp = makePostCall(theatre, "/theatre/create", dbApiUrl, new HashMap<>());
       Theatre theaterResp = mapper.map(resp, Theatre.class);
       return theaterResp;
    }

    public CreateUserRequestBody getUserById(UUID userId){
        String endPoint = "/user/" + userId.toString();
        Object resp = makeGetCall(endPoint, dbApiUrl, new HashMap<>());
        return mapper.map(resp, CreateUserRequestBody.class);
    }

    public Theatre getTheaterById(UUID theaterId){
        String endPoint = "/theatre/" + theaterId.toString();
        Object resp = makeGetCall(endPoint, dbApiUrl, new HashMap<>());
        return mapper.map(resp, Theatre.class);
    }

    public Hall createHall(Hall hall){
        String endPoint = "/hall/create";
        Object resp = makePostCall(hall, endPoint, dbApiUrl, new HashMap<>());
        return mapper.map(resp, Hall.class);
    }

    public List<Show> getAllShowsByHallId(UUID hallId){
        String endPoint = "/show/all/" + hallId.toString();
        Object resp = makeGetCall(endPoint, dbApiUrl, new HashMap<>());
        Type listType = new TypeToken<List<Show>>(){}.getType();
        List<Show> shows = mapper.map(resp, listType);
        return shows;
    }

    public Hall getHallbyId(UUID hallId){
        String endPoint = "/hall/" + hallId.toString();
        Object resp = makeGetCall(endPoint, dbApiUrl, new HashMap<>());
        return mapper.map(resp, Hall.class);
    }

    public Show createShow(Show show){
        String endPoint = "/show/create";
        Object resp = makePostCall(show, endPoint, dbApiUrl, new HashMap<>());
        return mapper.map(resp, Show.class);
    }

    public Show getShowById(UUID showId){
        String endPoint = "/show/" + showId.toString();
        Object resp = makeGetCall(endPoint, dbApiUrl, new HashMap<>());
        return mapper.map(resp, Show.class);
    }

    public BookedSeats getBookedSeats(UUID showId, int rowNum, int colNum){
        String endPoint = "/bookedSeat/status";
        HashMap<String, String> params = new HashMap<>();
        params.put("showId", showId.toString());
        params.put("rowNum", rowNum + "");
        params.put("colNum", colNum + "");
        Object resp = makeGetCall(endPoint, dbApiUrl, params);
        if(resp == null){
            return null;
        }
        return mapper.map(resp, BookedSeats.class);
    }


    public void createBookedSeats(BookedSeats seats){
        String endPoint = "/bookedSeat/create";
        makePostCall(seats, endPoint, dbApiUrl, new HashMap<>());
    }

}