package br.com.tecsiscom.omapp.exception;

public class ItemSaidaNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ItemSaidaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ItemSaidaNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de item de saída para o id %d", compraId));
	}
	

}
