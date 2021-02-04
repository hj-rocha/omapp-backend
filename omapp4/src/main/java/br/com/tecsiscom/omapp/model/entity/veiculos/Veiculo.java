package br.com.tecsiscom.omapp.model.entity.veiculos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.message.Message;
import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Veiculo extends Produto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(unique=true)
	private String renavam;

	private String RNTRC;
	
	@ManyToMany
	@JoinTable(name = "veiculo_proprietario",
		joinColumns =  @JoinColumn(name = "veiculo_id"),
		inverseJoinColumns = @JoinColumn(name = "proprietario"))
	private Set<Pessoa> proprietarios = new HashSet<>(); 
	
	@Column(nullable = false)
	@NotEmpty(message = "Placa não pode estar vazia")
	@Size(min = 7, max = 7, message = "Placa deve ter 7 dígitos")
	private String placa;
	
	private String placaAnterior;
	
	private String chassi;
	
	private CTBEspecie especie;
	
	private String tipo;
	
	private Combustivel combustivel;

	//private String marca;
	
	private String modelo;
	
	private Integer anoFabricacao;


	private Integer anoModelo;
	
	private String capacidade;
	
	private String potencia;
	
	private String cilindradas;

	private CTBCategoria categoria;
	
	private String corPredominante;

}
