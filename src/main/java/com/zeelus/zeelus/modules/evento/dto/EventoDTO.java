package com.zeelus.zeelus.modules.evento.dto;

import com.zeelus.zeelus.modules.evento.EventoEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class EventoDTO {
    private UUID id_eventos;
    private LocalDate data;
    private String titulo;
    private String descricao;

    public EventoDTO(EventoEntity evento) {
        this.id_eventos = evento.getId_eventos();
        this.data = evento.getData();
        this.titulo = evento.getTitulo();
        this.descricao = evento.getDescricao();
    }
}
