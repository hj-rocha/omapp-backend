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
