package br.com.tecsiscom.omapp.exception;

public class ValeNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ValeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ValeNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de vale para o id %d", compraId));
	}
	

}
