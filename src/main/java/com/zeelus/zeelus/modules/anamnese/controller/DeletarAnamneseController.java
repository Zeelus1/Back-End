package com.zeelus.zeelus.modules.anamnese.controller;

import com.zeelus.zeelus.modules.anamnese.service.DeletarAnamneseService;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
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
@RequestMapping("/anamnese")
public class DeletarAnamneseController {
    @Autowired
    private DeletarAnamneseService anamneseService;

    @PreAuthorize("hasRole('CUIDADOR')")
    @DeleteMapping("/deletar/{idAcompanhado}")
    public ResponseEntity<Object> execute(@PathVariable UUID idAcompanhado){
        try{
            String result = this.anamneseService.execute(idAcompanhado);

            RespostaDTO respostaDTO = new RespostaDTO(null, result);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respostaDTO);
        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
