package Redes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class OuvindoUDP implements Runnable{
	
	private DatagramSocket servidorSocket;
	private Servidor servidor;
	 
	private DatagramPacket pkgRecebido;

	private boolean inicializado;
	private boolean executando;

	private Thread  thread;
	
	public OuvindoUDP(Servidor servidorParametro) throws Exception {
		this.servidor = servidorParametro;
		
		inicializado = false;
		executando   = false;

		open();
	}
	
	private void open()throws Exception{
		servidorSocket = new DatagramSocket(2525, InetAddress.getByName("0.0.0.0"));
		servidorSocket.setBroadcast(true);
		
		inicializado = true;	
	}
	
	private void close() {
		
		try {
			servidorSocket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		servidorSocket = null;
		servidor = null;
		
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
			
			System.out.print("Ping DNS recebido!");
			
			RespostaUDP respostaUDP = new RespostaUDP(servidorSocket, pkgRecebido, servidor);
			respostaUDP.start();
			
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
