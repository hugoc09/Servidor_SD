package Exceptions;

public class ConexaoInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConexaoInexistenteException(Throwable causa) {
		super("Conexão Inexistente: " + causa);
	}
}
