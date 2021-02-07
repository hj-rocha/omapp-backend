package br.com.tecsiscom.omapp.model.entity.financeiro;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.tecsiscom.omapp.model.entity.financeiro.pagar.ItemContaPagar;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Debito extends TransacaoCaixa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(name="item_conta_pagar_id",referencedColumnName = "id", unique = true)
    private ItemContaPagar itemContaPagar;

}
