package com.zeelus.zeelus.modules.evento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoDTO {
    @NotNull(message = "O campo (data) não pode estar vazio.")
    private LocalDate data;


    @NotBlank(message = "O campo (titulo) não pode estar vazio.")
    private String titulo;

    @NotBlank(message = "O campo (descricao) não pode estar vazio.")
    private String descricao;
}
