package br.com.tecsiscom.omapp.model.entity.transacoescomerciais.saidapecautilizada;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.ItemTransacaoComercial;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class ItemSaidaPecaUtilizada extends ItemTransacaoComercial {

	@ManyToOne
	@JoinColumn(nullable = false)
    private SaidaPecaUtilizada saidaPecaUtilizada;

}
