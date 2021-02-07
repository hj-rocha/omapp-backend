package br.com.tecsiscom.omapp.model.entity.financeiro;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.tecsiscom.omapp.model.entity.financeiro.receber.ItemContaReceber;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Credito extends TransacaoCaixa{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(name="item_conta_receber_id",referencedColumnName = "id", unique = true)
    private ItemContaReceber itemContaReceber;
    
}
