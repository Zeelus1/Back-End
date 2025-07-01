package com.zeelus.zeelus.modules.pergunta.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PerguntaDTO {
    @NotBlank(message = "O campo (titulo) não pode estar vazio.")
    private String titulo;

    @NotBlank(message = "O campo (descricao) não pode estar vazio.")
    private String descricao;

    @NotNull(message = "O campo (data) não pode estar vazio.")
    private LocalDate data;

    @NotBlank(message = "O campo (tag) não pode estar vazio.")
    private String tag;
}
