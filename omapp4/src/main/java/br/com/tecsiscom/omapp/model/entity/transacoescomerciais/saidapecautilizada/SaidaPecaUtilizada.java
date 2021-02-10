package br.com.tecsiscom.omapp.model.entity.transacoescomerciais.saidapecautilizada;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.tecsiscom.omapp.model.entity.manutencoes.PecaUtilizada;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.TransacaoComercialSaida;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class SaidaPecaUtilizada extends TransacaoComercialSaida {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name="peca_utilizada_id",referencedColumnName = "id", unique = true)
    private PecaUtilizada pecaUtilizada;
}
