package com.zeelus.zeelus.modules.acompanhado.service;

import com.zeelus.zeelus.modules.acompanhado.AcompanhadoEntity;
import com.zeelus.zeelus.modules.acompanhado.dto.CadastrarAcompanhadoDTO;
import com.zeelus.zeelus.modules.acompanhado.repository.AcompanhadoRepository;
import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import com.zeelus.zeelus.modules.cuidador.repository.CuidadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastrarAcompanhadoSerivce {
    @Autowired
    private AcompanhadoRepository acompanhadoRepository;

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Transactional
    public AcompanhadoEntity execute(CadastrarAcompanhadoDTO acompanhadoDTO){
        CuidadorEntity cuidador = this.cuidadorRepository.findById(acompanhadoDTO.getCuidadorID())
                .orElseThrow(() -> {
                    throw new RuntimeException("Cuidador não encontrado.");
                });

        AcompanhadoEntity acompanhado = AcompanhadoEntity.builder()
                .nome_acompanhado(acompanhadoDTO.getNome_acompanhado())
                .genero(acompanhadoDTO.getGenero())
                .data_nascimento(acompanhadoDTO.getData_nascimento())
                .cuidador(cuidador)
                .build();

        return this.acompanhadoRepository.save(acompanhado);

    }
}
