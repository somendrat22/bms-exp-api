package com.bms.exp.api.bms_exp_api.security;

import com.bms.exp.api.bms_exp_api.requestbody.CreateUserRequestBody;
import com.bms.exp.api.bms_exp_api.util.DBApiUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenHandler {

    @Value("${api.secret.key}")
    String key;

    Long expirationTime = 10000000L;

    @Autowired
    DBApiUtil dbApiUtil;

    public String generateToken(String credentials){
        return Jwts.builder()
                .setSubject(credentials)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public String extractCredentials(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        // token
        // I will extract credentials
        String credentials = this.extractCredentials(token);
        String email = credentials.split(":")[0];
        String password = credentials.split(":")[1];
        CreateUserRequestBody user  = dbApiUtil.getUserByEmail(email);
        if(user == null){
            return false;
        }
        if(!user.getPassword().equals(password)){
            return false;
        }
        return true;

    }

}
