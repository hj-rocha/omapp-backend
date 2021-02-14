package br.com.tecsiscom.omapp.rest.models.comprasveiculos;

import java.math.BigDecimal;

import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;


public class InputItemCompraVeiculo{


	private Long id;
		
    private BigDecimal quantidade;

    private BigDecimal valorUnitario;

	private Veiculo veiculo;
	
	private Compra compra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}



	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	
}
