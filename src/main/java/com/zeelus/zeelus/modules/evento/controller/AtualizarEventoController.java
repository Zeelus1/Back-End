package com.zeelus.zeelus.modules.evento.controller;

import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.evento.dto.EventoDTO;
import com.zeelus.zeelus.modules.evento.service.AtualizarEventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evento")
@Tag(name = "Eventos", description = "Endpoints para gerenciamento de eventos")
public class AtualizarEventoController {
    @Autowired
    private AtualizarEventoService atualizarEventoService;

    @Operation(summary = "Atualizar evento", description = "Este endpoint atualiza os dados de um evento existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Evento atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = EventoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Erro ao atualizar evento",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @PutMapping("/atualizar")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<Object> execute(@RequestBody EventoDTO eventoDTO){
        try{

            var eventoDTO1 = this.atualizarEventoService.execute(eventoDTO);

            RespostaDTO respostaDTO = new RespostaDTO(eventoDTO1, "Evento atualizado com sucesso.");

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respostaDTO);

        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO("", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
