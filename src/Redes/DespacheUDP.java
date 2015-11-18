package Redes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import Negocios.Traducao;

public class DespacheUDP implements Runnable{
	
	private DatagramSocket servidorSocket;
	private Servidor servidor;
	private Control controle;
	
	private DatagramPacket pkgEnviado;
	private DatagramPacket pkgRecebido;

	private boolean inicializado;
	private boolean executando;

	private Thread  thread;
	
	public DespacheUDP(DatagramSocket datagramSocket, DatagramPacket pkgRecebidoParamentro, Servidor servidorParametro) {
		this.servidorSocket = datagramSocket;
		this.pkgRecebido = pkgRecebidoParamentro;
		this.servidor = servidorParametro;
		this.controle = new Traducao();
		
		inicializado = false;
		executando =false;
		
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
		executando =false;
		
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
			
			String msgEnviada = controle.ChecarServidor(servidor);
			
			if(msgEnviada == null){
			System.out.println("Servidor lotado!");
			}else{
			pkgEnviado = new DatagramPacket(msgEnviada.getBytes(),msgEnviada.length(), pkgRecebido.getAddress(), pkgRecebido.getPort());
			servidorSocket.send(pkgEnviado);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		close();
	}

}
