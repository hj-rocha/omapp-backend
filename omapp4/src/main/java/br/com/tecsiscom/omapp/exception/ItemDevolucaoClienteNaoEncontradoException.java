package br.com.tecsiscom.omapp.exception;

public class ItemDevolucaoClienteNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ItemDevolucaoClienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ItemDevolucaoClienteNaoEncontradoException(Long idvcId) {
		this(String.format("Não existe um cadastro do item de devolução para o id %d", idvcId));
	}
	

}
