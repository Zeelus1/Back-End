package com.zeelus.zeelus.modules.cuidador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespostaDTO {
    private Object data;
    private String mensagem;
    
    public RespostaDTO(String mensagem) {
        this.mensagem = mensagem;
    }
}
