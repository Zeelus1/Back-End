package com.zeelus.zeelus.modules.anamnese.controller;

import com.zeelus.zeelus.modules.anamnese.dto.AnamneseDTO;
import com.zeelus.zeelus.modules.anamnese.service.PegarAnamneseService;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/anamnese")
@Tag(name = "Anamnese", description = "Endpoints para gerenciamento de anamneses")
public class PegarAnamneseController {

    @Autowired
    private PegarAnamneseService pegarAnamneseService;

    @Operation(summary = "Buscar anamnese", description = "Este endpoint retorna a anamnese de um acompanhado específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Anamnese encontrada com sucesso",
            content = @Content(schema = @Schema(implementation = AnamneseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Anamnese não encontrada ou erro na busca",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @GetMapping("/{idAcompanhado}")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<RespostaDTO> execute(
        @Parameter(description = "ID do acompanhado cuja anamnese será buscada") @PathVariable UUID idAcompanhado) {
        try {
             AnamneseDTO anamnese = this.pegarAnamneseService.execute(idAcompanhado);

            return ResponseEntity.ok(new RespostaDTO(anamnese, "Anamnese encontrada com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new RespostaDTO(null, e.getMessage()));
        }
    }
}
