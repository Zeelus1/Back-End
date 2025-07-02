package com.zeelus.zeelus.modules.pergunta.controller;

import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.pergunta.service.DeletarPerguntaService;
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
@RequestMapping("/pergunta")
@Tag(name = "Perguntas", description = "Endpoints para gerenciamento de perguntas")
public class DeletarPerguntaController {

    @Autowired
    private DeletarPerguntaService deletarPerguntaService;

    @Operation(summary = "Deletar pergunta", description = "Este endpoint deleta uma pergunta específica do cuidador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pergunta deletada com sucesso",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Erro ao deletar pergunta",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @DeleteMapping("/deletar/{idPergunta}")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<Object> execute(HttpServletRequest request, @PathVariable UUID idPergunta){
        try{
            String userId = (String) request.getAttribute("cuidador_id");
            UUID cuidadorId = UUID.fromString(userId);

            String result = this.deletarPerguntaService.execute(idPergunta, cuidadorId);

            RespostaDTO respostaDTO = new RespostaDTO(null, result);

            return ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
