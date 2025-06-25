package com.zeelus.zeelus.modules.evento.dto;

import com.zeelus.zeelus.modules.evento.EventoEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class EventoResponseDTO {
    private UUID id_eventos;
    private LocalDate data;
    private String titulo;
    private String descricao;
    private UUID cuidadorId;

    public EventoResponseDTO(EventoEntity entity) {
        this.id_eventos = entity.getId_eventos();
        this.data = entity.getData();
        this.titulo = entity.getTitulo();
        this.descricao = entity.getDescricao();
        this.cuidadorId = entity.getCuidador().getId_cuidador();
    }
} 