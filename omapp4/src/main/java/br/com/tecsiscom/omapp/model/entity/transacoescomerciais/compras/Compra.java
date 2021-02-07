package br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.TransacaoComercialEntrada;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Compra extends TransacaoComercialEntrada {
	
	@ManyToOne
	@JoinColumn(nullable = false)
    private Pessoa fornecedor;
    
	@ManyToOne
	@JoinColumn(nullable = false)
    private Pessoa comprador;
}
