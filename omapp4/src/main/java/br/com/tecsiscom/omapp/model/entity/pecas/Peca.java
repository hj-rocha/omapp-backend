package br.com.tecsiscom.omapp.model.entity.pecas;

import javax.persistence.Entity;

import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Peca extends Produto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoInterno;

	private String codigoDeBarras;
	

}
