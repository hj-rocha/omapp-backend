package br.com.tecsiscom.omapp.rest.model.input;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InputDespachamentoVendaVeiculo {

	private Long idVenda;
	
	@JsonFormat(pattern = "d/M/yyyy")
	//@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	private LocalDate dataPrimeiraParcela  = LocalDate.now();
	
	private int numeroParcelas;
	
	private int intervaloEntrePagamentos = 30;	
	
	private Long idConferenteLogado;

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	/**
	 * @return the dataPrimeiraParcela
	 */
	public LocalDate getDataPrimeiraParcela() {
		return dataPrimeiraParcela;
	}

	/**
	 * @param dataPrimeiraParcela the dataPrimeiraParcela to set
	 */
	public void setDataPrimeiraParcela(LocalDate dataPrimeiraParcela) {
		this.dataPrimeiraParcela = dataPrimeiraParcela;
	}

	/**
	 * @return the numeroParcelas
	 */
	public int getNumeroParcelas() {
		return numeroParcelas;
	}

	/**
	 * @param numeroParcelas the numeroParcelas to set
	 */
	public void setNumeroParcelas(int numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
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
