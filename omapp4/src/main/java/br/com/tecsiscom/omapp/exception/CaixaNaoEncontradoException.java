package br.com.tecsiscom.omapp.exception;

public class CaixaNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public CaixaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CaixaNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de caixa para o id %d", compraId));
	}
	

}
