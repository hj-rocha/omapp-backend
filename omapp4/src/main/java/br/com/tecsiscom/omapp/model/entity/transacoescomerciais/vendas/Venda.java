package br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.TransacaoComercialSaida;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Venda extends TransacaoComercialSaida {
	
	@ManyToOne
	@JoinColumn(nullable = false)
    private Pessoa cliente;
  
	@ManyToOne
	@JoinColumn(nullable = false)
    private Pessoa vendedor;
}
