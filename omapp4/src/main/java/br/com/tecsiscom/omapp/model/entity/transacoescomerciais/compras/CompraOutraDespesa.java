package br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.tecsiscom.omapp.model.entity.manutencoes.OutraDespesa;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class CompraOutraDespesa extends Compra{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name="outra_despesa_id",referencedColumnName = "id", unique = true)
	private OutraDespesa outraDespesa;
}
