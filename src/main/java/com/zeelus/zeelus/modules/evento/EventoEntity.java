package com.zeelus.zeelus.modules.evento;

import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tbl_evento")
@DynamicUpdate
@Table(name = "tbl_evento", schema = "public")
public class EventoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_eventos")
    private UUID id_eventos;

    @NotNull(message = "O campo (data) não pode estar vazio.")
    private LocalDate data;

    @NotBlank(message = "O campo (titulo) não pode estar vazio.")
    private String titulo;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    // Relacionamentos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_id_cuidador")
    private CuidadorEntity cuidador;
}
