package br.com.tecsiscom.omapp.model.entity.manutencoes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Manutencao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataEntrada;
	

//	@UpdateTimestamp
//	@Column(nullable = false, columnDefinition = "datetime(6)")
//	private LocalDateTime dataTermino;
	
	//@JsonFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	private LocalDateTime dataSaida;	

	private boolean ativa = true;
	
	@ManyToOne
	private Pessoa responsavelManutencao;
	
	
	@ManyToOne
	private Veiculo veiculo;

	@JsonIgnore
	@OneToMany(mappedBy = "manutencao")
	@Column(nullable = true)
	private List<Despesa> despesas = new ArrayList<Despesa>();


}
