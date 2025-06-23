package com.zeelus.zeelus.modules.anamnese.controller;

import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import com.zeelus.zeelus.modules.anamnese.dto.AnamneseAtualizarDTO;
import com.zeelus.zeelus.modules.anamnese.dto.AnamneseResponseDTO;
import com.zeelus.zeelus.modules.anamnese.service.AtualizarAnamneseService;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import com.zeelus.zeelus.modules.respostas.RespostaEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/anamnese")
public class AtualizarAnamneseController {

    @Autowired
    private AtualizarAnamneseService atualizarAnamneseService;

    @PutMapping("/atualizar/{idAcompanhado}")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<Object> execute(@PathVariable UUID idAcompanhado, @Valid @RequestBody AnamneseAtualizarDTO atualizarDTO){
        try{
            AnamneseResponseDTO result = this.atualizarAnamneseService.execute(idAcompanhado, atualizarDTO);

            RespostaDTO respostaDTO = new RespostaDTO(result, "Anamnese atualizada com sucesso");

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respostaDTO);
        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }
}
