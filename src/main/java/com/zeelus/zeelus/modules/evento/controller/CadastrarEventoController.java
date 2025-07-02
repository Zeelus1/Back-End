package com.zeelus.zeelus.modules.evento.controller;

import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.evento.EventoEntity;
import com.zeelus.zeelus.modules.evento.dto.EventoCadastroDTO;
import com.zeelus.zeelus.modules.evento.dto.EventoResponseDTO;
import com.zeelus.zeelus.modules.evento.service.CadastrarEventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/evento")
@Tag(name = "Eventos", description = "Endpoints para gerenciamento de eventos")
public class CadastrarEventoController {
    @Autowired
    private CadastrarEventoService cadastrarEventoService;

    @Operation(summary = "Cadastrar evento", description = "Este endpoint realiza o cadastro de um novo evento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Evento cadastrado com sucesso",
            content = @Content(schema = @Schema(implementation = EventoResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @PreAuthorize("hasRole('CUIDADOR')")
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> execute(HttpServletRequest request, @Valid @RequestBody EventoCadastroDTO eventoDTO) {
        try{
            String userId = (String) request.getAttribute("cuidador_id");
            UUID cuidadorId = UUID.fromString(userId);

            EventoEntity result = this.cadastrarEventoService.execute(cuidadorId, eventoDTO);

            EventoResponseDTO responseDTO = new EventoResponseDTO(result);
            
            RespostaDTO respostaDTO = new RespostaDTO(responseDTO, "Evento cadastrado com sucesso.");

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respostaDTO);

        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
