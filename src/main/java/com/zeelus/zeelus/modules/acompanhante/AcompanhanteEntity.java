package com.zeelus.zeelus.modules.acompanhante;

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
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tbl_acompanhante")
@DynamicUpdate
@Table(name = "tbl_acompanhante", schema = "public")
public class AcompanhanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_acompanhante;

    @NotBlank(message = "O campo (nome_acompanhante) não pode estar vazio")
    private String nome_acompanhante;

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

}
