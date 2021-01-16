package br.com.tecsiscom.omapp.exception;

public class ServicoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ServicoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ServicoNaoEncontradoException(Long servicoId) {
		this(String.format("Não existe um cadastro da serviço para o id %d", servicoId));
	}
	

}
