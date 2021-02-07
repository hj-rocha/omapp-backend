package br.com.tecsiscom.omapp.exception;

public class DevolucaoClienteNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public DevolucaoClienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public DevolucaoClienteNaoEncontradoException(Long compraId) {
		this(String.format("Não existe um cadastro de devolucação para o id %d", compraId));
	}
	

}
