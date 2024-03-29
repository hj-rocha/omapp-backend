package br.com.tecsiscom.omapp.model.entity.financeiro.receber;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.tecsiscom.omapp.model.entity.financeiro.ItemConta;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class ItemContaReceber extends ItemConta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(name="conta_receber_id",referencedColumnName = "id", unique = true)
    private ContaReceber contaReceber ;
	
}
