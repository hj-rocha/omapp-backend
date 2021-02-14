package br.com.tecsiscom.omapp.model.entity.produtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "Nome não pode estar vazio")
	@Size(min = 2, max = 50, message = "Nome deve ter min: 2 e max 50 caracteres")
	private String nome;

	private String descricao;

	//Custo total do produto é a soma de todas as despesas
	private BigDecimal custo;
	
	@Column(unique = true)
	private String codigoInterno;
	
	@ManyToOne
	@JoinColumn(columnDefinition="integer", name = "marca_id", nullable = true)
	private Marca marca;
	
	//@JsonIgnore
	@ManyToMany
	@JoinTable(name = "produto_imposto",
			joinColumns = @JoinColumn(name = "produto_id"),
			inverseJoinColumns = @JoinColumn(name = "imposto_id"))
	private Set<Imposto> impostos;
	
	private BigDecimal margemLucro;
	
	private BigDecimal venda;
	
	//@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	//@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime(6)")
	private LocalDateTime dataAtualizacao;
	
	//@JsonIgnore
	@ManyToMany
	@JoinTable(name = "produto_fornecedor",
			joinColumns = @JoinColumn(name = "produto_id"),
			inverseJoinColumns = @JoinColumn(name = "pessoa_id"))
	private Set<Pessoa> fornecedores = new HashSet<>();
	
	public boolean removerImposto(Imposto imposto) {
		return getImpostos().remove(imposto);
	}
	
	public boolean adicionarImposto(Imposto 
			imposto) {
		return getImpostos().add(imposto);
	}
}
