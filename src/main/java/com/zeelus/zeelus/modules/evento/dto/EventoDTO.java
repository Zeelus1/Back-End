package com.zeelus.zeelus.modules.evento.dto;

import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import com.zeelus.zeelus.modules.anamnese.dto.AnamneseResponseDTO;
import com.zeelus.zeelus.modules.evento.EventoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public static EventoDTO fromEntity(EventoEntity evento) {
        return new EventoDTO(
                evento.getId_eventos(),
                evento.getData(),
                evento.getTitulo(),
                evento.getDescricao()
        );
    }
}
