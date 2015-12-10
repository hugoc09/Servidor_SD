package Redes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import Negocios.Pesquisa;

public class RespostaUDP implements Runnable{
	
	private DatagramSocket servidorSocket;
	private Servidor servidor;
	private Control controle;
	
	private DatagramPacket pkgEnviado;
	private DatagramPacket pkgRecebido;

	private boolean inicializado;

	private Thread thread;
	
	public RespostaUDP(DatagramSocket datagramSocket, DatagramPacket pkgRecebidoParamentro, Servidor servidorParametro) {
		this.servidorSocket = datagramSocket;
		this.pkgRecebido = pkgRecebidoParamentro;
		this.servidor = servidorParametro;
		this.controle = new Pesquisa();
		
		inicializado = false;
		
		open();
	}
	
	private void open(){
		inicializado = true;	
	}

	private void close() {
		
		this.servidorSocket = null;
		this.servidor = null;
		this.controle = null;
		
		inicializado = false;
		
		
		thread = null;
	}
	
	public void start() {
		if(!inicializado ){ 
			return;
		}
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() throws Exception {
		
		if(thread!=null){
		thread.join();
		}
	}
	
	@Override
	public void run() {
		
		try {
			String msgEnviada = controle.checarServidor(servidor);
			
			if(msgEnviada!=null && !servidorSocket.isClosed()){
			pkgEnviado = new DatagramPacket(msgEnviada.getBytes(),msgEnviada.length(), pkgRecebido.getAddress(), pkgRecebido.getPort());
			servidorSocket.send(pkgEnviado);		
			
			}else{
			System.out.println("< Servidor Lotado >");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		
	}

}
