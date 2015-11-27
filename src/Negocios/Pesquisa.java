package Negocios;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.json.JSONException;

import Entidades.Palavra;
import Exceptions.ConexaoInexistenteException;
import Exceptions.ErroInternoException;
import Internet.NetworkManagement;
import Persistence.RepositorioPalavras;
import Persistence.RepositorioPalavrasJDBC;
import Redes.Control;
import Redes.Servidor;

public class Pesquisa implements Control{
	
	private RepositorioPalavras repPalavras;
	private NetworkManagement netWork;
	
	public Pesquisa() {
		repPalavras = new RepositorioPalavrasJDBC();
		netWork = new NetworkManagement();
	}
	
	@Override
	public String pesquisar(String palavra1) {
		Palavra p = null;
		String argumentos[] = palavra1.split(Pattern.quote(";"));
		
		Palavra palavra = new Palavra();
		palavra.setPalavra1(argumentos[0]);
		palavra.setLinguagem1(argumentos[1]);
		palavra.setLinguagem2(argumentos[2]);
		
		
			try {
				p = repPalavras.buscar(palavra);
				if(p.getPalavra1()==null){

					try {
						
						try {
							p = netWork.traduzir(palavra);
						} catch (Exception e1) {
							
							System.out.println("Servidor sem conex�o com a internet");
							return "Servidor sem Conex�o";
						}
						verifica(p);
					} catch (ErroInternoException | ConexaoInexistenteException e1) {
						System.out.println("Erro na Persistencia do Servidor");
					}
					
				}else{
				repPalavras.atualizar(p);
				}
			} catch (ErroInternoException | ConexaoInexistenteException e) {
				
				e.printStackTrace();
			}
				
		return p.getPalavra2();
	}

	@Override
	public void verifica(Palavra palavra) throws ErroInternoException, ConexaoInexistenteException {
		
		ArrayList<Palavra> palavras = new ArrayList<Palavra>();
		palavras = (ArrayList<Palavra>) repPalavras.listaPalavras();
		
		if(palavras.size()<3){
			repPalavras.adicionar(palavra);
		}else{
			
			Palavra p = palavras.get(0);
			
			for(int i=0;i<palavras.size()-1;i++){
				if(p.getContador() >= palavras.get(i+1).getContador()){
					p = palavras.get(i+1);	
				}
				
			}
			
			repPalavras.remover(p);
			repPalavras.adicionar(palavra);
			
		}
	}


	@Override
	public String checarServidor(Servidor servidor) {
		
		if(Servidor.atendentes.size() < 5){
			return "Ola";
		}
		
		
		return null;
	}

}