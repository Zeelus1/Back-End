package com.zeelus.zeelus.modules.evento.controller;

import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.evento.EventoEntity;
import com.zeelus.zeelus.modules.evento.dto.EventoListResponseDTO;
import com.zeelus.zeelus.modules.evento.dto.EventoResponseDTO;
import com.zeelus.zeelus.modules.evento.service.PegarEventosService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/evento")
public class PegarEventosController {
    @Autowired
    private PegarEventosService pegarEventosService;

    @PreAuthorize("hasRole('CUIDADOR')")
    @GetMapping
    public ResponseEntity<Object> execute(HttpServletRequest request){
        try{
            String userId = (String) request.getAttribute("cuidador_id");
            UUID cuidadorId = UUID.fromString(userId);

            EventoListResponseDTO evento = this.pegarEventosService.execute(cuidadorId);

            RespostaDTO respostaDTO = new RespostaDTO(evento, "Eventos encontrados");

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respostaDTO);
        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
