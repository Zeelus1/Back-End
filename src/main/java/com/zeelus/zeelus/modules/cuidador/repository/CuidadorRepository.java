package com.zeelus.zeelus.modules.cuidador.repository;

import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CuidadorRepository extends JpaRepository<CuidadorEntity, UUID> {
    Optional<CuidadorEntity> findByEmailAndSenha(String email, String senha);

    Optional<CuidadorEntity> findByEmail(String email);
}
