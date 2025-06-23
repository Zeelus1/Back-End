package com.zeelus.zeelus.modules.anamnese.service;

import com.zeelus.zeelus.modules.acompanhado.AcompanhadoEntity;
import com.zeelus.zeelus.modules.acompanhado.repository.AcompanhadoRepository;
import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import com.zeelus.zeelus.modules.anamnese.repository.AnamneseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeletarAnamneseService {
    
    @Autowired
    private AnamneseRepository anamneseRepository;

    @Autowired
    private AcompanhadoRepository acompanhadoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String execute(UUID idAcompanhado) {
        
        AcompanhadoEntity acompanhado = this.acompanhadoRepository.findById(idAcompanhado).orElseThrow(
                () -> {
                    throw new EntityNotFoundException("Acompanhado não encontrado");
                });

        AnamneseEntity anamnese = this.anamneseRepository.findByAcompanhado(acompanhado).orElseThrow(
                () -> {
                    throw new EntityNotFoundException("Anamnese não encontrada.");
                });

        acompanhado.setAnamnese(null);
        anamnese.setAcompanhado(null);

        acompanhadoRepository.save(acompanhado);

        this.anamneseRepository.delete(anamnese);

        return "Anamnese excluída com sucesso.";
    }
}