package com.zeelus.zeelus.modules.pergunta.repository;

import com.zeelus.zeelus.modules.pergunta.PerguntaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PerguntaRepository extends JpaRepository<PerguntaEntity, UUID> {
}
