package br.com.tecsiscom.omapp.model.entity.financeiro.receber;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.tecsiscom.omapp.model.entity.financeiro.Conta;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.Venda;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class ContaReceber extends Conta{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(name="venda_id",referencedColumnName = "id", unique = true, nullable = false)
    private Venda venda ;
	
	@OneToOne
	@JoinColumn(name="devedor_id", referencedColumnName = "id", nullable = false)
	private Pessoa devedor;


}
