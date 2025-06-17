package com.zeelus.zeelus.modules.respostas.repository;

import com.zeelus.zeelus.modules.respostas.RespostaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RespostasRepository extends JpaRepository<RespostaEntity, UUID> {
}
