package com.zeelus.zeelus.modules.evento.dto;

import com.zeelus.zeelus.modules.evento.EventoEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class EventoListResponseDTO {
    private List<EventoDTO> eventos;

    public EventoListResponseDTO(List<EventoEntity> eventoEntityList) {
        this.eventos = eventoEntityList.stream()
                .map(EventoDTO::new)
                .collect(Collectors.toList());
    }
}
