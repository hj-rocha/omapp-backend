package br.com.tecsiscom.omapp.rest.model.input;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InputRecebimentoCompraVeiculo {

	private Long idCompra;
	
	@JsonFormat(pattern = "d/M/yyyy")
	//@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	private LocalDate dataPrimeiraParcela = LocalDate.now();
	
	private int numeroParcelas;
	
	private int intervaloEntrePagamentos = 30;	
	
	private Long idConferenteLogado;

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
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
