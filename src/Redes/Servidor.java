package Redes;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;


public class Servidor implements Runnable {
	
	private ServerSocket serve;

	private  List<Atendente> atendentes;
	
	private boolean inicializado;
	private boolean executando;
	
	private Thread thread;
	
	public Servidor(int porta) throws Exception{
		atendentes = new ArrayList<Atendente>();
		
		inicializado = false;
		executando = false;
		
		open(porta);
	}
	
	private void open(int porta)throws Exception{
		serve = new ServerSocket(porta);
		inicializado = true;
	}
	
	public void close() {
		
		for(Atendente atendente: atendentes){
			try {
				atendente.stop();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		try {
			serve.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		serve = null;
		
		inicializado = false;
		executando = false;
		
		thread = null;
	}
	
	public void start() {
		if(!inicializado || executando){
			return;
		}
		
		executando = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() throws Exception{
		executando = false;
		
		
		if(thread != null){
		thread.join();
		}
	}
	
	public List<Atendente> getAtendentes() {
		return atendentes;
	}

	public void setAtendentes(List<Atendente> atendentes) {
		this.atendentes = atendentes;
	}
	
	public int atualizaList(){
		
		for(Atendente a: atendentes){
			
			if(!a.getStatus()){
				atendentes.remove(a);
			}
			
		}
		
		return atendentes.size();
	}

	public void run() {
		System.out.println("Aguardadando conexao...");
		
		while(executando){
			
			try {
				
			serve.setSoTimeout(2500);	
			
			Socket socket = serve.accept();
			
			System.out.println("Conexao estabelecida.");
		
			
			Atendente atendente = new Atendente(socket);
			atendente.start();
			
			atendentes.add(atendente);
			
			//teste
			System.out.println(atualizaList());
			//teste
			
			}catch(SocketTimeoutException e){
				// ignorar
			}catch (Exception e) {
				System.out.println(e);
				return;
			}
			
			
			
		}
		
		close();
	}

}
