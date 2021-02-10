package br.com.tecsiscom.omapp.exception;

public class CaixaNaoPodeSerReabertoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public CaixaNaoPodeSerReabertoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CaixaNaoPodeSerReabertoEncontradoException(Long caixaId) {
		this(String.format("O caixa com o id %d já foi fechado e não pode ser reaberto.", caixaId));
	}

}
