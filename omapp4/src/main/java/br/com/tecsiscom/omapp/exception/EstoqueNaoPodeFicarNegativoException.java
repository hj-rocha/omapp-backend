package br.com.tecsiscom.omapp.exception;

public class EstoqueNaoPodeFicarNegativoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EstoqueNaoPodeFicarNegativoException(String mensagem) {
		super(mensagem);
	}
	
	public EstoqueNaoPodeFicarNegativoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
