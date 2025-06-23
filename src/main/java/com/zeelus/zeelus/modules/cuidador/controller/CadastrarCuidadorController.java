package com.zeelus.zeelus.modules.cuidador.controller;

import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import com.zeelus.zeelus.modules.cuidador.dto.CadastrarCuidadorDTO;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.cuidador.service.CadastrarCuidadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuidador")
public class CadastrarCuidadorController {
    @Autowired
    private CadastrarCuidadorService cadastrarAcompanhanteService;

    @Operation(summary = "Cadastrar cuidador", description = "Este endpoint realizara o cadastro do cuidador e armazenara os dados dele no banco de dados")
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = CuidadorEntity.class))
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarAcompanhante(@Valid @RequestBody CadastrarCuidadorDTO acompanhanteDTO){
        try{
            CuidadorEntity resultado = this.cadastrarAcompanhanteService.execute(acompanhanteDTO);

            RespostaDTO respostaDTO = new RespostaDTO(resultado, "Usuario cadastrado com sucesso.");

            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(respostaDTO);
        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO("", e.getMessage());

            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(respostaDTO);
        }
    }
}
