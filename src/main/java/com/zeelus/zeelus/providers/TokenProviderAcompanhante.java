package com.zeelus.zeelus.providers;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;

@Service
public class TokenProviderAcompanhante {
    @Value("${security.token.secret}")
    public String secretKey;

    public DecodedJWT verifyToken(String token){
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try{
            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return jwt;

        } catch (Exception e) {

            return null;
        }
    }
}
