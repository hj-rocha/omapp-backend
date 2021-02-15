package br.com.tecsiscom.omapp.exception;

public class EstoqueVeiculoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public EstoqueVeiculoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EstoqueVeiculoNaoEncontradoException(Long compraId) {
		this(String.format("O estoque de um veículo não pode ser maior que um. Id: %d", compraId));
	}
	

}
