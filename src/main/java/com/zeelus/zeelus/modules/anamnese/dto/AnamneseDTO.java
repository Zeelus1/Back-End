package com.zeelus.zeelus.modules.anamnese.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AnamneseDTO {
    @NotBlank(message = "Diagnostico não pode estar vazio")
    private String diagnostico;

    @NotBlank(message = "Necessidades não pode estar vazio")
    private String necessidades;

    @NotNull(message = "Id do acompanhado não pode estar vazio")
    private UUID idAcompanhado;
}
