package br.com.tecsiscom.omapp.exception;

public class ItemContaReceberNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ItemContaReceberNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ItemContaReceberNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de item de conta a receber para o id %d", compraId));
	}
	

}
