package com.zeelus.zeelus.modules.cuidador.controller;

import com.zeelus.zeelus.modules.cuidador.dto.LoginCuidadorDTO;
import com.zeelus.zeelus.modules.cuidador.dto.LoginAcompanhanteResponseDTO;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.cuidador.service.LoginCuidadorService;
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
public class LoginCuidadorController {
    @Autowired
    private LoginCuidadorService acompanhanteService;

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
