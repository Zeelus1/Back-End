package com.zeelus.zeelus.modules.anamnese.controller;

import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import com.zeelus.zeelus.modules.anamnese.dto.AnamneseDTO;
import com.zeelus.zeelus.modules.anamnese.service.CadastrarAnamneseService;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anamnese")
@Tag(name = "Anamnese", description = "Endpoints para gerenciamento de anamneses")
public class CadastrarAnamneseController {

    @Autowired
    private CadastrarAnamneseService cadastrarAnamneseService;

    @Operation(summary = "Cadastrar anamnese", description = "Este endpoint realiza o cadastro de uma nova anamnese para um acompanhado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Anamnese cadastrada com sucesso",
            content = @Content(schema = @Schema(implementation = AnamneseEntity.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<Object> execute(@Valid @RequestBody AnamneseDTO dto){
        try{
            AnamneseEntity anamnese = this.cadastrarAnamneseService.execute(dto);

            RespostaDTO respostaDTO = new RespostaDTO(anamnese, "Anamnese cadastrada com sucesso.");

            return ResponseEntity.status(HttpStatus.CREATED).body(respostaDTO);

        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }

}
