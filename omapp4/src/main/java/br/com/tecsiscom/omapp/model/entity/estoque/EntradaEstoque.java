package br.com.tecsiscom.omapp.model.entity.estoque;

import java.io.Serializable;

import javax.persistence.Entity;

import br.com.tecsiscom.omapp.model.entity.manutencoes.OutraDespesa;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class EntradaEstoque extends MovimentoEstoque implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
