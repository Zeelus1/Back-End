package com.zeelus.zeelus.modules.anamnese.controller;

import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import com.zeelus.zeelus.modules.anamnese.dto.AnamneseAtualizarDTO;
import com.zeelus.zeelus.modules.anamnese.dto.AnamneseResponseDTO;
import com.zeelus.zeelus.modules.anamnese.service.AtualizarAnamneseService;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.respostas.RespostaEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/anamnese")
@Tag(name = "Anamnese", description = "Endpoints para gerenciamento de anamneses")
public class AtualizarAnamneseController {

    @Autowired
    private AtualizarAnamneseService atualizarAnamneseService;

    @Operation(summary = "Atualizar anamnese", description = "Este endpoint atualiza os dados de uma anamnese existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Anamnese atualizada com sucesso",
            content = @Content(schema = @Schema(implementation = AnamneseResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou anamnese não encontrada",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @PutMapping("/atualizar/{idAcompanhado}")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<Object> execute(
        @Parameter(description = "ID do acompanhado") @PathVariable UUID idAcompanhado,
        @Valid @RequestBody AnamneseAtualizarDTO atualizarDTO){
        try{
            AnamneseResponseDTO result = this.atualizarAnamneseService.execute(idAcompanhado, atualizarDTO);

            RespostaDTO respostaDTO = new RespostaDTO(result, "Anamnese atualizada com sucesso");

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respostaDTO);
        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
