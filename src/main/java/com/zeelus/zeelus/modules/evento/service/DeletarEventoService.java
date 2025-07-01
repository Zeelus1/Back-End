package com.zeelus.zeelus.modules.evento.service;

import com.zeelus.zeelus.modules.evento.EventoEntity;
import com.zeelus.zeelus.modules.evento.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletarEventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public String execute(UUID idEvento){
        EventoEntity evento = this.eventoRepository.findById(idEvento).orElseThrow(() -> {
            throw new EntityNotFoundException("Evento não encontrado.");
        });

        this.eventoRepository.delete(evento);

        return "Evento deletado com sucesso.";
    }
}
