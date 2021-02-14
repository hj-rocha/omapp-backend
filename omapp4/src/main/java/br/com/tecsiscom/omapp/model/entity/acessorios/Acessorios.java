package br.com.tecsiscom.omapp.model.entity.acessorios;

import javax.persistence.Entity;

import br.com.tecsiscom.omapp.model.entity.produtos.Mercadoria;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Acessorios extends Mercadoria {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
