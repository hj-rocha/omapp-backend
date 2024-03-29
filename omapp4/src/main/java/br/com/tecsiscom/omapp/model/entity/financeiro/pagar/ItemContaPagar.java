package br.com.tecsiscom.omapp.model.entity.financeiro.pagar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import br.com.tecsiscom.omapp.model.entity.financeiro.ItemConta;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class ItemContaPagar extends ItemConta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
//	@OneToOne
//	@JoinColumn(name="conta_pagar_id",referencedColumnName = "id", unique = true)
//    private ContaPagar contaPagar ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ContaPagar contaPagar;
	
}