package br.com.tecsiscom.omapp.exception;

public class RetiradaPorDefeitoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public RetiradaPorDefeitoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RetiradaPorDefeitoNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de compra para o id %d", compraId));
	}
	

}
