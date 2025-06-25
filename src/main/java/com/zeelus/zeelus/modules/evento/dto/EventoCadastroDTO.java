package com.zeelus.zeelus.modules.evento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoCadastroDTO {
    @NotNull(message = "O campo (data) não pode estar vazio.")
    private LocalDate data;

    @NotBlank(message = "O campo (titulo) não pode estar vazio.")
    private String titulo;

    private String descricao;
} 