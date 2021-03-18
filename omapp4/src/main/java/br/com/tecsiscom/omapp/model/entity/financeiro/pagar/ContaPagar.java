package br.com.tecsiscom.omapp.model.entity.financeiro.pagar;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.tecsiscom.omapp.model.entity.financeiro.Conta;
import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
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
	
	@OneToOne
	@JoinColumn(name="credor_id", referencedColumnName = "id", nullable = false)
	private Pessoa credor;
	
	@OneToMany(mappedBy = "contaPagar")
	List<ItemContaPagar> itensContaPagar = new ArrayList<>();

}
