package com.bilgeadam.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bilgeadam.exception.AuthManagerException;
import com.bilgeadam.exception.ErrorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JwtTokenProvider {
    String secretKey = "secretKey";
    String audience = "audience";
    String issuer = "issuer";

    public Optional<String> createToken(Long id, List<String> role, String name, String surname, String email){
        String token = null;
        Date date = new Date(System.currentTimeMillis() + (1000*60*5));
        try {
            token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .withClaim("id", id)
                    .withClaim("role", role.toString())
                    .withClaim("name", name)
                    .withClaim("surname", surname)
                    .withClaim("email", email)
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }


    public Optional<Long> getIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withAudience(audience).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null){
                throw new AuthManagerException(ErrorType.INVALID_TOKEN);
            }
            Long id = decodedJWT.getClaim("id").asLong();
            return Optional.of(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public Optional<String> getRoleFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withAudience(audience).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null){
                throw new AuthManagerException(ErrorType.INVALID_TOKEN);
            }
            String role = decodedJWT.getClaim("role").asString();
            return Optional.of(role); // == Optional<String>
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    //Password encode
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
