package com.zeelus.zeelus.modules.registro;

import com.zeelus.zeelus.modules.acompanhante.AcompanhanteEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Entity(name = "tbl_registro")
@DynamicUpdate
@Table(name = "tbl_registro", schema = "public")
public class RegistroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_registros;

    @NotBlank(message = "O campo (titulo) não pode estar vazio.")
    private String titulo;

    @NotBlank(message = "O campo (descricao) não pode estar vazio.")
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @NotBlank(message = "O campo (data) não pode estar vazio.")
    private LocalDate data;

    // Relacionamentos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_id_acompanhante")
    private AcompanhanteEntity acompanhante;
}
