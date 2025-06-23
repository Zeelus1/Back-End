package com.zeelus.zeelus.modules.evento.service;

import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import com.zeelus.zeelus.modules.cuidador.repository.CuidadorRepository;
import com.zeelus.zeelus.modules.evento.EventoEntity;
import com.zeelus.zeelus.modules.evento.dto.EventoDTO;
import com.zeelus.zeelus.modules.evento.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CadastrarEventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Transactional
    public EventoEntity execute(UUID idCuidador, EventoDTO eventoDTO){
        CuidadorEntity cuidador = this.cuidadorRepository.findById(idCuidador).orElseThrow(() -> {
            throw new EntityNotFoundException("Cuidador não encontrado");
        });

        EventoEntity evento = EventoEntity.builder()
                .data(eventoDTO.getData())
                .titulo(eventoDTO.getTitulo())
                .descricao(eventoDTO.getDescricao())
                .acompanhante(cuidador)
                .build();

        return this.eventoRepository.save(evento);
    }
}
