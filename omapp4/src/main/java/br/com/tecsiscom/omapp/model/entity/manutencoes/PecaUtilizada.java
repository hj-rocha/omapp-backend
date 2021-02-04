package br.com.tecsiscom.omapp.model.entity.manutencoes;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.tecsiscom.omapp.model.entity.pecas.Peca;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class PecaUtilizada extends Despesa{

	@ManyToOne
	private Peca peca;
	
	private Long quantidade;
	
		
}
