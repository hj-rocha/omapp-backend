package br.com.tecsiscom.omapp.model.entity.veiculos;

import javax.persistence.Entity;

import br.com.tecsiscom.omapp.model.entity.produtos.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Veiculo extends Produto{
	
	private String renavam;

}
