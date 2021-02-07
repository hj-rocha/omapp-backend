package br.com.tecsiscom.omapp.exception;

public class ItemVendaNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ItemVendaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ItemVendaNaoEncontradoException(Long itemCompraId) {
		this(String.format("Não existe um cadastro de itemCompra para o id %d", itemCompraId));
	}
	

}
