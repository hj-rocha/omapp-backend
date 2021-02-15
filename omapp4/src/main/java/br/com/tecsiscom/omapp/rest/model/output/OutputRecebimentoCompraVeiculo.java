package br.com.tecsiscom.omapp.rest.model.output;


import java.util.Set;

import br.com.tecsiscom.omapp.model.entity.estoque.Estoque;
import br.com.tecsiscom.omapp.model.entity.financeiro.pagar.ContaPagar;
import br.com.tecsiscom.omapp.model.entity.financeiro.pagar.ItemContaPagar;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.compras.Compra;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;

public class OutputRecebimentoCompraVeiculo {

	private Estoque estoque;
	
	private Compra compra;
	
//	private Entrada entrada;
//	
//	private Set<ItemEntrada> itensEntrada;
	
	private Veiculo veiculo;
	
	private ContaPagar contaPagar;
	
	private Set<ItemContaPagar> itensContaPagar;
	
	private Long idManutencao;

	
	
	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public void setItensContaPagar(Set<ItemContaPagar> itensContaPagar) {
		this.itensContaPagar = itensContaPagar;
	}

//	public Entrada getEntrada() {
//		return entrada;
//	}
//
//	public void setEntrada(Entrada entrada) {
//		this.entrada = entrada;
//	}
//
//	public Set<ItemEntrada> getItensEntrada() {
//		return itensEntrada;
//	}
//
//	public void setItensEntrada(Set<ItemEntrada> itensEntrada) {
//		this.itensEntrada = itensEntrada;
//	}

	public ContaPagar getContaPagar() {
		return contaPagar;
	}

	public void setContaPagar(ContaPagar contaPagar) {
		this.contaPagar = contaPagar;
	}

	public Set<ItemContaPagar> getItensContaPagar() {
		return itensContaPagar;
	}

	public void setItensContaPgar(Set<ItemContaPagar> itensContaPagar) {
		this.itensContaPagar = itensContaPagar;
	}

	public Long getIdManutencao() {
		return idManutencao;
	}

	public void setIdManutencao(Long idManutencao) {
		this.idManutencao = idManutencao;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	

	
	
}
