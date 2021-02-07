package br.com.tecsiscom.omapp.exception;

public class DebitoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public DebitoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public DebitoNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de débito para o id %d", compraId));
	}
	

}
