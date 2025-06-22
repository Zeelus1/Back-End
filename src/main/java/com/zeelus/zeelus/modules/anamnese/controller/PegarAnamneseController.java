package com.zeelus.zeelus.modules.anamnese.controller;

import com.zeelus.zeelus.modules.anamnese.dto.AnamneseDTO;
import com.zeelus.zeelus.modules.anamnese.service.PegarAnamneseService;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/anamnese")
public class PegarAnamneseController {

    @Autowired
    private PegarAnamneseService pegarAnamneseService;

    @GetMapping("/{idAcompanhado}")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<RespostaDTO> execute(@PathVariable UUID idAcompanhado) {
        try {
             AnamneseDTO anamnese = this.pegarAnamneseService.execute(idAcompanhado);

            return ResponseEntity.ok(new RespostaDTO(anamnese, "Anamnese encontrada com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new RespostaDTO(null, e.getMessage()));
        }
    }
}
