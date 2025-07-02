package com.zeelus.zeelus.modules.pergunta.controller;

import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.pergunta.dto.PerguntaDTO;
import com.zeelus.zeelus.modules.pergunta.dto.PerguntaResponseDTO;
import com.zeelus.zeelus.modules.pergunta.service.CadastrarPerguntaService;
import com.zeelus.zeelus.modules.respostas.RespostaEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/pergunta")
public class CadastrarPerguntaController {

    @Autowired
    private CadastrarPerguntaService cadastrarPerguntaService;

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<Object> execute(HttpServletRequest request, @Valid @RequestBody PerguntaDTO perguntaDTO){
        String userId = (String) request.getAttribute("cuidador_id");
        UUID cuidadorId = UUID.fromString(userId);

        try{
            PerguntaResponseDTO result = this.cadastrarPerguntaService.execute(cuidadorId, perguntaDTO);
            RespostaDTO respostaDTO = new RespostaDTO(result, "Pergunta cadastrada com sucesso.");
            return ResponseEntity.status(HttpStatus.CREATED).body(respostaDTO);
        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
