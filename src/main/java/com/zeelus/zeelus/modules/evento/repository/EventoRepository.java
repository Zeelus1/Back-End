package com.zeelus.zeelus.modules.evento.repository;

import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import com.zeelus.zeelus.modules.evento.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventoRepository extends JpaRepository<EventoEntity, UUID> {
    List<EventoEntity> findAllByCuidador(CuidadorEntity cuidador);
}
