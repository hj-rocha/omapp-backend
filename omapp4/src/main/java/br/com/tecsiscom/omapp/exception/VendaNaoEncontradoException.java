package br.com.tecsiscom.omapp.exception;

public class VendaNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public VendaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public VendaNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de compra para o id %d", compraId));
	}
	

}
