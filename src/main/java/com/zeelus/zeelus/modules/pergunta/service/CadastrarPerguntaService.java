package com.zeelus.zeelus.modules.pergunta.service;

import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import com.zeelus.zeelus.modules.cuidador.repository.CuidadorRepository;
import com.zeelus.zeelus.modules.pergunta.PerguntaEntity;
import com.zeelus.zeelus.modules.pergunta.dto.PerguntaDTO;
import com.zeelus.zeelus.modules.pergunta.dto.PerguntaResponseDTO;
import com.zeelus.zeelus.modules.pergunta.repository.PerguntaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CadastrarPerguntaService {
    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Transactional
    public PerguntaResponseDTO execute(UUID idCuidador, PerguntaDTO perguntaDTO){
        CuidadorEntity cuidador = this.cuidadorRepository.findById(idCuidador).orElseThrow(() -> {
            throw new EntityNotFoundException("Cuidador não encontrado.");
        });

        PerguntaEntity pergunta = PerguntaEntity.builder()
                .tag(perguntaDTO.getTag())
                .data(perguntaDTO.getData())
                .titulo(perguntaDTO.getTitulo())
                .descricao(perguntaDTO.getDescricao())
                .cuidador(cuidador)
                .build();

        PerguntaEntity savedPergunta = this.perguntaRepository.save(pergunta);
        return PerguntaResponseDTO.fromEntity(savedPergunta);
    }
}
