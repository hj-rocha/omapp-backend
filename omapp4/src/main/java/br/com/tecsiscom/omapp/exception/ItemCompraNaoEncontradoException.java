package br.com.tecsiscom.omapp.exception;

public class ItemCompraNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ItemCompraNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ItemCompraNaoEncontradoException(Long itemCompraId) {
		this(String.format("Não existe um cadastro de itemCompra para o id %d", itemCompraId));
	}
	

}
