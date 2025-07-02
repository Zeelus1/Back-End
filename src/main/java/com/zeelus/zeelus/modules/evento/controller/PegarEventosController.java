package com.zeelus.zeelus.modules.evento.controller;

import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.evento.EventoEntity;
import com.zeelus.zeelus.modules.evento.dto.EventoListResponseDTO;
import com.zeelus.zeelus.modules.evento.dto.EventoResponseDTO;
import com.zeelus.zeelus.modules.evento.service.PegarEventosService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/evento")
@Tag(name = "Eventos", description = "Endpoints para gerenciamento de eventos")
public class PegarEventosController {
    @Autowired
    private PegarEventosService pegarEventosService;

    @Operation(summary = "Buscar eventos", description = "Este endpoint retorna todos os eventos cadastrados pelo cuidador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Eventos encontrados com sucesso",
            content = @Content(schema = @Schema(implementation = EventoListResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Erro ao buscar eventos",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @PreAuthorize("hasRole('CUIDADOR')")
    @GetMapping
    public ResponseEntity<Object> execute(HttpServletRequest request){
        try{
            String userId = (String) request.getAttribute("cuidador_id");
            UUID cuidadorId = UUID.fromString(userId);

            EventoListResponseDTO evento = this.pegarEventosService.execute(cuidadorId);

            RespostaDTO respostaDTO = new RespostaDTO(evento, "Eventos encontrados");

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respostaDTO);
        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
