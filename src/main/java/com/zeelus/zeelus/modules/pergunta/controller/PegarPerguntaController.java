package com.zeelus.zeelus.modules.pergunta.controller;

import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.pergunta.PerguntaEntity;
import com.zeelus.zeelus.modules.pergunta.dto.PerguntaResponseDTO;
import com.zeelus.zeelus.modules.pergunta.service.PegarPerguntaService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pergunta")
@Tag(name = "Perguntas", description = "Endpoints para gerenciamento de perguntas")
public class PegarPerguntaController {

    @Autowired
    private PegarPerguntaService perguntaService;

    @Operation(summary = "Buscar perguntas", description = "Este endpoint retorna todas as perguntas cadastradas pelo cuidador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Perguntas encontradas com sucesso",
            content = @Content(schema = @Schema(implementation = PerguntaResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Erro ao buscar perguntas",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @GetMapping("/pegar")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<Object> execute(HttpServletRequest request){
        try{
            String userId = (String) request.getAttribute("cuidador_id");
            UUID cuidadorId = UUID.fromString(userId);

            List<PerguntaEntity> perguntas = this.perguntaService.execute(cuidadorId);
            
            List<PerguntaResponseDTO> result = perguntas.stream()
                .map(PerguntaResponseDTO::fromEntity)
                .collect(Collectors.toList());

            RespostaDTO respostaDTO = new RespostaDTO(result, "Perguntas encontradas");

            return ResponseEntity.status(HttpStatus.OK).body(respostaDTO);

        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
