package com.zeelus.zeelus.modules.cuidador.controller;

import com.zeelus.zeelus.modules.cuidador.dto.LoginCuidadorDTO;
import com.zeelus.zeelus.modules.cuidador.dto.LoginAcompanhanteResponseDTO;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.cuidador.service.LoginCuidadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cuidador", description = "Endpoints para gerenciamento de cuidadores")
public class LoginCuidadorController {
    @Autowired
    private LoginCuidadorService acompanhanteService;

    @Operation(summary = "Login do cuidador", description = "Este endpoint realiza a autenticação do cuidador no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
            content = @Content(schema = @Schema(implementation = LoginAcompanhanteResponseDTO.class))),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
            content = @Content(schema = @Schema(implementation = RespostaDTO.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<Object> loginAcompanhante(@Valid @RequestBody LoginCuidadorDTO acompanhanteDTO){
        try{
            LoginAcompanhanteResponseDTO result = this.acompanhanteService.execute(acompanhanteDTO);

            RespostaDTO respostaDTO = new RespostaDTO(result, "Login realizado");

            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(respostaDTO);

        } catch (Exception e) {

            RespostaDTO respostaDTO = new RespostaDTO("", e.getMessage());

            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(respostaDTO);
        }
    }
}
