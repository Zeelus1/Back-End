package com.zeelus.zeelus.modules.anamnese;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zeelus.zeelus.modules.acompanhado.AcompanhadoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tbl_anamnese")
@DynamicUpdate
@Table(name = "tbl_anamnese", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_anamnese")
public class AnamneseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_anamnese;

    @Column(name = "diagnostico", columnDefinition = "TEXT")
    @NotBlank(message = "O campo (diagnostico) não pode estar vazio.")
    private String diagnostico;

    @NotBlank(message = "O campo (necessidades) não pode estar vazio.")
    @Column(name = "necessidades", columnDefinition = "TEXT")
    private String necessidades;

    // Relacionamentos
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_id_acompanhado")
    private AcompanhadoEntity acompanhado;
}

