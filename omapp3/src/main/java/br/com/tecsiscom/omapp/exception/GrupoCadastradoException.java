package br.com.tecsiscom.omapp.exception;

public class GrupoCadastradoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GrupoCadastradoException( String nome ){
        super("Grupo já cadastrado" + nome);
    }
}
