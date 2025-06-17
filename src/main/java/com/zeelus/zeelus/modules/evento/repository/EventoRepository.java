package com.zeelus.zeelus.modules.evento.repository;

import com.zeelus.zeelus.modules.evento.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventoRepository extends JpaRepository<EventoEntity, UUID> {
}
