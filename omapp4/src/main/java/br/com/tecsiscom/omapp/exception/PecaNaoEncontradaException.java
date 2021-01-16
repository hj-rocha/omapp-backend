package br.com.tecsiscom.omapp.exception;

public class PecaNaoEncontradaException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public PecaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public PecaNaoEncontradaException(Long pecaId) {
		this(String.format("Não existe um cadastro da peça para o id %d", pecaId));
	}
	

}
