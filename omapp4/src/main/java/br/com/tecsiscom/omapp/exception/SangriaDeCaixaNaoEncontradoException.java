package br.com.tecsiscom.omapp.exception;

public class SangriaDeCaixaNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public SangriaDeCaixaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public SangriaDeCaixaNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de sangria de caixa para o id %d", compraId));
	}
	

}
