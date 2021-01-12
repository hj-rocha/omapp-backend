package br.com.tecsiscom.omapp.exception;

public class ProdutoCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProdutoCadastradoException( String nome ){
        super("Produto : " + nome + " já cadastrado." );
    }
}
