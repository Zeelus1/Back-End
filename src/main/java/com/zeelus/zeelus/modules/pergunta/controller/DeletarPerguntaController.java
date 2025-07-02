package com.zeelus.zeelus.modules.pergunta.controller;

import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.pergunta.service.DeletarPerguntaService;
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
@RequestMapping("/pergunta")
public class DeletarPerguntaController {

    @Autowired
    private DeletarPerguntaService deletarPerguntaService;

    @DeleteMapping("/deletar/{idPergunta}")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<Object> execute(HttpServletRequest request, @PathVariable UUID idPergunta){
        try{
            String userId = (String) request.getAttribute("cuidador_id");
            UUID cuidadorId = UUID.fromString(userId);

            String result = this.deletarPerguntaService.execute(idPergunta, cuidadorId);

            RespostaDTO respostaDTO = new RespostaDTO(null, result);

            return ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
