package com.zeelus.zeelus.modules.acompanhante.repository;

import com.zeelus.zeelus.modules.acompanhante.AcompanhanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AcompanhanteRepository extends JpaRepository<AcompanhanteEntity, UUID> {
    Optional<AcompanhanteEntity> findByEmailAndSenha(String email, String senha);

    Optional<AcompanhanteEntity> findByEmail(String email);
}
