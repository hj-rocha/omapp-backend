package br.com.tecsiscom.omapp.exception;

public class EstoqueVeiculoNaoUnitarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EstoqueVeiculoNaoUnitarioException(String mensagem) {
		super(mensagem);
	}
	
	public EstoqueVeiculoNaoUnitarioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
