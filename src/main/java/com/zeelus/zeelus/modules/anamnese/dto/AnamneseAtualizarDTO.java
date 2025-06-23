package com.zeelus.zeelus.modules.anamnese.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnamneseAtualizarDTO {
    @NotBlank(message = "Diagnostico não pode estar vazio")
    private String diagnostico;

    @NotBlank(message = "Necessidades não pode estar vazio")
    private String necessidades;
}
