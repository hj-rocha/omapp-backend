package br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolucaovendaaocliente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.TransacaoComercialEntrada;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.Venda;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class DevolucaoDeVendaAoCliente extends TransacaoComercialEntrada {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(nullable = false)
    private Venda venda;

}
