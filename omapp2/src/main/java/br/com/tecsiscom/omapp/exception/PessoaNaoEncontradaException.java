package br.com.tecsiscom.omapp.exception;



public class PessoaNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public PessoaNaoEncontradaException(Long pessoaId) {
		this(String.format("Não existe um cadastro de pessoa para o id %d", pessoaId));
	}
		
}
