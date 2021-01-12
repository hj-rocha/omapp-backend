package br.com.tecsiscom.omapp.model.entity.pessoas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Pessoa pessoa;
    
    @Column(unique = true, name = "login")
    @NotEmpty(message = "{campo.login.obrigatorio}")
    @Email(message = "{campo.login.email.valido}")
    private String username;

    @Column(name = "senha")
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;
    
	public boolean isNovo() {
		return getId() == null;
	}
    
//	@ManyToMany
//	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"),
//			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
//	private List<Grupo> grupos = new ArrayList<>();
}
