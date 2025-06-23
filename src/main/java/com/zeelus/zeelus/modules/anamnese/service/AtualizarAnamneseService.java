package com.zeelus.zeelus.modules.anamnese.service;

import com.zeelus.zeelus.modules.acompanhado.AcompanhadoEntity;
import com.zeelus.zeelus.modules.acompanhado.repository.AcompanhadoRepository;
import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import com.zeelus.zeelus.modules.anamnese.dto.AnamneseAtualizarDTO;
import com.zeelus.zeelus.modules.anamnese.dto.AnamneseResponseDTO;
import com.zeelus.zeelus.modules.anamnese.repository.AnamneseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AtualizarAnamneseService {
    @Autowired
    private AnamneseRepository anamneseRepository;

    @Autowired
    private AcompanhadoRepository acompanhadoRepository;

    @Transactional
    public AnamneseResponseDTO execute(UUID idAcompanhado, AnamneseAtualizarDTO atualizarDTO) {
        if (idAcompanhado == null) {
            throw new IllegalArgumentException("ID do acompanhado não pode ser nulo");
        }

        if (atualizarDTO == null) {
            throw new IllegalArgumentException("Dados de atualização não podem ser nulos");
        }

        AcompanhadoEntity acompanhado = this.acompanhadoRepository.findById(idAcompanhado)
                .orElseThrow(() -> new EntityNotFoundException("Acompanhado não encontrado com ID: " + idAcompanhado));

        AnamneseEntity anamnese = anamneseRepository.findByAcompanhado(acompanhado)
                .orElseThrow(() -> new EntityNotFoundException("Anamnese não encontrada para o acompanhado: " + idAcompanhado));

        if (atualizarDTO.getDiagnostico() != null) {
            anamnese.setDiagnostico(atualizarDTO.getDiagnostico());
        }
        
        if (atualizarDTO.getNecessidades() != null) {
            anamnese.setNecessidades(atualizarDTO.getNecessidades());
        }

        AnamneseEntity savedAnamnese = this.anamneseRepository.save(anamnese);
        return AnamneseResponseDTO.fromEntity(savedAnamnese);
    }
}
