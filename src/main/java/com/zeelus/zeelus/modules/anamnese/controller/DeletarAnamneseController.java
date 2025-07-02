package com.zeelus.zeelus.modules.anamnese.controller;

import com.zeelus.zeelus.modules.anamnese.service.DeletarAnamneseService;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/anamnese")
@Tag(name = "Anamnese", description = "Endpoints para gerenciamento de anamneses")
public class DeletarAnamneseController {
    @Autowired
    private DeletarAnamneseService anamneseService;

    @Operation(summary = "Deletar anamnese", description = "Este endpoint deleta uma anamnese específica de um acompanhado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Anamnese deletada com sucesso",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Erro ao deletar anamnese",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @PreAuthorize("hasRole('CUIDADOR')")
    @DeleteMapping("/deletar/{idAcompanhado}")
    public ResponseEntity<Object> execute(
        @Parameter(description = "ID do acompanhado cuja anamnese será deletada") @PathVariable UUID idAcompanhado){
        try{
            String result = this.anamneseService.execute(idAcompanhado);

            RespostaDTO respostaDTO = new RespostaDTO(null, result);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respostaDTO);
        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
