package com.zeelus.zeelus.modules.acompanhante.controller;

import com.zeelus.zeelus.modules.acompanhante.AcompanhanteEntity;
import com.zeelus.zeelus.modules.acompanhante.dto.LoginAcompanhanteDTO;
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
            AcompanhanteEntity result = this.acompanhanteService.execute(acompanhanteDTO);

            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Login realizado");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(e.getMessage());
        }
    }
}
