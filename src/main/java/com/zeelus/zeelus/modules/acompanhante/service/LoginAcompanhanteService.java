package com.zeelus.zeelus.modules.acompanhante.service;

import com.zeelus.zeelus.modules.acompanhante.AcompanhanteEntity;
import com.zeelus.zeelus.modules.acompanhante.dto.LoginAcompanhanteDTO;
import com.zeelus.zeelus.modules.acompanhante.repository.AcompanhanteRepository;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginAcompanhanteService {
    @Autowired
    private AcompanhanteRepository acompanhanteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AcompanhanteEntity execute(LoginAcompanhanteDTO acompanhanteDTO) throws AuthException {
        AcompanhanteEntity acompanhanteEntity = this.acompanhanteRepository.findByEmail(acompanhanteDTO.getEmail())
                .orElseThrow(() -> {
                    throw new RuntimeException("E-mail não encontrado!");
                });

        boolean passwordVerify = this.passwordEncoder.matches(acompanhanteDTO.getSenha(), acompanhanteEntity.getSenha());

        if(!passwordVerify){
            throw new AuthException("Senha invalida");
        }

        return acompanhanteEntity;
    }
}
