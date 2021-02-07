package br.com.tecsiscom.omapp.exception;

public class CreditoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public CreditoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CreditoNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de crédito para o id %d", compraId));
	}
	

}
