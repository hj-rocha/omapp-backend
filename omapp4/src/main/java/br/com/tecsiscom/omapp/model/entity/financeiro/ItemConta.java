package br.com.tecsiscom.omapp.model.entity.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hj_ro
 *
 */
@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemConta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;

	private LocalDateTime dataVencimento;

	private BigDecimal valorDocumento;
	
	private BigDecimal desconto;
	
	/**
	 * O saldo é calculado pela diferença do valor do documento 
	 * e a soma dos valores processados. Vaores processados são os respectivos 
	 * créditos, recebidos das contas a receber e débitos, recebidos das contas a 
	 * pagar.
	 */
	private BigDecimal saldo;
   
}
