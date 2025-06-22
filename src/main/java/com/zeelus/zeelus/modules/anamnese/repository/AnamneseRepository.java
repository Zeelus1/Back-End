package com.zeelus.zeelus.modules.anamnese.repository;

import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AnamneseRepository extends JpaRepository<AnamneseEntity, UUID> {
}
