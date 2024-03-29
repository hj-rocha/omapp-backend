package br.com.tecsiscom.omapp.exception;

public class VeiculoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public VeiculoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public VeiculoNaoEncontradoException(Long veiculoId) {
		this(String.format("Não existe um cadastro de veículo para o id %d", veiculoId));
	}
	

}
