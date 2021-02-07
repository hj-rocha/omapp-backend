package br.com.tecsiscom.omapp.exception;

public class ReforcoDeCaixaNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ReforcoDeCaixaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ReforcoDeCaixaNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de reforço de caixa para o id %d", compraId));
	}
	

}
