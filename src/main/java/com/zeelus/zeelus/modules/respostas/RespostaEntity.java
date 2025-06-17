package com.zeelus.zeelus.modules.respostas;

import com.zeelus.zeelus.modules.acompanhante.AcompanhanteEntity;
import com.zeelus.zeelus.modules.pergunta.PerguntaEntity;
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
@Entity(name = "tbl_resposta")
@DynamicUpdate
@Table(name = "tbl_resposta", schema = "public")
public class RespostaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_resposta;

    @Column(name = "resposta", columnDefinition = "TEXT")
    @NotBlank(message = "O campo (resposta) não pode estar vazio.")
    private String resposta;

    @NotBlank(message = "O campo (data) não pode estar vazio.")
    private LocalDate data;

    private Integer curtidas = 0;

    // Relacionamentos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_id_acompanhante")
    private AcompanhanteEntity acompanhante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_id_perguntas")
    private PerguntaEntity pergunta;

}
