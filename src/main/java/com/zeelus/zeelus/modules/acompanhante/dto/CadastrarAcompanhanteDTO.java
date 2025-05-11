package com.zeelus.zeelus.modules.acompanhante.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CadastrarAcompanhanteDTO {
    @NotBlank(message = "O campo (nome_acompanhante) não pode estar vazio")
    private String nome_acompanhante;

    @NotBlank(message = "O campo (email) não pode estar vazio")
    @Email(message = "O campo (email) deve receber um e-mail valido.")
    private String email;

    @NotBlank(message = "O campo (senha) não pode estar vazio")
    @Length(max = 70, min = 8, message = "A senha deve ter no minimo 8 carateres")
    private String senha;

    private String urlImg;
}
