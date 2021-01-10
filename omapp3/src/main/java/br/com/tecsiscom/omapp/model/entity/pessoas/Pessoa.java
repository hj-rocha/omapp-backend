package br.com.tecsiscom.omapp.model.entity.pessoas;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pessoa {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = true)
	private String email;
	
	private String identidade;

	@CPF(message = "CPF inválido")
	private String cpf;
	
	//@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	//@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime(6)")
	private LocalDateTime dataAtualizacao;
		
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	private String telefone;
	
	private String telefoneComercial;
	
	private String anotacoes;
	
	@OneToOne
	@JoinColumn(name="usuario_id",referencedColumnName = "id")
    public Usuario usuario;

	//@JsonIgnore
	@Embedded // anotação usada para embutir uma classe @Embeddable
	private EnderecoPessoa endereco;
	
	//@JsonIgnore
	@ManyToMany
	@JoinTable(name = "pessoa_grupo", joinColumns = @JoinColumn(name = "pessoa_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private Set<Grupo> grupos = new HashSet<>();
	
	public boolean removerGrupo(Grupo grupo) {
		return getGrupos().remove(grupo);
	}
	
	public boolean adicionarGrupo(Grupo grupo) {
		return getGrupos().add(grupo);
	}

}
