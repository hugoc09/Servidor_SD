package Persistence;

import java.util.List;

import Entidades.Palavra;
import Exceptions.ConexaoInexistenteException;
import Exceptions.ErroInternoException;

public interface RepositorioPalavras {

	public void adicionar(Palavra traducao) throws ErroInternoException, ConexaoInexistenteException;

	public void remover(Palavra traducao) throws ErroInternoException, ConexaoInexistenteException;

	public void atualizar(Palavra palavra) throws ErroInternoException, ConexaoInexistenteException;

	public Palavra buscar(Palavra palavra1) throws ErroInternoException, ConexaoInexistenteException;
	
	public List<Palavra> listaPalavras() throws ErroInternoException, ConexaoInexistenteException;
	
}
