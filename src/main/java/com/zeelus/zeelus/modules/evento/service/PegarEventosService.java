package com.zeelus.zeelus.modules.evento.service;

import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import com.zeelus.zeelus.modules.cuidador.repository.CuidadorRepository;
import com.zeelus.zeelus.modules.evento.EventoEntity;
import com.zeelus.zeelus.modules.evento.dto.EventoListResponseDTO;
import com.zeelus.zeelus.modules.evento.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PegarEventosService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Transactional
    public EventoListResponseDTO execute(UUID cuidadorId){
        CuidadorEntity cuidador = this.cuidadorRepository.findById(cuidadorId).orElseThrow(() -> {
            throw new EntityNotFoundException("Cuidador não encontrado.");
        });

        List<EventoEntity> evento = this.eventoRepository.findAllByCuidador(cuidador);

        EventoListResponseDTO eventoResponseDTO = new EventoListResponseDTO(evento);

        return eventoResponseDTO;
    }
}
