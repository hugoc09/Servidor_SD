package Redes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class OuvindoUDP implements Runnable{
	
	private DatagramSocket servidorSocket;
	
	private List<DespacheUDP> despachantesUDP;//Não utilizamos
	 
	private DatagramPacket pkgRecebido;

	private boolean inicializado;
	private boolean executando;

	private Thread  thread;
	
	public OuvindoUDP() throws Exception {
		despachantesUDP = new ArrayList<DespacheUDP>();//Não utilizamos
		
		inicializado = false;
		executando   = false;

		open();
	}
	
	private void open()throws Exception{
		servidorSocket = new DatagramSocket(2526, InetAddress.getByName("0.0.0.0"));
		servidorSocket.setBroadcast(true);
		
		inicializado = true;
		
	}
	
	private void close() {
		
		for(DespacheUDP despacheUDP: despachantesUDP){
			try {
				despacheUDP.stop();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		try {
			servidorSocket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		servidorSocket = null;
		
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
	
	@Override
	public void run() {
		
	System.out.println("Aguardadando conexao...");
	byte[] dadosRecebidos = new byte[1024];
	
		while(executando){
			
			try {
			
			pkgRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
				
			servidorSocket.setSoTimeout(2000);	
			
			servidorSocket.receive(pkgRecebido);
			
			System.out.println("Pacote recebido.");
			
			DespacheUDP despacheUDP = new DespacheUDP(servidorSocket, pkgRecebido);
			despacheUDP.start();
			
			despachantesUDP.add(despacheUDP);
			
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
