package br.com.tecsiscom.omapp.model.entity.financeiro.caixa;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Vale extends TransacaoFinanceira{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nota;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Pessoa funcionario;
}
