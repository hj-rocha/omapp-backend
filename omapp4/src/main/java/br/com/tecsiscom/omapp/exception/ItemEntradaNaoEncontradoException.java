package br.com.tecsiscom.omapp.exception;

public class ItemEntradaNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ItemEntradaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ItemEntradaNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de item de entrada para o id %d", compraId));
	}
	

}
