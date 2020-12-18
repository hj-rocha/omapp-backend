package br.com.tecsiscom.omapp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "login")
    @NotEmpty(message = "{campo.login.obrigatorio}")
    @Email(message = "{campo.login.email.valido}")
    private String username;

    @Column(name = "senha")
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;
}
