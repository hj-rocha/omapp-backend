package br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.ItemTransacaoComercial;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class ItemVenda extends ItemTransacaoComercial {

//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "venda_id")
//    private Venda venda;

	@ManyToOne
	@JoinColumn(nullable = false)
    private Venda venda;
	
}
