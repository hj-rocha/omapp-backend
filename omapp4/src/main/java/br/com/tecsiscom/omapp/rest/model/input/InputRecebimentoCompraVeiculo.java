package br.com.tecsiscom.omapp.rest.model.input;

import java.time.LocalDate;

public class InputRecebimentoCompraVeiculo {

	private Long idCompra;
	
	private LocalDate dataPrimeiroPagamento;
	
	private int numeroParcela;
	
	private int intervaloEntrePagamentos = 30;
	
	private Long idConferenteLogado;

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public LocalDate getDataPrimeiroPagamento() {
		return dataPrimeiroPagamento;
	}

	public void setDataPrimeiroPagamento(LocalDate dataPrimeiroPagamento) {
		this.dataPrimeiroPagamento = dataPrimeiroPagamento;
	}

	public int getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Long getIdConferenteLogado() {
		return idConferenteLogado;
	}

	public void setIdConferenteLogado(Long idConferenteLogado) {
		this.idConferenteLogado = idConferenteLogado;
	}

	public int getIntervaloEntrePagamentos() {
		return intervaloEntrePagamentos;
	}

	public void setIntervaloEntrePagamentos(int intervaloEntrePagamentos) {
		this.intervaloEntrePagamentos = intervaloEntrePagamentos;
	}
	
	
	
}
