package com.zeelus.zeelus.modules.pergunta.service;

import com.zeelus.zeelus.modules.pergunta.PerguntaEntity;
import com.zeelus.zeelus.modules.pergunta.repository.PerguntaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeletarPerguntaService {
    @Autowired
    private PerguntaRepository perguntaRepository;

    @Transactional
    public String execute(UUID idPergunta, UUID idCuidador){
        PerguntaEntity pergunta = this.perguntaRepository.findById(idPergunta).orElseThrow(() -> {
            throw new EntityNotFoundException("Pergunta não encontrada.");
        });

        if (!pergunta.getCuidador().getId_cuidador().equals(idCuidador)) {
            throw new AccessDeniedException("Você não tem permissão para deletar esta pergunta.");
        }

        this.perguntaRepository.delete(pergunta);

        return "Pergunta deletada com sucesso.";
    }
}
