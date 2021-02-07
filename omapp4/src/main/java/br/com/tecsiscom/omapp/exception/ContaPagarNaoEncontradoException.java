package br.com.tecsiscom.omapp.exception;

public class ContaPagarNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ContaPagarNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ContaPagarNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de conta pagar para o id %d", compraId));
	}
	

}
