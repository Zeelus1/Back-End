package com.zeelus.zeelus.modules.evento.service;

import com.zeelus.zeelus.modules.evento.EventoEntity;
import com.zeelus.zeelus.modules.evento.dto.EventoDTO;
import com.zeelus.zeelus.modules.evento.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtualizarEventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Transactional
    public EventoDTO execute(EventoDTO eventoDTO){
        EventoEntity evento = this.eventoRepository.findById(eventoDTO.getId_eventos()).orElseThrow(() -> {
            throw new EntityNotFoundException("Evento não encontrado");
        });

        if(eventoDTO.getData() != null){
            evento.setData(eventoDTO.getData());
        }

        if(eventoDTO.getDescricao() != null){
            evento.setDescricao(eventoDTO.getDescricao());
        }

        if(eventoDTO.getTitulo() != null){
            evento.setTitulo(eventoDTO.getTitulo());
        }

        EventoEntity eventoSave = this.eventoRepository.save(evento);
        return EventoDTO.fromEntity(eventoSave);

    }
}
