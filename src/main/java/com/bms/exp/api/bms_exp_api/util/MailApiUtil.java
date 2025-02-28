package com.bms.exp.api.bms_exp_api.util;

import com.bms.exp.api.bms_exp_api.models.BillModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.BitSet;
import java.util.HashMap;

@Service
public class MailApiUtil extends ApiUtilImpl {

    @Value("${bms.mail.api.url}")
    String mailApiUrl;

    public void sendUserRegistrationMail(String userEmail, String userName){

        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("userEmail", userEmail);
        queryParams.put("userName", userName);
        makePutCall(null, "/user/registration", mailApiUrl, queryParams);
    }

    public void sendBookingMail(BillModel bill){
        String endPoint = "/user/booking";
        makePutCall(bill, endPoint, mailApiUrl, new HashMap<>());
    }



}
