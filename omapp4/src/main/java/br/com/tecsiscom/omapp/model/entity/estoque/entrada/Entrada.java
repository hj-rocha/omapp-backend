package br.com.tecsiscom.omapp.model.entity.estoque.entrada;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.TransacaoComercialEntrada;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrada{

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="transacao_comercial_entrada_id",referencedColumnName = "id", unique = true)
    private TransacaoComercialEntrada transacaoComercialEntrada;

}
