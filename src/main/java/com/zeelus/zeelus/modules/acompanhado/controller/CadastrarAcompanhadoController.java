package com.zeelus.zeelus.modules.acompanhado.controller;

import com.zeelus.zeelus.modules.acompanhado.AcompanhadoEntity;
import com.zeelus.zeelus.modules.acompanhado.dto.CadastrarAcompanhadoDTO;
import com.zeelus.zeelus.modules.acompanhado.service.CadastrarAcompanhadoSerivce;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.acompanhado.dto.CadastrarAcompanhadoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acompanhado")
@Tag(name = "Acompanhado", description = "Endpoints para gerenciamento de acompanhados")
public class CadastrarAcompanhadoController {
    @Autowired
    private CadastrarAcompanhadoSerivce cadastrarAcompanhado;

    @Operation(summary = "Cadastrar acompanhado", description = "Este endpoint realiza o cadastro de um novo acompanhado no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Acompanhado cadastrado com sucesso",
            content = @Content(schema = @Schema(implementation = CadastrarAcompanhadoResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @PreAuthorize("hasRole('CUIDADOR')")
    @PostMapping("/cadastrar")
    private ResponseEntity<Object> cadastrarAcompanhado(@Valid @RequestBody CadastrarAcompanhadoDTO acompanhadoDTO){
        try{
            AcompanhadoEntity result = this.cadastrarAcompanhado.execute(acompanhadoDTO);

            CadastrarAcompanhadoResponseDTO responseDTO = new CadastrarAcompanhadoResponseDTO(result);
            RespostaDTO resposta = new RespostaDTO(responseDTO, "Acompanhado cadastrado com sucesso");

            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);

        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO("", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
