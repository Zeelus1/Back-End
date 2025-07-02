package com.zeelus.zeelus.modules.evento.controller;

import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.evento.service.DeletarEventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/evento")
@Tag(name = "Eventos", description = "Endpoints para gerenciamento de eventos")
public class DeletarEventoController {

    @Autowired
    private DeletarEventoService deletarEventoService;

    @Operation(summary = "Deletar evento", description = "Este endpoint deleta um evento específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Evento deletado com sucesso",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Erro ao deletar evento",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @DeleteMapping("/deletar/{idEvento}")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<Object> execute(
        @Parameter(description = "ID do evento a ser deletado") @PathVariable UUID idEvento){
        try{
            String result = this.deletarEventoService.execute(idEvento);

            RespostaDTO respostaDTO = new RespostaDTO(null , result);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respostaDTO);

        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
