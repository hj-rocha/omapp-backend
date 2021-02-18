package br.com.tecsiscom.omapp.model.entity.financeiro.caixa;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Caixa {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean fechado =false;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataHoraAbertura;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime(6)")
	private LocalDateTime dataHoraFechamento;
    
    private BigDecimal fundoDeCaixa;

    private BigDecimal saldoDoFechamento;
    
//	@JsonIgnore
//	@OneToMany
//    private List<Debito> debitos;
//    
//	@JsonIgnore
//	@OneToMany
//    private List<Credito> creditos;
//    
//	@JsonIgnore
//	@OneToMany
//    private List<SangriaDeCaixa> sangrias;
//
//	@JsonIgnore
//	@OneToMany
//    private List<ReforcoDeCaixa> reforcos;
//
//	@JsonIgnore
//	@OneToMany
//    private List<Vale> vales;

}
