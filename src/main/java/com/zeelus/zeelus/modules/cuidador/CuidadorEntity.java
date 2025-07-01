package com.zeelus.zeelus.modules.cuidador;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zeelus.zeelus.modules.acompanhado.AcompanhadoEntity;
import com.zeelus.zeelus.modules.evento.EventoEntity;
import com.zeelus.zeelus.modules.pergunta.PerguntaEntity;
import com.zeelus.zeelus.modules.registro.RegistroEntity;
import com.zeelus.zeelus.modules.respostas.RespostaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tbl_acompanhante")
@DynamicUpdate
@Table(name = "tbl_acompanhante", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_cuidador")
public class CuidadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cuidador")
    private UUID id_cuidador;

    @NotBlank(message = "O campo (nome_acompanhante) não pode estar vazio")
    private String nome_cuidador;

    @NotBlank(message = "O campo (email) não pode estar vazio")
    @Email(message = "O campo (email) deve receber um e-mail valido.")
    private String email;

    @NotBlank(message = "O campo (senha) não pode estar vazio")
    @Length(max = 70, min = 8, message = "A senha deve ter no minimo 8 carateres")
    private String senha;

    private String urlImg;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    // Relacionamentos
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AcompanhadoEntity> acompanhados;

    @OneToMany(mappedBy = "acompanhante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RegistroEntity> registros;

    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PerguntaEntity> perguntas;

    @OneToMany(mappedBy = "acompanhante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RespostaEntity> respostas;

    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EventoEntity> eventos;
}
