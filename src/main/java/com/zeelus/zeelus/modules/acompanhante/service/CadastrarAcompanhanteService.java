package com.zeelus.zeelus.modules.acompanhante.service;

import com.zeelus.zeelus.modules.acompanhante.AcompanhanteEntity;
import com.zeelus.zeelus.modules.acompanhante.dto.CadastrarAcompanhanteDTO;
import com.zeelus.zeelus.modules.acompanhante.repository.AcompanhanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastrarAcompanhanteService {
    @Autowired
    private AcompanhanteRepository acompanhanteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public AcompanhanteEntity execute(CadastrarAcompanhanteDTO cadastrarAcompanhanteDTO){
        this.acompanhanteRepository.findByEmail(cadastrarAcompanhanteDTO.getEmail())
                .ifPresent(value -> {
                    throw new RuntimeException("E-mail já foi cadastrado!");
                });

        String password = passwordEncoder.encode(cadastrarAcompanhanteDTO.getSenha());

        AcompanhanteEntity acompanhante = AcompanhanteEntity.builder()
                .nome_acompanhante(cadastrarAcompanhanteDTO.getNome_acompanhante())
                .email(cadastrarAcompanhanteDTO.getEmail())
                .senha(password)
                .urlImg(cadastrarAcompanhanteDTO.getUrlImg())
                .build();

        return this.acompanhanteRepository.save(acompanhante);
    }
}
