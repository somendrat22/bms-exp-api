package com.bms.exp.api.bms_exp_api.util;

import com.bms.exp.api.bms_exp_api.requestbody.CreateUserRequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class DBApiUtil {

    @Value("${bms.db.api.url}")
    String dbApiUrl;

    public void createUser(CreateUserRequestBody createUserRequestBody){
        String url = dbApiUrl + "/user/create";
        URI finalUrl = URI.create(url);

        RequestEntity req = RequestEntity.post(finalUrl).body(createUserRequestBody);

        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<CreateUserRequestBody> resp = restTemplate.exchange(finalUrl, HttpMethod.POST, req, CreateUserRequestBody.class);
        }catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }
}
