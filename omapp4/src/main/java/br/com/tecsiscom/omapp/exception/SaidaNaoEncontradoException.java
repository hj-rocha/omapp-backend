package br.com.tecsiscom.omapp.exception;

public class SaidaNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public SaidaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public SaidaNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de saída para o id %d", compraId));
	}
	

}
