package Entidades;

import java.util.ArrayList;
import java.util.List;

public class Palavras11 {
	
	private List<String> portugues;
	private List<String> ingles;
	
	public Palavras11() {
		portugues = new ArrayList<String>();
		ingles = new ArrayList<String>();
		
		portugues.add("CASA");
		portugues.add("PEGAR");
		portugues.add("LAPIS");
		portugues.add("COMPUTADOR");
		portugues.add("TECLADO");
		portugues.add("CADEIRA");
		portugues.add("MESA");
		portugues.add("LIVRO");
		portugues.add("CAMISA");
		
		ingles.add("HOUSE");
		ingles.add("TAKE");
		ingles.add("PENCIL");
		ingles.add("COMPUTER");
		ingles.add("KEYBOARD");
		ingles.add("CHAIR");
		ingles.add("TABLE");
		ingles.add("BOOK");
		ingles.add("SHIRT");
	}

	public List<String> getPortugues() {
		return portugues;
	}

	public void setPortugues(List<String> portugues) {
		this.portugues = portugues;
	}

	public List<String> getIngles() {
		return ingles;
	}

	public void setIngles(List<String> ingles) {
		this.ingles = ingles;
	}
	
}
