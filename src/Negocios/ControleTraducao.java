package Negocios;

import Entidades.Palavra;
import Exceptions.ConexaoInexistenteException;
import Exceptions.ErroInternoException;

public interface ControleTraducao{

	public String pesquisar(String palavra1);
	public void verifica(Palavra palavra)throws ErroInternoException, ConexaoInexistenteException;
	
}
