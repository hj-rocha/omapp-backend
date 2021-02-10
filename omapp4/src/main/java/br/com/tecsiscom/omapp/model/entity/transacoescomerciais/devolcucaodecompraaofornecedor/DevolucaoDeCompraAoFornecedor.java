package br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolcucaodecompraaofornecedor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.TransacaoComercialSaida;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class DevolucaoDeCompraAoFornecedor extends TransacaoComercialSaida {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Compra compra;
}
