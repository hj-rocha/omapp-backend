package br.com.tecsiscom.omapp.exception;

public class ImpostoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ImpostoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ImpostoNaoEncontradoException(Long impostoId) {
		this(String.format("Não existe um cadastro de imposto para o id %d", impostoId));
	}
	

}
