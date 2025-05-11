package com.zeelus.zeelus.modules.acompanhante.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginAcompanhanteDTO {
    @NotBlank(message = "O campo (email) não pode estar vazio")
    @Email(message = "O campo (email) deve receber um e-mail valido.")
    private String email;

    @NotBlank(message = "O campo (senha) não pode estar vazio")
    private String senha;
}
