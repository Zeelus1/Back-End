package com.zeelus.zeelus.modules.pergunta.dto;

import com.zeelus.zeelus.modules.pergunta.PerguntaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerguntaResponseDTO {
    private UUID id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private String tag;
    private Integer curtidas;
    private UUID cuidadorId;
    private String cuidadorNome;

    public static PerguntaResponseDTO fromEntity(PerguntaEntity pergunta) {
        return new PerguntaResponseDTO(
            pergunta.getId_perguntas(),
            pergunta.getTitulo(),
            pergunta.getDescricao(),
            pergunta.getData(),
            pergunta.getTag(),
            pergunta.getCurtidas(),
            pergunta.getCuidador().getId_cuidador(),
            pergunta.getCuidador().getNome_cuidador()
        );
    }
} 