package com.zeelus.zeelus.modules.cuidador.controller;

import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import com.zeelus.zeelus.modules.cuidador.dto.CadastrarCuidadorDTO;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.cuidador.service.CadastrarCuidadorService;
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
