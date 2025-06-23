package com.zeelus.zeelus.modules.evento.controller;

import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.evento.EventoEntity;
import com.zeelus.zeelus.modules.evento.dto.EventoDTO;
import com.zeelus.zeelus.modules.evento.dto.EventoResponseDTO;
import com.zeelus.zeelus.modules.evento.service.CadastrarEventoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/evento")
public class CadastrarEventoController {
    @Autowired
    private CadastrarEventoService cadastrarEventoService;

    @PreAuthorize("hasRole('CUIDADOR')")
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> execute(HttpServletRequest request, @Valid @RequestBody EventoDTO eventoDTO) {
        try{
            String userId = (String) request.getAttribute("acompanhante_id");
            UUID cuidadorId = UUID.fromString(userId);

            EventoEntity result = this.cadastrarEventoService.execute(cuidadorId, eventoDTO);

            EventoResponseDTO responseDTO = new EventoResponseDTO(result);
            
            RespostaDTO respostaDTO = new RespostaDTO(responseDTO, "Evento cadastrado com sucesso.");

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respostaDTO);

        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
