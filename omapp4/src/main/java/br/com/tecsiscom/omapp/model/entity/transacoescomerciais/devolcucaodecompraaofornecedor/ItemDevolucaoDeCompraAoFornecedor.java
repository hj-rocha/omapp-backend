package br.com.tecsiscom.omapp.model.entity.transacoescomerciais.devolcucaodecompraaofornecedor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.ItemTransacaoComercial;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class ItemDevolucaoDeCompraAoFornecedor extends ItemTransacaoComercial {

	@ManyToOne
	@JoinColumn(nullable = false)
    private DevolucaoDeCompraAoFornecedor devolucaoDeCompraAoFornecedor;

}
