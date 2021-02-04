package br.com.tecsiscom.omapp.exception;

public class CampoUnicoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public CampoUnicoException(String mensagem) {
		super(String.format("O campo %s não pode ter valor repetido", mensagem));
	}
	
	public CampoUnicoException(Long pecaId) {
		this(String.format("Os campos %d não podem ter valores repetidos", pecaId));
	}
	

}
