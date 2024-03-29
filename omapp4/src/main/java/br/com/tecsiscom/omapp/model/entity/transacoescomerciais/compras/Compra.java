package br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.tecsiscom.omapp.model.entity.pessoas.Pessoa;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.TransacaoComercialEntrada;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Compra extends TransacaoComercialEntrada {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "datetime")
	private LocalDateTime dataRecebimento;
	
	@ManyToOne
	@JoinColumn(nullable = false)
    private Pessoa fornecedor;
    
	@ManyToOne
	@JoinColumn(nullable = false)
    private Pessoa comprador;

//	@JsonIgnore
//	@OneToMany
//	@JoinColumn(name = "compra_id")
//	List<ItemCompra> itensCompra = new ArrayList<ItemCompra>();

}
