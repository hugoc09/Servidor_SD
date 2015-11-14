package Entidades;

public class Palavra {
	
	private long codigo;
	private String palavra1;
	private String palavra2;
	private long contador;
	private String linguagem1;
	private String linguagem2;
	
	

	public Palavra(long codigo, String palavra1, String palavra2,
			long contador, String linguagem1, String linguagem2) {
		this.codigo = codigo;
		this.palavra1 = palavra1;
		this.palavra2 = palavra2;
		this.contador = contador;
		this.linguagem1 = linguagem1;
		this.linguagem2 = linguagem2;
	}

	public Palavra() {
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getPalavra1() {
		return palavra1;
	}

	public void setPalavra1(String palavra1) {
		this.palavra1 = palavra1;
	}

	public String getPalavra2() {
		return palavra2;
	}

	public void setPalavra2(String palavra2) {
		this.palavra2 = palavra2;
	}

	public long getContador() {
		return contador;
	}

	public void setContador(long contador) {
		this.contador = contador;
	}

	public String getLinguagem1() {
		return linguagem1;
	}

	public void setLinguagem1(String linguagem1) {
		this.linguagem1 = linguagem1;
	}

	public String getLinguagem2() {
		return linguagem2;
	}

	public void setLinguagem2(String linguagem2) {
		this.linguagem2 = linguagem2;
	}

	@Override
	public String toString() {
		return "Palavra [codigo=" + codigo + ", palavra1=" + palavra1
				+ ", palavra2=" + palavra2 + ", contador=" + contador
				+ ", linguagem1=" + linguagem1 + ", linguagem2=" + linguagem2
				+ "]";
	}
	
}
