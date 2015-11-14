package Negocios;

import java.util.ArrayList;
import java.util.regex.Pattern;

import Entidades.Palavra;
import Exceptions.ConexaoInexistenteException;
import Exceptions.ErroInternoException;
import Persistence.RepositorioPalavras;
import Persistence.RepositorioPalavrasJDBC;
import Redes.NetworkManagement;
import Redes.OuvindoUDP;
import Redes.Servidor;

public class Traducao {
	
	private RepositorioPalavras repPalavras;
	private NetworkManagement netWork;
	
	public Traducao() {
		repPalavras = new RepositorioPalavrasJDBC();
		netWork = new NetworkManagement();
	}
	

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
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						verifica(p);
					} catch (ErroInternoException | ConexaoInexistenteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else{
				repPalavras.atualizar(p);
				}
			} catch (ErroInternoException | ConexaoInexistenteException e) {
				
				e.printStackTrace();
			}
				
		return p.getPalavra2();
	}


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

}
