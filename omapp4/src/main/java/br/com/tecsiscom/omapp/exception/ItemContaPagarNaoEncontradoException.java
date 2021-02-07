package br.com.tecsiscom.omapp.exception;

public class ItemContaPagarNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ItemContaPagarNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ItemContaPagarNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de item de conta a pagar para o id %d", compraId));
	}
	

}
