package com.zeelus.zeelus.modules.cuidador.service;

import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import com.zeelus.zeelus.modules.cuidador.dto.CadastrarCuidadorDTO;
import com.zeelus.zeelus.modules.cuidador.repository.CuidadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastrarCuidadorService {
    @Autowired
    private CuidadorRepository acompanhanteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public CuidadorEntity execute(CadastrarCuidadorDTO cadastrarAcompanhanteDTO){
        this.acompanhanteRepository.findByEmail(cadastrarAcompanhanteDTO.getEmail())
                .ifPresent(value -> {
                    throw new RuntimeException("E-mail já foi cadastrado!");
                });

        String password = passwordEncoder.encode(cadastrarAcompanhanteDTO.getSenha());

        CuidadorEntity acompanhante = CuidadorEntity.builder()
                .nome_acompanhante(cadastrarAcompanhanteDTO.getNome_acompanhante())
                .email(cadastrarAcompanhanteDTO.getEmail())
                .senha(password)
                .urlImg(cadastrarAcompanhanteDTO.getUrlImg())
                .build();

        return this.acompanhanteRepository.save(acompanhante);
    }
}
