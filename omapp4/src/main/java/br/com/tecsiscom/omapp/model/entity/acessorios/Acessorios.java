package br.com.tecsiscom.omapp.model.entity.acessorios;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Acessorios extends Produto {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(unique = true)
	private String codigoInterno;

	@Column(unique = true)
	private String codigoDeBarras;
	

	
}
