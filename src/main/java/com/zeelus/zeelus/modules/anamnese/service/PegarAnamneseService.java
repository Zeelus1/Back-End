package com.zeelus.zeelus.modules.anamnese.service;

import com.zeelus.zeelus.modules.acompanhado.AcompanhadoEntity;
import com.zeelus.zeelus.modules.acompanhado.repository.AcompanhadoRepository;
import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import com.zeelus.zeelus.modules.anamnese.dto.AnamneseDTO;
import com.zeelus.zeelus.modules.anamnese.repository.AnamneseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PegarAnamneseService {
    @Autowired
    private AnamneseRepository anamneseRepository;

    @Autowired
    private AcompanhadoRepository acompanhadoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public AnamneseDTO execute(UUID idAcompanhado) {
        // Verifica se o acompanhado existe
        AcompanhadoEntity acompanhado = this.acompanhadoRepository.findById(idAcompanhado)
                .orElseThrow(() -> new RuntimeException("Acompanhado não encontrado"));

        // Busca a anamnese do acompanhado
        AnamneseEntity anamnese = this.anamneseRepository.findByAcompanhado(acompanhado)
                .orElseThrow(() -> new RuntimeException("Anamnese não encontrada para este acompanhado"));

        // Inicializa os objetos lazy antes de retornar
        Hibernate.initialize(anamnese);
        Hibernate.initialize(anamnese.getAcompanhado());
        if (anamnese.getAcompanhado() != null) {
            Hibernate.initialize(anamnese.getAcompanhado().getCuidador());
        }

        // Converte para DTO para evitar problemas de serialização

        AnamneseDTO anamneseDTO = new AnamneseDTO();

        anamneseDTO.setDiagnostico(anamnese.getDiagnostico());
        anamneseDTO.setNecessidades(anamnese.getNecessidades());
        anamneseDTO.setIdAcompanhado(anamnese.getAcompanhado().getId_acompanhado());

        return anamneseDTO;

    }
}
