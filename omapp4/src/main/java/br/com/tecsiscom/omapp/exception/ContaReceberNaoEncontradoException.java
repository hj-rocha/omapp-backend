package br.com.tecsiscom.omapp.exception;

public class ContaReceberNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ContaReceberNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ContaReceberNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de conta receber para o id %d", compraId));
	}
	

}
