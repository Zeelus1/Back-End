package com.zeelus.zeelus.modules.pergunta.service;

import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import com.zeelus.zeelus.modules.cuidador.repository.CuidadorRepository;
import com.zeelus.zeelus.modules.evento.EventoEntity;
import com.zeelus.zeelus.modules.pergunta.PerguntaEntity;
import com.zeelus.zeelus.modules.pergunta.repository.PerguntaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PegarPerguntaService {
    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Transactional
    public List<PerguntaEntity> execute(UUID idCuidador){
        CuidadorEntity cuidador = cuidadorRepository.findById(idCuidador).orElseThrow(() -> {
            throw new EntityNotFoundException("Cuidador não encontrado.");
        });

        List<PerguntaEntity> result = this.perguntaRepository.findAllByCuidador(cuidador);

        return result;
    }
}
