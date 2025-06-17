package com.zeelus.zeelus.modules.registro.repository;

import com.zeelus.zeelus.modules.registro.RegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegistroRepository extends JpaRepository<RegistroEntity, UUID> {
}
