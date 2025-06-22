package com.zeelus.zeelus.modules.anamnese.service;

import com.zeelus.zeelus.modules.acompanhado.AcompanhadoEntity;
import com.zeelus.zeelus.modules.acompanhado.repository.AcompanhadoRepository;
import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import com.zeelus.zeelus.modules.anamnese.dto.AnamneseDTO;
import com.zeelus.zeelus.modules.anamnese.repository.AnamneseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastrarAnamneseService {
    @Autowired
    private AnamneseRepository anamneseRepository;

    @Autowired
    private AcompanhadoRepository acompanhadoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public AnamneseEntity execute(AnamneseDTO anamneseDTO) {
        AcompanhadoEntity acompanhado = this.acompanhadoRepository.findById(anamneseDTO.getIdAcompanhado())
                .orElseThrow(() -> new RuntimeException("Acompanhado não encontrado"));

        // Inicializa explicitamente os relacionamentos necessários
        entityManager.refresh(acompanhado);
        if (acompanhado.getCuidador() != null) {
            entityManager.refresh(acompanhado.getCuidador());
        }

        AnamneseEntity anamnese = AnamneseEntity.builder()
                .diagnostico(anamneseDTO.getDiagnostico())
                .necessidades(anamneseDTO.getNecessidades())
                .acompanhado(acompanhado)
                .build();

            return this.anamneseRepository.save(anamnese);
    }
}
