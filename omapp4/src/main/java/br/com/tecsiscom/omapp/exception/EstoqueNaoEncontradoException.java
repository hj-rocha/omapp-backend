package br.com.tecsiscom.omapp.exception;

public class EstoqueNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public EstoqueNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EstoqueNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de estoque para o id %d", compraId));
	}
	

}
