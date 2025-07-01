package com.zeelus.zeelus.modules.pergunta;

import com.zeelus.zeelus.modules.cuidador.CuidadorEntity;
import com.zeelus.zeelus.modules.respostas.RespostaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tbl_pergunta")
@DynamicUpdate
@Table(name = "tbl_pergunta", schema = "public")
public class PerguntaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_perguntas;

    @NotBlank(message = "O campo (titulo) não pode estar vazio.")
    private String titulo;

    @NotBlank(message = "O campo (descricao) não pode estar vazio.")
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @NotNull(message = "O campo (data) não pode estar vazio.")
    private LocalDate data;

    @NotBlank(message = "O campo (tag) não pode estar vazio.")
    private String tag;

    private Integer curtidas = 0;

    // Relacionamentos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_id_cuidador")
    private CuidadorEntity cuidador;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RespostaEntity> respostas;
}
