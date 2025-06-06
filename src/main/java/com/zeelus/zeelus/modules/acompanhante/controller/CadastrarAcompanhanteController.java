package com.zeelus.zeelus.modules.acompanhante.controller;

import com.zeelus.zeelus.modules.acompanhante.AcompanhanteEntity;
import com.zeelus.zeelus.modules.acompanhante.dto.CadastrarAcompanhanteDTO;
import com.zeelus.zeelus.modules.acompanhante.dto.RespostaDTO;
import com.zeelus.zeelus.modules.acompanhante.service.CadastrarAcompanhanteService;
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
public class CadastrarAcompanhanteController {
    @Autowired
    private CadastrarAcompanhanteService cadastrarAcompanhanteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarAcompanhante(@Valid @RequestBody CadastrarAcompanhanteDTO acompanhanteDTO){
        try{
            AcompanhanteEntity resultado = this.cadastrarAcompanhanteService.execute(acompanhanteDTO);

            RespostaDTO respostaDTO = new RespostaDTO(resultado, "Usuario cadastrado com sucesso.");

            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(respostaDTO);
        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO("", e.getMessage());

            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(respostaDTO);
        }
    }
}
