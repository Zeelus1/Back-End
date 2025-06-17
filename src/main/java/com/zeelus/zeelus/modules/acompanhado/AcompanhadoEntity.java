package com.zeelus.zeelus.modules.acompanhado;

import com.zeelus.zeelus.modules.acompanhante.AcompanhanteEntity;
import com.zeelus.zeelus.modules.anamnese.AnamneseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tbl_acompanhado")
@DynamicUpdate
@Table(name = "tbl_acompanhado", schema = "public")
public class AcompanhadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_acompanhado;

    @NotBlank(message = "O campo (nome_acompanhado) não pode estar vazio.")
    private String nome_acompanhado;

    @NotBlank(message = "O campo (data_nascimento) não pode estar vazio.")
    private LocalDate data_nascimento;

    @NotBlank(message = "O campo (genero) não pode estar vazio.")
    private String genero;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    // Relacionamentos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_id_acompanhante")
    private AcompanhanteEntity acompanhante;

    @OneToOne(mappedBy = "acompanhado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AnamneseEntity anamnese;
}
