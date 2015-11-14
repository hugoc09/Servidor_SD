package Exceptions;

public class ErroInternoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ErroInternoException(Throwable causa) {
		super("Erro Interno: " + causa);
	}
}
