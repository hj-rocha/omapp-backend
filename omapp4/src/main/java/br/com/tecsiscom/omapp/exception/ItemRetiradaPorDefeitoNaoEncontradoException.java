package br.com.tecsiscom.omapp.exception;

public class ItemRetiradaPorDefeitoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ItemRetiradaPorDefeitoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ItemRetiradaPorDefeitoNaoEncontradoException(Long idvcId) {
		this(String.format("Não existe um cadastro do item de devolução para o id %d", idvcId));
	}
	

}
