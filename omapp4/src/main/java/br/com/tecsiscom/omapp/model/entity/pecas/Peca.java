package br.com.tecsiscom.omapp.model.entity.pecas;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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

	//@JsonIgnore
	@ManyToMany
	@JoinTable(name = "peca_marca",
			joinColumns = @JoinColumn(name = "peca_id"),
			inverseJoinColumns = @JoinColumn(name = "marca_id"))
	private java.util.Set<Marca> marcas;
	

}
