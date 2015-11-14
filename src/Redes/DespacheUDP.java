package Redes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DespacheUDP implements Runnable{
	
	private DatagramSocket servidorSocket;
	 
	private DatagramPacket pkgEnviado;
	private DatagramPacket pkgRecebido;

	private boolean inicializado;
	private boolean executando;

	private Thread  thread;
	
	public DespacheUDP(DatagramSocket datagramSocket, DatagramPacket pkgRecebidoParamentro) {
		this.servidorSocket = datagramSocket;
		this.pkgRecebido = pkgRecebidoParamentro;
		
		inicializado = false;
		executando =false;
		
		open();
	}
	
	private void open(){
			
		inicializado = true;	
	}

	//NÃO ESTÁ SENDO USADO!
	private void close() {
		
		try {
			servidorSocket.close();
		} catch (Exception e) {
			System.out.println(e);			
		}
		
		servidorSocket = null;
		
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
	
	public void stop() throws Exception {
		
		executando = false;
		
		if(thread!=null){
		thread.join();
		}
	}
	
	@Override
	public void run() {
		
		try {
			
			String msgEnviada = "Aqui.";
			pkgEnviado = new DatagramPacket(msgEnviada.getBytes(),msgEnviada.length(), pkgRecebido.getAddress(), pkgRecebido.getPort());
			servidorSocket.send(pkgEnviado);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		//close();
	}

}
