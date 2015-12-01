package Redes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import Negocios.Pesquisa;

public class Atendente implements Runnable{
	
	private Socket socket;
	
	private BufferedReader in;
	private PrintStream out;
	private Control controle;

	
	private boolean inicializado;
	private boolean executando;
	
	private Thread thread;
	
	public Atendente(Socket socket) throws Exception {
		this.socket = socket;
		this.controle = new Pesquisa();
		
		inicializado = false;
		executando =false;
		
		open();
	}
	
	private void open() throws Exception{
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
			inicializado = true;
			
		} catch (Exception e) {
			
			close();
			throw e;		
			}
	
	}
	
	private void close() {
		
		if(in == null){
			try {
				in.close();
			} catch (Exception e) {
				
				System.out.println(e);			
				}
		}
		
		if(out == null){
			try {
				out.close();
			} catch (Exception e) {
				
				System.out.println(e);			
				}
		}
		
		try {
			socket.close();
		} catch (Exception e) {
			
			System.out.println(e);			
		}
		
		in = null;
		out = null;
		socket = null;
		controle = null;
		
		inicializado = false;
		executando = false;
		
		thread = null;
		
		try {
		for(int i = 0; i < Servidor.atendentes.size();i++){
			
			if(!Servidor.atendentes.get(i).executando){
			Servidor.atendentes.remove(Servidor.atendentes.get(i));
			}
			
		}	
		} catch (Exception e) {
		e.printStackTrace();
	}
		
		//try {
			//for(Atendente a: Servidor.atendentes){
				
				//if(!a.executando){
				//Servidor.atendentes.remove(a);
				//}
				
			//}	
		//} catch (Exception e) {
			//e.printStackTrace();
		//}
		
	}
	
	public void start() {
		if(!inicializado || executando){
			return;
		}
		
		executando = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() throws Exception {
		
		executando = false;
		
		if(thread!=null){
		thread.join();
		}
	}
	
	@Override
	public void run() {
		
		while(executando){
			try {
				socket.setSoTimeout(2500);
				
				String palavra = in.readLine();
				
				System.out.println(
						"Palavra recebida do cliente [" +
				socket.getInetAddress().getHostName() +
				":" + socket.getPort() +
				"]:" +
				palavra);
				
				if ("FIM".equalsIgnoreCase(palavra)){ //MODIFICADO
					break;	
				}
				
				System.out.println("Vishh");
				out.println(controle.pesquisar(palavra));
					
			} catch (SocketTimeoutException e) {
				//Ignorar
			}catch (Exception e) {
				System.out.println("Cliente se desligou inesperadamente");
			break;	
			}	
		}
		System.out.println("Encerrando conexão!");
		close();
		
	}

}
