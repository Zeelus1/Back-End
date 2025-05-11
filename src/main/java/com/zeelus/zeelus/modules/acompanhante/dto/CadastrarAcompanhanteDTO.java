package com.zeelus.zeelus.modules.acompanhante.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastrarAcompanhanteDTO(@NotBlank String nome_acompanhante,
                                       @NotBlank @Email String email,
                                       @NotBlank String senha,
                                       @NotBlank String urlImg) {
}
