package com.zeelus.zeelus.modules.acompanhado.repository;

import com.zeelus.zeelus.modules.acompanhado.AcompanhadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AcompanhadoRepository extends JpaRepository<AcompanhadoEntity, UUID> {
}
