package br.com.tecsiscom.omapp.exception;

public class ManutencaoNaoEncontradaException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public ManutencaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public ManutencaoNaoEncontradaException(Long marcaId) {
		this(String.format("Não existe um cadastro de manutenação para o id %d", marcaId));
	}
	

}
