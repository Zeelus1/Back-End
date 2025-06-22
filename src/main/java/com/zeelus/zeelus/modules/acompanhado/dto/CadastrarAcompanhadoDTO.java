package com.zeelus.zeelus.modules.acompanhado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CadastrarAcompanhadoDTO {

    @NotBlank(message = "O campo (nome_acompanhado) não deve estar vazio.")
    private String nome_acompanhado;

    @NotNull(message = "O campo (data_nascimento) não deve estar vazio.")
    private LocalDate data_nascimento;

    @NotBlank(message = "O campo (genereo) não deve estar vazio.")
    private String genero;

    @NotNull(message = "O campo (cuidadorID) não deve estar vazio.")
    private UUID cuidadorID;

}
