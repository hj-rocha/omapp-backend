package br.com.tecsiscom.omapp.model.entity.manutencoes;

import javax.persistence.Entity;

import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Servico extends Produto{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long tempoDeExecucao;
}
