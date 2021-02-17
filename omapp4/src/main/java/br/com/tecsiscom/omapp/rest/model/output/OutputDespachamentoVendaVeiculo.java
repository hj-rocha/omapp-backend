package br.com.tecsiscom.omapp.rest.model.output;

import java.util.Set;
import br.com.tecsiscom.omapp.model.entity.estoque.Estoque;
import br.com.tecsiscom.omapp.model.entity.financeiro.receber.ContaReceber;
import br.com.tecsiscom.omapp.model.entity.financeiro.receber.ItemContaReceber;
import br.com.tecsiscom.omapp.model.entity.transacoescomerciais.vendas.Venda;
import br.com.tecsiscom.omapp.model.entity.veiculos.Veiculo;

public class OutputDespachamentoVendaVeiculo {

	private Estoque estoque;
	
	private Venda venda;
		
	private Veiculo veiculo;
	
	private ContaReceber contaReceber;
	
	private Set<ItemContaReceber> itensContaReceber;
	
	private Long idManutencao;

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public ContaReceber getContaReceber() {
		return contaReceber;
	}

	public void setContaReceber(ContaReceber contaReceber) {
		this.contaReceber = contaReceber;
	}

	public Set<ItemContaReceber> getItensContaReceber() {
		return itensContaReceber;
	}

	public void setItensContaReceber(Set<ItemContaReceber> itensContaReceber) {
		this.itensContaReceber = itensContaReceber;
	}

	public Long getIdManutencao() {
		return idManutencao;
	}

	public void setIdManutencao(Long idManutencao) {
		this.idManutencao = idManutencao;
	}

	
	}
