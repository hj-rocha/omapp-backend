package br.com.tecsiscom.omapp.model.entity.manutencoes;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class OutraDespesa extends Despesa{

	private String descricao;
	
}
