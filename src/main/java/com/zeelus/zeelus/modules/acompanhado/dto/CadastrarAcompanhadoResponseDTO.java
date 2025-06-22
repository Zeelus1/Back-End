package com.zeelus.zeelus.modules.acompanhado.dto;

import com.zeelus.zeelus.modules.acompanhado.AcompanhadoEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CadastrarAcompanhadoResponseDTO {
    private UUID id_acompanhado;
    private String nome_acompanhado;
    private LocalDate data_nascimento;
    private String genero;
    private UUID cuidadorId;

    public CadastrarAcompanhadoResponseDTO(AcompanhadoEntity entity) {
        this.id_acompanhado = entity.getId_acompanhado();
        this.nome_acompanhado = entity.getNome_acompanhado();
        this.data_nascimento = entity.getData_nascimento();
        this.genero = entity.getGenero();
        this.cuidadorId = entity.getCuidador().getId_acompanhante();
    }
} 