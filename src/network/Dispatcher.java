package network;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

	private List<TranslateListener> listener;
	private static Dispatcher facade;

	private Dispatcher() {
		this.listener = new ArrayList<>();
	}

	public static Dispatcher getInstance() {
		if (facade == null) {
			facade = new Dispatcher();
		}
		return facade;
	}

	public void registrarEvento(TranslateListener translateListener) {
		this.listener.add(translateListener);
	}

	public void desregistrarEvento(TranslateListener translateListener) {
		this.listener.remove(translateListener);
	}

	public void dispatchEvent(List<String> palavraTraduzida) {

		for (TranslateListener l : this.listener) {
			l.traducao(palavraTraduzida);
		}
	}

}
