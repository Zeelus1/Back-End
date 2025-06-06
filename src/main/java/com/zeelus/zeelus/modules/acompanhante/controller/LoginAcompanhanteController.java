package com.zeelus.zeelus.modules.acompanhante.controller;

import com.zeelus.zeelus.modules.acompanhante.AcompanhanteEntity;
import com.zeelus.zeelus.modules.acompanhante.dto.LoginAcompanhanteDTO;
import com.zeelus.zeelus.modules.acompanhante.dto.LoginAcompanhanteResponseDTO;
import com.zeelus.zeelus.modules.acompanhante.dto.RespostaDTO;
import com.zeelus.zeelus.modules.acompanhante.service.LoginAcompanhanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acompanhante")
public class LoginAcompanhanteController {
    @Autowired
    private LoginAcompanhanteService acompanhanteService;

    @PostMapping("/login")
    public ResponseEntity<Object> loginAcompanhante(@Valid @RequestBody LoginAcompanhanteDTO acompanhanteDTO){
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
