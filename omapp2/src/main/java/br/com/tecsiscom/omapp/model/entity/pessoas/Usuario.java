package br.com.tecsiscom.omapp.model.entity.pessoas;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @JsonIgnore
//    @OneToOne(mappedBy = "usuario")
//    private Pessoa pessoa;
    
    @Column(unique = true, name = "login")
    @NotEmpty(message = "{campo.login.obrigatorio}")
    @Email(message = "{campo.login.email.valido}")
    private String username;

    @Column(name = "senha")
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;
}
