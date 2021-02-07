package br.com.tecsiscom.omapp.exception;

public class EntradaNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public EntradaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EntradaNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de entrada para o id %d", compraId));
	}
	

}
