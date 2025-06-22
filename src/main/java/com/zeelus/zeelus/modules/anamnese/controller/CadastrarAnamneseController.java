package com.zeelus.zeelus.modules.anamnese.controller;

import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import com.zeelus.zeelus.modules.anamnese.dto.AnamneseDTO;
import com.zeelus.zeelus.modules.anamnese.service.CadastrarAnamneseService;
import com.zeelus.zeelus.modules.cuidador.dto.RespostaDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anamnese")
public class CadastrarAnamneseController {

    @Autowired
    private CadastrarAnamneseService cadastrarAnamneseService;

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('CUIDADOR')")
    public ResponseEntity<Object> execute(@Valid @RequestBody AnamneseDTO dto){
        try{
            AnamneseEntity anamnese = this.cadastrarAnamneseService.execute(dto);

            RespostaDTO respostaDTO = new RespostaDTO(anamnese, "Anamnese cadastrada com sucesso.");

            return ResponseEntity.status(HttpStatus.CREATED).body(respostaDTO);

        } catch (Exception e) {
            RespostaDTO respostaDTO = new RespostaDTO(null, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDTO);
        }
    }

}
