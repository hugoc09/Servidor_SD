package Redes;

import Entidades.Palavra;
import Exceptions.ConexaoInexistenteException;
import Exceptions.ErroInternoException;

public interface Control {
	
	public String pesquisar(String palavra1);
	public void verifica(Palavra palavra) throws ErroInternoException, ConexaoInexistenteException;
	public String checarServidor(Servidor servidor);
}
