package com.zeelus.zeelus.modules.acompanhante.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zeelus.zeelus.modules.acompanhante.AcompanhanteEntity;
import com.zeelus.zeelus.modules.acompanhante.dto.LoginAcompanhanteDTO;
import com.zeelus.zeelus.modules.acompanhante.dto.LoginAcompanhanteResponseDTO;
import com.zeelus.zeelus.modules.acompanhante.repository.AcompanhanteRepository;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class LoginAcompanhanteService {
    @Autowired
    private AcompanhanteRepository acompanhanteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretToken;

    public LoginAcompanhanteResponseDTO execute(LoginAcompanhanteDTO acompanhanteDTO) throws AuthException {
        AcompanhanteEntity acompanhanteEntity = this.acompanhanteRepository.findByEmail(acompanhanteDTO.getEmail())
                .orElseThrow(() -> {
                    throw new RuntimeException("E-mail não encontrado!");
                });

        boolean passwordVerify = this.passwordEncoder.matches(acompanhanteDTO.getSenha(), acompanhanteEntity.getSenha());

        if(!passwordVerify){
            throw new AuthException("Senha invalida");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretToken);

        Instant expireIn = Instant.now().plus(Duration.ofHours(24));

        String token = JWT.create()
                .withSubject(acompanhanteEntity.getId_acompanhante().toString())
                .withClaim("roles", Arrays.asList("ACOMPANHANTE"))
                .withExpiresAt(expireIn)
                .withIssuer("Zeelus") //nome de quem assina
                .sign(algorithm);

        return LoginAcompanhanteResponseDTO.builder()
                .token(token)
                .expireAt(expireIn)
                .build();
    }
}
