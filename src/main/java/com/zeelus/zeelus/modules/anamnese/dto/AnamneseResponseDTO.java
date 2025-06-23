package com.zeelus.zeelus.modules.anamnese.dto;

import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnamneseResponseDTO {
    private UUID id;
    private String diagnostico;
    private String necessidades;
    private UUID idAcompanhado;

    public static AnamneseResponseDTO fromEntity(AnamneseEntity anamnese) {
        return new AnamneseResponseDTO(
            anamnese.getId_anamnese(),
            anamnese.getDiagnostico(),
            anamnese.getNecessidades(),
            anamnese.getAcompanhado().getId_acompanhado()
        );
    }
} 