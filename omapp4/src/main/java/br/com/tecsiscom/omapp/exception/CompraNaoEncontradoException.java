package br.com.tecsiscom.omapp.exception;

public class CompraNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public CompraNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CompraNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de compra para o id %d", compraId));
	}
	

}
