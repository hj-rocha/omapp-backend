package br.com.tecsiscom.omapp.model.entity.financeiro.pagar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.tecsiscom.omapp.model.entity.financeiro.Conta;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class ContaPagar extends Conta{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(name="compra_id",referencedColumnName = "id", unique = true, nullable = false)
    private Compra compra ;

}
