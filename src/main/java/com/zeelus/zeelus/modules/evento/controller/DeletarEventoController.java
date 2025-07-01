package com.zeelus.zeelus.modules.evento.controller;

import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.evento.service.DeletarEventoService;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/evento")
public class DeletarEventoController {

    @Autowired
    private DeletarEventoService deletarEventoService;

    @DeleteMapping("/deletar/{idEvento}")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<Object> execute(@PathVariable UUID idEvento){
        try{
            String result = this.deletarEventoService.execute(idEvento);

            RespostaDTO respostaDTO = new RespostaDTO(null , result);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respostaDTO);

        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
