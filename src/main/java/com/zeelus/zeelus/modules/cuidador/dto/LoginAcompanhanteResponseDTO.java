package com.zeelus.zeelus.modules.cuidador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginAcompanhanteResponseDTO {
    private String token;
    private Instant expireAt;
}
